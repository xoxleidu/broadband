package com.zjts.broadband.job.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zjts.broadband.job.model.CustomerMessage;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface CustomerMapper extends BaseMapper<CustomerMessage> {
    CustomerMessage selectByPrimaryKey(Integer id);
    List<CustomerMessage> query(Page<CustomerMessage> page,CustomerMessage customerMessage);
}