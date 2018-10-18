package com.zjts.broadband.common.model.req.job.community;

import com.zjts.broadband.common.model.BaseModel;

import javax.validation.constraints.NotNull;

public class ReqCommunityAdd extends BaseModel{

    @NotNull(message = "小区名称不能为空")
    private String communityName;

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName == null ? null : communityName.trim();
    }
}