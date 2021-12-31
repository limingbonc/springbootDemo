package com.example.demo.examples.copyBean.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class StudentVO {
    private String name;
    private int age;
    private String gender;
    private Double height;
    private String birthday;
}
