package com.zjts.broadband.job.service.serviceimpl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zjts.broadband.common.constant.CodeEnum;
import com.zjts.broadband.common.log.ServiceLog;
import com.zjts.broadband.common.model.APIResponse;
import com.zjts.broadband.common.model.req.job.product.ReqEquipmentAdd;
import com.zjts.broadband.common.model.req.job.product.ReqEquipmentQuery;
import com.zjts.broadband.common.model.req.job.product.ReqEquipmentUse;
import com.zjts.broadband.job.dao.EquipmentMapper;
import com.zjts.broadband.job.model.Equipment;
import com.zjts.broadband.job.service.EquipmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private EquipmentMapper equipmentMapper;

    /*
     * 添加设备
     * */
    @Override
    @ServiceLog(description = "添加设备")
    public APIResponse add(ReqEquipmentAdd reqEquipmentAdd) {
        Equipment equipment = new Equipment();
        BeanUtils.copyProperties(reqEquipmentAdd, equipment);
        int current = (int) (System.currentTimeMillis() / 1000);
        equipment.setAddTime(current);
        equipment.setStatus("0");
        Integer insert = equipmentMapper.insert(equipment);
        if (insert != 1) {
            return APIResponse.error(CodeEnum.SAVE_ERROR);
        }
        return APIResponse.success();
    }

    /*
     * 修改单条记录
     * */
    @Override
    @ServiceLog(description = "修改设备")
    public APIResponse update(ReqEquipmentAdd reqEquipmentAdd) {
        Equipment equipment = new Equipment();
        BeanUtils.copyProperties(reqEquipmentAdd, equipment);

        if(equipment.getStatus().equals("0")||equipment.getStatus().equals("1")||equipment.getStatus().equals("2")){
            if (equipment.getStatus().equals("2")) {
                int current = (int) (System.currentTimeMillis() / 1000);
                equipment.setOutTime(current);
            }
            if (equipment.getStatus().equals("0")){
                equipment.setOutTime(0);
            }
            Integer delete = equipmentMapper.updateById(equipment) ;
            if (delete != 1) {
                return APIResponse.error(CodeEnum.DELETE_ERROR);
            }
            return APIResponse.success();
        }else {
            return APIResponse.error(CodeEnum.STATYS_ERROR);
        }

    }

    /*
     * 查询设备
     * */
    @Override
    public APIResponse findEquipment(ReqEquipmentQuery reqEquipmentQuery) {
        Page<Equipment> page = new Page<Equipment>(reqEquipmentQuery.getCurrentPage(), reqEquipmentQuery.getPageSize());
        Equipment equipment = new Equipment();
        BeanUtils.copyProperties(reqEquipmentQuery, equipment);
        List<Equipment> myItems = equipmentMapper.findEquipment(page, equipment);

        if (myItems.isEmpty()) {
            return APIResponse.error(CodeEnum.FIND_NULL_ERROR);
        }
        return APIResponse.success(page.setRecords(myItems));
    }

    /*
     * 设备调用
     * */
    @Override
    @ServiceLog(description = "设备调用")
    public APIResponse useEquipment(List<ReqEquipmentUse> list) {
        List<Equipment> resultList = new ArrayList<>();
        List<Equipment> list1=new ArrayList<>();

       //限制查询设备表中特定型号的一台设备，再用数量作次数循环
        for (ReqEquipmentUse r : list) {
            for (int i = 0; i < r.getNumber(); i++) {
                list1 = equipmentMapper.selectList(
                        new EntityWrapper<Equipment>().eq("model_id", r.getModelId())
                                .eq("status", "0")
                                .last("limit 1"));
                if (list1.isEmpty()) {
                    return APIResponse.error(CodeEnum.NUMBER_NOT_ENOUGH);
                } else {
                    list1.get(0).setStatus("1");
                    equipmentMapper.updateById(list1.get(0));
                    resultList.add(list1.get(0));
                    list1 = null;
                }
            }
        }
        return APIResponse.success(resultList);
    }

    /*
     * 导出Excel文件
     * */
    @Override
    public List<Equipment> findAllGift2() {
        List<Equipment> myItems = equipmentMapper.selectList(new EntityWrapper<Equipment>());
        return myItems;
    }

}
