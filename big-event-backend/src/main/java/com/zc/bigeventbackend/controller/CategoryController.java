package com.zc.bigeventbackend.controller;

import com.zc.bigeventbackend.pojo.Category;
import com.zc.bigeventbackend.result.Result;
import com.zc.bigeventbackend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 新增文章分类
     * @param category
     * @return
     */
    @PostMapping
    public Result add(@RequestBody @Validated(Category.Add.class) Category category){

        categoryService.add(category);
        return Result.success("添加成功");
    }

    /**
     * 文章分类列表
     * @return
     */
    @GetMapping
    public Result<List<Category>> list(){
        List<Category> categories = categoryService.list();
        return Result.success(categories);
    }


    @GetMapping("/{id}")
    public Result<Category> detail(@PathVariable Integer id){
        Category category = categoryService.findById(id);
        return Result.success(category);
    }

    @PutMapping
    public Result update(@RequestBody @Validated(Category.Update.class) Category category){
        categoryService.update(category);
        return Result.success("修改成功");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        Category category = categoryService.findById(id);
        if (category == null)
            return Result.error("未找到该分类");
        categoryService.delete(id);
        return Result.success("删除成功");
    }
}
