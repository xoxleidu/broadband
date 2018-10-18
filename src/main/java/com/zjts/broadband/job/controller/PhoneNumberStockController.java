package com.zjts.broadband.job.controller;

import com.zjts.broadband.common.constant.CodeEnum;
import com.zjts.broadband.common.controller.BaseController;
import com.zjts.broadband.common.log.ControllerLog;
import com.zjts.broadband.common.model.APIResponse;
import com.zjts.broadband.common.model.req.job.product.ReqPhoneNumberStockAdd;
import com.zjts.broadband.common.model.req.job.product.ReqPhoneNumberStockQuery;
import com.zjts.broadband.common.model.req.job.product.ReqProductAdd;
import com.zjts.broadband.job.model.Gift;
import com.zjts.broadband.job.model.PhoneNumberStock;
import com.zjts.broadband.job.service.PhoneNumberStockService;
import com.zjts.broadband.job.service.ProductService;
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


@Api(tags = "固话号码管理")
@RestController
@RequestMapping("product")
public class PhoneNumberStockController extends BaseController {
    @Autowired
    private PhoneNumberStockService phoneNumberStockService;

    @ApiOperation(value = "添加固话号码接口")
    @ControllerLog(description = "添加固话号码")
    @RequestMapping(value = "phoneNumber/add", method = RequestMethod.POST)
    public APIResponse addPhoneNumber(@RequestBody @Validated ReqPhoneNumberStockAdd reqPhoneNumberStockAdd,
                                      BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return parameterVerification(bindingResult);
        }
        try {
            return phoneNumberStockService.addPhoneNumber(reqPhoneNumberStockAdd);
        } catch (Exception e) {
            e.printStackTrace();
            return APIResponse.error(CodeEnum.SAVE_ERROR);
        }
    }

    @ApiOperation(value = "根据id修改固话号码（状态）接口")
    @ControllerLog(description = "修改固话号码")
    @RequestMapping(value = "phoneNumber/update", method = RequestMethod.POST)
    public APIResponse updatePhoneNumber(@RequestBody @Validated ReqPhoneNumberStockAdd reqPhoneNumberStockAdd,
                                      BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return parameterVerification(bindingResult);
        }
        try {
            return phoneNumberStockService.updatePhoneNumber(reqPhoneNumberStockAdd);
        } catch (Exception e) {
            e.printStackTrace();
            return APIResponse.error(CodeEnum.SAVE_ERROR);
        }
    }
    @ApiOperation(value = "查询固话号码接口")
    @RequestMapping(value = "phoneNumber/find", method = RequestMethod.POST)
    public APIResponse findPhoneNumber(@RequestBody ReqPhoneNumberStockQuery reqPhoneNumberStockQuery,
                                         HttpServletRequest request, HttpServletResponse response) {
        try {
            return phoneNumberStockService.findPhoneNumber(reqPhoneNumberStockQuery);
        } catch (Exception e) {
            e.printStackTrace();
            return APIResponse.error(CodeEnum.SAVE_ERROR);
        }
    }

    @ApiOperation(value = "生成Excel文件") //导出Excel时，实体类要指定列名
    @RequestMapping(value = "phoneNumber/excel", method = RequestMethod.POST)
    public APIResponse phoneNumberExcel(@RequestBody PhoneNumberStock phoneNumberStock, HttpServletRequest request, HttpServletResponse response) {
        try {
            List<PhoneNumberStock> list = this.phoneNumberStockService.findForExcel();
            return FileUtils.createExcelByPOIKit("号码表", list, PhoneNumberStock.class);
        } catch (Exception e) {
            e.printStackTrace();
            return APIResponse.error(CodeEnum.ERROR, "导出Excel失败，请联系网站管理员！");
        }
    }

}
