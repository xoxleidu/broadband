package com.zjts.broadband.job.service.serviceimpl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zjts.broadband.common.constant.CodeEnum;
import com.zjts.broadband.common.log.ServiceLog;
import com.zjts.broadband.common.model.APIResponse;
import com.zjts.broadband.common.model.req.job.product.ReqGiftAdd;
import com.zjts.broadband.common.model.req.job.product.ReqGiftQuery;
import com.zjts.broadband.common.model.req.job.product.ReqGiftUpdate;
import com.zjts.broadband.common.model.req.job.product.ReqGiftUse;
import com.zjts.broadband.job.dao.GiftMapper;
import com.zjts.broadband.job.model.EquipmentModel;
import com.zjts.broadband.job.model.Gift;
import com.zjts.broadband.job.service.GiftService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class GiftServiceImpl implements GiftService {
    @Autowired
    private GiftMapper giftMapper;

    /*
     * 增加赠品分类
     * */
    @Override
    @ServiceLog(description = "添加赠品分类")
    public APIResponse add(ReqGiftAdd reqGiftAdd) {
        Gift gift = new Gift();
        BeanUtils.copyProperties(reqGiftAdd, gift);
        Integer stock = gift.getStock();
        gift.setOutput(0);
        gift.setStatus("0");
        gift.setAmount(stock.intValue());//给总量赋值
        Integer insert = giftMapper.insert(gift);

        return APIResponse.success();
    }

    /*
     * 修改赠品
     * */
    @Override
    @ServiceLog(description = "修改赠品")
    public APIResponse update(ReqGiftUpdate reqGiftUpdate) {
        Gift gift = new Gift();
        BeanUtils.copyProperties(reqGiftUpdate, gift);
        gift.setAmount(gift.getOutput() + gift.getStock());
        if (gift.getStatus().equals("0")||gift.getStatus().equals("1")){
            Integer insert = giftMapper.updateById(gift);
            if (insert != 1) {
                return APIResponse.error(CodeEnum.SAVE_ERROR);
            }
            return APIResponse.success();
        }else {
            return APIResponse.error(CodeEnum.STATYS_ERROR);
        }

    }

    /*
     * 多条件查询
     * */
    @Override
    public APIResponse findGift(ReqGiftQuery reqGiftQuery) {
        Page<Gift> page = new Page<Gift>(reqGiftQuery.getCurrentPage(), reqGiftQuery.getPageSize());
        Gift gift = new Gift();
        BeanUtils.copyProperties(reqGiftQuery, gift);
        List<Gift> myItems = giftMapper.findGift(page,gift);
        if (myItems.isEmpty()) {
            return APIResponse.error(CodeEnum.FIND_NULL_ERROR);
        }
        return APIResponse.success(page.setRecords(myItems));
    }

    /*
     * 根据id多选查询赠品
     * */
    @Override
    public APIResponse findById(List<Integer> ids) {
        List<Gift> result =new ArrayList<>();
        for (Integer id : ids) {
            Gift gift=giftMapper.selectById(id);
            if (gift.equals(null)) {
                return APIResponse.error(CodeEnum.FIND_NULL_ERROR);
            }
            result.add(gift);
        }
        return APIResponse.success(result);
    }

    /*
     * 调用赠品
     * */
    @Override
    @ServiceLog(description = "调用赠品")
    public APIResponse useGift(List<ReqGiftUse> list) {
        Gift gift = new Gift();
        for (ReqGiftUse g : list) {
            Gift gift1 = giftMapper.selectById(g.getId());
            if (gift1.getStock()<g.getOutNumber()) {
                return APIResponse.error(CodeEnum.NUMBER_NOT_ENOUGH);
            } else {
                gift.setStock(gift1.getStock() - g.getOutNumber());
                gift.setOutput(gift.getOutput() + g.getOutNumber());
                gift.setAmount(gift.getStock() + gift.getOutput());
                Integer update = giftMapper.updateById(gift);
                if (update != 1) {
                    return APIResponse.error(CodeEnum.USE_ERROR);
                }
            }
        }
        return APIResponse.success();
    }

    /*
     * 导出Excel文件
     * */
    @Override
    public List<Gift> findAllGift2() {
        List<Gift> giftList = giftMapper.selectList(new EntityWrapper<>());

        return giftList;
    }
}
