package com.zjts.broadband.job.model;

import java.io.Serializable;

public class OrdersAll implements Serializable {
    //订单ID
    private Integer OrderId;
    //订单状态
    private Integer type;
    //安装时间
    private Integer installDate;
    // 安装地址
    private String installAddress;
    //订单金额
    private Integer discount;
    //用户ID
    private Integer customerId;
    //订单号
    private Integer orderNumber;
    //订单创建时间
    private Integer createTime;
    //余额
    private Integer money;
    //备注
    private String remarks;
    //金额
    private  int discountmoney;
    //资费名称
    private  String expensesName;
    //产品类型
    private  int projectType;
    //产品类型ID
    private  int projectId;

    public int getProjectType() {
        return projectType;
    }

    public void setProjectType(int projectType) {
        this.projectType = projectType;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getExpensesName() {
        return expensesName;
    }

    public void setExpensesName(String expensesName) {
        this.expensesName = expensesName;
    }


    public int getDiscountmoney() {
        return discountmoney;
    }

    public void setDiscountmoney(int discountmoney) {
        this.discountmoney = discountmoney;
    }

    public Integer getOrderId() {
        return OrderId;
    }

    public void setOrderId(Integer orderId) {
        OrderId = orderId;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }
    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
