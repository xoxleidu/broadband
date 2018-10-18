package com.zjts.broadband.job.service;


import com.zjts.broadband.common.model.APIResponse;
import com.zjts.broadband.common.model.req.job.product.ReqPhoneNumberStockAdd;
import com.zjts.broadband.common.model.req.job.product.ReqPhoneNumberStockQuery;
import com.zjts.broadband.common.model.req.job.product.ReqProductAdd;
import com.zjts.broadband.job.model.PhoneNumberStock;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface PhoneNumberStockService {

    APIResponse addPhoneNumber(ReqPhoneNumberStockAdd reqPhoneNumberStockAdd);


    APIResponse updatePhoneNumber(ReqPhoneNumberStockAdd reqPhoneNumberStockAdd);

    APIResponse findPhoneNumber(ReqPhoneNumberStockQuery reqPhoneNumberStockQuery);

    List<PhoneNumberStock> findForExcel();
}
