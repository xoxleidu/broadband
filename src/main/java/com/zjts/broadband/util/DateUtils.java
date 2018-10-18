package com.zjts.broadband.util;

import org.apache.commons.lang3.time.FastDateFormat;
import org.joda.time.DateTime;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYYMMDD = "yyyyMMdd";
    public static final SimpleDateFormat DAY_DATE_FORMAT = new SimpleDateFormat(YYYY_MM_DD);
    public static final String DATE_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm"; // 包含时间分钟
    public static final SimpleDateFormat DAY_DATE_FORMAT_HH_MM_SS = new SimpleDateFormat(DATE_YYYY_MM_DD_HH_MM_SS);
    public static final SimpleDateFormat DAY_DATE_FORMAT_HH_MM = new SimpleDateFormat(DATE_YYYY_MM_DD_HH_MM);// 包含时间分钟
    public static final FastDateFormat DATE_FORMAT_yyyy_MM_dd = FastDateFormat.getInstance("yyyy-MM-dd");
    public static final FastDateFormat DATE_FORMAT_YMDHMS = FastDateFormat.getInstance("yyyyMMddHHmmss");
    public static final SimpleDateFormat DAY_DATE_FORMAT_CN = new SimpleDateFormat("YYYY年MM月");

    public static final String DATE_HH_MM_SS = "HH:mm:ss";
    public static final SimpleDateFormat DATE_FORMAT_HH_MM_SS = new SimpleDateFormat(DATE_HH_MM_SS);


    public static final String DATE_YYYY_MM_DD_HH = "yyyyMMddHH";

    public static final String DATE_HH_MM = "HH:mm";
    public static final SimpleDateFormat DATE_FORMAT_HH_MM = new SimpleDateFormat(DATE_HH_MM);
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(YYYYMMDD);
    public static final SimpleDateFormat SIMPLE_FORMAT_YMCH = new SimpleDateFormat("yyyy年MM月");
    //private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    //private static Logger log = LoggerFactory.getLogger(DateUtils.class);

    public static final int TIME_TYPE_MONTH = 0;
    public static final int TIME_TYPE_DAY = 1;
    public static final int TIME_TYPE_HOUR = 2;
    public static final int TIME_TYPE_MINUTE = 3;
    public static final int TIME_TYPE_SECOND = 4;

    public static boolean compareTimeMinute(long date1, long date2, double n) {
        long cha = Math.abs(date1 - date2);
        double result = n * (1000 * 60 * 60*60);
        return result <= cha;
    }

    /**
     * 比较两个时间是否相差超过n小时
     *
     *
     * */
    public static boolean compareTime(long date1, long date2, double n) {
        long cha = Math.abs(date1 - date2);
        double result = n * (1000 * 60 * 60);
        return result <= cha;
    }
    /**
     * 比较两个时间是否相差几小时
     *
     *
     * */
    public static double compareTime(long date1, long date2) {
        long cha = Math.abs(date1 - date2);
        long result = 1000 * 60 * 60;
        return (double)cha/(double)result;
    }
    /**
     * 在指定时间上加上或减去固定时间
     * */
    public static Date addTime(Date date, int time, int type) {
        DateTime dateTime = new DateTime(date);
        DateTime newDate = null;
        switch (type){
            case TIME_TYPE_MONTH:
                newDate = dateTime.plusMonths(time);
                break;
            case TIME_TYPE_DAY:
                newDate = dateTime.plusDays(time);
                break;
            case TIME_TYPE_HOUR:
                newDate = dateTime.plusHours(time);
                break;
            case TIME_TYPE_MINUTE:
                newDate = dateTime.plusMinutes(time);
                break;
            case TIME_TYPE_SECOND:
                newDate = dateTime.plusSeconds(time);
                break;
        }
        return newDate.toDate();
    }

    public static final String convertDateFormatToString(SimpleDateFormat fastDateFormat, Date date) {
        SimpleDateFormat format = DAY_DATE_FORMAT;
        if (fastDateFormat == null) {
            fastDateFormat = format;
        }

        return fastDateFormat.format(date);
    }

    /**
     * 两个日期相差的天数
     *
     * @param from
     * @param to
     * @return
     */
    public static int dateDiffDay(Date from, Date to) {
        return (int) ((to.getTime() - from.getTime()) / (1000 * 60 * 60 * 24));
    }

    public static Date getYesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();
    }

    public static Date getToday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }
    
    public static Date strToDate(String dateStr) {
        Date date = null;
        try {
            date = DAY_DATE_FORMAT.parse(dateStr);
        } catch (ParseException e) {

        }
        return date;
    }

    public static Date strToDate(String dateStr, SimpleDateFormat sdf) {
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {

        }
        return date;
    }

    public static Date getNextDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    public static Date addMonth(Date date, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }

    public static int getYear() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR);
    }

    /**
     * 将date（日期）和time（时间）转化为时间长串
     *
     * */
    public static Date setDateAndTimeToDate(Date date, Date time) {

        Calendar calendarDate = Calendar.getInstance();
        calendarDate.setTime(date);

        Calendar calendarTime = Calendar.getInstance();
        calendarTime.setTime(time);

        calendarDate.set(Calendar.HOUR, calendarTime.get(Calendar.HOUR));
        calendarDate.set(Calendar.MINUTE, calendarTime.get(Calendar.MINUTE));
        calendarDate.set(Calendar.SECOND, calendarTime.get(Calendar.SECOND));

        return  calendarDate.getTime();
    }

    /**
     * 获取昨天凌晨时间
     * @param
     * @throws ParseException
     */
    public static Date getYesterdayMorningDate(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.DAY_OF_MONTH,(cal.get(Calendar.DAY_OF_MONTH)-1));
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取指定时间的那天 00:00:00.000 的时间
     *
     * @param date
     * @return
     */
    public static Date dayBegin(final Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }
    /**
     * 获取指定时间的那天 23:59:59.999 的时间
     *
     * @param date
     * @return
     */
    public static Date dayEnd(final Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        return c.getTime();
    }

    /**
     * 根据返回的时间串获取是否是今天，否则处理为凌晨
     * @param
     * @throws ParseException
     */
    public static Date isCurrentDay(Date date){
       /* if(date.getTime() >= dayBegin(new Date()).getTime()
                && date.getTime() <= dayEnd(new Date()).getTime()) {
            return new Date();
        }else {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0);
            return c.getTime();
        }*/
        return date;
    }


    public static void main(String[] args) throws ParseException {
        System.out.println(isCurrentDay(new Timestamp(1448452358974l)));

        System.out.println(new Date(1448452358974l));


        Calendar cal = Calendar.getInstance();//使用日历类
        int year = cal.get(Calendar.YEAR);//得到年
        int month = cal.get(Calendar.MONTH) + 1;//得到月，因为从0开始的，所以要加1
        int day = cal.get(Calendar.DAY_OF_MONTH);//得到天
        int hour = cal.get(Calendar.HOUR);//得到小时
        int minute = cal.get(Calendar.MINUTE);//得到分钟
        int second = cal.get(Calendar.SECOND);//得到秒
        System.out.println("结果：" + year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second);

        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy/MM/dd");
        long x = strToDate("2015/07/15", dateFormat1).getTime();
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH:mm:ss");
        long y = strToDate("14:14:56", dateFormat2).getTime();
        long z = x + y;
        System.out.println("new Date()1:" + z);
        System.out.println("new Date()2:" + new Date().getTime());

        System.out.println("new Date()1:" + z);
/*

        DateTime dateTime = new DateTime(1436912096000);
        System.out.println(c.getDate());

        DateTime dateTime = new DateTime(1436940866271);
        System.out.println(c.getDate());
*/

       // Long qw = new Long((long)1436912096000);
        long qw = 1440889200000L;
        SimpleDateFormat sdf= new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date dt = new Date(qw);
        String sDateTime = sdf.format(dt);  //得到精确到秒的表示：08/31/2006 21:08:00
        System.out.println(sDateTime);
    }

    public static final Date getLMTomorrowDate() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        if (hour >= 6) {
            date = getTomorrowToDate();
        }
        return date;
    }

    /**
     * 计算后一天时间
     *
     * @return
     */
    public static final Date getTomorrowToDate(Date date) {
        Calendar ca = Calendar.getInstance();// 得到一个Calendar的实例
        ca.setTime(date == null ? new Date() : date); // 设置时间为当前时间
        ca.add(Calendar.DATE, 1);// 
        return ca.getTime();
    }

    /**
     * 计算后一天时间
     *
     * @return
     */
    public static final Date getTomorrowToDate() {
        Calendar ca = Calendar.getInstance();// 得到一个Calendar的实例
        ca.setTime(new Date()); // 设置时间为当前时间
        ca.add(Calendar.DATE, 1);// 
        return ca.getTime();
    }

    /**
     * 计算前一天时间
     *
     * @return
     */
    public static final Date getYestodayToDate(Date date) {
        Calendar ca = Calendar.getInstance();// 得到一个Calendar的实例
        ca.setTime(date == null ? new Date() : date); // 设置时间为当前时间
        ca.add(Calendar.DATE, -1);// 
        return ca.getTime();
    }

    /**
     * 计算前一天时间
     *
     * @return
     */
    public static final Date getYestodayToDate() {
        Calendar ca = Calendar.getInstance();// 得到一个Calendar的实例
        ca.setTime(new Date()); // 设置时间为当前时间
        ca.add(Calendar.DATE, -1);// 
        return ca.getTime();
    }

    /**
     * 格式化日期
     *
     * @return
     */
    public static final String getDateToStringYYYY_MM_DD() {
        return DATE_FORMAT_yyyy_MM_dd.format(new Date());
    }

    public static final String getTimeStringYYYYMMDDHHMMSS(Date date) {
        return DATE_FORMAT_YMDHMS.format(date);
    }

    public static final String getTimeStirngMMDDHHMM(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-DD HH:MM");
        return simpleDateFormat.format(date);
    }

    public static int getHourByDate(Date date){
        if(date == null){
            return 0;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public static int getMinuteByDate(Date date){
        if(date == null){
            return 0;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 比较两个日期，date1大于date2返回ture，否则返回false
     * @param bequals
     * @return
     */
    public static boolean dateCompare(Date date1, Date date2, boolean bequals) {
        boolean isRetrun = false;
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate1 = fmt.format(date1.getTime());
        String strDate2 = fmt.format(date2.getTime());
        try {
            if(bequals){
                isRetrun = (fmt.parse(strDate1).getTime() >= fmt.parse(strDate2).getTime());
            } else {
                isRetrun = (fmt.parse(strDate1).getTime() > fmt.parse(strDate2).getTime());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return isRetrun;
    }


    /**
     * 是否在区间，date2(5-6-7) 是否在区间，否则返回false
     * @param
     * @return
     */
    public static boolean dateCompareInterval(Date date1, Date date2,Date date3,boolean bequals) {
        if(dateCompare(date2,date1,bequals)&&dateCompare(date3,date2,bequals))
            return true;
        return false;
    }
    /**
     * 获得当天0点时间
      */
    public static Date getTimesmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date getTimesmorning(Date date) throws ParseException {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat formater2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return formater2.parse(formater.format(date)+ " 00:00:00");
    }

    /**
     * 获得当天24点时间
     * */
    public static Date getTimesnight() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 24);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return  cal.getTime();
    }

    public static Date getTimesnight(Date date) throws ParseException {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat formater2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return formater2.parse(formater.format(date) + " 23:59:59");
    }

    /**
     * 时间转换为东八区时间 在原有的时间上加8小时
     *
     * @param date
     * @return
     */
    public static Date convertGMT8(Date date) {
        if (date == null) {
            return date;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) + 8);
        return cal.getTime();
    }

    /**
     * 格式化CST（ Thu Aug 27 18:05:49 CST 2015 ）格式字符串
     *
     * @param date   date
     * @param format format
     * @return String
     * @throws ParseException ParseException
     */
    public static String formatCSTTime(String date, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        Date d = sdf.parse(date);
        return getDateFormat(d, format);
    }

    /**
     * 按特定的日期格式获取当前字符串型日期
     *
     * @param date           指定的时间
     * @param dateFormatType String，日期格式<br>
     *                       几种日期格式和测试的结果<br>
     *                       "yyyy-MM-dd": 2012-08-02<br>
     *                       "yyyy-MM-dd hh:mm:ss": 2012-08-02 11:27:41<br>
     *                       "yyyy-MM-dd hh:mm:ss EE": 2012-08-02 11:27:41 星期四<br>
     *                       "yyyy年MM月dd日 hh:mm:ss EE": 2012年08月02日 11:27:41 星期四<br>
     * @return String 当前字符串型日期
     */
    public static String getDateFormat(Date date, String dateFormatType) {
        SimpleDateFormat simformat = new SimpleDateFormat(dateFormatType);
        return simformat.format(date);
    }
}
