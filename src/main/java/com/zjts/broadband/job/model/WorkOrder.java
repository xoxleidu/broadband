package com.zjts.broadband.job.model;

public class WorkOrder {
    private Integer id;

    private String theRepairOrderId;

    private Integer appointmentDate;

    private String customerName;

    private String mobile;

    private Boolean type;

    private Boolean maintenanceType;

    private Boolean installState;

    private Integer userId;

    private String maintenanceReason;

    private String orderNumber;

    private String note;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTheRepairOrderId() {
        return theRepairOrderId;
    }

    public void setTheRepairOrderId(String theRepairOrderId) {
        this.theRepairOrderId = theRepairOrderId == null ? null : theRepairOrderId.trim();
    }

    public Integer getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Integer appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public Boolean getMaintenanceType() {
        return maintenanceType;
    }

    public void setMaintenanceType(Boolean maintenanceType) {
        this.maintenanceType = maintenanceType;
    }

    public Boolean getInstallState() {
        return installState;
    }

    public void setInstallState(Boolean installState) {
        this.installState = installState;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMaintenanceReason() {
        return maintenanceReason;
    }

    public void setMaintenanceReason(String maintenanceReason) {
        this.maintenanceReason = maintenanceReason == null ? null : maintenanceReason.trim();
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}