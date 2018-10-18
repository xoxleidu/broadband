package com.zjts.broadband.common.model.req.job.product;

import com.zjts.broadband.common.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class ReqExpensesQuery extends BaseModel {

    private Integer id;

    private String name;
    @ApiModelProperty(name = "资费类型",example = "1")
    private String type;

    @ApiModelProperty(name = "ip段id",example = "1")
    private Integer ipId;

    @ApiModelProperty(name = "带宽id",example = "1")
    private Integer bandwidthId;

    private BigDecimal price;
    @ApiModelProperty(name = "周期",example = "1")
    private Integer cycle;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}