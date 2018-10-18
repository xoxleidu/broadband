package com.zjts.broadband.job.model;

/**
 * @ClassNameOrdersQuery
 * @Description TODO
 * @Authoradmin
 * @Date2018/9/2915:20
 * @Version1.0
 **/

public class OrdersQuery {

    private Integer id ;//客户id

    private String orderNumber;//订单编号

    private Integer productId ;//产品id

    private  Integer status;//订单状态


    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }



    public Integer getId() {
        return id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
