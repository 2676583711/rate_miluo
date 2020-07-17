package com.rate.web.statistic.util;

import com.rate.system.rate_system.utils.DateUtils;
import com.rate.system.rate_system.utils.StringUtils;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 日期参数
 *
 * @ClassName DateParamUtil
 * @Author LiuYong
 * @Date 2019/6/25 16:23
 * @Version 1.0
 **/
public class DateParamUtil {


    public static Map<String, Object> initParam(Map<String, Object> params, final int type) {
        String startDate = null;
        String endDate = null;
        try {
            startDate = (String) params.get("startDate");
            endDate = (String) params.get("endDate");
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<>(6);
        }
        switch (type) {
            case Calendar.YEAR:
                return initYear(startDate, endDate, type, params);
            case Calendar.MONTH:
                return initMonth(startDate, endDate, type, params);
            case Calendar.DATE:
                return initDateTime(startDate,endDate,params);
            default:
                return new HashMap<>(6);
        }
    }

    private static Map<String, Object> initYear(String startDate, String endDate, final int type, Map<String, Object> params) {
        Calendar c = Calendar.getInstance();
        if (StringUtils.isBlank(startDate) || StringUtils.isBlank(endDate)) {
            c.add(Calendar.DAY_OF_MONTH, -1);
            params.put("startDate", DateUtils.format(c.getTime(), DateUtils.DATE_PATTERN));
            params.put("endDate", DateUtils.format(c.getTime(), DateUtils.DATE_PATTERN));
            // 当前时间的前一天的前一年
            c.add(type, -1);
            params.put("startPreDate", DateUtils.format(c.getTime(), DateUtils.DATE_PATTERN));
            params.put("endPreDate", DateUtils.format(c.getTime(), DateUtils.DATE_PATTERN));

        } else {
            c.setTime(DateUtils.format(startDate, DateUtils.DATE_PATTERN));
            // 前一年
            c.add(type, -1);
            params.put("startPreDate", DateUtils.format(c.getTime(), DateUtils.DATE_PATTERN));
            c.setTime(DateUtils.format(endDate, DateUtils.DATE_PATTERN));
            c.add(type, -1);
            params.put("endPreDate", DateUtils.format(c.getTime(), DateUtils.DATE_PATTERN));
        }
        return params;
    }

    private static Map<String, Object> initMonth(String startDate, String endDate, final int type, Map<String, Object> params) {
        if (StringUtils.isBlank(startDate) || StringUtils.isBlank(endDate)) {
            // 当前时间的前一天
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_MONTH, -1);
            params.put("endDate", DateUtils.format(c.getTime(), DateUtils.DATE_PATTERN));
            // 前一个月的前一天
            c.add(type, -1);
            params.put("startDate", DateUtils.format(c.getTime(), DateUtils.DATE_PATTERN));
        }
        return params;
    }

    private static Map<String, Object> initDateTime(String startDate, String endDate, Map<String, Object> params) {
        if (StringUtils.isBlank(startDate) || StringUtils.isBlank(endDate)) {
            Calendar calendar = Calendar.getInstance();
            params.put("endDate", calendar.getTime());
            // 当前系统时间的0点
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            params.put("startDate", calendar.getTime());
        }
        return params;
    }
}
