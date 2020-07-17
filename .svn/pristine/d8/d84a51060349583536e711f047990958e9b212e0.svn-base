package com.rate.web.utils;

import java.text.SimpleDateFormat;
import java.util.*;

public class Tutil {

    // 用于站点拼接sql
    public static String getString(String[] split){
        String str="'";
        for (String s : split) {
            str+=s+"','";
        }
        str=str.substring(0,str.length()-2);
        return str;
    }

    // 获取年集合
    public static List<String> getYear(String startTime, String endTime){
        Integer str = Integer.parseInt(startTime.substring(0, 4));
        Integer end = Integer.parseInt(endTime.substring(0, 4));
        ArrayList<String> list = new ArrayList<>();
        list.add(str.toString());
        while (true){
            if (str.equals(end)){
                break;
            }
            str+=1;
            list.add(str.toString());
        }
        return list;
    }

    // 获取月集合
    public static List<String> getMonthList(String minDate, String maxDate) throws Exception {
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

    // 去年此时
    public static  long getYearBefore(long currentTime) {
        Calendar c = Calendar.getInstance();
        Date date = new Date(currentTime);
        c.setTime(date);
        c.add(Calendar.YEAR, -1);
        return c.getTime().getTime();
    }

    // 百分位计算
    public static Double percentile(List<Double> data, double p) {
       /* int n = data.size();
        Collections.sort(data);
        double k = p * (n - 1)+1;
        int s =(int) Math.floor(k);
        Double ss=data.get(s-1)+(data.get(s)-data.get(s-1))*(k-s);

        return ss;*/

        int n = data.size();
        Collections.sort(data);
        double px = p * (n - 1);
        int i = (int) Math.floor(px);
        double g = px - i;
        if (g == 0) {
            return data.get(i);
        } else {
            Double d = null;
            try{
                d = (1 - g) * data.get(i) + g * data.get(i + 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return d;
        }
    }

    // 某月有多少天
    public static int getDaysOnMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    // 季度时间集合
    public static List<String> jdList(Map<String, Object> params){
        Integer startTime = Integer.parseInt((String) params.get("startTime")) ;
        Integer endTime = Integer.parseInt((String) params.get("endTime"));
        Integer jd1 = Integer.parseInt((String) params.get("jd1"));
        Integer jd2 = Integer.parseInt((String) params.get("jd2"));
        ArrayList<String> start = new ArrayList<>();
        ArrayList<String> end = new ArrayList<>();
        while (true){
            if (startTime<endTime){
                start.add(startTime+getStartQuarter(jd1));
                end.add(startTime+getEndQuarter(jd1));
                jd1++;
                if (jd1>4){
                    startTime++;
                    jd1=1;
                }
            }else if (startTime.equals(endTime)){
                if (jd1<=jd2){
                    start.add(startTime+getStartQuarter(jd1));
                    end.add(startTime+getEndQuarter(jd1));
                    jd1++;
                }else {
                    break;
                }
            }
        }
        start.addAll(end);
        return start;
    }

    public static String getStartQuarter(Integer quarter){
        if (quarter.equals(1)){
            return "-01-01";
        }else if (quarter.equals(2)){
            return "-04-01";
        }else if (quarter.equals(3)){
            return "-07-01";
        }else {
            return "-10-01";
        }
    }

    public static String getEndQuarter(Integer quarter){
        if (quarter.equals(1)){
            return "-03-31";
        }else if (quarter.equals(2)){
            return "-06-30";
        }else if (quarter.equals(3)){
            return "-09-30";
        }else {
            return "-12-31";
        }
    }

    // 上月环比日期
    public static String getHbDate(String currentDate) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        String monthFormat;
        try {
            date = sdf.parse(currentDate + "-" + "01");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, -1);
        int month = c.get(Calendar.MONTH) + 1;
        if(month<=9) {
            monthFormat="0"+month;
        }else {
            monthFormat=String.valueOf(month);
        }
        String hbDate = c.get(Calendar.YEAR) + "-"
                + monthFormat+"-01";
        return hbDate;
    }

    // 去年同比日期
    public static String getTbDate(String currentDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        String monthFormat;
        try {
            date = sdf.parse(currentDate + "-" + "01");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, -1);
        int month = c.get(Calendar.MONTH) + 1;
        if(month<=9) {
            monthFormat="0"+month;
        }else {
            monthFormat=String.valueOf(month);
        }
        String tbDate = c.get(Calendar.YEAR) + "-"
                + monthFormat+"-01";
        return tbDate;
    }

    // 上月今日
    public static Date getMonth(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); // 设置为当前时间
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1); // 设置为上一个月
        return calendar.getTime();
    }

    // 当月最后一天
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

    public static String startWeek(Integer week){
        if (week.equals(1)){
            return "-01";
        }else if (week.equals(2)){
            return "-08";
        }else if (week.equals(3)){
            return "-15";
        }else {
            return "-22";
        }
    }
    public static String endWeek(Integer week){
        if (week.equals(1)){
            return "-07";
        }else if (week.equals(2)){
            return "-14";
        }else if (week.equals(3)){
            return "-21";
        }else {
            return "-28";
        }
    }

    // 季度时间集合
    public static List<String> weekList(Map<String, Object> params){
        String start = (String) params.get("startTime");
        String end = (String) params.get("endTime");
        Integer startTime = Integer.parseInt(start.replace("-","")) ;
        Integer endTime = Integer.parseInt(end.replace("-",""));
        Integer jd1 = Integer.parseInt((String) params.get("jd1"));
        Integer jd2 = Integer.parseInt((String) params.get("jd2"));
        ArrayList<String> startList = new ArrayList<>();
        ArrayList<String> endList = new ArrayList<>();
        while (true){
            if (startTime<endTime){
                startList.add(start+startWeek(jd1));
                endList.add(start+endWeek(jd1));
                jd1++;
                if (jd1>4){
                    if (startTime.toString().substring(4,6).equals("12")){
                        startTime = Integer.parseInt(String.valueOf(startTime + 100).substring(0,4)+"-01");
                    }else {
                        startTime++;
                    }
                    jd1=1;
                }
            }else if (startTime.equals(endTime)){
                if (jd1<=jd2){
                    String time = startTime.toString().substring(0,4)+"-"+startTime.toString().substring(4,6);
                    startList.add(time+startWeek(jd1));
                    endList.add(time+endWeek(jd1));
                    jd1++;
                }else {
                    break;
                }
            }
        }
        startList.addAll(endList);
        return startList;
    }
}
