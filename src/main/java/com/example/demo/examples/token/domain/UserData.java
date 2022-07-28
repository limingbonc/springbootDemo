package com.example.demo.examples.token.domain;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: liming522
 * @description:
 * @date: 2022/1/21 4:48 下午
 * @hope: The newly created file will not have a bug
 */
public class UserData {
    public static Map<String,User> userData = new HashMap<String, User>();

    public static void addUserInfo(String userId,String userName,String password){
        User user = new User();
        user.setUserId(userId);
        user.setUserName(userName);
        user.setPassword(password);
        userData.put(userId,user);
    }

    public static  User getUserInfo(String userId){
        if(userData.containsKey(userId)){
            return  userData.get(userId);
        }
        return null;
    }
}
