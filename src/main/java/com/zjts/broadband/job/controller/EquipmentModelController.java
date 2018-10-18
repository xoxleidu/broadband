package com.zjts.broadband.job.controller;

import com.zjts.broadband.common.constant.CodeEnum;
import com.zjts.broadband.common.controller.BaseController;
import com.zjts.broadband.common.log.ControllerLog;
import com.zjts.broadband.common.model.APIResponse;
import com.zjts.broadband.common.model.req.job.product.ReqEquipmentModelAdd;
import com.zjts.broadband.common.model.req.job.product.ReqEquipmentModelQuery;
import com.zjts.broadband.common.model.req.job.product.ReqEquipmentUse;
import com.zjts.broadband.job.model.Equipment;
import com.zjts.broadband.job.model.EquipmentModel;
import com.zjts.broadband.job.service.EquipmentModelService;
import com.zjts.broadband.util.FileUtils;
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
import java.util.List;


@Api(tags = "设备型号管理")
@RestController
@RequestMapping("product")
public class EquipmentModelController extends BaseController {
    @Autowired
    private EquipmentModelService equipmentModelService;

    @ApiOperation(value = "设备型号添加接口")
    @ControllerLog(description = "设备型号添加")
    @RequestMapping(value = "model/add", method = RequestMethod.POST)
    public APIResponse addEquipmentModel(@RequestBody @Validated  ReqEquipmentModelAdd reqEquipmentModelAdd ,BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return parameterVerification(bindingResult);
        }
        try {
            return equipmentModelService.add(reqEquipmentModelAdd);
        } catch (Exception e) {
            e.printStackTrace();
            return APIResponse.error(CodeEnum.ERROR);
        }
    }


    @ApiOperation(value = "根据id修改设备（status：0/1 --> 可用/不可用")
    @ControllerLog(description = "设备型号修改")
    @RequestMapping(value = "model/update", method = RequestMethod.POST)
    public APIResponse updateEquipmentModel(@RequestBody @Validated  ReqEquipmentModelAdd reqEquipmentModelAdd,BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return parameterVerification(bindingResult);
        }
        try {
            return equipmentModelService.update(reqEquipmentModelAdd);
        } catch (Exception e) {
            e.printStackTrace();
            return APIResponse.error(CodeEnum.ERROR);
        }
    }

    @ApiOperation(value = "任意条件查询（id，name，model，price，status）")
    @RequestMapping(value = "model/find", method = RequestMethod.POST)
    public APIResponse findEquipmentModel(@RequestBody ReqEquipmentModelQuery query, HttpServletRequest request, HttpServletResponse response) {
        try {
            return equipmentModelService.findEquipmentModel(query);
        } catch (Exception e) {
            e.printStackTrace();
            return APIResponse.error(CodeEnum.ERROR);
        }
    }

    @ApiOperation(value = "根据id多选设备")
    @RequestMapping(value = "model/findById", method = RequestMethod.POST)
    public APIResponse findEquipmentModelById(@RequestBody List<Integer> idList, HttpServletRequest request, HttpServletResponse response) {
        try {
            return equipmentModelService.findEquipmentModelById(idList);
        } catch (Exception e) {
            e.printStackTrace();
            return APIResponse.error(CodeEnum.FIND_NULL_ERROR);
        }
    }


    @ApiOperation(value = "生成Excel文件（空参）")
    @RequestMapping(value = "model/excel", method = RequestMethod.POST)
    public APIResponse deptExcel(@RequestBody EquipmentModel EquipmentModel, HttpServletRequest request, HttpServletResponse response) {
        try {
            List<EquipmentModel> list = this.equipmentModelService.findForExcel();
            return FileUtils.createExcelByPOIKit("设备型号表", list, Equipment.class);
        } catch (Exception e) {
            e.printStackTrace();
            return APIResponse.error(CodeEnum.ERROR, "导出Excel失败，请联系网站管理员！");
        }
    }


}
