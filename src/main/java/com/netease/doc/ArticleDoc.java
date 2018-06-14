package com.netease.doc;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

/**
 * 文章
 * @author yc
 */
@Document(indexName="test",type="article",indexStoreType="fs",shards=5,replicas=1,refreshInterval="-1")
public class ArticleDoc  {

    @Id
    private              String   id;
    /**
     * 标题
     */
    private              String title;
    /**
     * 摘要
     */
    private              String abstracts;
    /**
     * 内容
     */
    private              String content;
    /**
     * 发表时间
     */
    private              Date   postTime;
    /**
     * 点击率
     */
    private              Long   clickCount;
    /**
     * 作者姓名
     */
    private              String author;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public Long getClickCount() {
        return clickCount;
    }

    public void setClickCount(Long clickCount) {
        this.clickCount = clickCount;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
