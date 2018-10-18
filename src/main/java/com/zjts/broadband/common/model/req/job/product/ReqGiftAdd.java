package com.zjts.broadband.common.model.req.job.product;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ReqGiftAdd implements Serializable {
    private static final long serialVersionUID =1001943494952931101L;

    @ApiModelProperty(name = "赠品名", example = "洗衣液", required = true)
    @NotNull(message = "赠品名不能为空")
    @Length(max = 10,message = "赠品名称长度为1-10")
    private String name;

    @Min(value = 0, message = "入库数量不能为负数")
    @ApiModelProperty(name = "库存数量", example = "80", required = true)
    private Integer stock;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }


}
