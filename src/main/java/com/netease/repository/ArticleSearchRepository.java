package com.netease.repository;

import com.netease.doc.ArticleDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ArticleSearchRepository extends ElasticsearchRepository<ArticleDoc, Long> {
}
