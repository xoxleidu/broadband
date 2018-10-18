package com.zjts.broadband.job.service.serviceimpl;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zjts.broadband.common.log.ControllerLog;
import com.zjts.broadband.common.log.ServiceLog;
import com.zjts.broadband.job.dao.IpSegmentMapper;
import com.zjts.broadband.job.model.IpSegment;
import com.zjts.broadband.job.service.IpSegmentService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 小区ip段的速度管理表,编辑ip的上传速度与下载速度 服务实现类
 * </p>
 *
 * @author han zq
 * @since 2018-09-13
 */
@Service
public class IpSegmentServiceImpl extends ServiceImpl<IpSegmentMapper, IpSegment> implements IpSegmentService {

    @Autowired
    private IpSegmentMapper ipMapper;

    org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());


    @Transactional
    @Override
    public Integer addIpSegment(IpSegment ipSegment) throws  IllegalAccessException {
        ipSegment.setStatus(0);
        return ipMapper.insert(ipSegment);

    }





    @Transactional
    @Override
    public Integer updateIpSegmentDataById(IpSegment ipSegment)  {


        return ipMapper.updateAllColumnById(ipSegment);
    }

    @Override
    public List<IpSegment> findAllIpSegmentByPage(Integer pageCurrent, Integer pageSize )  {

        EntityWrapper<IpSegment> wrapper = new EntityWrapper<IpSegment>();
        List list = ipMapper.selectPage(new Page<IpSegment>(pageCurrent,pageSize),wrapper);
        return list;
    }

    @Override
    public Boolean forbidIpSegmentById(Integer id)  {
        IpSegment ipSegment = new IpSegment();
        ipSegment.setId(id);
        ipSegment.setStatus(1);
        int a =   ipMapper.updateById(ipSegment);
        if(a == 0)
            throw new RuntimeException("停用ip号段失败!");
        return true;
    }

    @Override
    public List<IpSegment> findIpSegmentByIpName(String ipName) {
        List<IpSegment> list = ipMapper.selectList( new EntityWrapper<IpSegment>().like("ip_name",ipName, SqlLike.RIGHT));
        return list;
    }
}
