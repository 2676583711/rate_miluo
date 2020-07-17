package com.rate.web.statistic.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author chenshixue
 * @Date 2019/12/23
 */
public class TimeConstant {

    public static final Map<String, String> PATTERN = new HashMap<>();

    static {
        PATTERN.put("1", "%Y-%m");      // 月
        PATTERN.put("3", "%Y");         // 年
        PATTERN.put("4", "%Y-%m-%d");   // 日
    }
}
