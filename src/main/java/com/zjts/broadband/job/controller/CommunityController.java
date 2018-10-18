package com.zjts.broadband.job.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.zjts.broadband.common.constant.CodeEnum;
import com.zjts.broadband.common.controller.BaseController;
import com.zjts.broadband.common.model.APIResponse;
import com.zjts.broadband.common.model.BaseModel;
import com.zjts.broadband.common.model.req.job.community.ReqCommunityAdd;
import com.zjts.broadband.common.model.req.job.community.ReqCommunityUpdate;
import com.zjts.broadband.job.model.Community;
import com.zjts.broadband.job.service.CommunityService;
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

@Api(tags = "小区管理")
@RestController
@RequestMapping("community")
public class CommunityController extends BaseController{

    @Autowired
    private CommunityService communityService;

    @ApiOperation(value = "小区添加接口")
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public APIResponse addCommunity(@RequestBody @Validated ReqCommunityAdd reqCommunityAdd, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return parameterVerification(bindingResult);
        }
        try {
            return communityService.add(reqCommunityAdd);
        } catch (Exception e) {
            e.printStackTrace();
            return APIResponse.error(CodeEnum.ERROR, "新增失败");
        }
    }
    @ApiOperation(value = "小区修改接口")
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public APIResponse updateCommunity(@RequestBody @Validated ReqCommunityUpdate reqCommunityUpdate, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return parameterVerification(bindingResult);
        }
        try {
            return communityService.update(reqCommunityUpdate);
        } catch (Exception e) {
            e.printStackTrace();
            return APIResponse.error(CodeEnum.ERROR, "修改失败");
        }
    }
    @ApiOperation(value = "小区删除接口")
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public APIResponse delecteCommunity(@RequestBody Integer id , BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return parameterVerification(bindingResult);
        }
        try {
            return communityService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return APIResponse.error(CodeEnum.ERROR, "删除失败");
        }
    }
    @ApiOperation(value = "小区查询接口")
    @RequestMapping(value = "query",method = RequestMethod.POST)
    public APIResponse queryCommunity(@RequestBody BaseModel baseModel, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {

        if (bindingResult.hasErrors()) {
            return parameterVerification(bindingResult);
        }
        try {
            Page<Community> page = new Page(baseModel.getCurrentPage(),baseModel.getPageSize());
            return communityService.query(page);
        } catch (Exception e) {
            e.printStackTrace();
            return APIResponse.error(CodeEnum.ERROR, "查询失败");
        }
    }

}
