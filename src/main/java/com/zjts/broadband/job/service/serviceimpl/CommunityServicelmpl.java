package com.zjts.broadband.job.service.serviceimpl;

import com.baomidou.mybatisplus.plugins.Page;
import com.zjts.broadband.common.constant.CodeEnum;
import com.zjts.broadband.common.model.APIResponse;
import com.zjts.broadband.common.model.req.job.community.ReqCommunityAdd;
import com.zjts.broadband.common.model.req.job.community.ReqCommunityUpdate;
import com.zjts.broadband.job.dao.CommunityMapper;
import com.zjts.broadband.job.model.Community;
import com.zjts.broadband.job.service.CommunityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommunityServicelmpl implements CommunityService{

    @Autowired
    private CommunityMapper communityMapper;

    /**
     * 添加小区信息
     */
    @Override
    public APIResponse add(ReqCommunityAdd reqCommunityAdd) throws Exception {
        Community community = new Community();
        BeanUtils.copyProperties(reqCommunityAdd, community);;
        Integer insert = communityMapper.insert(community);
        if (insert != 1) {
            return APIResponse.error(CodeEnum.SAVE_ERROR);
        }
        return APIResponse.success();
    }

    /**
     * 修改小区信息
     */
    @Override
    public APIResponse update(ReqCommunityUpdate reqCommunityUpdate) throws Exception {
        Community community = new Community();
        BeanUtils.copyProperties(reqCommunityUpdate, community);
        Integer insert = communityMapper.updateById(community);
        if (insert != 1) {
            return APIResponse.error(CodeEnum.SAVE_ERROR);
        }
        return APIResponse.success();
    }

    /**
     * 删除小区信息
     */
    @Override
    public APIResponse delete(Integer id) throws Exception {
        Integer integer = communityMapper.deleteById(id);
        if (integer != 1) {
            return APIResponse.error(CodeEnum.DELETE_ERROR);
        }
        return APIResponse.success();
    }

    /**
     * 查询小区信息
     */
    @Override
    public APIResponse query(Page<Community>page) throws Exception {
        List<Community>communityList = communityMapper.selectPage(page, null);
        if (communityList.isEmpty()) {
            return APIResponse.error(CodeEnum.SAVE_ERROR);
        }
        return APIResponse.success();
    }
}
