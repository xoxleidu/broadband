package com.zjts.broadband.job.service.serviceimpl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zjts.broadband.common.constant.CodeEnum;
import com.zjts.broadband.common.model.APIResponse;
import com.zjts.broadband.common.model.req.job.product.*;
import com.zjts.broadband.job.dao.EquipmentMapper;
import com.zjts.broadband.job.dao.ExpensesMapper;
import com.zjts.broadband.job.dao.PhoneNumberStockMapper;
import com.zjts.broadband.job.dao.ProductMapper;
import com.zjts.broadband.job.model.Equipment;
import com.zjts.broadband.job.model.PhoneNumberStock;
import com.zjts.broadband.job.model.Product;
import com.zjts.broadband.job.service.PhoneNumberStockService;
import com.zjts.broadband.job.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class PhoneNumberStockServiceImpl implements PhoneNumberStockService {

    @Autowired
    private  PhoneNumberStockMapper phoneNumberStockMapper;

    @Override
    public APIResponse addPhoneNumber(ReqPhoneNumberStockAdd reqPhoneNumberStockAdd) {
        PhoneNumberStock phoneNumberStock=new PhoneNumberStock();
        BeanUtils.copyProperties(reqPhoneNumberStockAdd, phoneNumberStock);
        phoneNumberStock.setStatus("0");
        Integer insert=phoneNumberStockMapper.insert(phoneNumberStock);
        if (insert != 1) {
            return APIResponse.error(CodeEnum.SAVE_ERROR);
        }
        return APIResponse.success();
    }

    /*
    * 修改号码库（状态）
    * */
    @Override
    public APIResponse updatePhoneNumber(ReqPhoneNumberStockAdd reqPhoneNumberStockAdd) {
        PhoneNumberStock phoneNumberStock=new PhoneNumberStock();
        BeanUtils.copyProperties(reqPhoneNumberStockAdd, phoneNumberStock);
        if(phoneNumberStock.getStatus().equals("0")||phoneNumberStock.getStatus().equals("1")){
            Integer update = phoneNumberStockMapper.updateById(phoneNumberStock) ;
            if (update != 1) {
                return APIResponse.error(CodeEnum.DELETE_ERROR);
            }
            return APIResponse.success();
        }
        return APIResponse.error(CodeEnum.STATYS_ERROR);
    }

    /*
    * 查询号码
    * */
    @Override
    public APIResponse findPhoneNumber(ReqPhoneNumberStockQuery reqPhoneNumberStockQuery) {
        Page<PhoneNumberStock> page = new Page<PhoneNumberStock>(reqPhoneNumberStockQuery.getCurrentPage(), reqPhoneNumberStockQuery.getPageSize());
        PhoneNumberStock phoneNumberStock=new PhoneNumberStock();
        BeanUtils.copyProperties(reqPhoneNumberStockQuery, phoneNumberStock);
        List<PhoneNumberStock> list=phoneNumberStockMapper.findPhoneNumber(page,phoneNumberStock);
        if (list.isEmpty()) {
            return APIResponse.error(CodeEnum.FIND_NULL_ERROR);
        }
        return APIResponse.success(page.setRecords(list));

    }

    @Override
    public List<PhoneNumberStock> findForExcel() {
        List<PhoneNumberStock> list=phoneNumberStockMapper.selectList(new EntityWrapper<>());
        return list;
    }
}
