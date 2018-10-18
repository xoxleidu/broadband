package com.zjts.broadband.system.service;

import com.zjts.broadband.common.model.APIResponse;
import com.zjts.broadband.common.model.req.system.ReqSysUserAdd;
import com.zjts.broadband.common.model.req.system.ReqSysUserLogin;
import com.zjts.broadband.system.model.SysRole;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SysRoleService {
    List<SysRole> findUserRole(String userName);
}
