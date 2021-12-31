package com.example.demo.examples.P6y.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: liming522
 * @description: 查询条件
 * @date: 2021/12/23 1:56 下午
 * @hope: The newly created file will not have a bug
 */
@Data
public class SkuMaxShelfQuery implements Serializable {

    private Long venderId;

    /**
     * 当前页码
     */
    private Integer pageNum;

    /**
     * 每页多少条
     */
    private Integer pageSize;
}
