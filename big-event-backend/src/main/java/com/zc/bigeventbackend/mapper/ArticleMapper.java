package com.zc.bigeventbackend.mapper;

import com.zc.bigeventbackend.pojo.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ArticleMapper {
    void add(Article article);

    @Select("select * from article")
    List<Article> getAll();


    List<Map> getByStateAndCategoryId(@Param("category")String category, @Param("state")String state);
}
