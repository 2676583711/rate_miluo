package com.rate.web.statistic.controller;

import com.rate.system.rate_system.controller.BaseController;
import com.rate.system.rate_system.service.UserService;
import com.rate.web.site.service.SiteService;
import com.rate.web.statement.vo.AirDailyVO;
import com.rate.web.statistic.service.CompareService;
import com.rate.web.statistic.entity.AverageCompare;
import com.rate.web.statistic.util.DateParamUtil;
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
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * 大气综合分析同比
 *
 * @author mayn
 * @title: CompareController
 * @projectName rate_miluo_parent
 * @description: TODO
 * @date 2020/7/1410:21
 */

@Controller
@RequestMapping("/statistic/averageCompare")
public class AirAverageCompareController extends BaseController {
    @Autowired
    private CompareService compareService;
    @Autowired
    private UserService userService;

    /**
     * 页面跳转
     */
    @RequestMapping()
    public String goAverageCompare() {
        return "/statistic/averageCompareList";
    }

    /**
     * 同比数据
     *
     * @param params
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public List<AverageCompare> list(@RequestParam Map<String, Object> params) {
        //site id
        params = DateParamUtil.initParam(params, Calendar.YEAR);
        String siteCode = (String) params.get("siteCodes");
        String[] siteCodes = null;
        if (siteCode != null && !siteCode.equals("")) {
//            siteCodes = siteCode.split(",");
//            params.put("siteCodes", siteCodes);
            params.put("siteIds", userService.getSitePowerList(getUserId()));
        }
        List<AverageCompare> averageCompares = compareService.averageCompareList(params);
        return averageCompares;
    }

    /**
     * 导出数据到 excel
     *
     * @param params
     * @param response
     * @param request
     * @throws Exception
     */
    @RequestMapping("/exportExcel")
    @ResponseBody
    public void exportExcel(@RequestParam Map<String, Object> params, HttpServletResponse response,
                            HttpServletRequest request) throws Exception {
        params = DateParamUtil.initParam(params, Calendar.YEAR);
        List<AverageCompare> averageCompares = compareService.averageCompareList(params);
        String startDate = (String) params.get("startDate");
        String endDate = (String) params.get("endDate");
        String dateRange = startDate + "至" + endDate;
        this.doExportXls(response, request, averageCompares, AverageCompare.class, "大气综合分析同比", dateRange);
    }

    /**
     * 导出数据到 excel,实际执行者
     *
     * @param response
     * @param request
     * @param dataSet
     * @param pojoClass
     * @param title
     * @param dateRange
     * @param <T>
     * @throws Exception
     */
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
            HSSFWorkbook wb = excelStatementUtil.exportAverageCompare(dataSet, dateRange);
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
}