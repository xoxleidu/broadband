package com.zjts.broadband.common.model.req.job.ip;

import com.zjts.broadband.common.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 
 * </p>
 *
 * @author han zq
 * @since 2018-09-13
 */
public class ReqSpeedAdd extends BaseModel {



	@ApiModelProperty(name="id",example = "1")
	private Integer id;

	@NotNull(message = "所属号段id不能为空")
	@ApiModelProperty(name="该限速所对应的ip号段",example = "1",required=true)
	private Integer ipId;
    /**
     * 上行速度
     */
    @NotNull(message = "上行限速不能为空")
	@ApiModelProperty(name="最大上行速度",example = "1024",required=true)
	private Integer maxUploadSpeed;
    /**
     * 下行速度
     */
    @NotNull(message = "下行限速不能为空")
	@ApiModelProperty(name="最大下载速度",example = "10240",required=true)
	private Integer maxDownloadSpeed;



	public Integer getId() {
		return id;
	}

	public ReqSpeedAdd setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getIpId() {
		return ipId;
	}

	public ReqSpeedAdd setIpId(Integer ipId) {
		this.ipId = ipId;
		return this;
	}

	public Integer getMaxUploadSpeed() {
		return maxUploadSpeed;
	}

	public ReqSpeedAdd setMaxUploadSpeed(Integer maxUploadSpeed) {
		this.maxUploadSpeed = maxUploadSpeed;
		return this;
	}

	public Integer getMaxDownloadSpeed() {
		return maxDownloadSpeed;
	}

	public ReqSpeedAdd setMaxDownloadSpeed(Integer maxDownloadSpeed) {
		this.maxDownloadSpeed = maxDownloadSpeed;
		return this;
	}



}
