package com.zjts.broadband.common.model.req.job.product;

import com.zjts.broadband.common.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class ReqExpensesUse implements Serializable {
    private static final long serialVersionUID = 1001943494952931101L;
    @ApiModelProperty(name = "id")
    private Integer id;
    @ApiModelProperty(name = "价格")
    private BigDecimal price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}