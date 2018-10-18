package com.zjts.broadband.common.model;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.zjts.broadband.common.constant.CodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

@ApiModel(value = "返回值", description = "统一返回值包装类")
@JsonInclude(JsonInclude.Include.NON_NULL) // 不输出NULL字段
public class APIResponse<T> implements Serializable  {

	private String code = CodeEnum.SUCCESS.getCode();

	@ApiModelProperty(value = "应答码描述")
	private String message = CodeEnum.SUCCESS.getMessage();

	@ApiModelProperty(value = "应答数据体")
	private T result;

	@ApiModelProperty(value = "状态")
	private boolean ret;

	public APIResponse(String code, String message, T t, boolean ret) {
		this.code = code;
		this.message = message;
		this.result = t;
		this.ret=ret;
	}

	/**
	 * 返回成功，返回类型为服务器类型
	 */
	public static <T> APIResponse<T> success(T t) {
		return new APIResponse(CodeEnum.SUCCESS.getCode(), CodeEnum.SUCCESS.getMessage(), t,true);
	}

	/**
	 * 返回成功，返回类型为服务器类型
	 */
	public static <T> APIResponse<T> success() {
		return new APIResponse(CodeEnum.SUCCESS.getCode(), CodeEnum.SUCCESS.getMessage(),
				null,true);
	}

	/**
	 * 返回失败，自定义
	 */
	public static <T> APIResponse<T> error(String code, String message, T t) {
		return new APIResponse(code, message, t,false);
	}

	/**
	 * 返回失败，自定义
	 */
	public static <T> APIResponse<T> error(CodeEnum codeEnum, T t) {
		return new APIResponse(codeEnum.getCode(), codeEnum.getMessage(), t,false);
	}

	/**
	 * 返回失败，自定义
	 */
	public static <T> APIResponse<T> error(CodeEnum codeEnum,String msg) {
		return new APIResponse(codeEnum.getCode(), msg, null,false);
	}

	/**
	 * 返回失败，自定义
	 */
	public static <T> APIResponse<T> error(CodeEnum codeEnum,String msg, T t) {
		return new APIResponse(codeEnum.getCode(), msg, t,false);
	}

	/**
	 * 返回失败，自定义
	 */
	public static <T> APIResponse<T> errorInfo(CodeEnum codeEnum,String msg) {
		return new APIResponse(codeEnum.getCode(), codeEnum.getMessage()+"["+msg+"]", null,false);
	}

	/**
	 * 返回失败，自定义
	 */
	public static <T> APIResponse<T> error(CodeEnum codeEnum) {
		return new APIResponse(codeEnum.getCode(), codeEnum.getMessage(), null,false);
	}



	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public boolean isRet() {
		return ret;
	}

	public void setRet(boolean ret) {
		this.ret = ret;
	}
}
