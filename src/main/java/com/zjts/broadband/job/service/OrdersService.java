package com.zjts.broadband.job.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.zjts.broadband.common.model.APIResponse;
import com.zjts.broadband.common.model.req.job.customer.ReqCustomerUpdate;
import com.zjts.broadband.common.model.req.job.orders.*;
import com.zjts.broadband.job.model.CustomerMessage;

import java.util.Map;

public interface OrdersService {
    APIResponse findByProductId(Integer id) throws Exception;
    Page<Map> findSelectAll(Page<Map> mapPage, ReqCustomerOrderQuery reqCustomerOrderQuery) throws Exception;
    APIResponse updateMoney(ReqUpdateMoney reqUpdateMoney) throws Exception;
    APIResponse orderInsert(ReqOrderAdd reqOrderAdd) throws Exception;
    public APIResponse updateOrderType(ReqUpdateOrderType reqUpdateOrderType) throws Exception;
    APIResponse orderQuery(Page page,ReqOrderQuery reqOrderQuery)throws Exception;
    APIResponse orderDetailedQuery(Page<ReqOrderDetailed> page,ReqOrderDetailed reqOrderDetailed)throws Exception;
    APIResponse updateDiscount(ReqOrderDiscount reqOrderDiscount)throws Exception;
  APIResponse ordersAll(Page page);
}
