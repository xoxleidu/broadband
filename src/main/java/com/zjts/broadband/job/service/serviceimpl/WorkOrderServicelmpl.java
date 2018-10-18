package com.zjts.broadband.job.service.serviceimpl;

import com.baomidou.mybatisplus.plugins.Page;
import com.zjts.broadband.common.constant.CodeEnum;
import com.zjts.broadband.common.model.APIResponse;
import com.zjts.broadband.common.model.req.job.workorder.ReqWorkOrderAdd;
import com.zjts.broadband.common.model.req.job.workorder.ReqWorkOrderQuery;
import com.zjts.broadband.common.model.req.job.workorder.ReqWorkOrderUpdate;
import com.zjts.broadband.job.dao.WorkOrderMapper;
import com.zjts.broadband.job.model.WorkOrder;
import com.zjts.broadband.job.service.WorkOrderService;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WorkOrderServicelmpl implements WorkOrderService {

    @Autowired
    private WorkOrderMapper workOrderMapper;

    /**
     * 添加安装/维修工单
     */
    @Override
    public APIResponse add(ReqWorkOrderAdd reqWorkOrderAdd) throws Exception {
        WorkOrder workOrder = new WorkOrder();
        BeanUtils.copyProperties(reqWorkOrderAdd, workOrder);
        workOrder.setTheRepairOrderId("233");
        Integer insert = workOrderMapper.insert(workOrder);
        if (insert != 1) {
            return APIResponse.error(CodeEnum.SAVE_ERROR);
        }
        return APIResponse.success();
    }

    /**
     * 修改安装/维修工单
     */
    @Override
    public APIResponse update(ReqWorkOrderUpdate reqWorkOrderUpdate) throws Exception{
        WorkOrder workOrder = new WorkOrder();
        BeanUtils.copyProperties(reqWorkOrderUpdate, workOrder);
        Integer insert = workOrderMapper.updateById(workOrder);
        if (insert != 1) {
            return APIResponse.error(CodeEnum.SAVE_ERROR);
        }
        return APIResponse.success();
    }

    /**
     * 查询工单
     */
    @Override
    public APIResponse query(Page<WorkOrder> page, ReqWorkOrderQuery reqWorkOrderQuery) throws Exception{
        WorkOrder workOrder = new WorkOrder();
        BeanUtils.copyProperties(reqWorkOrderQuery, workOrder);
        List<WorkOrder> workOrderList = workOrderMapper.selectByWorks(page, workOrder);
        if (workOrderList.isEmpty()) {
            return APIResponse.error(CodeEnum.SAVE_ERROR);
        }
        return APIResponse.success(page.setRecords(workOrderList));
    }
}
