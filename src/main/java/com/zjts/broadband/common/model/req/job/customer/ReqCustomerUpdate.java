package com.zjts.broadband.common.model.req.job.customer;

import com.zjts.broadband.common.model.BaseModel;
import com.zjts.broadband.util.pio.convert.ExportConfig;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @ClassNameReqUpdateCustomer
 * @Description TODO
 * @Authoradmin
 * @Description:前端传输客户进行逻辑删除类
 * @Date2018/9/1716:07
 * @Version1.0
 **/

public class ReqCustomerUpdate{
    @NotNull(message = "客户编号不能为空")
    @Min(value = 1 ,message = "客户编号不能小于1")
    private Integer id;//客户编号

    @NotNull(message = "客户名称不能为空")
    @Length(min = 1, max = 16, message = "客户名称不能超过16位")
    private String customerName;//客户名称

    @NotNull(message = "手机号码不能为空")
    @Pattern(regexp = "^1(3|4|5|7|8|9)\\d{9}$",message = "手机号码格式错误")
    private String mobile;//客户电话

    @NotNull(message = "联系人不能为空")
    @Length(min = 1, max = 10, message = "联系人名称不能超过10位")
    private String contacts;//联系人

    @NotNull(message = "联系电话不能为空")
    @Length(min = 11, max = 11, message = "联系人电话不能超过11位")
    private String contactMobile;//联系电话

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getContacts() { return contacts; }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
