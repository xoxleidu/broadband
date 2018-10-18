package com.zjts.broadband.job.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zjts.broadband.job.model.Equipment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentMapper extends BaseMapper<Equipment> {

    List<Equipment> findEquipment(Page<Equipment> page, Equipment equipment);
}