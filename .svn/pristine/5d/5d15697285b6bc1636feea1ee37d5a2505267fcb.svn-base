package com.rate.web.connected.datarate.controller;

import com.rate.system.rate_system.controller.BaseController;
import com.rate.system.rate_system.service.UserService;
import com.rate.web.connected.datarate.service.DataRateService;
import com.rate.web.connected.newdata.entity.PolluteWaterHourStatement;
import com.rate.web.site.entity.Site;
import com.rate.web.site.enums.RoleConstant;
import com.rate.web.statement.entity.PolluteRealtime;
import com.rate.web.statement.entity.WaterSite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Author chenshixue
 * @Date 2019/12/4
 */
@Controller
@RequestMapping("dataRate")
public class DataRateController2 extends BaseController {

    @Autowired
    DataRateService dataRateService;
    @Autowired
    UserService userService;

    /**
     * 空气24小时数据获取率
     * @param params
     * @Return  java.util.List<com.rate.web.site.entity.Site>
     * @Author  chenshixue
     * @Date    2019/12/5 11:24
     */
    @RequestMapping("/getAirList")
    @ResponseBody
    public List<Site> getDataRateAirList(@RequestParam Map<String, Object> params) {
        // 按指定站点查询
        String siteCodeList = (String) params.get("siteIds");
        if (siteCodeList==null || siteCodeList.equals("undefined")|| siteCodeList=="") {
            if (Arrays.asList(RoleConstant.ROLEIDS).contains(getRoleId())) {
                params.put("siteIds", userService.getSitePowerList(getUserId()));
            } else {
                params.put("siteIds", userService.getAllSiteId());
            }
        } else {
            params.put("siteIds", siteCodeList.split(","));
        }
        List<Site> list = dataRateService.getDataRateAirList(params);
        return list;
    }

    /**
     * 污染源24小时数据获取率
     * @param params
     * @Return  java.util.List<com.rate.web.statement.entity.PolluteRealtime>
     * @Author  chenshixue
     * @Date    2019/12/5 11:24
     */
    @RequestMapping("/getPollutantList")
    @ResponseBody
    public List<PolluteRealtime> getDataRatePolluteList(@RequestParam Map<String, Object> params) {
        // 按指定站点查询
        String siteCodeList = (String) params.get("siteIds");
        if (siteCodeList==null || siteCodeList.equals("undefined")|| siteCodeList=="") {
            if (Arrays.asList(RoleConstant.ROLEIDS).contains(getRoleId())) {
                params.put("siteIds", userService.getSitePowerList(getUserId()));
            } else {
                params.put("siteIds", userService.getAllSiteId());
            }
        } else {
            params.put("siteIds", siteCodeList.split(","));
        }
        List<PolluteRealtime> list = dataRateService.getDataRatePolluteList(params);
        return list;
    }

    /**
     * 涉水污染源24小时数据获取率
     * @param params
     * @Return  java.util.List<com.rate.web.connected.newdata.entity.PolluteWaterHourStatement>
     * @Author  chenshixue
     * @Date    2019/12/5 11:24
     */
    @RequestMapping("/getWaterPlantList")
    @ResponseBody
    public List<PolluteWaterHourStatement> getDataRateWaterPlantList(@RequestParam Map<String, Object> params) {
        // 按指定站点查询
        String siteCodeList = (String) params.get("siteIds");
        if (siteCodeList==null || siteCodeList.equals("undefined")|| siteCodeList=="") {
            if (Arrays.asList(RoleConstant.ROLEIDS).contains(getRoleId())) {
                params.put("siteIds", userService.getSitePowerList(getUserId()));
            } else {
                params.put("siteIds", userService.getAllSiteId());
            }
        } else {
            params.put("siteIds", siteCodeList.split(","));
        }
        List<PolluteWaterHourStatement> list = dataRateService.getDataRateWaterPlantList(params);
        return list;
    }

    /**
     * 水质站24小时数据获取率
     * @param params
     * @Return  java.util.List<com.rate.web.statement.entity.WaterSite>
     * @Author  chenshixue
     * @Date    2019/12/5 11:25
     */
    @RequestMapping("/getWaterList")
    @ResponseBody
    public List<WaterSite> getDataRateWaterList(@RequestParam Map<String, Object> params) {
        // 按指定站点查询
        String siteCodeList = (String) params.get("siteIds");
        if (siteCodeList==null || siteCodeList.equals("undefined")|| siteCodeList=="") {
            if (Arrays.asList(RoleConstant.ROLEIDS).contains(getRoleId())) {
                params.put("siteIds", userService.getSitePowerList(getUserId()));
            } else {
                params.put("siteIds", userService.getAllSiteId());
            }
        } else {
            params.put("siteIds", siteCodeList.split(","));
        }
        List<WaterSite> list = dataRateService.getDataRateWaterList(params);
        return list;
    }


}
