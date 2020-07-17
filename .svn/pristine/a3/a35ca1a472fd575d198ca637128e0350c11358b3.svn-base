package com.rate.web.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @program: rate_miluo_parent
 * @ClassName: GetLastYear
 * @description: 获取去年得时间
 * @author: chenh
 * @create: 2020-07-16 16:07
 **/
public class GetLastYear {

    public static String getTbDate(String currentDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
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
                + monthFormat;
        return tbDate;
    }
}
