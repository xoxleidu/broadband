package com.zjts.broadband.job.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.zjts.broadband.common.constant.CodeEnum;
import com.zjts.broadband.common.controller.BaseController;
import com.zjts.broadband.common.model.APIResponse;
import com.zjts.broadband.common.model.req.job.workorder.ReqWorkOrderAdd;
import com.zjts.broadband.common.model.req.job.workorder.ReqWorkOrderQuery;
import com.zjts.broadband.common.model.req.job.workorder.ReqWorkOrderUpdate;
import com.zjts.broadband.job.model.WorkOrder;
import com.zjts.broadband.job.service.WorkOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(tags = "工单管理")
@RestController
@RequestMapping("workOrder")
public class WorkOrderController extends BaseController{

    @Autowired
    private WorkOrderService workOrderService;

    @ApiOperation(value = "工单添加接口")
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public APIResponse addWorkOrder(@RequestBody @Validated ReqWorkOrderAdd reqWorkOrderAdd, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response){
        if (bindingResult.hasErrors()) {
            return parameterVerification(bindingResult);
        }
        try {
            return workOrderService.add(reqWorkOrderAdd);
        }catch (Exception e) {
            e.printStackTrace();
            return APIResponse.error(CodeEnum.ERROR, "新增工单失败");
        }
    }

    @ApiOperation(value = "工单修改接口")
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public APIResponse updateWordOrder(@RequestBody @Validated ReqWorkOrderUpdate reqWorkOrderUpdate, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response){
        if (bindingResult.hasErrors()) {
            return parameterVerification(bindingResult);
        }
        try {
            return  workOrderService.update(reqWorkOrderUpdate);
        } catch (Exception e) {
            e.printStackTrace();
            return APIResponse.error(CodeEnum.ERROR);
        }
    }

    @ApiOperation(value = "工单查询接口")
    @RequestMapping(value = "query", method = RequestMethod.POST)
    public APIResponse queryWordOrder(@RequestBody @Validated ReqWorkOrderQuery reqWorkOrderQuery, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response){

        if (bindingResult.hasErrors()) {
            return parameterVerification(bindingResult);
        }
        try {
            Page<WorkOrder> page = new Page(reqWorkOrderQuery.getCurrentPage(),reqWorkOrderQuery.getPageSize());
            return  workOrderService.query(page, reqWorkOrderQuery);
        } catch (Exception e) {
            e.printStackTrace();
            return APIResponse.error(CodeEnum.ERROR);
        }
    }

}
