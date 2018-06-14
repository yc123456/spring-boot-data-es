package com.netease.repository;

import com.netease.doc.ArticleDoc;

import java.util.List;

public interface MyCustomRepository {

    List<ArticleDoc> getByTitle(String title);

    void indexAllArticle();

    //void indexAllAuthor();

    void refresh();

    Boolean deleteIndex(String indexName);
}
