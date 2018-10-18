package com.zjts.broadband.job.controller;
import com.baomidou.mybatisplus.plugins.Page;
import com.zjts.broadband.common.constant.CodeEnum;
import com.zjts.broadband.common.controller.BaseController;
import com.zjts.broadband.common.model.APIResponse;
import com.zjts.broadband.common.model.req.job.customer.ReqCustomerAdd;
import com.zjts.broadband.common.model.req.job.customer.ReqCustomerDelete;
import com.zjts.broadband.common.model.req.job.customer.ReqCustomerQuery;
import com.zjts.broadband.common.model.req.job.customer.ReqCustomerUpdate;
import com.zjts.broadband.job.model.CustomerMessage;

import io.swagger.annotations.Api;
import com.zjts.broadband.job.service.CustomerService;
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

@Api(tags = "客户管理")
@RestController
@RequestMapping("customer")
public class CustomerController  extends BaseController  {

    @Autowired
    private CustomerService customerService;

    @ApiOperation(value = "客户添加接口")
    @RequestMapping(value = "customerMessage/add", method = RequestMethod.POST)
    public APIResponse addUser(@RequestBody @Validated ReqCustomerAdd reqCustomerAdd, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
        if (bindingResult.hasErrors()) return parameterVerification(bindingResult);
        try {
            return customerService.add(reqCustomerAdd);
        } catch (Exception e) {
            e.printStackTrace();
            return APIResponse.error(CodeEnum.ERROR);
        }
    }
    @ApiOperation(value = "客户修改接口")
    @RequestMapping(value = "customerMessage/update", method = RequestMethod.POST)
    public APIResponse update(@RequestBody @Validated ReqCustomerUpdate reqCustomerUpdate, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {

        if (bindingResult.hasErrors()) return parameterVerification(bindingResult);
        try {
            return customerService.update(reqCustomerUpdate);
        } catch (Exception e) {
            e.printStackTrace();
            return APIResponse.error(CodeEnum.ERROR,"修改失败");
        }
    }
    @ApiOperation(value = "客户删除接口")
    @RequestMapping(value = "customerMessage/delete", method = RequestMethod.POST)
    public APIResponse delete(@RequestBody @Validated ReqCustomerDelete reqCustomerDelete, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
        if (bindingResult.hasErrors()) return parameterVerification(bindingResult);
        try {
            return customerService.delete(reqCustomerDelete);
        } catch (Exception e) {
            e.printStackTrace();
            return APIResponse.error(CodeEnum.ERROR,"删除失败");
        }
    }
    @ApiOperation(value = "综合查询")
    @RequestMapping(value = "customerMessage/queryAllCustomer",method = RequestMethod.POST)
    public APIResponse selectAll(@RequestBody @Validated ReqCustomerQuery reqCustomerQuery, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) throws Exception {

        if (bindingResult.hasErrors())
            return parameterVerification(bindingResult);
       try {
           Page<CustomerMessage> page = new Page(reqCustomerQuery.getCurrentPage(),reqCustomerQuery.getPageSize());
           return customerService.query(page,reqCustomerQuery);
       }catch (Exception e){
           return APIResponse.error(CodeEnum.ERROR,"查询失败");
       }

    }
}
