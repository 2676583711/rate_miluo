package com.rate.system.rate_system.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期处理
 */
public class DateUtils {
    private final static Logger logger = LoggerFactory.getLogger(DateUtils.class);
    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_PATTERN = "yyyy-MM-dd";

    /**
     * @Fields DATE_PATTERNSIMP : 时间格式(yyyyMMdd)
     */
    public final static String DATE_PATTERNSIMP = "yyyyMMdd";
    /**
     * @Fields DATE_PATTERNMONTH : yyyy-MM
     */
    public final static String DATE_PATTERNMONTH = "yyyy-MM";
    /**
     * @Fields DATE_PATTERNYEAR : yyyy
     */
    public final static String DATE_PATTERNYEAR = "yyyy";
    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    /**
     * @Fields DATE_TIME_PATTERN2 : yyyy-MM-dd HH:mm:ss:SSS
     */
    public final static String DATE_TIME_PATTERN2 = "yyyy-MM-dd HH:mm:ss:SSS";
    /**
     * @Fields DATE_TIME_PATTERN2 : yyyy-MM-dd HH:mm
     */
    public final static String DATE_TIME_PATTERN3 = "yyyy-MM-dd HH:mm";
    /**
     * @Fields DATE_TIME_HOUR : yyyy-MM-dd HH
     */
    public final static String DATE_TIME_HOUR = "yyyy-MM-dd HH";
    /**
     * @Fields DATE_TIME_PATTERN1 : yyyyMMddHHmmssSSS
     */
    public final static String DATE_TIME_PATTERN1 = "yyyyMMddHHmmssSSS";

    /**
     * @Fields RANDOM : TODO
     */
    public final static Random RANDOM = new Random();

    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }
    //把字符串转换成日期
    public static Date format(String date,String pattern) {
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

    public static String getTimeId(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_TIME_PATTERN1);
        String time = simpleDateFormat.format(new Date());
        for(int i = 0; i<2; i++){
            time += RANDOM.nextInt(10);
        }
        return time;

    }

    /**
     * yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getCurrentDateTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_TIME_PATTERN);
        return simpleDateFormat.format(System.currentTimeMillis());
    }
    /**
     * yyyy-MM-dd HH:mm:ss SSS
     * @return
     */
    public static String getCurrentDateTime2(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_TIME_PATTERN2);
        return simpleDateFormat.format(System.currentTimeMillis());
    }
    /**
     * yyyyMMddHHmmssSSS
     * @return
     */
    public static String getCurrentDateTime3(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_TIME_PATTERN1);
        return simpleDateFormat.format(System.currentTimeMillis());
    }

    /**
     * 计算距离现在多久，非精确
     *
     * @param date
     * @return
     */
    public static String getTimeBefore(Date date) {
        Date now = new Date();
        long l = now.getTime() - date.getTime();
        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        String r = "";
        if (day > 0) {
            r += day + "天";
        } else if (hour > 0) {
            r += hour + "小时";
        } else if (min > 0) {
            r += min + "分";
        } else if (s > 0) {
            r += s + "秒";
        }
        r += "前";
        return r;
    }

    /**
     * 计算距离现在多久，精确
     *
     * @param date
     * @return
     */
    public static String getTimeBeforeAccurate(Date date) {
        Date now = new Date();
        long l = now.getTime() - date.getTime();
        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        String r = "";
        if (day > 0) {
            r += day + "天";
        }
        if (hour > 0) {
            r += hour + "小时";
        }
        if (min > 0) {
            r += min + "分";
        }
        if (s > 0) {
            r += s + "秒";
        }
        r += "前";
        return r;
    }

    /**
     * 获取时间段之间的所有年月日 时分秒
     * @param begin 起始时间
     * @param end 结束时间
     */
    public static List<String> getBetweenDate(String begin, String end){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<String> betweenList = new ArrayList<>();
        try{
            Calendar startDay = Calendar.getInstance();
            startDay.setTime(format.parse(begin));
            startDay.add(Calendar.MINUTE, -1);

            while(true){
                startDay.add(Calendar.MINUTE, 1);
                Date newDate = startDay.getTime();
                String newend=format.format(newDate);
                betweenList.add(newend);
                if(end.equals(newend)){
                    break;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return betweenList;
    }

    /**
     * 获取指定年月的最后一天
     * @param date 年月[yyyy-MM]
     */
    public static String getLastDayOfMonth(String date) {
        String[] dateYM  = date.split("-");
        int year = Integer.parseInt(dateYM[0]);
        int month = Integer.parseInt(dateYM[1]);
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DATE);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

    /**
     * 根据具体年份周数获取日期范围
     *
     * @param year 年
     * @param week 周
     * @return
     */
    public static String[] getWeekDays(int year, int week) {
        // 计算目标周数
        if (week > 52) {
            year++;
            week -=  52;
        } else if (week <= 0) {
            year--;
            week += 52;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        // 设置每周的开始日期
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, week);
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        String beginDate = sdf.format(cal.getTime());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        String endDate = sdf.format(cal.getTime());
        String[] dateArr = new String[2];
        dateArr[0] = beginDate;
        dateArr[1] = endDate;
        return dateArr;
    }

    /**
     * 获取指定月的 天数
     */
    public static int getMonthDay(String year, String month) {
        int y = Integer.parseInt(year);
        int m = Integer.parseInt(month);
        return getMonthDay(y, m);
    }
    /**
     * 获取指定月的 天数
     */
    public static int getMonthDay(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        return a.get(Calendar.DATE);
    }

    public static List<String> getMonthBetween(String minDate, String maxDate) throws ParseException {
        ArrayList<String> result = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月

        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        min.setTime(sdf.parse(minDate));
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

        max.setTime(sdf.parse(maxDate));
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

        Calendar curr = min;
        while (curr.before(max)) {
            result.add(sdf.format(curr.getTime()));
            curr.add(Calendar.MONTH, 1);
        }

        return result;
    }

}
