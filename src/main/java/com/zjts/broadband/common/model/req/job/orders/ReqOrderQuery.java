package com.zjts.broadband.common.model.req.job.orders;

import com.zjts.broadband.common.model.BaseModel;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @ClassNameReqOrderQuery
 * @Description TODO
 * @Authoradmin
 * @Date2018/9/2813:49
 * @Version1.0
 **/

public class ReqOrderQuery extends BaseModel  {

    @Min(value = 1 ,message = "客户编号不能小于1")
    @NotNull(message = "不能为空")
    private Integer id;//客户id
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
}
