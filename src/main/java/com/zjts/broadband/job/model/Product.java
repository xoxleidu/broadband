package com.zjts.broadband.job.model;

import com.zjts.broadband.common.model.req.job.product.ReqEquipmentUse;
import com.zjts.broadband.common.model.req.job.product.ReqGiftUse;

import java.math.BigDecimal;
import java.util.List;

public class Product {
    private Integer id;

    private String name;

    private BigDecimal price;

    private String status;

    private List<ReqEquipmentUse> equipmentList;

    private List<ReqGiftUse> giftList;

    private List<Expenses> expensesList;

    public List<ReqEquipmentUse> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(List<ReqEquipmentUse> equipmentList) {
        this.equipmentList = equipmentList;
    }

    public List<ReqGiftUse> getGiftList() {
        return giftList;
    }

    public void setGiftList(List<ReqGiftUse> giftList) {
        this.giftList = giftList;
    }

    public List<Expenses> getExpensesList() {
        return expensesList;
    }

    public void setExpensesList(List<Expenses> expensesList) {
        this.expensesList = expensesList;
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
        this.status = status == null ? null : status.trim();
    }
}