package com.rate.web.connected.newdata.controller;


import com.rate.system.rate_system.controller.BaseController;
import com.rate.system.rate_system.service.UserService;
import com.rate.system.rate_system.utils.PageUtils;
import com.rate.web.connected.newdata.entity.AirMinuteSmallStatement;
import com.rate.web.connected.newdata.entity.PolluteWaterMinutes;
import com.rate.web.connected.newdata.entity.WaterAuto;
import com.rate.web.connected.newdata.service.AirMinuteSmallStatementService;
import com.rate.web.connected.newdata.service.PollutantService;
import com.rate.web.connected.newdata.service.PolluteWaterMinutesService;
import com.rate.web.connected.newdata.service.WaterAutoService;
import com.rate.web.site.enums.RoleConstant;
import com.rate.web.statement.entity.PolluteRealtime;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/internet")
public class StatementController extends BaseController {

    private String prefix="module/connected";

    @Autowired
    private AirMinuteSmallStatementService airMinuteSmallStatementService;

    @Autowired
    private PolluteWaterMinutesService polluteWaterMinutesService;

    @Autowired
    PollutantService pollutantService;

    @Autowired
    private WaterAutoService waterAutoService;

    @Autowired
    private UserService userService;

    /**
     * 根据全选展示不同的页面
     * @param model
     * @Return  java.lang.String
     * @Author  chenshixue
     * @Date    2019/12/4 15:54
     */
    @RequestMapping("/latestData")
    public String getAirHourSmallStatement(Model model) {
        Long roleId = getRoleId();
        model.addAttribute("roleId", roleId);
        if(roleId == 1 || roleId == 73 || roleId == 74 || roleId == 75 || roleId == 77){
            return prefix+"/newData/show";
        }else{
            return prefix+"/newData/showPlus";
        }

    }

    /**
     * 空气站点最新分钟数据
     * @param params
     * @param pageSize
     * @param pageNumber
     * @Return  com.rate.system.rate_system.utils.PageUtils
     * @Author  chenshixue
     * @Date    2019/12/4 15:54
     */
    @RequestMapping("/getAir")
    @ResponseBody
    PageUtils getList(@RequestParam Map<String,Object> params, Long pageSize, Long pageNumber) {
        // 按指定站点查询
        String siteCodeList = (String) params.get("siteIds");
        if ( siteCodeList==null || siteCodeList.equals("undefined")|| siteCodeList=="") {
            if (Arrays.asList(RoleConstant.ROLEIDS).contains(getRoleId())) {
                params.put("siteIds", userService.getSitePowerList(getUserId()));
            } else {
                params.put("siteIds", userService.getAllSiteId());
            }
        } else {
            params.put("siteIds", siteCodeList.split(","));
        }
        PageQuery<AirMinuteSmallStatement> paras = new PageQuery<AirMinuteSmallStatement>(pageNumber, pageSize, params);
        PageQuery<AirMinuteSmallStatement> page = airMinuteSmallStatementService.minuteQueryByCondition(paras);
        PageUtils  pageUtils = new PageUtils(page.getList(), page.getTotalRow());
        return pageUtils;
    }

    /**
     * 污染源设备最新分钟数据
     * @param params
     * @Return  java.util.List<com.rate.web.statement.entity.PolluteRealtime>
     * @Author  chenshixue
     * @Date    2019/12/4 15:55
     */
    @RequestMapping("/getPollutantList")
    @ResponseBody
    List<PolluteRealtime> getPollutantList(@RequestParam Map<String,Object> params) {
        // 按指定站点查询
        String siteCodeList = (String) params.get("siteIds");
        if ( siteCodeList==null || siteCodeList.equals("undefined")|| siteCodeList=="") {
            if (Arrays.asList(RoleConstant.ROLEIDS).contains(getRoleId())) {
                params.put("siteIds", userService.getSitePowerList(getUserId()));
            } else {
                params.put("siteIds", userService.getAllSiteId());
            }
        } else {
            params.put("siteIds", siteCodeList.split(","));
        }
        return pollutantService.getPollutantNewestData(params);
    }

    /**
     * 水质自动站最新分钟数据
     * @param params
     * @param pageSize
     * @param pageNumber
     * @Return  com.rate.system.rate_system.utils.PageUtils
     * @Author  chenshixue
     * @Date    2019/12/4 15:55
     */
    @RequestMapping("/getPolluteWaterMinutes")
    @ResponseBody
    PageUtils getPolluteWaterList(@RequestParam Map<String,Object> params, Long pageSize, Long pageNumber) {
        // 按指定站点查询
        String siteCodeList = (String) params.get("siteIds");
        if (siteCodeList==null || siteCodeList.equals("undefined")|| siteCodeList=="") {
            params.remove("siteIds");
        } else {
            params.put("siteIds", siteCodeList.split(","));
        }
        if (Arrays.asList(RoleConstant.ROLEIDS).contains(getRoleId())) {
            params.put("siteIds2", userService.getSitePowerList(getUserId()));
        } else {
            params.put("siteIds2", userService.getAllSiteId());
        }
        PageQuery<PolluteWaterMinutes> paras = new PageQuery<>(pageNumber, pageSize, params);
        PageQuery<PolluteWaterMinutes> page = polluteWaterMinutesService.waterMinutes(paras);
        PageUtils  pageUtils = new PageUtils(page.getList(), page.getTotalRow());
        return pageUtils;
    }


    @RequestMapping("/getWaterAuto")
    @ResponseBody
    PageUtils getWaterAutoList(@RequestParam Map<String,Object> params, Long pageSize, Long pageNumber) {
        // 按指定站点查询
        String siteCodeList = (String) params.get("siteIds");
        if ( siteCodeList==null || siteCodeList.equals("undefined")|| siteCodeList=="") {
            params.remove("siteIds");
        } else {
            params.put("siteIds", siteCodeList.split(","));
        }
        params.put("siteIds2", userService.getSitePowerList(getUserId()));
        PageQuery<WaterAuto> paras = new PageQuery<WaterAuto>(pageNumber, pageSize, params);
        PageQuery<WaterAuto> page = waterAutoService.waterAutoLatest(paras);
        PageUtils  pageUtils = new PageUtils(page.getList(), page.getTotalRow());
        return pageUtils;
    }
}
