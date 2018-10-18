package com.zjts.broadband.common.model.req.job.workorder;

import com.zjts.broadband.common.model.BaseModel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class ReqWorkOrderUpdate extends BaseModel{
    @NotNull(message = "编号不能为空")
    private Integer id;//编号

    @NotNull(message = "预约时间不能为空")
    private Integer appointmentDate;//预约时间

    @NotNull(message = "联系电话不能为空")
    @Pattern(regexp = "^1(3|4|5|7|8|9)\\d{9}$",message = "手机号码格式错误")
    private String mobile;//联系电话

    @NotNull(message = "类型不能为空")
    private Boolean type;//类型（1 维修，2 安装）

    private Boolean maintenanceType;//维修类型（1 宽带，2 电视，3 固话）

    private Boolean installState;//完成状态（1 未完成，2 完成，3 确认完成）

    private Integer userId;//安装维修人员及其联系电话

    private String maintenanceReason;//维修原因

    private String orderNumber;//订单编号 （ 关联安装地址，产品名称，产品信息 ）

    private String note;//备注

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Integer appointmentDate) {
        this.appointmentDate = appointmentDate;
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