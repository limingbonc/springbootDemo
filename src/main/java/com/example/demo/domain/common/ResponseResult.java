package com.example.demo.domain.common;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: liming522
 * @description:
 * @date: 2022/7/27 5:45 PM
 * @hope: The newly created file will not have a bug
 */
@Data
@NoArgsConstructor
public class ResponseResult<T> {

    private Integer code;

    private T data;

    private String message;

    public ResponseResult(Integer code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }
}
