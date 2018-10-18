package com.zjts.broadband.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 校验工具类
 * 
 * @author guofan
 *
 */
public class ValidUtils {

  private static final Logger logger = LoggerFactory.getLogger(ValidUtils.class);

  /**
   * 校验邮箱格式
   */
  public static boolean checkEmail(String value) {
    boolean flag = false;
    Pattern p1 = null;
    Matcher m = null;
    p1 = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
    m = p1.matcher(value);
    flag = m.matches();
    return flag;
  }

  /**
   * 手机号验证
   * 
   * @param str
   * @return 验证通过返回true
   */
  public static boolean isMobile(final String str) {
    Pattern p = null;
    Matcher m = null;
    boolean b = false;
    p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
    m = p.matcher(str);
    b = m.matches();
    return b;
  }

  /**
   * 电话号码验证
   * 
   * @param str
   * @return 验证通过返回true
   */
  public static boolean isPhone(final String str) {
    Pattern p1 = null, p2 = null;
    Matcher m = null;
    boolean b = false;
    p1 = Pattern.compile("^[0][1-9][0-9]{1,2}[0-9]{5,10}$"); // 验证带区号的
    p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$"); // 验证没有区号的
    if (str.length() > 9) {
      m = p1.matcher(str);
      b = m.matches();
    } else {
      m = p2.matcher(str);
      b = m.matches();
    }
    return b;
  }

  /**
   * SpringBoot校验方法(输出所有错误的参数)
   * @param result
   * @return
   */
  public static String getBindErrorMessage(BindingResult result) {
	  String message = "";
	  if(result.hasErrors()){
		  for(ObjectError error : result.getAllErrors()){
				message = message.concat(error.getDefaultMessage()).concat(",");
			}
	  }
	  // 去掉最后一个逗号
	  if(StringUtils.isNotBlank(message)){
		  message = message.substring(0, message.length() - 1);
	  }
	  return message;
  }
  
  /**
   * SpringBoot校验方法(只输出第一个匹配的参数)
   * @param result
   * @return
   */
  public static String getBindErrorMessageOne(BindingResult result) {
	  if(result.hasErrors()){
		  for(ObjectError error : result.getAllErrors()){
			  return error.getDefaultMessage();
			}
	  }
	  return "";
  }
  
  
}
