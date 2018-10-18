package com.zjts.broadband.job.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.zjts.broadband.util.pio.convert.ExportConfig;

@TableName(value = "gift")
public class Gift  {

    @TableId
    @ExportConfig(value = "编号")  //导出Excel时，指定列名
    private Integer id;
    @ExportConfig(value = "名称")
    private String name;
    @ExportConfig(value = "总量")
    private Integer amount;
    @ExportConfig(value = "出库")
    private Integer output;
    @ExportConfig(value = "库存")
    private Integer stock;
    @ExportConfig(value = "状态")
    private String status;

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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getOutput() {
        return output;
    }

    public void setOutput(Integer output) {
        this.output = output;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

}