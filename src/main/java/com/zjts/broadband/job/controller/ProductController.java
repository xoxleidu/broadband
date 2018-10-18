package com.zjts.broadband.job.controller;

import com.zjts.broadband.common.constant.CodeEnum;
import com.zjts.broadband.common.controller.BaseController;
import com.zjts.broadband.common.log.ControllerLog;
import com.zjts.broadband.common.model.APIResponse;
import com.zjts.broadband.common.model.req.job.product.*;
import com.zjts.broadband.job.model.Gift;
import com.zjts.broadband.job.service.GiftService;
import com.zjts.broadband.job.service.ProductService;
import com.zjts.broadband.util.FileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.runtime.logging.Logger;
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


@Api(tags = "套餐管理")
@RestController
@RequestMapping("product")
public class ProductController extends BaseController {
    @Autowired
    private ProductService productService;

    @ApiOperation(value = "添加套餐接口")
    @ControllerLog(description = "添加套餐接口")
    @RequestMapping(value = "product/add", method = RequestMethod.POST)
    public APIResponse addProduct(@RequestBody @Validated ReqProductAdd reqProductAdd, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return parameterVerification(bindingResult);
        }
        try {
            return productService.addProduct(reqProductAdd);
        } catch (Exception e) {
            e.printStackTrace();
            return APIResponse.error(CodeEnum.SAVE_ERROR);
        }
    }

    @ApiOperation(value = "修改套餐接口")
    @ControllerLog(description = "修改套餐接口")
    @RequestMapping(value = "product/update", method = RequestMethod.POST)
    public APIResponse updateProduct(@RequestBody @Validated ReqProductAdd reqProductAdd, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return parameterVerification(bindingResult);
        }
        try {
            return productService.updateProduct(reqProductAdd);
        } catch (Exception e) {
            e.printStackTrace();
            return APIResponse.error(CodeEnum.SAVE_ERROR);
        }
    }


    @ApiOperation(value = "根据id查询套餐详情")
    @RequestMapping(value = "product/findProductById", method = RequestMethod.POST)
    public APIResponse findProductById(@RequestBody @Validated Integer id, HttpServletRequest request, HttpServletResponse response) {
        try {
            return productService.findProductById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return APIResponse.error(CodeEnum.FIND_NULL_ERROR);
        }
    }

    @ApiOperation(value = "查询套餐基本情况列表")
    @RequestMapping(value = "product/findProductBase", method = RequestMethod.POST)
    public APIResponse findProductBase(@RequestBody @Validated ReqProductQuery reqProductQuery, HttpServletRequest request, HttpServletResponse response) {
        try {
            return productService.findProductBase(reqProductQuery);
        } catch (Exception e) {
            e.printStackTrace();
            return APIResponse.error(CodeEnum.EXIT_ERROR);
        }
    }

}
