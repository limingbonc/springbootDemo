package com.example.demo.examples.P6y.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author: liming522
 * @description: 商家最大在架数量限制对象
 * @date: 2021/12/23 1:56 下午
 * @hope: The newly created file will not have a bug
 */
@Data
@TableName("vender_sku_shelf")
public class SkuMaxShelf {

    // 自增主建
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    // 店铺id
    private Long venderId;

    // 店铺名称
    private String venderName;

    // 在架最大限制
    private Integer maxShelfNum;

    // 更新时间
    private Date updateTime;

    // 创建时间
    private Date createTime;

    // 取申请添加白名单的采销人员ERP
    private String applicant;

    // 操作更新的运行人员
    private String operator;

    private Integer yn;
}
