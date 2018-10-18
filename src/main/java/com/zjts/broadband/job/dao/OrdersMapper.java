package com.zjts.broadband.job.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zjts.broadband.job.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface OrdersMapper extends BaseMapper<Orders> {
    List<OrdersAll> selectByProductId(Integer id);

    List<Map> selectOrderAll(Page<Map> mapPage, OrdersAll orderQuery);

    int updateOrderProduct(OrderUpdate orderUpdate);
    int insertOrder(OrderInsert orderInsert);
    OrderInsert selectExpensesCycle(int id);

    List orderQuery(Page page,OrdersQuery ordersQuery);
    List orderDetailedQuery(OrdersDetailed ordersDetailed);

    int updateDiscount(OrderDiscount orderDiscount);
    int selectOrderProduct(OrderUpdate orderUpdate);
    int updateByType(Orders orders);

}