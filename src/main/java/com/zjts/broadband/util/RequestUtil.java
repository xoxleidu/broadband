package com.zjts.broadband.util;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestUtil {

    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        Pattern pattern = Pattern.compile("((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))");
        if(ip==null)return null;
        Matcher matcher = pattern.matcher(ip);
        while (matcher.find()) {
            ip = matcher.group();
            break;
        }
        return ip;
    }

    public static String getFullStackTraceToString(Exception exception) {
        StringWriter stringWriter = new StringWriter();
        exception.printStackTrace(new PrintWriter(stringWriter, true));
        return stringWriter.toString();
    }

//
//    public static void main(String[] args) {
//        String str = "116.90.82.118,00:00:00:00:00:01";
//        str = "116.90.82.118";
//        str = "116.90.82.118,116.90.82.119";
//
//
//        //  str = "116.90.82.118";
//        Pattern pattern = Pattern.compile("(\\d+[\\.]){3}\\d+");
//        Matcher matcher = pattern.matcher(str);
//
//        System.out.println(matcher.group());
//        System.out.println(matcher.group(0));
//        System.out.println(matcher.group(1));
//    }

    public static void response(HttpServletResponse response, String content) {
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.println(content);
        } catch (IOException e) {
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }


    /**
     * 请求转JSON
     * @param request
     * @return
     */
    public static JSONObject convertToJSON(HttpServletRequest request) {
        try {
            return (JSONObject) JSONObject.parse(getRequestJsonString(request));
        }catch (Exception ex){
        }
        return null;
    }
    /***
     * 获取 request 中 json 字符串的内容
     *
     * @param request
     * @return : <code>byte[]</code>
     * @throws IOException
     */
    public static String getRequestJsonString(HttpServletRequest request)
            throws IOException {
        String submitMehtod = request.getMethod();
        // GET
        if (submitMehtod.equals("GET")) {
            return new String(request.getQueryString().getBytes("iso-8859-1"),"utf-8").replaceAll("%22", "\"");
            // POST
        } else {
            return getRequestPostStr(request);
        }
    }

    /**
     * 描述:获取 post 请求的 byte[] 数组
     * <pre>
     * 举例：
     * </pre>
     * @param request
     * @return
     * @throws IOException
     */
    public static byte[] getRequestPostBytes(HttpServletRequest request)
            throws IOException {
        int contentLength = request.getContentLength();
        if(contentLength<0){
            return null;
        }
        byte buffer[] = new byte[contentLength];
        for (int i = 0; i < contentLength;) {

            int readlen = request.getInputStream().read(buffer, i,
                    contentLength - i);
            if (readlen == -1) {
                break;
            }
            i += readlen;
        }
        return buffer;
    }

    /**
     * 描述:获取 post 请求内容
     * <pre>
     * 举例：
     * </pre>
     * @param request
     * @return
     * @throws IOException
     */
    public static String getRequestPostStr(HttpServletRequest request)
            throws IOException {
        byte buffer[] = getRequestPostBytes(request);
        String charEncoding = request.getCharacterEncoding();
        if (charEncoding == null) {
            charEncoding = "UTF-8";
        }
        return new String(buffer, charEncoding);
    }

    public static byte[] getInputStreamytes(InputStream inputStream)
            throws IOException {
        int contentLength = inputStream.available();
        if(contentLength<0){
            return null;
        }
        byte buffer[] = new byte[contentLength];
        for (int i = 0; i < contentLength;) {

            int readlen = inputStream.read(buffer, i,
                    contentLength - i);
            if (readlen == -1) {
                break;
            }
            i += readlen;
        }
        return buffer;
    }

    public static String getInputStreamString(InputStream inputStream)
            throws IOException {
        byte buffer[] = getInputStreamytes(inputStream);
        return new String(buffer,  "UTF-8");
    }

    public static JSONObject convertToJSON(InputStream inputStream) {
        try {
            return (JSONObject) JSONObject.parse(getInputStreamString(inputStream));
        }catch (Exception ex){
        }
        return null;
    }

}
