package com.zc.bigeventbackend.controller;

import com.zc.bigeventbackend.pojo.Article;
import com.zc.bigeventbackend.result.Result;
import com.zc.bigeventbackend.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/articles")
@CrossOrigin
@Slf4j
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public Result add(@RequestBody @Validated Article article) {
        articleService.add(article);
        return Result.success("添加成功");
    }


    @GetMapping
    public Result<List<Article>> getAll(){
        List<Article> articleList = articleService.getAll();

        return Result.success(articleList);
    }

    @GetMapping("/search")
    public Result<List<Map>> search( String category, String state){
        log.info("search==========================================");
        List<Map> articleList = articleService.getByStateAndCategoryId(category,state);
        articleList.forEach(System.out::println);
        return Result.success(articleList);
    }

}
