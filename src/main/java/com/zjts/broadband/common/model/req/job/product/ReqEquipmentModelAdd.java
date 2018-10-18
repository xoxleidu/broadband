package com.zjts.broadband.common.model.req.job.product;


import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

public class ReqEquipmentModelAdd implements Serializable {
    private static final long serialVersionUID = 1001943494952931101L;
    private Integer id;

    @ApiModelProperty(name="设备名称",example = "华为光猫",required = true)
    @NotNull(message = "名称不能为空")
    @Length(max = 10,message = "设备名称长度为1-10")
    private String name;

    @ApiModelProperty(name="设备型号",example = "WIFF-003")
    @NotNull(message = "型号不能为空")
    private String model;

    @ApiModelProperty(name="价格",example = "180",required = true)
    @NotNull(message = "价格不能为空")
    @Min(0)
    private BigDecimal price;

    @ApiModelProperty(name="设备型号状态",example = "0")
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model == null ? null : model.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}