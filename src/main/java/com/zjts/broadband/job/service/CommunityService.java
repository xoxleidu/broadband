package com.zjts.broadband.job.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.zjts.broadband.common.model.APIResponse;
import com.zjts.broadband.common.model.req.job.community.ReqCommunityAdd;
import com.zjts.broadband.common.model.req.job.community.ReqCommunityUpdate;
import com.zjts.broadband.job.model.Community;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;

@Service
public interface CommunityService {
    APIResponse add(ReqCommunityAdd reqCommunityAdd) throws Exception;
    APIResponse update(ReqCommunityUpdate reqCommunityUpdate) throws Exception;
    APIResponse delete(Integer id) throws Exception;
    APIResponse query(Page<Community> page) throws Exception;
}
