package com.zjts.broadband.common.model.req.job.product;

import com.baomidou.mybatisplus.annotations.TableName;
import com.zjts.broadband.common.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@TableName("equipment")
public class ReqEquipmentAdd implements Serializable {
    private static final long serialVersionUID = 1001943494952931101L;
    private Integer id;

    @ApiModelProperty(name="型号id",example = "1",required = true)
    @NotNull(message = "型号id不能为空")
    private Integer modelId;

    @ApiModelProperty(name="设备ID",example = "001",required = true)
    @NotNull(message = "设备ID不能为空")
    private String equipmentNumber;


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
        this.status = status;
    }
}
