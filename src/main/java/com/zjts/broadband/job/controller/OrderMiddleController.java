package com.zjts.broadband.job.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.zjts.broadband.common.constant.CodeEnum;
import com.zjts.broadband.common.controller.BaseController;
import com.zjts.broadband.common.model.APIResponse;
import com.zjts.broadband.common.model.BaseModel;
import com.zjts.broadband.common.model.req.job.ReqAdd;
import com.zjts.broadband.common.model.req.job.orders.*;
import com.zjts.broadband.job.model.Community;
import com.zjts.broadband.job.model.CustomerMessage;
import com.zjts.broadband.job.service.OrdersService;
import com.zjts.broadband.job.service.ProductService;
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
import java.util.Map;

@Api(tags = "订单管理")
@RestController
@RequestMapping("order")
public class OrderMiddleController extends BaseController {
    @Autowired
    private OrdersService ordersService;


    @ApiOperation(value = "根据用户ID查询产品类型接口")
    @RequestMapping(value = "/selectByProductId", method = RequestMethod.POST)
    public APIResponse selectProductId(@RequestBody Integer id, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {

        if (bindingResult.hasErrors()) return parameterVerification(bindingResult);
        try {
            return ordersService.findByProductId(id);
        } catch (Exception e) {
            e.printStackTrace();
            return APIResponse.error(CodeEnum.ERROR);
        }
    }

    @ApiOperation(value = "根据用户ID和产品类型分页查询订单接口")
    @RequestMapping(value = "/findSelectAll", method = RequestMethod.POST)
    public APIResponse findSelectAll(@RequestBody ReqCustomerOrderQuery reqCustomerOrderQuery, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {

        if (bindingResult.hasErrors()) return parameterVerification(bindingResult);
        try {

            Page<Map> mapPage = new Page<>(reqCustomerOrderQuery.getCurrentPage(), reqCustomerOrderQuery.getPageSize());
            Page<Map> myItems = ordersService.findSelectAll(mapPage, reqCustomerOrderQuery);
            return APIResponse.success(myItems);
        } catch (Exception e) {
            e.printStackTrace();
            return APIResponse.error(CodeEnum.ERROR);
        }
    }

    @ApiOperation(value = "用户充值接口")
    @RequestMapping(value = "/updateMoney", method = RequestMethod.POST)
    public APIResponse updateMoney(@RequestBody ReqUpdateMoney reqUpdateMoney, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {

        if (bindingResult.hasErrors()) return parameterVerification(bindingResult);
        try {
            return APIResponse.success(ordersService.updateMoney(reqUpdateMoney));
        } catch (Exception e) {
            e.printStackTrace();
            return APIResponse.error(CodeEnum.ERROR,"用户充值成功");
        }
    }
    @ApiOperation(value = "订单生成接口")
    @RequestMapping(value = "/orderInsert", method = RequestMethod.POST)
    public APIResponse orderInsert(@RequestBody ReqOrderAdd reqOrderAdd, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {

        if (bindingResult.hasErrors()) return parameterVerification(bindingResult);
        try {
            return APIResponse.success(ordersService.orderInsert(reqOrderAdd));
        } catch (Exception e) {
            e.printStackTrace();
            return APIResponse.error(CodeEnum.ERROR,"订单生成失败！");
        }
    }
    @ApiOperation(value = "修改订单状态接口")
    @RequestMapping(value = "/updateOrderType", method = RequestMethod.POST)
    public APIResponse updateOrderType(@RequestBody ReqUpdateOrderType reqUpdateOrderType, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {

        if (bindingResult.hasErrors()) return parameterVerification(bindingResult);
        try {
            return APIResponse.success(ordersService.updateOrderType(reqUpdateOrderType));
        } catch (Exception e) {
            e.printStackTrace();
            return APIResponse.error(CodeEnum.ERROR,"修改失败");
        }
    }
    @ApiOperation(value = "客户订单查询接口")
    @RequestMapping(value = "/orderQuery",method = RequestMethod.POST)
    public APIResponse orderQuery(@RequestBody @Validated ReqOrderQuery reqOrderQuery, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (bindingResult.hasErrors())
            return parameterVerification(bindingResult);
        try {
            Page<CustomerMessage> page = new Page(reqOrderQuery.getCurrentPage(),reqOrderQuery.getPageSize());
            return ordersService.orderQuery(page,reqOrderQuery);
        }catch (Exception e){
            return APIResponse.error(CodeEnum.ERROR,"查询失败");
        }
    }

    @ApiOperation(value = "客户订单详细查询接口")
    @RequestMapping(value = "/orderDetailedQuery",method = RequestMethod.POST)
    public APIResponse orderDetailedQuery(@RequestBody @Validated ReqOrderDetailed reqOrderDetailed, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (bindingResult.hasErrors())
            return parameterVerification(bindingResult);
        try {
            Page<ReqOrderDetailed> page = new Page(reqOrderDetailed.getCurrentPage(),reqOrderDetailed.getPageSize());
            return ordersService.orderDetailedQuery(page,reqOrderDetailed);
        }catch (Exception e){
            return APIResponse.error(CodeEnum.ERROR,"查询失败");
        }
    }



    @ApiOperation(value = "客户订单折扣接口")
    @RequestMapping(value = "/orderDiscountQuery",method = RequestMethod.POST)
    public APIResponse orderDiscount(@RequestBody @Validated ReqOrderDiscount reqOrderDiscount, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (bindingResult.hasErrors())
            return parameterVerification(bindingResult);
        try {
            return ordersService.updateDiscount(reqOrderDiscount);
        }catch (Exception e){
            return APIResponse.error(CodeEnum.ERROR,"查询失败");
        }
    }
    @ApiOperation(value = "查询所有订单")
    @RequestMapping(value = "/orders",method = RequestMethod.POST)
    public APIResponse orders(@RequestBody BaseModel baseModel, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (bindingResult.hasErrors())
            return parameterVerification(bindingResult);
        try {
            Page page = new Page(baseModel.getCurrentPage(),baseModel.getPageSize());
            return ordersService.ordersAll(page);
        }catch (Exception e){
            return APIResponse.error(CodeEnum.ERROR,"查询失败");
        }
    }

}
