package com.example.demo.examples.excel.controller;

import com.example.demo.examples.excel.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: liming522
 * @description:
 * @date: 2021/11/15 5:10 下午
 * @hope: The newly created file will not have a bug
 */
@Controller
@RequestMapping("/excelTest")
public class ExcelController {

    @Autowired
    private FileService fileService;


    /**
     * 安装 openffice
     * 启动  soffice -headless -accept="socket,host=localhost,port=8100;urp;" -nofirststartwizard
     */

    /**
     * 请求地址案例：http://localhost:8080/excelTest//api/file/onlinePreview?url=/Users/liming522/Desktop/Update_BasicSKUInfo_Template.xls
     * 注：xlsx 和 docx 格式目前好像不支持
     * 只支持 xls 和doc
     */
   // @ApiOperation(value = "系统文件在线预览接口")
    @GetMapping("/api/file/onlinePreview")
    public void onlinePreview(@RequestParam("url") String url, HttpServletResponse response) throws Exception{
        fileService.onlinePreview(url,response);
    }
}
