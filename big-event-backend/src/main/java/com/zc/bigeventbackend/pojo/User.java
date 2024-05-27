package com.zc.bigeventbackend.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    //非空
    @NotNull
    private Integer id;

    //非空，且内容不能为""
    @NotEmpty
    private String username;

    //让springmvc把当前对象转化为json字符串的时候忽略password，最终的字符串中就没有password属性了
    @JsonIgnore
    private String password;

    @NotEmpty
    //正则表达式
    @Pattern(regexp = "\\S{1,10}$")
    private String nickname;

    @NotEmpty
    //要符合邮箱格式
    @Email
    private String email;
    private String userPic;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
