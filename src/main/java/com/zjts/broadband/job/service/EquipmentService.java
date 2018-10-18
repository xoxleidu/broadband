package com.zjts.broadband.job.service;

import com.zjts.broadband.common.model.APIResponse;
import com.zjts.broadband.common.model.req.job.product.ReqEquipmentAdd;
import com.zjts.broadband.common.model.req.job.product.ReqEquipmentQuery;
import com.zjts.broadband.common.model.req.job.product.ReqEquipmentUse;
import com.zjts.broadband.job.model.Equipment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EquipmentService {
    APIResponse add(ReqEquipmentAdd reqEquipmentAdd);

    APIResponse update(ReqEquipmentAdd reqEquipmentQuery);

    List<Equipment> findAllGift2();

    APIResponse findEquipment(ReqEquipmentQuery reqEquipmentQuery);
    /*
     * 设备调用测试
     * */
    APIResponse useEquipment(List<ReqEquipmentUse> list);

}
