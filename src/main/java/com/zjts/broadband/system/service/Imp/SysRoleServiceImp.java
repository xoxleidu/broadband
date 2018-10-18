package com.zjts.broadband.system.service.Imp;

import com.zjts.broadband.system.model.SysRole;
import com.zjts.broadband.system.service.SysRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysRoleServiceImp implements SysRoleService {
    @Override
    public List<SysRole> findUserRole(String userName) {

        return null;
    }
}
