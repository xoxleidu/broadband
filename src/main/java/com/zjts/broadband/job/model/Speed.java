package com.zjts.broadband.job.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author han zq
 * @since 2018-09-13
 */
@TableName("ip_speed")
public class Speed extends Model<Speed> {



	@ApiModelProperty(name="id",example = "1")
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;

	@NotNull(message = "所属号段id不能为空")
	@ApiModelProperty(name="该限速所对应的ip号段",example = "1",required=true)
	@TableField("ip_id")
	private Integer ipId;
    /**
     * 上行速度
     */
    @NotNull(message = "上行限速不能为空")
	@ApiModelProperty(name="最大上行速度",example = "1024",required=true)
	@TableField("max_upload_speed")
	private Integer maxUploadSpeed;
    /**
     * 下行速度
     */
    @NotNull(message = "下行限速不能为空")
	@ApiModelProperty(name="最大下载速度",example = "10240",required=true)
	@TableField("max_download_speed")
	private Integer maxDownloadSpeed;

	@Override
	public String toString() {
		return "Speed{" +
				"id=" + id +
				", ipId=" + ipId +
				", maxUploadSpeed=" + maxUploadSpeed +
				", maxDownloadSpeed=" + maxDownloadSpeed +
				'}';
	}

	public Integer getId() {
		return id;
	}

	public Speed setId(Integer id) {
		this.id = id;
		return this;
	}

	public Integer getIpId() {
		return ipId;
	}

	public Speed setIpId(Integer ipId) {
		this.ipId = ipId;
		return this;
	}

	public Integer getMaxUploadSpeed() {
		return maxUploadSpeed;
	}

	public Speed setMaxUploadSpeed(Integer maxUploadSpeed) {
		this.maxUploadSpeed = maxUploadSpeed;
		return this;
	}

	public Integer getMaxDownloadSpeed() {
		return maxDownloadSpeed;
	}

	public Speed setMaxDownloadSpeed(Integer maxDownloadSpeed) {
		this.maxDownloadSpeed = maxDownloadSpeed;
		return this;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
