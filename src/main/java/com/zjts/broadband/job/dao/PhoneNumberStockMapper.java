package com.zjts.broadband.job.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zjts.broadband.job.model.PhoneNumberStock;

import java.util.List;

public interface PhoneNumberStockMapper extends BaseMapper<PhoneNumberStock> {
    PhoneNumberStock selectByPrimaryKey(Integer id);

    List<PhoneNumberStock> findPhoneNumber(Page<PhoneNumberStock> page, PhoneNumberStock phoneNumberStock);
}