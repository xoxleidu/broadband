package com.zjts.broadband.common.model.req.job.customer;

import com.zjts.broadband.common.model.BaseModel;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

/**
 * @ClassNameReqAddCustomer
 * @Description TODO
 * @Authoradmin
 * @Description:前端传输进行添加客户类
 * @Date2018/9/1716:01
 * @Version1.0
 **/

public class ReqCustomerAdd {

    @NotNull(message = "客户名不能为空")
    @Length(min = 1, max = 16, message = "客户名称不能超过16位")
    private String customerName;//客户姓名
    @Min(value=0,message = "最小不能小于0")
    @Max(value=1,message = "最大不能大于1")
    @NotNull(message = "性别不能为空")
    private Integer sex;//客户性别

    @NotNull(message = "证件号码不能为空")
    @Pattern(regexp = "(^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}$)",message = "身份证号码或者位数错误")
    private String idcard;//证件号码
    
    private String tel;//家庭电话

    @NotNull(message = "手机号码不能为空")
    @Pattern(regexp = "^1(3|4|5|7|8|9)\\d{9}$",message = "手机号码格式错误")
    private String mobile;//联系电话

    @NotNull(message = "证件地址不能为空")
    @Length(min = 1, max = 50, message = "证件地址不能超过50位长度")
    private String address;//证件地址

    @NotNull(message = "联系人不能为空")
    @Length(min = 1, max = 10, message = "联系人不能超过10位长度")
    private String contacts;//联系人

    @NotNull(message = "联系电话不能为空")
    private String contactMobile;//联系人电话

    @Min(value=0,message = "最小不能小于0")
    @Max(value=1,message = "最大不能大于1")
    @NotNull(message = "客户类型不能为空0.客户,1.企业")
    private Integer type;//客户类型

    public String getName() {
        return customerName;
    }

    public void setName(String name) {
        this.customerName = name == null ? null : name.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts == null ? null : contacts.trim();
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile == null ? null : contactMobile.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}
