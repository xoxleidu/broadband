package com.zjts.broadband.common.model.req.job.product;

import com.baomidou.mybatisplus.annotations.TableName;
import com.zjts.broadband.common.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
@TableName(value = "expenses")
public class ReqExpensesAdd extends BaseModel {
    private Integer id;

    @NotNull(message = "资费名称不能为空")
    @ApiModelProperty(name = "资费名称")
    @Length(max = 10,message = "设备名称长度为1-10")
    private String name;

    @NotNull(message = "资费类型不能为空")
    @ApiModelProperty(name = "资费类型",example = "1")
    private String type;

    @NotNull(message = "ip段不能为空")
    private Integer ipId;

    @NotNull(message = "带宽不能为空")
    private Integer bandwidthId;

    @NotNull(message = "价格不能为空")
    @Min(0)
    private BigDecimal price;

    @NotNull(message = "周期不能为空")
    private Integer cycle;

    @NotNull(message = "周期类型不能为空")
    @ApiModelProperty(name = "周期单位",example = "1")
    private String cycleType;
    @NotNull(message = "状态不能为空")
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getIpId() {
        return ipId;
    }

    public void setIpId(Integer ipId) {
        this.ipId = ipId;
    }

    public Integer getBandwidthId() {
        return bandwidthId;
    }

    public void setBandwidthId(Integer bandwidthId) {
        this.bandwidthId = bandwidthId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getCycle() {
        return cycle;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    public String getCycleType() {
        return cycleType;
    }

    public void setCycleType(String cycleType) {
        this.cycleType = cycleType == null ? null : cycleType.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

}