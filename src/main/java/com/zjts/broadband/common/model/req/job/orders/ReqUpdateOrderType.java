package com.zjts.broadband.common.model.req.job.orders;

import com.zjts.broadband.common.model.BaseModel;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class ReqUpdateOrderType extends BaseModel {
    //订单状态
    @NotNull(message = "订单状态不能为空")
    private Integer type;
    //订单Id
    @NotNull(message = "订单Id不能为空")
    private String orderNumber;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
