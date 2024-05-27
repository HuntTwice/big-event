package com.zc.bigeventbackend.anno;

import com.zc.bigeventbackend.validation.StateValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

//元注解，表示@State可以抽取到帮助文档里面
@Documented
//元注解，表示@State可以用在那些类上
@Target(ElementType.FIELD)
//元注解，表示@State注解会在哪个阶段会被保留(编译、运行？)
@Retention(RetentionPolicy.RUNTIME)
//指定提供校验规则的类
@Constraint(
        validatedBy = {StateValidation.class}
)
public @interface State {
    //提供校验失败后的提示信息
    String message() default "state参数的值只能是已发布或者草稿";

    //指定分组
    Class<?>[] groups() default {};

    //负载 获取State注解的附加信息
    Class<? extends Payload>[] payload() default {};


}
