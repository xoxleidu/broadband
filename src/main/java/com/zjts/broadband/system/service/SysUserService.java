package com.zjts.broadband.system.service;

import com.zjts.broadband.common.model.APIResponse;
import com.zjts.broadband.common.model.req.system.ReqSysUserAdd;
import com.zjts.broadband.common.model.req.system.ReqSysUserLogin;
import com.zjts.broadband.common.model.req.system.ReqSysUserQuery;
import com.zjts.broadband.system.model.SysUser;
import org.springframework.stereotype.Service;

@Service
public interface SysUserService {
    APIResponse add(ReqSysUserAdd sysUser) throws Exception;

    APIResponse login(ReqSysUserLogin sysUser);

    APIResponse query(ReqSysUserQuery sysUser);
}
