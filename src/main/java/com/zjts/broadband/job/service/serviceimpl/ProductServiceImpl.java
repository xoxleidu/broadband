package com.zjts.broadband.job.service.serviceimpl;


import com.baomidou.mybatisplus.plugins.Page;
import com.zjts.broadband.common.constant.CodeEnum;
import com.zjts.broadband.common.log.ServiceLog;
import com.zjts.broadband.common.model.APIResponse;
import com.zjts.broadband.common.model.req.job.product.*;
import com.zjts.broadband.job.dao.ProductMapper;
import com.zjts.broadband.job.model.Product;
import com.zjts.broadband.job.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;


@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    /*
     * 套餐添加
     * */

    @Override
    @ServiceLog(description = "添加套餐")
    public APIResponse addProduct(ReqProductAdd reqProductAdd) {
        BigDecimal totlePrice = BigDecimal.valueOf(0);
        Product product = new Product();
        BeanUtils.copyProperties(reqProductAdd, product);
        List<ReqExpensesUse> expensesUseList = reqProductAdd.getExpensesList();
        List<ReqEquipmentUse> equipmentUseList = reqProductAdd.getEquipmentList();
        List<ReqGiftUse> giftList = reqProductAdd.getGiftList();

        //计算套餐价格
        for (ReqExpensesUse r : expensesUseList) {
            totlePrice = totlePrice.add(r.getPrice());
        }
        for (ReqEquipmentUse r : equipmentUseList) {
            totlePrice = totlePrice.add(r.getPrice().multiply(new BigDecimal(r.getNumber())));//计算所有设备价格总和
        }
        product.setPrice(totlePrice);
        product.setStatus("0");
        //保存套餐基本信息
        Integer insert = productMapper.save(product);
        //获取主键
        Integer productId = product.getId();
        product.setId(productId);
        productMapper.saveProductDetails(product);
        return APIResponse.success(productId); //返回套餐id
    }

    /*
     * 修改套餐
     * */
    @Override
    @ServiceLog(description = "修改套餐")
    public APIResponse updateProduct(ReqProductAdd reqProductAdd) {
        BigDecimal totlePrice = BigDecimal.valueOf(0);
        Product product = new Product();
        BeanUtils.copyProperties(reqProductAdd, product);

        if(product.getStatus().equals("0")||product.getStatus().equals("1")){
            //计算套餐价格
            for (ReqExpensesUse r : reqProductAdd.getExpensesList()) {
                totlePrice = totlePrice.add(r.getPrice());
            }
            for (ReqEquipmentUse r : reqProductAdd.getEquipmentList()) {
                totlePrice = totlePrice.add(r.getPrice().multiply(new BigDecimal(r.getNumber())));//计算所有设备价格总和
            }
            product.setPrice(totlePrice);
            Integer update = productMapper.myUpdate(product);
            if (update != 1) {
                return APIResponse.error(CodeEnum.SAVE_ERROR, "修改套餐失败");
            }
            return APIResponse.success();
        }else {
            return APIResponse.error(CodeEnum.STATYS_ERROR);
        }


    }

    /*
     * 套餐详情查询
     * */
    @Override
    public APIResponse findProductById(Integer id) {
        List<Product> product = productMapper.findProductDetails(id);
        return APIResponse.success(product);
    }

    /*
     * 套餐基本情况
     * */

    @Override
    public APIResponse findProductBase(ReqProductQuery reqProductQuery) {
        Page<Product> page = new Page<>(reqProductQuery.getCurrentPage(), reqProductQuery.getPageSize());
        Product product = new Product();
        BeanUtils.copyProperties(reqProductQuery, product);
        List<Product> result = productMapper.findProductBase(page, product);
        return APIResponse.success(page.setRecords(result));
    }

}
