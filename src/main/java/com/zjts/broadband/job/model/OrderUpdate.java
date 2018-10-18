package com.zjts.broadband.job.model;

import javax.validation.constraints.NotNull;

public class OrderUpdate {
    //订单ID
    private String  orderNumber;
    //充值金额
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
