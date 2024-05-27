package com.zc.bigeventbackend.service.impl;

import com.zc.bigeventbackend.mapper.ArticleMapper;
import com.zc.bigeventbackend.pojo.Article;
import com.zc.bigeventbackend.service.ArticleService;
import com.zc.bigeventbackend.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void add(Article article) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        article.setCreateUser(id);
        articleMapper.add(article);
    }

    @Override
    public List<Article> getAll() {
        return articleMapper.getAll();
    }

    @Override
    public List<Map> getByStateAndCategoryId(String category,String state) {
        return articleMapper.getByStateAndCategoryId(category,state);
    }
}
