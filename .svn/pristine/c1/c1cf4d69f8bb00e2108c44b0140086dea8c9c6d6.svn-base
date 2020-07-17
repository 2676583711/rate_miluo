package com.rate.web.statistic.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author chenshixue
 * @Date 2019/12/25
 */
public class FormatEntity {

    private static final Map<Integer, String> MAP = new HashMap<Integer, String>(){{
        put(1, "一");
        put(2, "二");
        put(3, "三");
        put(4, "四");
    }};

    public static List<String> formatXItemBySeason(String startDate, String endDate) {
        List<String> xItem = new ArrayList<>();
        int startYear = Integer.parseInt(startDate.substring(0,4));
        int startSeason = Integer.parseInt(startDate.substring(4));
        int endYear = Integer.parseInt(endDate.substring(0, 4));
        int endSeason = Integer.parseInt(endDate.substring(4));
        while (true) {
            String start = startYear + "年第" + MAP.get(startSeason) + "季度";
            String end = endYear + "年第" + MAP.get(endSeason) + "季度";
            xItem.add(start);
            if (start.equals(end)) {
                break;
            }
            if (startSeason == 4) {
                startYear++;
                startSeason = 1;
            } else {
                startSeason++;
            }
        }
        return xItem;
    }

    public static List<String> formatXItemByYear(String startDate, String endDate) {
        List<String> xItem = new ArrayList<String>();
        int startYear = Integer.parseInt(startDate);
        int endYear = Integer.parseInt(endDate);
        while (true) {
            xItem.add(startYear + "");
            if (startYear == endYear) {
                break;
            }
            startYear++;
        }
        return xItem;
    }

    public static String formatSingleSeasonByYear(String str) {  // 20192
        String year = str.substring(0,4);
        String season = str.substring(4);
        return year + "年第" + MAP.get(Integer.parseInt(season)) + "季度";
    }
}
