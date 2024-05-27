package com.zc.bigeventbackend.interceptor;

import com.zc.bigeventbackend.utils.JwtUtil;
import com.zc.bigeventbackend.utils.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        log.info("===============拦截器预处理=========");
        String token = request.getHeader("Authorization");
        //验证token
        try {
            //获取业务数据
            Map<String, Object> claims = JwtUtil.parseToken(token);
            System.out.println(claims);
            //把业务数据存储到ThreadLocal中
            ThreadLocalUtil.set(claims);
            //放行
            return true;
        } catch (Exception e) {
            //设置响应状态码为401
            response.setStatus(401);
            //不放行
            return false;
        }
    }



    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws  Exception{
        //防止内存泄漏
        ThreadLocalUtil.remove();
    }
}



