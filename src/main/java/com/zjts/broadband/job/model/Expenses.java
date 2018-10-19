package com.zjts.broadband.job.model;

import com.baomidou.mybatisplus.annotations.TableName;

import java.math.BigDecimal;
@TableName(value = "expenses")
public class Expenses {
    private Integer id;

    private String name;

    private String type;

    private Integer ipId;

    private Integer bandwidthId;

    private BigDecimal price;

    private Integer cycle;

    private Integer cycleType;

    private String status;

    private String ipName;

    public String getIpName() {
        return ipName;
    }

    public void setIpName(String ipName) {
        this.ipName = ipName;
    }

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

    public Integer getCycleType() {
        return cycleType;
    }

    public void setCycleType(Integer cycleType) {
        this.cycleType = cycleType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    @Override
    public String toString() {
        return "Expenses{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", ipId=" + ipId +
                ", bandwidthId=" + bandwidthId +
                ", price=" + price +
                ", cycle=" + cycle +
                ", cycleType='" + cycleType + '\'' +
                ", status='" + status + '\'' +
                ", ipName='" + ipName + '\'' +
                '}';
    }
}