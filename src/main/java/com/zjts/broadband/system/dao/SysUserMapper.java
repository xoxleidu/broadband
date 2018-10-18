package com.zjts.broadband.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zjts.broadband.system.model.SysUser;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserMapper extends BaseMapper<SysUser>{
    SysUser selectByPrimaryKey(Integer id);
}