package com.zjts.broadband.job.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zjts.broadband.job.model.Gift;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GiftMapper extends BaseMapper<Gift> {
    List<Gift> findGift(Page<Gift> page, Gift gift);

}
