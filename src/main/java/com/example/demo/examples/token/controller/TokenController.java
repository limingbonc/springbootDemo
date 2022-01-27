package com.example.demo.examples.token.controller;

import com.example.demo.examples.token.domain.UserData;
import com.example.demo.utils.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: liming522
 * @description:
 * @date: 2022/1/21 4:20 下午
 * @hope: The newly created file will not have a bug
 */
@RestController
@Slf4j
public class TokenController {


    // 模拟登录接口
    @GetMapping("/testLogin")
    public  String testLogin(String userName,String passWord){
        String token = null;
        // 默认账号密码校验通过
        if(true){
            token = JwtTokenUtils.getJwtToken("1", "liming522");
        }
        UserData.addUserInfo("1",userName);
        return token;
    }

    @PostMapping("/testOtherPath")
    public  String testOtherPath(){


        return "跳转到了其他请求";
    }
}
