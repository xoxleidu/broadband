package com.zjts.broadband.system.service.Imp;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zjts.broadband.common.constant.CodeEnum;
import com.zjts.broadband.common.log.ServiceLog;
import com.zjts.broadband.common.model.APIResponse;
import com.zjts.broadband.common.model.req.system.ReqSysUserAdd;
import com.zjts.broadband.common.model.req.system.ReqSysUserLogin;
import com.zjts.broadband.common.model.req.system.ReqSysUserQuery;
import com.zjts.broadband.system.dao.SysUserMapper;
import com.zjts.broadband.system.model.SysUser;
import com.zjts.broadband.system.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class SysUserServiceImp implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     *
     * @param sysUser
     * @return
     * @throws Exception
     */
    @Override
    @ServiceLog(description = "添加用户")
    public APIResponse add(ReqSysUserAdd sysUser) throws Exception {
        SysUser sysUser1 = new SysUser();
        BeanUtils.copyProperties(sysUser, sysUser1);
        sysUser1.setCrateTime((int) sysUser.getCrateTime().getTime());
        Integer insert = sysUserMapper.insert(sysUser1);
        if (insert != 1) {
            return APIResponse.error(CodeEnum.SAVE_ERROR);
        }
        return APIResponse.success();
    }

    @Override
    public APIResponse login(ReqSysUserLogin sysUser) {
        SysUser sysUser1 = new SysUser();
        BeanUtils.copyProperties(sysUser, sysUser1);
        SysUser sysUser2 = sysUserMapper.selectById(2);
        if (sysUser2 == null) {
            return APIResponse.error(CodeEnum.FIND_NULL_ERROR);
        }

        return APIResponse.success(sysUser2);
    }

    @Override
    public APIResponse query(ReqSysUserQuery sysUser) {
        Page<SysUser> sysUserPage = new Page<SysUser>(sysUser.getCurrentPage(), sysUser.getPageSize());
        List<SysUser> sysUserList = sysUserMapper.selectPage(sysUserPage, new EntityWrapper<SysUser>().like("username", sysUser.getUsername()));
        if (sysUserList.isEmpty()) {
            return APIResponse.error(CodeEnum.FIND_NULL_ERROR);
        }
        return APIResponse.success(sysUserPage.setRecords(sysUserList));
    }

}
