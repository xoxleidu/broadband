package com.zjts.broadband.system.model;

import java.util.Date;

public class SysLog {
    private String id;

    private String username;

    private String operation;

    private String method;

    private String requestip;

    private Date createTime;

    private String exceptioncode;

    private String exceptiondetail;

    private int type;

    private String params;

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation == null ? null : operation.trim();
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    public String getRequestip() {
        return requestip;
    }

    public void setRequestip(String requestip) {
        this.requestip = requestip == null ? null : requestip.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getExceptioncode() {
        return exceptioncode;
    }

    public void setExceptioncode(String exceptioncode) {
        this.exceptioncode = exceptioncode == null ? null : exceptioncode.trim();
    }

    public String getExceptiondetail() {
        return exceptiondetail;
    }

    public void setExceptiondetail(String exceptiondetail) {
        this.exceptiondetail = exceptiondetail == null ? null : exceptiondetail.trim();
    }
}