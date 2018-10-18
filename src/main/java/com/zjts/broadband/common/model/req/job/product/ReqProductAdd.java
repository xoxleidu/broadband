package com.zjts.broadband.common.model.req.job.product;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class ReqProductAdd implements Serializable {
    private static final long serialVersionUID = 1001943494952931101L;

    private Integer id;
    @NotNull
    @Length(max = 30,message = "设备名称长度为1-30")
    private String name;

    @DecimalMin("0")
    private BigDecimal price;

    private String status;

    private List<ReqEquipmentUse> equipmentList;

    private List<ReqGiftUse> giftList;

    private List<ReqExpensesUse> expensesList;


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

    public List<ReqExpensesUse> getExpensesList() {
        return expensesList;
    }

    public void setExpensesList(List<ReqExpensesUse> expensesList) {
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
        this.status = status;
    }
}