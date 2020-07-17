package com.rate.web.statistic.controller;

import com.rate.system.rate_system.controller.BaseController;
import com.rate.system.rate_system.utils.DateUtils;
import com.rate.system.rate_system.utils.StringUtils;
import com.rate.web.statistic.entity.AverageCompare;
import com.rate.web.statistic.service.CompareService;
import com.rate.web.statistic.service.DayExamineService;
import com.rate.web.statistic.util.ExportExcelStatementUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

/**
 *  涉气污染物环比
 *
 * @author mayn
 * @title: ChainCompareController
 * @projectName rate_miluo_parent
 * @description: TODO
 * @date 2020/7/1410:52
 */

@Controller
@RequestMapping("/statistic/aboutGasChainCompare")
public class AboutGasChainCompareController extends BaseController {
    @Autowired
    private CompareService compareService;

    @Autowired
    private DayExamineService dayExamineService;

    /**
     * 页面跳转
     */
    @RequestMapping("")
    public String goChainComparePage() {
        return "/statistic/chainCompare";
    }


    @ResponseBody
    @RequestMapping("/list")
    public Map<String, Object> chainCompareList(@RequestParam Map<String, Object> params) {
        params = initMonthDate(params);
        List<AverageCompare> averageCompares = compareService.averageCompareList(params);
        Map<String, Object> result = new HashMap<>(6);
        result.put("code", 0);
        result.put("msg", "成功");
        result.put("count", averageCompares.size());
        result.put("data", averageCompares);
        return result;
    }


    @RequestMapping("/exportExcel")
    @ResponseBody
    public void exportExcel(@RequestParam Map<String, Object> params, HttpServletResponse response,
                            HttpServletRequest request) throws Exception {
        params = initMonthDate(params);
        List<AverageCompare> averageCompares =   compareService.averageCompareList(params);

        String startDate = (String) params.get("startDate");
        String endDate = (String) params.get("endDate");
        String dateRange = startDate + "至" + endDate;
        this.doExportXls(response, request, averageCompares, AverageCompare.class, "大气综合分析环比", dateRange);
    }

    protected <T> void doExportXls(HttpServletResponse response, HttpServletRequest request,
                                   List<AverageCompare> dataSet, Class<?> pojoClass, String title, String dateRange) throws Exception {
        response.setContentType("application/vnd.ms-excel");
        ServletOutputStream fOut = null;

        try {
            response.reset();
            response.setHeader("Cache-Control", "private");
            response.setHeader("Pragma", "private");
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("Content-Type", "application/force-download");
            String workbookname = URLEncoder.encode(title + ".xls", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + workbookname);
            ExportExcelStatementUtil excelStatementUtil = new ExportExcelStatementUtil();
            HSSFWorkbook wb = excelStatementUtil.exportChainCompare(dataSet, dateRange);
            fOut = response.getOutputStream();
            wb.write(fOut);
        } catch (Exception var15) {
            var15.printStackTrace();
            throw new Exception("导出失败！");
        } finally {
            try {
                fOut.flush();
                fOut.close();
            } catch (IOException var14) {
                var14.printStackTrace();
            }
        }
    }

    private Map<String, Object> initMonthDate(Map<String, Object> params) {
        Calendar c = Calendar.getInstance();
        if (StringUtils.isBlank((String) params.get("startDate"))
                || StringUtils.isBlank((String) params.get("endDate"))) {
            // 当前时间的前一个月
            c.add(Calendar.DAY_OF_MONTH, -1);
            params.put("startDate", DateUtils.format(c.getTime(), DateUtils.DATE_PATTERN));
            params.put("endDate", DateUtils.format(c.getTime(), DateUtils.DATE_PATTERN));
            // 当前时间的前一天的前一个月
            c.add(Calendar.MONTH, -1);
            params.put("startPreDate", DateUtils.format(c.getTime(), DateUtils.DATE_PATTERN));
            params.put("endPreDate", DateUtils.format(c.getTime(), DateUtils.DATE_PATTERN));
        } else {
            Date startDate = DateUtils.format((String) params.get("startDate"), DateUtils.DATE_PATTERN);
            c.setTime(startDate);
            // 前一个月
            c.add(Calendar.MONTH, -1);
            params.put("startPreDate", DateUtils.format(c.getTime(), DateUtils.DATE_PATTERN));
            Date endDate = DateUtils.format((String) params.get("endDate"), DateUtils.DATE_PATTERN);
            c.setTime(endDate);
            c.add(Calendar.MONTH, -1);
            params.put("endPreDate", DateUtils.format(c.getTime(), DateUtils.DATE_PATTERN));
        }
        return params;
    }
}