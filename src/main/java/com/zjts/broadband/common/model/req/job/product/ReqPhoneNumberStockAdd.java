package com.zjts.broadband.common.model.req.job.product;

import com.zjts.broadband.common.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ReqPhoneNumberStockAdd implements Serializable {
    private static final long serialVersionUID =1001943494952931101L;

    private Integer id;
    @NotNull(message = "号码不能为空")
    @Length(min = 7,max = 8,message = "固话号码长度7-8位")
    @ApiModelProperty(name = "固话号码",example = "2635468",required = true)
    private String phoneNumber;

    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}