package com.rate.web.connected.datarate.controller;

import com.rate.system.rate_system.utils.PageUtils;
import com.rate.system.rate_system.utils.StringUtils;
import com.rate.web.connected.datarate.service.DataRateService;
import com.rate.web.connected.newdata.entity.AirHourStatement;
import com.rate.web.connected.newdata.entity.PolluteWaterHourStatement;
import com.rate.web.connected.newdata.service.AirHourStatementService;
import com.rate.web.statement.entity.PolluteRealtime;
import com.rate.web.statement.entity.WaterSite;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @Author chenshixue
 * @Date 2019/12/5
 */
@Controller
@RequestMapping("/dataDetail")
public class DataDetailController {

    @Autowired
    private AirHourStatementService airHourStatementService;
    @Autowired
    private DataRateService dataRateService;

    @RequestMapping("/details/{type}")
    public String getWaterAutoDetails(@PathVariable String type, @RequestParam(value="siteCode",required=false) String siteCode, Model model) {
        model.addAttribute("type", type);
        model.addAttribute("siteCode",siteCode);
        return "/module/connected/dataRate/details";
    }

    /**
     * 空气站点小时数据详情
     * @param params
     * @param pageSize
     * @param pageNumber
     * @Return  com.rate.system.rate_system.utils.PageUtils
     * @Author  chenshixue
     * @Date    2019/12/5 14:03
     */
    @RequestMapping("/airDetailList")
    @ResponseBody
    PageUtils getDetailsList(@RequestParam Map<String, Object> params, Long pageSize, Long pageNumber){
        String siteCode = (String) params.get("siteCode");
        if (StringUtils.isBlank(siteCode)) {
            return null;
        }
        PageQuery<AirHourStatement> paras=new PageQuery<>(pageNumber,pageSize,params);
        PageQuery<AirHourStatement> page = airHourStatementService.findDetails(paras);
        PageUtils pageUtils = new PageUtils(page.getList(), page.getTotalRow());
        return pageUtils;
    }

    /**
     * 污染源站点小时数据详情
     * @param params
     * @Return  java.util.List<com.rate.web.statement.entity.PolluteRealtime>
     * @Author  chenshixue
     * @Date    2019/12/5 14:03
     */
    @RequestMapping("/polluteDetailList")
    @ResponseBody
    public List<PolluteRealtime> getPollutantList(@RequestParam Map<String, Object> params) {
        String siteCode = (String) params.get("siteCode");
        if (StringUtils.isBlank(siteCode)) {
            return null;
        }
        return dataRateService.get24HourPollutantList(params);
    }

    /**
     * 污水厂站点小时数据详情
     * @param params
     * @Return  java.util.List<com.rate.web.connected.newdata.entity.PolluteWaterHourStatement>
     * @Author  chenshixue
     * @Date    2019/12/5 14:03
     */
    @RequestMapping("/waterPlantDetailList")
    @ResponseBody
    public List<PolluteWaterHourStatement> getWaterPlantList(@RequestParam Map<String, Object> params) {
        String siteCode = (String) params.get("siteCode");
        if (StringUtils.isBlank(siteCode)) {
            return null;
        }
        return dataRateService.get24HourWaterPlantList(params);
    }

    @RequestMapping("/waterDetailList")
    @ResponseBody
    public List<WaterSite> getWaterList(@RequestParam Map<String, Object> params) {
        String siteCode = (String) params.get("siteCode");
        if (StringUtils.isBlank(siteCode)) {
            return null;
        }
        return dataRateService.get24HourWaterList(params);
    }
}
