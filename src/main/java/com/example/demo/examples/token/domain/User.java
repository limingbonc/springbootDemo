package com.example.demo.examples.token.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: liming522
 * @description:
 * @date: 2022/1/21 4:48 下午
 * @hope: The newly created file will not have a bug
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String userName;
    private String userId;
    private String password;
}
