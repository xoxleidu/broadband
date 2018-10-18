package com.zjts.broadband.job.service.serviceimpl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zjts.broadband.common.constant.CodeEnum;
import com.zjts.broadband.common.log.ServiceLog;
import com.zjts.broadband.common.model.APIResponse;
import com.zjts.broadband.common.model.req.job.product.ReqExpensesAdd;
import com.zjts.broadband.common.model.req.job.product.ReqExpensesQuery;
import com.zjts.broadband.job.dao.ExpensesMapper;
import com.zjts.broadband.job.model.Expenses;
import com.zjts.broadband.job.service.ExpensesService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ExpensesServiceImpl implements ExpensesService {

    @Autowired
    private ExpensesMapper expensesMapper;

    @Override
    @ServiceLog(description = "添加资费")
    public APIResponse add(ReqExpensesAdd reqExpensesAdd) {
        Expenses expenses=new Expenses();
        BeanUtils.copyProperties(reqExpensesAdd, expenses);
        expenses.setStatus("0");
        Integer insert = expensesMapper.insert(expenses);
        if (insert != 1) {
            return APIResponse.error(CodeEnum.SAVE_ERROR);
        }
        return APIResponse.success();
    }

    /*
    * 修改资费
    * */
    @Override
    @ServiceLog(description = "修改资费")
    public APIResponse update(ReqExpensesAdd reqExpensesAdd) {
        Expenses expenses=new Expenses();
        BeanUtils.copyProperties(reqExpensesAdd, expenses);
        if(expenses.getStatus().equals("0")||expenses.getStatus().equals("1")){
            Integer update = expensesMapper.updateById(expenses);
            if (update != 1) {
                return APIResponse.error(CodeEnum.SAVE_ERROR);
            }
            return APIResponse.success();
        }else {
            return APIResponse.error(CodeEnum.STATYS_ERROR);
        }

    }

    /*
    * 查询资费
    * */
    @Override
    public APIResponse findExpenses(ReqExpensesQuery reqExpensesQuery) {
        Page<Expenses> page = new Page<Expenses>(reqExpensesQuery.getCurrentPage(), reqExpensesQuery.getPageSize());
        Expenses expenses=new Expenses();
        BeanUtils.copyProperties(reqExpensesQuery, expenses);
        List<Expenses> list = expensesMapper.findExpenses(page, expenses);
        if (list.isEmpty()) {
            return APIResponse.error(CodeEnum.FIND_NULL_ERROR);
        }
        return APIResponse.success(page.setRecords(list));
    }

}
