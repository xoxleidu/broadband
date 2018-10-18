package com.zjts.broadband.common.model.req.job.ip;

import com.zjts.broadband.common.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * <p>
 * 小区ip段的速度管理表,编辑ip的上传速度与下载速度
 * </p>
 *
 * @author han zq
 * @since 2018-09-13
 */

public class ReqIpSegmentAdd extends BaseModel {



    /**
     * ip号段的索引
     */
	@ApiModelProperty(name="id",example = "1")
	private Integer id;
	/**
	 * ip段的名称
	 */
	@NotNull(message = "ip名称不能为空!")
	@ApiModelProperty(name="ip名称",example = "安次区ip段",required=true)
	private String ipName;
	/**
	 * ip号段的起始地址
	 */
	@Pattern(regexp = "(?=(\\b|\\D))(((\\d{1,2})|(1\\d{1,2})|(2[0-4]\\d)|(25[0-5]))\\.){3}((\\d{1,2})|(1\\d{1,2})|(2[0-4]\\d)|(25[0-5]))(?=(\\b|\\D))",message = "ip起始号段参数不合法")
	@NotNull(message = "起始地址不能为空!")
	@ApiModelProperty(name="ip起始地址",example = "192.168.0.1",required=true)
	private String startIp;
	/**
     * ip号段的结束地址
     */
	@Pattern(regexp = "(?=(\\b|\\D))(((\\d{1,2})|(1\\d{1,2})|(2[0-4]\\d)|(25[0-5]))\\.){3}((\\d{1,2})|(1\\d{1,2})|(2[0-4]\\d)|(25[0-5]))(?=(\\b|\\D))",message = "ip结束号段参数不合法")
	@NotNull(message = "结束地址不能为空")
	@ApiModelProperty(name="ip结束地址",example = "192.168.0.100",required=true)
	private String endIp;
	/**
     * 号段状态,0表示正常1表示被停用
     */
	@ApiModelProperty(name="ip结束地址",example = "0",required=true)
	private Integer status;


	public Integer getId() {
		return id;
	}

	public ReqIpSegmentAdd setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getIpName() {
		return ipName;
	}

	public ReqIpSegmentAdd setIpName(String ipName) {
		this.ipName = ipName;
		return this;
	}

	public String getStartIp() {
		return startIp;
	}

	public ReqIpSegmentAdd setStartIp(String startIp) {
		this.startIp = startIp;
		return this;
	}

	public String getEndIp() {
		return endIp;
	}

	public ReqIpSegmentAdd setEndIp(String endIp) {
		this.endIp = endIp;
		return this;
	}

	public Integer getStatus() {
		return status;
	}

	public ReqIpSegmentAdd setStatus(Integer status) {
		this.status = status;
		return this;
	}

}
