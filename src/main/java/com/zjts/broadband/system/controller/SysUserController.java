package com.zjts.broadband.system.controller;


import com.zjts.broadband.common.constant.CodeEnum;
import com.zjts.broadband.common.controller.BaseController;
import com.zjts.broadband.common.log.ControllerLog;
import com.zjts.broadband.common.model.APIResponse;
import com.zjts.broadband.common.model.req.system.ReqSysUserAdd;
import com.zjts.broadband.common.model.req.system.ReqSysUserLogin;
import com.zjts.broadband.common.model.req.system.ReqSysUserQuery;
import com.zjts.broadband.system.model.SysUser;
import com.zjts.broadband.system.service.SysUserService;
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

@Api(tags = "用户管理")
@RestController
@RequestMapping("admin")
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation(value = "用户添加接口")
    @ControllerLog(description = "用户添加")
    @RequestMapping(value = "user/add", method = RequestMethod.POST)
    public APIResponse addUser(@RequestBody @Validated ReqSysUserAdd sysUser, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
        if (bindingResult.hasErrors()) return parameterVerification(bindingResult);
        try {
            return sysUserService.add(sysUser);
        } catch (Exception e) {
            e.printStackTrace();
            return APIResponse.error(CodeEnum.ERROR);
        }
    }

    @ApiOperation(value = "用户登录接口")
    @RequestMapping(value = "user/login", method = RequestMethod.POST)
    public APIResponse selectUser(@RequestBody @Validated ReqSysUserLogin sysUser, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
        if (bindingResult.hasErrors()) return parameterVerification(bindingResult);
        return sysUserService.login(sysUser);
    }


    @ApiOperation(value = "用户查询")
    @RequestMapping(value = "user/query", method = RequestMethod.POST)
    public APIResponse selectUser(@RequestBody @Validated ReqSysUserQuery sysUser, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
        if (bindingResult.hasErrors()) return parameterVerification(bindingResult);
        return sysUserService.query(sysUser);
    }
}
