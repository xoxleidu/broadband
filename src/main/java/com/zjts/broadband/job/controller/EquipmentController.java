package com.zjts.broadband.job.controller;

import com.zjts.broadband.common.constant.CodeEnum;
import com.zjts.broadband.common.controller.BaseController;
import com.zjts.broadband.common.log.ControllerLog;
import com.zjts.broadband.common.model.APIResponse;
import com.zjts.broadband.common.model.req.job.product.ReqEquipmentAdd;
import com.zjts.broadband.common.model.req.job.product.ReqEquipmentQuery;
import com.zjts.broadband.common.model.req.job.product.ReqEquipmentUse;
import com.zjts.broadband.job.model.Equipment;
import com.zjts.broadband.job.service.EquipmentService;
import com.zjts.broadband.util.FileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Api(tags = "设备管理")
@RestController
@RequestMapping("product")
public class EquipmentController extends BaseController {
    @Autowired
    private EquipmentService equipmentService;

    @ApiOperation(value = "设备添加接口（modelId，equipmentId）")
    @ControllerLog(description = "设备添加")
    @RequestMapping(value = "equipment/add", method = RequestMethod.POST)
    public APIResponse addEquipment(@RequestBody @Validated ReqEquipmentAdd reqEquipmentAdd, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return parameterVerification(bindingResult);
        }
        try {
            return equipmentService.add(reqEquipmentAdd);
        } catch (Exception e) {
            e.printStackTrace();
            return APIResponse.error(CodeEnum.ERROR);
        }
    }


    @ApiOperation(value = "根据id修改设备（status：0/1/2 --> 可用/待出库/已出库）")
    @ControllerLog(description = "设备修改")
    @RequestMapping(value = "equipment/update", method = RequestMethod.POST)
    public APIResponse updateEquipment(@RequestBody ReqEquipmentAdd reqEquipmentAdd, HttpServletRequest request, HttpServletResponse response) {
        try {
            return equipmentService.update(reqEquipmentAdd);
        } catch (Exception e) {
            e.printStackTrace();
            return APIResponse.error(CodeEnum.ERROR);
        }
    }

    @ApiOperation(value = "任意条件查询（id，model，equipmentNumber，status）")
    @RequestMapping(value = "equipment/findEquipment", method = RequestMethod.POST)
    public APIResponse findEquipment(@RequestBody ReqEquipmentQuery reqEquipmentQuery, HttpServletRequest request, HttpServletResponse response) {
        try {
            return equipmentService.findEquipment(reqEquipmentQuery);
        } catch (Exception e) {
            e.printStackTrace();
            return APIResponse.error(CodeEnum.ERROR);
        }
    }

    @ApiOperation(value = "设备调用(modelId，number)")
    @ControllerLog(description = "设备调用")
    @RequestMapping(value = "equipment/useEquipment", method = RequestMethod.POST)
    public APIResponse useEquipment(@RequestBody @Validated List<ReqEquipmentUse> list, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return parameterVerification(bindingResult);
        }

        try {
            return equipmentService.useEquipment(list);
        } catch (Exception e) {
            e.printStackTrace();
            return APIResponse.error(CodeEnum.ERROR);
        }
    }




    @ApiOperation(value = "生成Excel文件（空参）")
    @RequestMapping(value = "equipment/excel", method = RequestMethod.POST)
    public APIResponse deptExcel(@RequestBody Equipment equipment, HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Equipment> list = this.equipmentService.findAllGift2();
            return FileUtils.createExcelByPOIKit("设备详情表", list, Equipment.class);
        } catch (Exception e) {
            e.printStackTrace();
            return APIResponse.error(CodeEnum.ERROR, "导出Excel失败，请联系网站管理员！");
        }
    }


}
