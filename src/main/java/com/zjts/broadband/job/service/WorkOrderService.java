package com.zjts.broadband.job.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.zjts.broadband.common.model.APIResponse;
import com.zjts.broadband.common.model.req.job.workorder.ReqWorkOrderAdd;
import com.zjts.broadband.common.model.req.job.workorder.ReqWorkOrderQuery;
import com.zjts.broadband.common.model.req.job.workorder.ReqWorkOrderUpdate;
import com.zjts.broadband.job.model.WorkOrder;
import io.swagger.annotations.ApiResponse;
import org.springframework.stereotype.Service;

@Service
public interface WorkOrderService {

    APIResponse add(ReqWorkOrderAdd reqWorkOrderAdd) throws Exception;

    APIResponse update(ReqWorkOrderUpdate reqWorkOrderUpdate) throws  Exception;

    APIResponse query(Page<WorkOrder> page, ReqWorkOrderQuery reqWorkOrderQuery) throws  Exception;
}
