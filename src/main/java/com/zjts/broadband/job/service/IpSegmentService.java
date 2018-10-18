package com.zjts.broadband.job.service;

import com.baomidou.mybatisplus.service.IService;
import com.zjts.broadband.job.model.IpSegment;

import java.util.List;

/**
 * <p>
 * 小区ip段的速度管理表,编辑ip的上传速度与下载速度 服务类
 * </p>
 *
 * @author han zq
 * @since 2018-09-13
 */
public interface IpSegmentService extends IService<IpSegment> {

    Integer addIpSegment(IpSegment ipSegment) throws IllegalAccessException;

    Integer updateIpSegmentDataById(IpSegment ipSegment) throws IllegalAccessException;

    List findIpSegmentByIpName(String ipName) ;

    List findAllIpSegmentByPage(Integer pageCurrent, Integer pageSize) ;

    Boolean forbidIpSegmentById(Integer id) ;

}
