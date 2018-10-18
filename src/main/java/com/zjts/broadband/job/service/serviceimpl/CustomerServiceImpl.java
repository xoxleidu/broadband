package com.zjts.broadband.job.service.serviceimpl;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zjts.broadband.common.constant.CodeEnum;
import com.zjts.broadband.common.model.APIResponse;
import com.zjts.broadband.common.model.req.job.customer.ReqCustomerAdd;
import com.zjts.broadband.common.model.req.job.customer.ReqCustomerDelete;
import com.zjts.broadband.common.model.req.job.customer.ReqCustomerQuery;
import com.zjts.broadband.common.model.req.job.customer.ReqCustomerUpdate;
import com.zjts.broadband.job.dao.CustomerMapper;
import com.zjts.broadband.job.model.CustomerMessage;
import com.zjts.broadband.job.service.CustomerService;
import com.zjts.broadband.system.model.SysUser;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;
    /**
     * 添加客户
     * @param: 接收一个ReqCustomerAdd类型的对象
     * @return: 返回到APIResponse包装类进行校验
     * @throws: Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class )
    public APIResponse add(ReqCustomerAdd reqCustomerAdd) throws Exception {
        CustomerMessage customerMessage = new CustomerMessage();
        BeanUtils.copyProperties(reqCustomerAdd, customerMessage);
         Integer time = Math.toIntExact(new Date().getTime() / 1000);
        customerMessage.setCreationTime(time);
        customerMessage.setStatus(0);
        Integer count=  customerMapper.selectCount(new EntityWrapper<CustomerMessage>().eq("idcard",customerMessage.getIdcard()));
        if (count>0   ) {
            return APIResponse.error(CodeEnum.SAVE_ERROR);
        }else{
            Integer insert = customerMapper.insert(customerMessage);
            if (insert != 1 ) {
                return APIResponse.error(CodeEnum.SAVE_ERROR);
            }
            return APIResponse.success();
        }

    }
    /**
     * 修改客户信息
     * @param: 接收一个ReqCustomerUpdate类型的对象
     * @return:返回到APIResponse包装类进行校验
     * @throws: Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class )
    public APIResponse update(ReqCustomerUpdate reqCustomerUpdate) throws Exception {
        CustomerMessage customerMessage = new CustomerMessage();
        BeanUtils.copyProperties(reqCustomerUpdate, customerMessage);
        Integer insert = customerMapper.updateById(customerMessage);
        if (insert != 1) {
            return APIResponse.error(CodeEnum.SAVE_ERROR);
        }
        return APIResponse.success();
    }
    /**
     * 逻辑删除客户信息
     * @param: 接收一个ReqCustomerDelete类型的对象
     * @return:返回到APIResponse包装类进行校验
     * @throws:Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class )
    public APIResponse delete(ReqCustomerDelete reqCustomerDelete) throws Exception {
        CustomerMessage customerMessage = new CustomerMessage();
        customerMessage.setStatus(1);
        BeanUtils.copyProperties(reqCustomerDelete, customerMessage);
        int delete = customerMapper.updateById(customerMessage);
        if (delete != 1) {
            return APIResponse.error(CodeEnum.SAVE_ERROR);
        }
        return APIResponse.success();
    }
    /**
     * 综合查询客户信息
     * @param: 接收一个ReqCustomerQuery类型的对象
     * @return: 返回到APIResponse包装类进行校验
     * @throws: Exception
     */
    @Override
    public APIResponse query(Page<CustomerMessage> page,ReqCustomerQuery reqCustomerQuery)throws Exception{
       CustomerMessage  customerMessage = new CustomerMessage();
        BeanUtils.copyProperties(reqCustomerQuery, customerMessage);
        List<CustomerMessage> customerList=  customerMapper.query(page,customerMessage);
        if (customerList.isEmpty()) {
            return APIResponse.error(CodeEnum.FIND_NULL_ERROR);
        }
        return APIResponse.success(page.setRecords(customerList));
    }
}
