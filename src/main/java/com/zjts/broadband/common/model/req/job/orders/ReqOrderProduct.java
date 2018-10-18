package com.zjts.broadband.common.model.req.job.orders;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ReqOrderProduct {
    //数量
    private int num;
    //余额
    @NotNull(message = "订单金额不能为空")
    @Min(value = 0, message = "最小不能小于0")
    private Integer discountMoney;
    //ID
    private int productId;
    //产品类型
    private Integer productType;

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Integer getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(Integer discountMoney) {
        this.discountMoney = discountMoney;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }


}
