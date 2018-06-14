package com.netease.domain;

import java.io.Serializable;
import java.util.Date;

public class Article implements Serializable {

    private static final long serialVersionUID = -1081268596104117286L;

    private Long   id;
    /**
     * 标题
     */
    private  String title;
    /**
     * 摘要
     */
    private  String abstracts;
    /**
     * 内容
     */
    private  String content;
    /**
     * 发表时间
     */
    private  Long   postTime;
    /**
     * 点击率
     */
    private  Long   clickCount;
    /**
     * 作者姓名
     */
    private  String author;

    private Long gmtCreate;

    private Date gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getPostTime() {
        return postTime;
    }

    public void setPostTime(Long postTime) {
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

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}
