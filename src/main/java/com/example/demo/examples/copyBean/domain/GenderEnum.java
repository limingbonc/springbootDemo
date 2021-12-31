package com.example.demo.examples.copyBean.domain;

/**
 * @author: liming522
 * @description:
 * @date: 2021/11/16 11:32 上午
 * @hope: The newly created file will not have a bug
 */
public enum GenderEnum {
    Male("1", "男"),
    Female("0", "女");

    private String code;
    private String name;

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    GenderEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
