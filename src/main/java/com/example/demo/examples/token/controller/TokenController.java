package com.example.demo.examples.token.controller;

import com.example.demo.domain.common.ResponseResult;
import com.example.demo.examples.token.domain.UserData;
import com.example.demo.utils.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: liming522
 * @description:
 * @date: 2022/1/21 4:20 下午
 * @hope: The newly created file will not have a bug
 */
@RestController
@RequestMapping("/api/auth")
@Slf4j
public class TokenController {


    // 模拟登录接口
    @PostMapping ("/login")
    @ResponseBody
    public ResponseResult testLogin(String userName, String passWord) {
        String token = null;
        // 默认账号密码校验通过
        if(true){
            token = JwtTokenUtils.getJwtToken(userName, passWord);
        }
        UserData.addUserInfo("1",userName,passWord);
        return new ResponseResult(200,  token, "登录成功");
    }

    @PostMapping("/testOtherPath")
    public  String testOtherPath(){


        return "跳转到了其他请求";
    }
}
