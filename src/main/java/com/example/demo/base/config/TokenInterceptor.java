package com.example.demo.base.config;

import com.example.demo.utils.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: liming522
 * @description: token鉴权拦截器
 * @date: 2022/1/21 3:40 下午
 * @hope: The newly created file will not have a bug
 */
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String JWT = request.getHeader("Authorization");
        try {
            if(StringUtils.isBlank(JWT)){
                log.error("未登录");
                return false;
            }

            // 1.校验JWT字符串中token
            return JwtTokenUtils.validateToken(JWT);
        }catch (Exception e){
            System.out.println("token校验异常");
            e.printStackTrace();
        }
        return false;
    }
}
