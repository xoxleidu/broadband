package com.zjts.broadband.job.controller;

import com.zjts.broadband.common.constant.CodeEnum;
import com.zjts.broadband.common.controller.BaseController;
import com.zjts.broadband.common.log.ControllerLog;
import com.zjts.broadband.common.model.APIResponse;
import com.zjts.broadband.common.model.req.job.product.ReqExpensesAdd;
import com.zjts.broadband.common.model.req.job.product.ReqExpensesQuery;
import com.zjts.broadband.job.service.ExpensesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Api(tags = "资费管理")
@RestController
@RequestMapping("product")
public class ExpensesController extends BaseController {
    @Autowired
    private ExpensesService expensesService;

    @ApiOperation(value = "资费添加接口")
    @ControllerLog(description = "资费添加接口")
    @RequestMapping(value = "expenses/add", method = RequestMethod.POST)
    public APIResponse addExpenses(@RequestBody @Validated ReqExpensesAdd reqExpensesAdd, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return parameterVerification(bindingResult);
        }
        try {
            return expensesService.add(reqExpensesAdd);
        } catch (Exception e) {
            e.printStackTrace();
            return APIResponse.error(CodeEnum.ERROR, "新增资费分类失败");
        }
    }

    @ApiOperation(value = "资费修改接口")
    @ControllerLog(description = "资费修改接口")
    @RequestMapping(value = "expenses/update", method = RequestMethod.POST)
    public APIResponse updateExpenses(@RequestBody  @Validated ReqExpensesAdd reqExpensesAdd, BindingResult bindingResult,HttpServletRequest request, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return parameterVerification(bindingResult);
        }
        try {
            return expensesService.update(reqExpensesAdd);
        } catch (Exception e) {
            e.printStackTrace();
            return APIResponse.error(CodeEnum.ERROR, "修改资费分类失败");
        }
    }

    @ApiOperation(value = "资费查询接口")
    @RequestMapping(value = "expenses/find", method = RequestMethod.POST)
    public APIResponse findExpenses(@RequestBody ReqExpensesQuery reqExpensesQuery, HttpServletRequest request, HttpServletResponse response) {
        try {
            return expensesService.findExpenses(reqExpensesQuery);
        } catch (Exception e) {
            e.printStackTrace();
            return APIResponse.error(CodeEnum.ERROR, "查询错误");
        }
    }


}
