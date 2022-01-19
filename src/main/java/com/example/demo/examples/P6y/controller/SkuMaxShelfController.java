package com.example.demo.examples.P6y.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dao.mapper.SkuMaxShelfMapper;
import com.example.demo.examples.P6y.domain.SkuMaxShelf;
import com.example.demo.examples.P6y.domain.SkuMaxShelfQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: liming522
 * @description:
 * @date: 2021/12/31 11:27 上午
 * @hope: The newly created file will not have a bug
 */
@Controller
@Slf4j
public class SkuMaxShelfController {

    @Autowired
    @Lazy
    private SkuMaxShelfMapper skuMaxShelfMapper;

    @RequestMapping("/queryForList")
    @ResponseBody
    public IPage<SkuMaxShelf> queryForList(@RequestBody SkuMaxShelfQuery query){
        IPage<SkuMaxShelf> skuMaxShelfIPage = null;
        try{
            Page page = new Page();
            page.setSize(query.getPageSize());
            page.setCurrent(query.getPageNum());
            QueryWrapper wrapper = new QueryWrapper();
            if(query.getVenderId()!=null){
                wrapper.eq("vender_id",query.getVenderId());
            }

            skuMaxShelfIPage = skuMaxShelfMapper.selectPage(page,wrapper);
        }catch(Exception e){
            log.error("异常");
        }
        return skuMaxShelfIPage;
    }
}
