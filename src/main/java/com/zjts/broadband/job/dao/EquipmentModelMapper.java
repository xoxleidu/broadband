package com.zjts.broadband.job.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zjts.broadband.job.model.EquipmentModel;

import java.util.List;

public interface EquipmentModelMapper extends BaseMapper<EquipmentModel> {

    List<EquipmentModel> findEquipmentModel(Page<EquipmentModel> page, EquipmentModel e);
}