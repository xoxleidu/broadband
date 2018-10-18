package com.zjts.broadband.common.model.req.job.orders;

/**
 * @ClassNameReqOrderDiscount
 * @Description TODO
 * @Authoradmin
 * @Date2018/9/3016:25
 * @Version1.0
 **/

public class ReqOrderDiscount  {

    private String ordersNumber;//订单编号

    private Integer productId ;//产品id

    private String type;//产品类型,1.产品,2.资费,3.设备,4.赠品

    private  Integer number;//各类产品的数量

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

    public String getOrdersNumber() {
        return ordersNumber;
    }

    public void setOrdersNumber(String ordersNumber) {
        this.ordersNumber = ordersNumber;
    }
}
