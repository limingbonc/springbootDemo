package com.example.demo.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.examples.mybatisEncrypt.domain.Customer;
import com.example.demo.examples.mybatisEncrypt.domain.Encrypt;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * @author: liming522
 * @description:
 * @date: 2022/8/2 3:26 PM
 * @hope: The newly created file will not have a bug
 */
@Mapper
public interface CustomerMapper extends BaseMapper<Customer> {

    void addCustomer(@Param("phone") Encrypt phone,@Param("address") String address);

    Customer selectByPhone(@Param("phone") Encrypt phone);
}
