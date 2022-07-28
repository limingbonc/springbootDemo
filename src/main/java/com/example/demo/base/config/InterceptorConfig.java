package com.example.demo.base.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: liming522
 * @description:
 * @date: 2022/1/18 5:29 下午
 * @hope: The newly created file will not have a bug
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    // 登录拦截 要拦截的路径以及不拦截的路径
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自定义拦截器，添加拦截路径和排除拦截路径
      /*  registry.addInterceptor(new TokenInterceptor()).addPathPatterns("/**").
                excludePathPatterns("/testLogin","/js/**","/css/**","/images/**");*/
    }
}