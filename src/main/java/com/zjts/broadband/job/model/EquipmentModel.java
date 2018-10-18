package com.zjts.broadband.job.model;

import com.zjts.broadband.util.pio.convert.ExportConfig;

import java.io.Serializable;
import java.math.BigDecimal;

public class EquipmentModel implements Serializable {
    private static final long serialVersionUID = 1001943494952931101L;
    @ExportConfig(value = "型号表id")
    private Integer id;
    @ExportConfig(value = "名称")
    private String name;
    @ExportConfig(value = "型号")
    private String model;

    @ExportConfig(value = "价格")
    private BigDecimal price;
    @ExportConfig(value = "状态")
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
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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