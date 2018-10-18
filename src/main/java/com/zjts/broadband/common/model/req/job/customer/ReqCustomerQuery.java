package com.zjts.broadband.common.model.req.job.customer;

import com.zjts.broadband.common.model.BaseModel;
import com.zjts.broadband.util.pio.convert.ExportConfig;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @Description:前端传输客户进行条件查询类
 */
public class ReqCustomerQuery   extends BaseModel{
    //定义的私有属性

    private String customerName;//客户姓名

    private String idcard;//证件号码

    private String mobile;//手机号码

    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
