package com.zjts.broadband.job.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.zjts.broadband.util.pio.convert.ExportConfig;

import java.io.Serializable;
import java.math.BigDecimal;
@TableName("equipment")
public class Equipment implements Serializable {
    private static final long serialVersionUID = 1001943494952931101L;
    @TableId
    @ExportConfig(value = "设备表id")  //导出Excel时，指定列名
    private Integer id;

    @ExportConfig(value = "设备型号表id")
    private Integer modelId;

    @ExportConfig(value = "设备编号")
    private String equipmentNumber;

    @ExportConfig(value = "入库时间")
    private Integer addTime;

    @ExportConfig(value = "出库时间")
    private Integer outTime;

    @ExportConfig(value = "状态")
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

    public Integer getAddTime() {
        return addTime;
    }

    public void setAddTime(Integer addTime) {
        this.addTime = addTime;
    }

    public Integer getOutTime() {
        return outTime;
    }

    public void setOutTime(Integer outTime) {
        this.outTime = outTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}