package com.zc.bigeventbackend.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.time.LocalDateTime;

@Data
public class Category {
    @NotNull(groups = Update.class)
    private Integer id;
    @NotEmpty
    private String categoryName;
    //分类别名
    @NotEmpty
    private String categoryAlias;
    //来自user表的主键id，用来记录这个分类是哪个用户创建的
    private Integer createUser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    public interface Add extends Default {}
    public interface Update extends Default{}
}
