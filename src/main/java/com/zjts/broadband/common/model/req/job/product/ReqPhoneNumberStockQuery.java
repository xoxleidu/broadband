package com.zjts.broadband.common.model.req.job.product;

import com.zjts.broadband.common.model.BaseModel;

public class ReqPhoneNumberStockQuery extends BaseModel {
    private Integer id;

    private String phoneNumber;

    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}