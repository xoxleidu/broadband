package com.zjts.broadband.common.model.req.job.orders;

import com.zjts.broadband.common.model.BaseModel;

import javax.validation.constraints.NotNull;

public class ReqUpdateMoney extends BaseModel {
    //订单ID
    @NotNull(message = "订单号不能为空")
    private String  orderNumber;
    //充值金额
    @NotNull(message = "充值金额不能为空")
    private Integer money;
    //产品类型
    private int type;
    //产品Id
    private int productId;
    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
