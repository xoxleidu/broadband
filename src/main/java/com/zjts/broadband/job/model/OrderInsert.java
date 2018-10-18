package com.zjts.broadband.job.model;

import java.util.List;

public class OrderInsert {
    //订单ID
    private String orderNumber;
    //套餐表ID
    private Integer productId;
    //订单单项金额
    private Integer discountMoney;
    //用户ID
    private Integer customerId;
    //产品类型
    private Integer productType;
    //安装时间
    private Integer installDate;
    // 安装地址
    private String installAddress;
    //余额
    private int money;
    //资费周期
    private int cycle;
    //资费周期类型
    private int cycleType;
    public int getCycle() {
        return cycle;
    }

    public void setCycle(int cycle) {
        this.cycle = cycle;
    }
    public int getCycleType() {
        return cycleType;
    }
    public void setCycleType(int cycleType) {
        this.cycleType = cycleType;
    }
    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getOrderNumber() {
        return orderNumber;
    }
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getDiscountMoney() {
        return discountMoney;
    }
    public void setDiscountMoney(Integer discountMoney) {
        this.discountMoney = discountMoney;
    }
    public Integer getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    public Integer getProductType() {
        return productType;
    }
    public void setProductType(Integer productType) {
        this.productType = productType;
    }
    public Integer getInstallDate() {
        return installDate;
    }

    public void setInstallDate(Integer installDate) {
        this.installDate = installDate;
    }

    public String getInstallAddress() {
        return installAddress;
    }

    public void setInstallAddress(String installAddress) {
        this.installAddress = installAddress;
    }

}
