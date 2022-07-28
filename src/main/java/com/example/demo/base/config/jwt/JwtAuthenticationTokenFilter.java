package com.example.demo.base.config.jwt;

import com.example.demo.utils.JwtTokenUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author: liming522
 * @description:
 * @date: 2022/7/27 6:00 PM
 * @hope: The newly created file will not have a bug
 */
@Component
@Slf4j
public class JwtAuthenticationTokenFilter  extends OncePerRequestFilter {

    private JwtTokenUtils jwtTokenUtils;

    public JwtAuthenticationTokenFilter(JwtTokenUtils jwtTokenUtils) {
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String requestRri = httpServletRequest.getRequestURI();
        //获取request token
        String token = httpServletRequest.getHeader(jwtTokenUtils.header);
        if (StringUtils.hasText(token) && token.startsWith(jwtTokenUtils.subJectStr)) {
            token = token.substring(jwtTokenUtils.subJectStr.length());
        }

        if (StringUtils.hasText(token) && jwtTokenUtils.validateToken(token)) {
            Jws<Claims> decodedJWT = JwtTokenUtils.decode(token);
            // 2.取出JWT字符串载荷中的随机token，从Redis中获取用户信息
            Map<String,Object> body_AUTHORITIES_KEY =  (Map<String,Object>)decodedJWT.getBody().get(jwtTokenUtils.AUTHORITIES_KEY);
            String userName = (String)body_AUTHORITIES_KEY.get("userName");
            String userId = (String)body_AUTHORITIES_KEY.get("userId");
            String password = (String)body_AUTHORITIES_KEY.get("passWord");
            log.info("获取到的用户信息为 userName:{},userId:{},passWord:{}",userName,userId,password);
            Authentication authentication = jwtTokenUtils.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.debug("set Authentication to security context for '{}', uri: {}", authentication.getName(), requestRri);
        } else {
            log.debug("no valid JWT token found, uri: {}", requestRri);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}

