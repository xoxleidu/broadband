package com.zjts.broadband.common.model.req.job.product;

import com.zjts.broadband.common.model.BaseModel;
import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ReqGiftUse implements Serializable {
    private static final long serialVersionUID = 1001943494952931101L;
   @Ignore
    private Integer id;

    private String name;
    @NotNull
    @Min(0)
    private Integer outNumber;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getOutNumber() {
        return outNumber;
    }

    public void setOutNumber(Integer outNumber) {
        this.outNumber = outNumber;
    }
}
