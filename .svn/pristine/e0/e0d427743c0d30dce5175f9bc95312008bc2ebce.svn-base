package com.rate.web.statistic.controller;

import com.rate.system.rate_system.controller.BaseController;
import com.rate.system.rate_system.utils.DateUtils;
import com.rate.system.rate_system.utils.PageUtils;
import com.rate.system.rate_system.utils.StringUtils;
import com.rate.web.statistic.dto.TimeConstant;
import com.rate.web.statistic.service.DailyStatementService;
import com.rate.web.statistic.util.FormatEntity;
import com.rate.web.statistic.util.PollutantExcelUtil;
import com.rate.web.statistic.entity.Grade;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author chenshixue
 * @Date 2019/12/24
 */
@Controller
@RequestMapping("/statistic/grade")
public class GradeController extends BaseController {

    @Autowired
    private DailyStatementService dailyStatementService;

    @GetMapping
    public String page() {
        Long roleId = getRoleId();
        if (roleId == 1 || roleId == 73 || roleId == 74 || roleId == 75 || roleId == 77 || roleId == 81) {
            return "/statistic/grade";
        } else {
            return "/statistic/404";
        }
    }

    @ResponseBody
    @GetMapping("/grade/list")
    public PageUtils gradeList(@RequestParam Map<String, Object> params, Long pageSize, Long pageNumber) {
        String siteCodeList = (String) params.get("siteCodes");
        if (StringUtils.isBlank(siteCodeList)) {
            params.remove("siteCodes");
        } else {
            params.put("siteCodes", siteCodeList.split(","));
        }
        PageQuery<Grade> pageQuery = new PageQuery<>(pageNumber, pageSize, params);
        String timeType = (String) params.get("timeType");
        params.put("timePattern", TimeConstant.PATTERN.get(timeType));
        // 季度
        if ("2".equals(timeType)) {
            String startTime = (String) params.get("startDate");
            String endTime = (String) params.get("endDate");
            Pattern p = Pattern.compile("[^0-9]");
            if (StringUtils.isNotBlank(startTime)) {
                Matcher m = p.matcher(startTime);
                params.put("startDate", m.replaceAll("").trim());
            }
            if (StringUtils.isNotBlank(endTime)) {
                Matcher m = p.matcher(endTime);
                m.replaceAll("").trim();
                params.put("endDate", m.replaceAll("").trim());
            }
        }
        PageQuery<Grade> page = dailyStatementService.gradeList(pageQuery);
        List<Grade> list = formatGrade(page.getList(), params);
        return new PageUtils(list, page.getTotalRow());
    }

    // 导出功能
    @RequestMapping("/exportExcelGrade")
    @ResponseBody
    public void exportExcelGrade(@RequestParam Map<String, Object> params, HttpServletResponse response,
                                 HttpServletRequest request) throws Exception {
        String siteCodeList = (String) params.get("siteCodes");
        if (StringUtils.isBlank(siteCodeList)) {
            params.remove("siteCodes");
        } else {
            params.put("siteCodes", siteCodeList.split(","));
        }
        String timeType = (String) params.get("timeType");
        params.put("timePattern", TimeConstant.PATTERN.get(timeType));
        String startTime = (String) params.get("startDate");
        String endTime = (String) params.get("endDate");
        String dateRange = startTime + "~" + endTime;
        // 季度
        if ("2".equals(timeType)) {
            Pattern p = Pattern.compile("[^0-9]");
            if (StringUtils.isNotBlank(startTime)) {
                Matcher m = p.matcher(startTime);
                params.put("startDate", m.replaceAll("").trim());
            }
            if (StringUtils.isNotBlank(endTime)) {
                Matcher m = p.matcher(endTime);
                m.replaceAll("").trim();
                params.put("endDate", m.replaceAll("").trim());
            }
        }
        List<Grade> list = dailyStatementService.gradeList(params);
        list = formatGrade(list, params);
        if ("2".equals(timeType)) {
            for (Grade days : list) {
                String season = FormatEntity.formatSingleSeasonByYear(days.getDataTime());
                days.setDataTime(season);
            }
        }
        this.doExportXlsGrade(response, request, list, Grade.class, "空气质量等级分布", dateRange);
    }

    protected <T> void doExportXlsGrade(HttpServletResponse response, HttpServletRequest request,
                                        List<Grade> dataSet, Class<?> pojoClass, String title, String dateRange) throws Exception {
        response.setContentType("application/vnd.ms-excel");
        ServletOutputStream out = null;
        try {
            response.reset();
            response.setHeader("Cache-Control", "private");
            response.setHeader("Pragma", "private");
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("Content-Type", "application/force-download");
            String workBookName = URLEncoder.encode(title + ".xls", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + workBookName);
            PollutantExcelUtil excelUtil = new PollutantExcelUtil();
            HSSFWorkbook wb;
            wb = excelUtil.exportExceedGrade(dataSet, dateRange);
            out = response.getOutputStream();
            wb.write(out);
        } catch (Exception var15) {
            var15.printStackTrace();
            throw new Exception("导出失败！");
        } finally {
            try {
                out.flush();
                out.close();
            } catch (IOException var14) {
                var14.printStackTrace();
            }
        }
    }

    private List<Grade> formatGrade(List<Grade> list, Map<String, Object> params) {
        String timeType = (String) params.get("timeType");
        Calendar cal = Calendar.getInstance();
        for (Grade grade : list) {
            grade.setOkDays(grade.getGreatDays() + grade.getGoodDays());  // 优良天数
            grade.setOkRate((double) grade.getOkDays() / grade.getValidDays());
            grade.setPm25OkDaysRate((double) grade.getPm25OkDays() / grade.getPm25ValidDays());  // pm2.5优良率
            String dataTime = grade.getDataTime();
            int totalDays = 0;

            if ("1".equals(timeType)) {     // 月
                String year = dataTime.split("-")[0];
                String month = dataTime.split("-")[1];
                // 如果是当前月
                if ((cal.get(Calendar.MONTH) + 1) == Integer.parseInt(month)) {
                    totalDays = cal.get(Calendar.DATE) - 1;
                } else {
                    totalDays = DateUtils.getMonthDay(year, month);
                }
            } else if ("2".equals(timeType)) {      // 季
                int year = Integer.parseInt(dataTime.substring(0, 4));
                int season = Integer.parseInt(dataTime.substring(4));
                int month = cal.get(Calendar.MONTH) + 1;  // 当前月
                int nowSeason = getQuarter(month);     // 当前季度
                // 如果是当前季度
                if (season == nowSeason && year == cal.get(Calendar.YEAR)) {
                    totalDays = getNowSeasonDays(season);
                } else {
                    totalDays = getOldSeasonDays(year, season);
                }
            } else if ("3".equals(timeType)) {      // 年
                int year = Integer.parseInt(dataTime);
                // 如果是当前年
                if (cal.get(Calendar.YEAR) == year) {
                    totalDays = cal.get(Calendar.DAY_OF_YEAR) - 1;
                } else {
                    cal.set(Calendar.YEAR, year);
                    totalDays = cal.getActualMaximum(Calendar.DAY_OF_YEAR);
                }
            } else if ("4".equals(timeType)) {      // 时间段
                String startDate = (String) params.get("startDate");
                String endDate = (String) params.get("endDate");
                totalDays = DateUtils.getBetweenDate(startDate, endDate).size() - 1;
            }
            grade.setTotalDays(totalDays);
        }
        return list;
    }

    // 返回已过的季度总天数
    private int getOldSeasonDays(int year, int season) {
        if (season == 1) {
            if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
                return 91;
            } else {
                return 90;
            }
        } else if (season == 2) {
            return 91;
        } else if (season == 3) {
            return 92;
        } else {
            return 92;
        }
    }

    // 返回当前季过了多少天
    private int getNowSeasonDays(int season) {
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        int month = 0;
        switch (season) {
            case 1: month = 1; break;
            case 2: month = 4; break;
            case 3: month = 7; break;
            case 4: month = 10; break;
            default: month = 1;
        }
        LocalDate start = LocalDate.of(year, month, 1);
        long days = now.toEpochDay() - start.toEpochDay();
        return (int) days;
    }

    // 根据月份判断是季度
    private int getQuarter(int month) {
        if(month == 1 || month == 2 || month == 3){
            return 1;
        }else if(month == 4 || month == 5 || month == 6){
            return  2;
        }else if(month == 7 || month == 8 || month == 9){
            return 3;
        }else{
            return 4;
        }
    }

}

