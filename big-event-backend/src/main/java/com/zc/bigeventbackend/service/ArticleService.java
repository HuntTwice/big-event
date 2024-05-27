package com.zc.bigeventbackend.service;

import com.zc.bigeventbackend.pojo.Article;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    //新增文章
    void add(Article article);

    List<Article> getAll();
    List<Map> getByStateAndCategoryId(String category,String state);


}
