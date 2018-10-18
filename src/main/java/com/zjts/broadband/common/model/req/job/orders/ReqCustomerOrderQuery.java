package com.zjts.broadband.common.model.req.job.orders;

import com.zjts.broadband.common.model.BaseModel;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class ReqCustomerOrderQuery extends BaseModel {
    //用户ID
    @NotNull(message = "用户ID不能为空")
    private Integer customerId;
    //产品类型
    @NotNull(message = "产品类型不能为空")
    private String expensesName;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getExpensesName() {
        return expensesName;
    }

    public void setExpensesName(String expensesName) {
        this.expensesName = expensesName;
    }
}
