package com.zjts.broadband.common.model.req.system;

import com.zjts.broadband.common.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

public class ReqSysUserLogin extends BaseModel{
    @NotNull(message = "用户名不能为空")
    @Length(min = 4, max = 10, message = "用户名格式为4-10位数字或字母")
    @ApiModelProperty(name = "用户名",example = "zhangsan",required = true)
    private String username;

    @NotNull(message = "密码不能为空")
    @ApiModelProperty(name = "密码",example = "23333",required = true)
    private String password;

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
}