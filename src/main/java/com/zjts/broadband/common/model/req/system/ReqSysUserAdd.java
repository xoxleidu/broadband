package com.zjts.broadband.common.model.req.system;

import com.zjts.broadband.common.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

public class ReqSysUserAdd extends BaseModel{
    @NotNull(message = "用户名不能为空")
    @Length(min = 4, max = 10, message = "用户名格式为4-10位数字或字母")
    @ApiModelProperty(name = "用户名",example = "zhangsan",required = true)
    private String username;

    @NotNull(message = "密码不能为空")
    @ApiModelProperty(name = "密码",example = "23333",required = true)
    private String password;

    private Long deptId;

    private String emall;

    @NotNull(message = "联系电话不能为空")
    @Pattern(regexp = "^1(3|4|5|7|8|9)\\d{9}$",message = "手机号码格式错误")
    @ApiModelProperty(name = "联系方式",example = "13112341234",required = true)
    private String mobile;

    private Date crateTime;

    private Date lastLoginTime;

    private String description;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getEmall() {
        return emall;
    }

    public void setEmall(String emall) {
        this.emall = emall == null ? null : emall.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Date getCrateTime() {
        return crateTime;
    }

    public void setCrateTime(Date crateTime) {
        this.crateTime = crateTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}