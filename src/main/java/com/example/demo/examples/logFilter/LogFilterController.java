package com.example.demo.examples.logFilter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: liming522
 * @description:
 * @date: 2022/7/27 4:39 PM
 * @hope: The newly created file will not have a bug
 */
@RestController
@RequestMapping( "/api/logFilter" )
@Slf4j
public class LogFilterController {

    @GetMapping(value = "/testFilter")
    public String log() {
        log.info("your email:{},you phone:{}","1322569@qq.com","13225694561");

        return "logFilter success";
    }
}
