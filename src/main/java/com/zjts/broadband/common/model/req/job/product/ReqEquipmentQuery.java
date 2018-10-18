package com.zjts.broadband.common.model.req.job.product;

import com.zjts.broadband.common.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;


public class ReqEquipmentQuery extends BaseModel {

    private Integer id;

    @ApiModelProperty(name = "型号表id", example = "1")
    private Integer modelId;

    @ApiModelProperty(name = "设备编码", example = "001")
    private String equipmentNumber;

    @ApiModelProperty(name = "设备状态", example = "001")
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public String getEquipmentNumber() {
        return equipmentNumber;
    }

    public void setEquipmentNumber(String equipmentNumber) {
        this.equipmentNumber = equipmentNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

}
