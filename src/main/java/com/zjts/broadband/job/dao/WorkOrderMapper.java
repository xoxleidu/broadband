package com.zjts.broadband.job.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zjts.broadband.job.model.WorkOrder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkOrderMapper extends BaseMapper<WorkOrder>{
    WorkOrder selectByPrimaryKey(Integer id);
    List<WorkOrder> selectByWorks(Page<WorkOrder> page, WorkOrder workOrder);
}