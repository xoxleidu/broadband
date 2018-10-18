package com.zjts.broadband.job.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zjts.broadband.common.model.req.job.product.ReqEquipmentUse;
import com.zjts.broadband.common.model.req.job.product.ReqExpensesUse;
import com.zjts.broadband.common.model.req.job.product.ReqGiftUse;
import com.zjts.broadband.job.model.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMapper extends BaseMapper<Product> {

    Integer save(Product product);

    Integer saveProductDetails(Product product);

    List<Product>findProductDetails(Integer id);

    Integer myUpdate(Product product);

    List<Product> findProductBase(Page<Product> page, Product product);

}