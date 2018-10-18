package com.zjts.broadband.common.model.req.job.orders;

import com.zjts.broadband.common.model.BaseModel;
import com.zjts.broadband.job.model.OrderInsert;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Map;

public class ReqOrderAdd extends BaseModel {


    //产品的list集合
    private List<ReqOrderProduct> product;

    //固话号码
    private String telephone;
    //用户ID
    @NotNull(message = "用户ID不能为空")
    private Integer customerId;
    //安装时间
    @NotNull(message = "安装时间不能为空")
    private Integer installDate;
    // 安装地址
    @NotNull(message = "安装地址不能为空")
    @Length(min = 1, max = 50, message = "安装地址长度不能超过50位")
    private String installAddress;
    //余额
    @NotNull(message = "余额不能为空")
    @Min(value=0,message = "最小余额不能小于0")
    private int money;


    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getInstallDate() {
        return installDate;
    }

    public void setInstallDate(Integer installDate) {
        this.installDate = installDate;
    }

    public String getInstallAddress() {
        return installAddress;
    }

    public void setInstallAddress(String installAddress) {
        this.installAddress = installAddress;
    }

    public List<ReqOrderProduct> getProduct() {
        return product;
    }

    public void setProduct(List<ReqOrderProduct> product) {
        this.product = product;
    }
}
