package com.example.demo.examples.copyBean.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: liming522
 * @description:
 * @date: 2021/11/16 11:30 上午
 * @hope: The newly created file will not have a bug
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    private String name;
    private int age;
    private GenderEnum gender;
    private Double height;
    private Date birthday;
}
