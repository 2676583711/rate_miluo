/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rate.system.rate_system.utils;


import org.apache.commons.lang3.time.DateFormatUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 时间计算工具类

 */
public class TimeUtils {
	
	public static String toTimeString(long time) {
		TimeUtils t = new TimeUtils(time);
		int day = t.get(TimeUtils.DAY);
		int hour = t.get(TimeUtils.HOUR);
		int minute = t.get(TimeUtils.MINUTE);
		int second = t.get(TimeUtils.SECOND);
		StringBuilder sb = new StringBuilder();
		if (day > 0){
			sb.append(day).append("天");
		}
		if (hour > 0){
			sb.append(hour).append("时");
		}
		if (minute > 0){
			sb.append(minute).append("分");
		}
		if (second > 0){
			sb.append(second).append("秒");
		}
		return sb.toString();
	}
	
    /**
     * 时间字段常量，表示“秒”
     */
    public final static int SECOND = 0;

    /**
     * 时间字段常量，表示“分”
     */
    public final static int MINUTE = 1;

    /**
     * 时间字段常量，表示“时”
     */
    public final static int HOUR = 2;

    /**
     * 时间字段常量，表示“天”
     */
    public final static int DAY = 3;

    /**
     * 各常量允许的最大值
     */
    private final int[] maxFields = { 59, 59, 23, Integer.MAX_VALUE - 1 };

    /**
     * 各常量允许的最小值
     */
    private final int[] minFields = { 0, 0, 0, Integer.MIN_VALUE };

    /**
     * 默认的字符串格式时间分隔符
     */
    private String timeSeparator = ":";

    /**
     * 时间数据容器
     */
    private int[] fields = new int[4];

    /**
     * 无参构造，将各字段置为 0
     */
    public TimeUtils() {
        this(0, 0, 0, 0);
    }

    /**
     * 使用时、分构造一个时间
     * @param hour      小时
     * @param minute    分钟
     */
    public TimeUtils(int hour, int minute) {
        this(0, hour, minute, 0);
    }

    /**
     * 使用时、分、秒构造一个时间
     * @param hour      小时
     * @param minute    分钟
     * @param second    秒
     */
    public TimeUtils(int hour, int minute, int second) {
        this(0, hour, minute, second);
    }

    /**
     * 使用一个字符串构造时间<br/>
     * Time time = new Time("14:22:23");
     * @param time      字符串格式的时间，默认采用“:”作为分隔符
     */
    public TimeUtils(String time) {
        this(time, null);
    }

    /**
     * 使用时间毫秒构建时间
     * @param time
     */
    public TimeUtils(long time){
    	this(new Date(time));
    }
    
    /**
     * 使用日期对象构造时间
     * @param date
     */
    public TimeUtils(Date date){
    	this(DateFormatUtils.formatUTC(date, "HH:mm:ss"));
    }

    /**
     * 使用天、时、分、秒构造时间，进行全字符的构造
     * @param day       天
     * @param hour      时
     * @param minute    分
     * @param second    秒
     */
    public TimeUtils(int day, int hour, int minute, int second) {
        initialize(day, hour, minute, second);
    }

    /**
     * 使用一个字符串构造时间，指定分隔符<br/>
     * Time time = new Time("14-22-23", "-");
     * @param time      字符串格式的时间
     */
    public TimeUtils(String time, String timeSeparator) {
        if(timeSeparator != null) {
            setTimeSeparator(timeSeparator);
        }
        parseTime(time);
    }

    /**
     * 设置时间字段的值
     * @param field     时间字段常量
     * @param value     时间字段的值
     */
    public void set(int field, int value) {
        if(value < minFields[field]) {
            throw new IllegalArgumentException(value + ", time value must be positive.");
        }
        fields[field] = value % (maxFields[field] + 1);
        // 进行进位计算
        int carry = value / (maxFields[field] + 1);
        if(carry > 0) {
            int upFieldValue = get(field + 1);
            set(field + 1, upFieldValue + carry);
        }
    }

    /**
     * 获得时间字段的值
     * @param field     时间字段常量
     * @return          该时间字段的值
     */
    public int get(int field) {
        if(field < 0 || field > fields.length - 1) {
            throw new IllegalArgumentException(field + ", field value is error.");
        }
        return fields[field];
    }

    /**
     * 将时间进行“加”运算，即加上一个时间
     * @param time      需要加的时间
     * @return          运算后的时间
     */
    public TimeUtils addTime(TimeUtils time) {
    	TimeUtils result = new TimeUtils();
        int up = 0;     // 进位标志
        for (int i = 0; i < fields.length; i++) {
            int sum = fields[i] + time.fields[i] + up;
            up = sum / (maxFields[i] + 1);
            result.fields[i] = sum % (maxFields[i] + 1);
        }
        return result;
    }

    /**
     * 将时间进行“减”运算，即减去一个时间
     * @param time      需要减的时间
     * @return          运算后的时间
     */
    public TimeUtils subtractTime(TimeUtils time) {
    	TimeUtils result = new TimeUtils();
        int down = 0;       // 退位标志
        for (int i = 0, k = fields.length - 1; i < k; i++) {
            int difference = fields[i] + down;
            if (difference >= time.fields[i]) {
                difference -= time.fields[i];
                down = 0;
            } else {
                difference += maxFields[i] + 1 - time.fields[i];
                down = -1;
            }
            result.fields[i] = difference;
        }
        result.fields[DAY] = fields[DAY] - time.fields[DAY] + down;
        return result;
    }

    /**
     * 获得时间字段的分隔符
     * @return
     */
    public String getTimeSeparator() {
        return timeSeparator;
    }

    /**
     * 设置时间字段的分隔符（用于字符串格式的时间）
     * @param timeSeparator     分隔符字符串
     */
    public void setTimeSeparator(String timeSeparator) {
        this.timeSeparator = timeSeparator;
    }

    private void initialize(int day, int hour, int minute, int second) {
        set(DAY, day);
        set(HOUR, hour);
        set(MINUTE, minute);
        set(SECOND, second);
    }

    private void parseTime(String time) {
        if(time == null) {
            initialize(0, 0, 0, 0);
            return;
        }
        String t = time;
        int field = DAY;
        set(field--, 0);
        int p = -1;
        while((p = t.indexOf(timeSeparator)) > -1) {
            parseTimeField(time, t.substring(0, p), field--);
            t = t.substring(p + timeSeparator.length());
        }
        parseTimeField(time, t, field--);
    }

    private void parseTimeField(String time, String t, int field) {
        if(field < SECOND || t.length() < 1) {
            parseTimeException(time);
        }
        char[] chs = t.toCharArray();
        int n = 0;
        for(int i = 0; i < chs.length; i++) {
            if(chs[i] <= ' ') {
                continue;
            }
            if(chs[i] >= '0' && chs[i] <= '9') {
                n = n * 10 + chs[i] - '0';
                continue;
            }
            parseTimeException(time);
        }
        set(field, n);
    }

    private void parseTimeException(String time) {
        throw new IllegalArgumentException(time + ", time format error, HH"
                + this.timeSeparator + "mm" + this.timeSeparator + "ss");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(16);
        sb.append(fields[DAY]).append(',').append(' ');
        buildString(sb, HOUR).append(timeSeparator);
        buildString(sb, MINUTE).append(timeSeparator);
        buildString(sb, SECOND);
        return sb.toString();
    }

    private StringBuilder buildString(StringBuilder sb, int field) {
        if(fields[field] < 10) {
            sb.append('0');
        }
        return sb.append(fields[field]);
    }

    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + Arrays.hashCode(fields);
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final TimeUtils other = (TimeUtils) obj;
        if (!Arrays.equals(fields, other.fields)) {
            return false;
        }
        return true;
    }
    
    /**获取上n个小时整点小时时间
     * @param date
     * @return
     */
    public static String getLastHourTime(Date date,int n){
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ca.set(Calendar.HOUR_OF_DAY, ca.get(Calendar.HOUR_OF_DAY)-n);
        date = ca.getTime();
        return sdf.format(date);
    }
    /**获取当前时间的整点小时时间
     * @param date
     * @return
     */
    public static String getCurrHourTime(Date date){
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        date = ca.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
    /**
     * 
     * @param dBegin
     * @param dEnd
     * 1开始时间，2结束时间，3时间格式，4.时间工具裁剪格式
     * @return
     */
    
    public static List<String> findDates(Date stime, Date etime, String format,Integer calendar) {
        List<String> lDate = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar calBegin = Calendar.getInstance();
        
    // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(stime);
        Calendar calEnd = Calendar.getInstance();
    // 使用给定的 Date 设置此 Calendar 的时间
          calEnd.setTime(etime);
        // 测试此日期是否在指定日期之后
        while (etime.after(calBegin.getTime()))  {
         // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
             calBegin.add(calendar, 1);
             String time=sdf.format(calBegin.getTime());
             lDate.add(time);
        }
        return lDate;
   }
    
    
    public static List<String> getMonthBetween(Date minDate, Date maxDate, String format){
    	ArrayList<String> result = new ArrayList<String>();
    	SimpleDateFormat sdf = new SimpleDateFormat(format);//格式化为年月
    	Calendar min = Calendar.getInstance();
    	Calendar max = Calendar.getInstance();
    	try {
			min.setTime(minDate);
			min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);
	    	max.setTime(maxDate);
	    	max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	Calendar curr = min;
    	while (curr.before(max)) {
    		result.add(sdf.format(curr.getTime()));
    		curr.add(Calendar.MONTH, 1);
    		}
    	return result;
    	
    	}
    
  /**
   * 获取某月有多少天
   * @param month
   * @return
   */
    public static Integer getDaysByYearMonth(Integer month) {
    Calendar a =Calendar.getInstance();
    a.set(Calendar.MONTH, month- 1);
    a.set(Calendar.DATE, 1);
    a.roll(Calendar.DATE,-1);
    int maxDate =a.get(Calendar.DATE);
    return maxDate;
      }

    /**
     * 时间转字符串
     * @param date
     * @param parse
     * @return
     */
    public static String DateToStr(Date date,String parse){
    	 SimpleDateFormat mat = new SimpleDateFormat(parse);
    	 String str=mat.format(date);
         return str;
    }
    
    
    /**
     * 字符串转时间
     * @param date
     * @param parse
     * @return
     * @throws java.text.ParseException 
     */
    public static Date StringToDate(String time,String parse) throws java.text.ParseException{
    	 SimpleDateFormat mat = new SimpleDateFormat(parse);
    	 Date date = mat.parse(time);
         return date;
    }
    
    /**
     * 把指定时间加减任意天，月，年
     * @param date
     * @param parse
     * @return
     */
    public static Date getMonthDate(Date date,Integer parse){
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);//设置起时间
    	//System.out.println("111111111::::"+cal.getTime());
    	//cal.add(Calendar.YEAR, 1);//增加一年
    	//cal.add(Calendar.DATE, n);//增加一天  
    	//cal.add(Calendar.DATE, -10);//减10天  
    	cal.add(Calendar.MONTH, parse);//增加一个月
    	Date time = cal.getTime();
        return time;
   }
    
    
    

    
}