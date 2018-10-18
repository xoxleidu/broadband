package com.zjts.broadband.job.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.zjts.broadband.util.pio.convert.ExportConfig;

public class PhoneNumberStock {
    @TableId
    @ExportConfig(value = "编号")
    private Integer id;
    @ExportConfig(value = "号码")
    private String phoneNumber;
    @ExportConfig(value = "状态")
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