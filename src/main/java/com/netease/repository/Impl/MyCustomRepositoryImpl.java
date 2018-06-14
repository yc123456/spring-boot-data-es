package com.netease.repository.Impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.netease.doc.ArticleDoc;
import com.netease.domain.Article;
import com.netease.repository.MyCustomRepository;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.DefaultResultMapper;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ResultsMapper;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyCustomRepositoryImpl implements MyCustomRepository {


    ResultsMapper resultsMapper = new DefaultResultMapper();

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ArticleDoc> getByTitle(String title) {
        NativeSearchQueryBuilder builder=new NativeSearchQueryBuilder();
        builder.withQuery(QueryBuilders.termQuery("title",title));
        return elasticsearchOperations.queryForList(builder.build(),ArticleDoc.class);
    }

    @Override
    public void refresh() {
        elasticsearchOperations.refresh("test");
        elasticsearchOperations.refresh("test2");
    }

    @Override
    public Boolean deleteIndex(String indexName) {
        return elasticsearchOperations.deleteIndex(indexName);
    }

    @Override
    public void indexAllArticle() {
        List<ArticleDoc> articleDocs=Lists.newArrayList();
        RowMapper<Article> rowMapper=new BeanPropertyRowMapper<>(Article.class);
        List<Article> list = jdbcTemplate.query("select * from article",rowMapper);
        for(Article article:list){
            String s = JSON.toJSONString(article);
            ArticleDoc articleDoc = JSON.parseObject(s,ArticleDoc.class);
            articleDocs.add(articleDoc);
        }

        List<UpdateQuery> updateQueries = new ArrayList<>();
        for (ArticleDoc articleDoc : articleDocs) {
            UpdateQuery updateQuery = buildUpdateQuery(articleDoc);
            if (updateQuery != null) {
                updateQueries.add(updateQuery);
            }
        }
        elasticsearchOperations.bulkUpdate(updateQueries);

    }

    private UpdateQuery buildUpdateQuery(ArticleDoc articleDoc) {

        IndexRequest indexRequest = new IndexRequest();
        try {
            String source = resultsMapper.getEntityMapper().mapToString(articleDoc);
            indexRequest.source(source, XContentType.JSON);
        } catch (Exception e) {
            return null;
        }

        UpdateRequest uRequest = new UpdateRequest();
        uRequest.doc(indexRequest);

        UpdateQuery updateQuery = new UpdateQuery();
        updateQuery.setId(String.valueOf(articleDoc.getId()));
        updateQuery.setDoUpsert(true);
        updateQuery.setClazz(ArticleDoc.class);
        updateQuery.setUpdateRequest(uRequest);

        return updateQuery;
    }

}
