package com.zjts.broadband.job.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @ClassNameOrdersDetailed
 * @Description TODO
 * @Authoradmin
 * @Date2018/9/2917:54
 * @Version1.0
 **/

public class OrdersDetailed {
    public String getOrdersNumber() {
        return ordersNumber;
    }

    public void setOrdersNumber(String ordersNumber) {
        this.ordersNumber = ordersNumber;
    }


    private String ordersNumber;//订单编号

    private Integer productId ;//产品id

    private String type;//产品类型,1.产品,2.资费,3.设备,4.赠品

    private  Integer number;//各类产品的数量


    private   double  discountmoney;//单个折扣后的价格

    private  double money;//订单总价格

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public double getDiscountmoney() {
        return discountmoney;
    }

    public void setDiscountmoney(double discountmoney) {
        this.discountmoney = discountmoney;
    }


}
