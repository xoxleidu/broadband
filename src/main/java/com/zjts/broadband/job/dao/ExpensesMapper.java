package com.zjts.broadband.job.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zjts.broadband.job.model.Expenses;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpensesMapper extends BaseMapper<Expenses> {

    List<Expenses> findExpenses(Page<Expenses> page, Expenses expenses);
}