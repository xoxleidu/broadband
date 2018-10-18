package com.zjts.broadband.job.service;


import com.zjts.broadband.common.model.APIResponse;
import com.zjts.broadband.common.model.req.job.product.ReqProductAdd;
import com.zjts.broadband.common.model.req.job.product.ReqProductQuery;
import org.springframework.stereotype.Service;


@Service
public interface ProductService {

    APIResponse addProduct(ReqProductAdd reqProductAdd);

    APIResponse findProductById(Integer id);

    APIResponse updateProduct(ReqProductAdd reqProductAdd);

    APIResponse findProductBase(ReqProductQuery reqProductQuery);
}
