package com.zjts.broadband.job.service.serviceimpl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zjts.broadband.common.constant.CodeEnum;
import com.zjts.broadband.common.model.APIResponse;
import com.zjts.broadband.common.model.req.job.customer.ReqCustomerUpdate;
import com.zjts.broadband.common.model.req.job.orders.*;
;
import com.zjts.broadband.job.dao.EquipmentMapper;
import com.zjts.broadband.job.dao.OrdersMapper;
import com.zjts.broadband.job.model.*;

import com.zjts.broadband.job.service.OrdersService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersMapper ordersMapper;

    /**
     * 根据用户ID查询产品类型
     *
     * @param: 接收一个 Integer类型的对象
     * @return: 返回到APIResponse包装类进行校验
     * @throws: Exception
     */
    @Override
    public APIResponse findByProductId(Integer id) throws Exception {
        List<OrdersAll> list = new ArrayList<OrdersAll>();
        try {
            list = ordersMapper.selectByProductId(id);
        } catch (Exception e) {
            return APIResponse.error(CodeEnum.FIND_NULL_ERROR);
        }
        return APIResponse.success(list);
    }

    /**
     * 根据用户ID和产品类型查询订单
     *
     * @param: 接收一个 Page<Map> 类型的对象 和 ReqOrderMiddleQuery类型的对象
     * @return: 返回到 Page<Map> 类型的的对象
     * @throws: Exception
     */
    @Override
    public Page<Map> findSelectAll(Page<Map> mapPage, ReqCustomerOrderQuery reqCustomerOrderQuery) throws Exception {
        OrdersAll ordersAll = new OrdersAll();
        BeanUtils.copyProperties(reqCustomerOrderQuery, ordersAll);
    mapPage.setRecords(ordersMapper.selectOrderAll(mapPage, ordersAll));

        return mapPage;
    }

    /**
     * 根据用户ID和订单ID进行用户充值
     *
     * @param: ReqOrderMiddleQuery类型的对象
     * @return: 返回到 APIResponse 类型的的对象
     * @throws: Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public APIResponse updateMoney(ReqUpdateMoney reqUpdateMoney) throws Exception {
        OrderUpdate orderUpdate = new OrderUpdate();
        BeanUtils.copyProperties(reqUpdateMoney, orderUpdate);
        int money=0;
        money=  ordersMapper.selectOrderProduct(orderUpdate);
         money=  money+reqUpdateMoney.getMoney();
          orderUpdate.setMoney(money);
       int update=   ordersMapper.updateOrderProduct(orderUpdate);
        if (update != 1) {
            return APIResponse.error(CodeEnum.SAVE_ERROR);
       }

        return APIResponse.success();
    }


    /**
     * 根据用户ID和产品ID产生订单
     *
     * @param: ReqOrderAdd类型的对象
     * @return: 返回到 APIResponse 类型的的对象
     * @throws: Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public APIResponse orderInsert(ReqOrderAdd reqOrderAdd) throws Exception {
        OrderInsert orderInsert = new OrderInsert();
        Orders orders = new Orders();
        List list = new ArrayList<>();
        int insert;
        BeanUtils.copyProperties(reqOrderAdd, orderInsert);
        BeanUtils.copyProperties(reqOrderAdd, orders);
        //订单号
        orders.setOrderNumber(GetRandomString(20));
        //订单创建时间
        int time = Math.toIntExact(new Date().getTime() / 1000);
        String result = new SimpleDateFormat("yyyyMMdd").format(new Date(time * 1000L));
        //int time=Math.toIntExact(new Date().getTime() / 1000);

        //其他的添加
        List<ReqOrderProduct> lists = reqOrderAdd.getProduct();

        for (ReqOrderProduct OrderProduct : lists
                ) {
            orderInsert.setProductType(Integer.valueOf(OrderProduct.getProductType()));
            orderInsert.setProductId(Integer .valueOf(OrderProduct.getProductId()));
            orderInsert.setDiscountMoney(OrderProduct.getDiscountMoney());

            if (orderInsert.getProductType() == 2) {
                OrderInsert or = new OrderInsert();
                or = ordersMapper.selectExpensesCycle(orderInsert.getProductId());
                if (or.getCycleType() == 1) {

                    orders.setExpiryTime(Integer.parseInt(dateUpdate(result, 0, 0, or.getCycle())));
                }
                if (or.getCycleType() == 2) {
                    orders.setExpiryTime(Integer.parseInt(dateUpdate(result, 0, or.getCycle(), 0)));
                }
                if (or.getCycleType() == 3) {
                    orders.setExpiryTime(Integer.parseInt(dateUpdate(result, or.getCycle(), 0, 0)));
                }
            }
            orderInsert.setOrderNumber(orders.getOrderNumber());
            if (orderInsert.getProductId()==3 || orderInsert.getProductId() == 4) {
                for (int i = 0; i < OrderProduct.getNum(); i++) {
                    insert = ordersMapper.insertOrder(orderInsert);
                    if (insert != 1) {
                        return APIResponse.error(CodeEnum.SAVE_ERROR);
                    }
                }
            }else {
                insert = ordersMapper.insertOrder(orderInsert);
                if (insert != 1) {
                    return APIResponse.error(CodeEnum.SAVE_ERROR);
                }
            }
        }
        orders.setCreateTime(time);
        int relust = ordersMapper.insert(orders);
        if (relust != 1) {
            return APIResponse.error(CodeEnum.SAVE_ERROR);
        }
        return APIResponse.success();
    }


    /**
     * 修改订单状态
     *
     * @param: 接收一个ReqOrderAdd类型的对象
     * @return:返回到APIResponse包装类进行校验
     * @throws: Exception
     */
    @Override

    public APIResponse updateOrderType(ReqUpdateOrderType reqUpdateOrderType) throws Exception {
        Orders orders = new Orders();
        BeanUtils.copyProperties(reqUpdateOrderType, orders);
        orders.setOrderNumber(reqUpdateOrderType.getOrderNumber());
        Integer insert = ordersMapper.updateByType(orders);
        if (insert != 1) {
            return APIResponse.error(CodeEnum.SAVE_ERROR);
        }
        return APIResponse.success();
    }

    /**
     * 客户订单查询
     *
     * @param: 接收一个ReqOrderQuery类型的对象
     * @return:返回到APIResponse包装类进行校验
     * @throws: Exception
     */
    @Override
    public APIResponse orderQuery(Page page, ReqOrderQuery reqOrderQuery) throws Exception {
        OrdersQuery ordersQuery = new OrdersQuery();
        BeanUtils.copyProperties(reqOrderQuery, ordersQuery);
        List orderQueryList = ordersMapper.orderQuery(page, ordersQuery);
        if (orderQueryList.isEmpty()) {
            return APIResponse.error(CodeEnum.FIND_NULL_ERROR);
        }
        return APIResponse.success(page.setRecords(orderQueryList));
    }

    /**
     * 客户订单详细查询
     *
     * @param: 接收一个ReqOrderDetailed类型的对象
     * @return:返回到APIResponse包装类进行校验
     * @throws: Exception
     */
    @Override
    public APIResponse orderDetailedQuery(Page<ReqOrderDetailed> page,ReqOrderDetailed reqOrderDetailed) throws Exception {
        OrdersDetailed ordersDetailed = new OrdersDetailed();
        BeanUtils.copyProperties(reqOrderDetailed, ordersDetailed);
        List<OrdersDetailed> orderDetailedQuery =null;
        if(reqOrderDetailed.getOrdersNumber()==null){
            orderDetailedQuery = ordersMapper.orderDetailedQuery(ordersDetailed);
        }else {
            orderDetailedQuery = ordersMapper.orderDetailedQuery(ordersDetailed);
        }
        if (orderDetailedQuery.isEmpty()) {
            return APIResponse.error(CodeEnum.FIND_NULL_ERROR);
        }
        return APIResponse.success(orderDetailedQuery);
    }

    /**
     * 客户订单折扣
     *
     * @param: 接收一个ReqOrderDiscount类型的对象
     * @return:返回到APIResponse包装类进行校验
     * @throws: Exception
     */

    @Override
    @Transactional(rollbackFor = Exception.class)
    public APIResponse updateDiscount(ReqOrderDiscount reqOrderDiscount) throws Exception {
        OrderDiscount orderDiscount = new OrderDiscount();
        BeanUtils.copyProperties(reqOrderDiscount, orderDiscount);
        int cout = ordersMapper.updateDiscount(orderDiscount);
        if (cout != 1) {
            return APIResponse.error(CodeEnum.ERROR);
        }
        return APIResponse.success();
    }

    @Override
    public APIResponse ordersAll(Page page) {
        List<Orders>communityList =ordersMapper.selectPage(page,null);

        if (communityList.isEmpty()) {
            return APIResponse.error(CodeEnum.SAVE_ERROR);
        }
        return APIResponse.success(communityList);
    }

    /**
     * 生成随机的订单号
     */
    public static String GetRandomString(int Len) {
        String[] baseString = {"0", "1", "2", "3",
                "4", "5", "6", "7", "8", "9",
                "a", "b", "c", "d", "e",
                "f", "g", "h", "i", "j",
                "k", "l", "m", "n", "o",
                "p", "q", "r", "s", "t",
                "u", "v", "w", "x", "y",
                "z", "A", "B", "C", "D",
                "E", "F", "G", "H", "I",
                "J", "K", "L", "M", "N",
                "O", "P", "Q", "R", "S",
                "T", "U", "V", "W", "X", "Y", "Z"};
        Random random = new Random();
        int length = baseString.length;
        String randomString = "";
        for (int i = 0; i < length; i++) {
            randomString += baseString[random.nextInt(length)];
        }
        random = new Random(System.currentTimeMillis());
        String resultStr = "";
        for (int i = 0; i < Len; i++) {
            resultStr += randomString.charAt(random.nextInt(randomString.length() - 1));
        }
        return resultStr;
    }

    /**
     * 通过创建时间算出结束时间
     */
    public static String dateUpdate(String creatTime, int day, int month, int year) throws ParseException {
        SimpleDateFormat S = new SimpleDateFormat("yyyyMMdd");
        Date dt = S.parse(creatTime);
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
        rightNow.add(Calendar.YEAR, year);
        rightNow.add(Calendar.MONTH, month);
        rightNow.add(Calendar.DAY_OF_YEAR, day);
        Date dt1 = rightNow.getTime();
        String reStr = S.format(dt1);
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = simpleDateFormat.parse(reStr);
        long ts = (date.getTime()) / 1000L;
        res = String.valueOf(ts);

        return res;
    }
}
