package com.zjts.broadband.common.model.req.job.customer;

import com.zjts.broadband.common.model.BaseModel;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @ClassNameReqCustomerDelete
 * @Description TODO
 * @Authoradmin
 * @Date2018/9/2111:12
 * @Version1.0
 **/

public class ReqCustomerDelete  {
    @NotNull(message = "客户编号不能为空")
    @Min(value = 1 ,message = "客户编号不能小于1")
    private Integer id;//客户编号

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
