package com.example.demo.base.config;

import com.example.demo.base.exception.AlgorithmMismatchException;
import com.example.demo.base.exception.SignatureVerificationException;
import com.example.demo.base.exception.TokenExpiredException;
import com.example.demo.utils.JwtTokenUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
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

            // 1.校验JWT字符串
            Jws<Claims> decodedJWT = JwtTokenUtils.decode(JWT);
            // 2.取出JWT字符串载荷中的随机token，从Redis中获取用户信息

            String userName = (String)decodedJWT.getBody().get("userName");
            String userId = (String)decodedJWT.getBody().get("userId");
            log.info("获取到的用户信息为 userId:{},userName:{}",userId,userName);
            return true;
        }catch (Exception e){
            System.out.println("token无效");
            e.printStackTrace();
        }
        return false;
    }


    // 校验异常后续增加
    /*catch (SignatureVerificationException e){
            System.out.println("无效签名");
            e.printStackTrace();
        }catch (TokenExpiredException e){
            System.out.println("token已经过期");
            e.printStackTrace();
        }catch (AlgorithmMismatchException e){
            System.out.println("算法不一致");
            e.printStackTrace();
        }*/
}
