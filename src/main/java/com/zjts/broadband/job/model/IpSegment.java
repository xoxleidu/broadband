package com.zjts.broadband.job.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 小区ip段的速度管理表,编辑ip的上传速度与下载速度
 * </p>
 *
 * @author han zq
 * @since 2018-09-13
 */
@TableName("ip_segment")
public class IpSegment extends Model<IpSegment> {



    /**
     * ip号段的索引
     */
	@ApiModelProperty(name="id",example = "1")
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	/**
	 * ip段的名称
	 */
	@ApiModelProperty(name="ip名称",example = "安次区ip段",required=true)
	@TableField("ip_name")
	private String ipName;
	/**
	 * ip号段的起始地址
	 */
	@ApiModelProperty(name="ip起始地址",example = "192.168.0.1",required=true)
	@TableField("start_ip")
	private String startIp;
	/**
     * ip号段的结束地址
     */

	@ApiModelProperty(name="ip结束地址",example = "192.168.0.100",required=true)
	@TableField("end_ip")
	private String endIp;
	/**
     * 号段状态,0表示正常1表示被停用
     */
	@ApiModelProperty(name="ip结束地址",example = "true",required=true)
	private Integer status;


	public Integer getId() {
		return id;
	}

	public IpSegment setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getIpName() {
		return ipName;
	}

	public IpSegment setIpName(String ipName) {
		this.ipName = ipName;
		return this;
	}

	public String getStartIp() {
		return startIp;
	}

	public IpSegment setStartIp(String startIp) {
		this.startIp = startIp;
		return this;
	}

	public String getEndIp() {
		return endIp;
	}

	public IpSegment setEndIp(String endIp) {
		this.endIp = endIp;
		return this;
	}

	public Integer getStatus() {
		return status;
	}

	public IpSegment setStatus(Integer status) {
		this.status = status;
		return this;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "IpSegment{" +
				"id=" + id +
				", ipName='" + ipName + '\'' +
				", startIp='" + startIp + '\'' +
				", endIp='" + endIp + '\'' +
				", status=" + status +
				'}';
	}
}
