package com.netease.controller;

import com.alibaba.fastjson.JSON;
import com.netease.doc.ArticleDoc;
import com.netease.repository.ArticleSearchRepository;
import com.netease.repository.MyCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private ArticleSearchRepository articleSearchRepository;

    @Autowired
    private MyCustomRepository myCustomRepository;

    @RequestMapping("/refresh")
    public void refresh(){
        myCustomRepository.refresh();
    }

    @RequestMapping("/index")
    public void index(){
        myCustomRepository.indexAllArticle();
    }

    @RequestMapping("/deleteAll")
    public void deleteAll(){
        articleSearchRepository.deleteAll();
    }

    @RequestMapping("/deleteIndex")
    public Boolean deleteIndex(@RequestParam(value = "indexName") String indexName){
        return  myCustomRepository.deleteIndex(indexName);
    }


    @RequestMapping("/add")
    public void testSaveArticleIndex() {

    }

    @RequestMapping("/query")
    public List<ArticleDoc> testSearch() {
        return articleSearchRepository.findAll(PageRequest.of(0,20)).getContent();
        //String queryString = "CY";//搜索关键字
//        Iterable<Article> articleIterator = articleSearchRepository.findAll();
//        List<Article> list=new ArrayList<>();
//        articleIterator.forEach(item->{
//            if(item != null){
//                list.add(item);
//            }
//        });
//        return JSON.toJSONString(list);
    }

    /**
     * DomainClassConverter
     * HandlerMethodArgumentResolver
     * @param article
     * @param model
     * @return
     */
    @RequestMapping("/{id}")
    public String showUserForm(@PathVariable("id") ArticleDoc article, Model model) {
     return JSON.toJSONString(article);

    }

    @RequestMapping("/findAll")
    public List<ArticleDoc>  findAll(){
        return articleSearchRepository.findAll(PageRequest.of(0,20)).getContent();
    }

    @RequestMapping("/searchByTitle")
    public String searchByTitle(@RequestParam(value = "title") String title){
        return JSON.toJSONString(myCustomRepository.getByTitle(title));
    }
}
