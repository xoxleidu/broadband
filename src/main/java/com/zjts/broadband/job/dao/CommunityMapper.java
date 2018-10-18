package com.zjts.broadband.job.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zjts.broadband.job.model.Community;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityMapper extends BaseMapper<Community>{
    Community selectByPrimaryKey(Integer id);
    List<Community> query(Page<Community> page, Community community);
}