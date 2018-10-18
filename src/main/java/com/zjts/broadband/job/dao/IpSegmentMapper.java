package com.zjts.broadband.job.dao;

import com.zjts.broadband.job.model.IpSegment;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
  * 小区ip段的速度管理表,编辑ip的上传速度与下载速度 Mapper 接口
 * </p>
 *
 * @author han zq
 * @since 2018-09-13
 */
@Repository
public interface IpSegmentMapper extends BaseMapper<IpSegment> {

    List<IpSegment> findIpSegmentByIpName(String ipName);
}