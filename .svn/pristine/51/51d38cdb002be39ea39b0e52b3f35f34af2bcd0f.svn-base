package com.rate.web.statement.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author chenshixue
 * @Date 2019/11/9
 */
public class TableSelUtil {

    /**
     * 分钟表，获得按月分表的标识
     * @param date
     * @param pattern 日期格式 必填
     * @Return  java.lang.String
     * @Author  chenshixue
     * @Date    2019/11/9 21:58
     */
    public static String getTableSignOfMonth(String date, String pattern) {
        String sign = null;
        try {
            Calendar cal = Calendar.getInstance();
            if (date != null) {
                Date dataDate = parse(date, pattern);
                cal.setTime(dataDate);
                String dataDateStr1 = format(dataDate, "yyyy-MM-dd HH:mm:ss");
                String dataDateStr2 = format(dataDate, "yyyy-MM-dd 00:00:00");
                // 如果时间是0点，则取昨天的时间
                if (dataDateStr1.equals(dataDateStr2)) {
                    cal.add(Calendar.DAY_OF_MONTH, -1);
                }
                sign = format(cal.getTime(), "yyyyMM");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sign;
    }

    // 分钟表，获取两个时间之间的表标识
    public static List<String> getTableSignListOfMonth(String startDate, String endDate, String pattern) {
        String startSign = getTableSignOfMonth(startDate, pattern);
        String endSign = getTableSignOfMonth(endDate, pattern);
        Date start = parse(startSign, "yyyyMM");
        Date end = parse(endSign, "yyyyMM");
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(start);
        c2.setTime(end);

        List<String> strList = new ArrayList<>();
        while (true) {
            String str = format(c1.getTime(), "yyyyMM");
            strList.add(str);
            if (c1.getTime().compareTo(c2.getTime()) == 0) {
                break;
            }
            c1.add(Calendar.MONTH, 1);
        }
        return strList;
    }


    /**
     * 小时表，获取按年分表的标识
     * @param date
     * @param pattern
     * @Return  java.lang.String
     * @Author  chenshixue
     * @Date    2019/11/26 10:39
     */
    public static String getTableSignOfYear(String date, String pattern) {
        String sign = null;
        try {
            Calendar cal = Calendar.getInstance();
            if (date != null) {
                Date dataDate = parse(date, pattern);
                cal.setTime(dataDate);
                String dataDateStr1 = format(dataDate, "yyyy-MM-dd HH:mm:ss");
                String dataDateStr2 = format(dataDate, "yyyy-01-01 00:00:00");
                // 如果时间是1月1日0点，则取昨天的时间
                if (dataDateStr1.equals(dataDateStr2)) {
                    cal.add(Calendar.DAY_OF_MONTH, -1);
                }
                sign = format(cal.getTime(), "yyyy");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sign;
    }

    // 日期转为字符串
    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    //把字符串转解析成日期
    public static Date parse(String date,String pattern) {
        if (pattern != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            try {
                return simpleDateFormat.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
