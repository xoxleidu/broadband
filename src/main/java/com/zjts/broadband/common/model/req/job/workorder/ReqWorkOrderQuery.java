package com.zjts.broadband.common.model.req.job.workorder;

import com.zjts.broadband.common.model.BaseModel;

public class ReqWorkOrderQuery extends BaseModel{
    private Integer appointmentDate;//预约时间

    private String customerName;//客户姓名

    private String mobile;//联系电话

    private Boolean type;//类型（1 维修，2 安装）

    private Boolean maintenanceType;

    private Boolean installState;//完成状态（1 未完成，2 完成，3 确认完成）


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

}