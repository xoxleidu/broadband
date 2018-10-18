package com.zjts.broadband.common.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * 操作码(三位数为系统常用，四位数为单独定义)
 * @create 2017-11-17 下午16:00:00
 * @author 索洵
 */
public enum CodeEnum {

  SUCCESS ("000", "操作成功"),
  ERROR ("001", "通用错误"),
  NO_AUTH("002","未授权"),
  NO_LOGIN("003","未登录"),
  NO_BINDING("003","未绑定"),
  SAVE_ERROR("005","储存失败"),
  SAVE_EXISTENCE_ERROR("006","储存失败、信息重复！"),
  FIND_NULL_ERROR("007","查询失败、信息不存在！"),
  EXIT_ERROR("008","数据存在！"),
  REQ_VERIFICATION_ERROR("009","入参验证错误！"),
  VERIFICATION_CODE_NULL_ERROR("010","验证码失效！"),
  VERIFICATION_CODE_ERROR("011","验证码错误！"),
  LOGIN_ERROR("012","用户名或密码错误！"),
  SESSION_NULL_ERROR("013","会话过期,请重新登录。"),
  DELETE_ERROR("1001","删除失败"),
  USE_ERROR("1002","赠品不可用"),
  NUMBER_NOT_ENOUGH("1003","库存不足"),
  STATYS_ERROR("1004","状态异常")
  ;

  private final String code;
  private final String message;

  private CodeEnum(String _code, String _message){
    this.code = _code;
    this.message = _message;
  }

  public String getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  /**
   * 通过枚举code获取对应的message
   * @return 取不到时返回null
   * @create 2017-11-17 下午16:00:00
   * @author 索洵
   */
  public static String getMessageByCode(String code){
    for(CodeEnum _enum : values()){
      if(_enum.getCode() == code){
        return _enum.getMessage();
      }
    }
    return null;
  }

  /**
   * 通过枚举code获取枚举对象
   * @return 取不到时返回null
   * @create 2017-11-17 下午16:00:00
   * @author 索洵
   */
  public static CodeEnum getByCode(String code){
    for(CodeEnum _enum : values()){
      if(_enum.getCode() == code){
        return _enum;
      }
    }
    return null;
  }

  /**
   * 获取全部枚举
   * @return 取不到时返回空List,即new ArrayList<CodeEnum>()
   * @create 2017-11-17 下午16:00:00
   * @author 索洵
   */
  public List<CodeEnum> getAllEnum(){
    List<CodeEnum> list = new ArrayList<CodeEnum>();
    for(CodeEnum _enum : values()){
      list.add(_enum);
    }
    return list;
  }

  /**
   * 获取全部枚举code
   * @return 取不到时返回空List,即new ArrayList<Integer>()
   * @create 2017-11-17 下午16:00:00
   * @author 索洵
   */
  public List<String> getAllEnumCode(){
    List<String> list = new ArrayList<String>();
    for(CodeEnum _enum : values()){
      list.add(_enum.getCode());
    }
    return list;
  }


}