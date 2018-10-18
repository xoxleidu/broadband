package com.zjts.broadband.util.sign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by yuanyong on 17/10/16.
 */
public class SignUtils {

    private static Logger logger = LoggerFactory.getLogger(SignUtils.class);

    public static String key = "rru8yxbjj3zr8uazjhaafgzq8oo04w0b";
    public static String input_charset = "UTF-8";


    /**
     * 生成参数数组
     *
     * @param sParaTemp 请求前的参数数组
     * @return 要请求的参数数组
     */
    @SuppressWarnings("unused")
	private static Map<String, String> buildRequestPara(Map<String, String> sParaTemp) {
        //除去数组中的空值和签名参数
        Map<String, String> sPara = AlipayCore.paraFilter(sParaTemp);
        //生成签名结果
        String mysign = buildRequestMysign(sPara);

        //签名结果与签名方式加入请求提交参数组中
        sPara.put("sign", mysign);
        sPara.put("sign_type", "MD5");

        return sPara;
    }

    /**
     * 生成签名结果
     *
     * @param sPara 要签名的数组
     * @return 签名结果字符串
     */
    public static String buildRequestMysign(Map<String, String> sPara) {
        String prestr = AlipayCore.createLinkString(sPara); //把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        logger.info(prestr);
        return MD5.sign(prestr, key, input_charset);
    }

    public static String buildRequestObjSign(Map<String, Object> sPara) {
        String prestr = AlipayCore.createLinkStringObj(sPara); //把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        String ret=MD5.sign(prestr, key, input_charset);
        logger.info(prestr);
        logger.info(ret);
        return ret;
    }
}
