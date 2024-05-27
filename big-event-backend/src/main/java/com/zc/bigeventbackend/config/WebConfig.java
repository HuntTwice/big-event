package com.zc.bigeventbackend.config;

import com.zc.bigeventbackend.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration

public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

//    注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //登录和注册接口不拦截，其他的接口拦截

        registry.addInterceptor(loginInterceptor).excludePathPatterns("/user/login","/user/register");
    }
}
