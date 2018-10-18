package com.zjts.broadband.job.service;

import com.zjts.broadband.common.model.APIResponse;
import com.zjts.broadband.common.model.req.job.product.ReqEquipmentModelAdd;
import com.zjts.broadband.common.model.req.job.product.ReqEquipmentModelQuery;
import com.zjts.broadband.job.model.EquipmentModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EquipmentModelService {
    APIResponse add(ReqEquipmentModelAdd reqEquipmentModelAdd);

    APIResponse update(ReqEquipmentModelAdd reqEquipmentModelAdd);

    APIResponse findEquipmentModel(ReqEquipmentModelQuery query);

    APIResponse findEquipmentModelById(List<Integer> idList);

    List<EquipmentModel> findForExcel();
}
