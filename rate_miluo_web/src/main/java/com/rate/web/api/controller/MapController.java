package com.rate.web.api.controller;

import com.rate.system.rate_system.api.service.ApiLoginService;
import com.rate.system.rate_system.utils.CodeMsg;
import com.rate.system.rate_system.utils.Result;
import com.rate.web.api.constant.Constant;
import com.rate.web.api.service.HourPolluteService;
import com.rate.web.api.service.HourStatementsService;
import com.rate.web.api.service.WaterSiteHourService;
import com.rate.web.connected.newdata.entity.PolluteWaterHourStatement;
import com.rate.web.desktop.service.DesktopService;
import com.rate.web.site.service.SiteService;
import com.rate.web.statement.service.PolluteService;
import com.rate.web.vidicon.entity.Vidicon;
import com.rate.web.vidicon.service.VidiconService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * APP地图接口
 *
 * @ClassName MapController
 * @Author liuyong
 * @Date 2019/6/4 17:10
 * @Version 1.0
 **/
@RestController
@RequestMapping("api/map")
public class MapController {

    @Autowired
    private ApiLoginService apiLoginService;

    @Autowired
    private DesktopService desktopService;

    @Autowired
    private HourStatementsService hourStatementsService;

    @Autowired
    private WaterSiteHourService waterSiteHourService;

    @Autowired
    private HourPolluteService hourPolluteService;

    @Autowired
    private PolluteService polluteService;

    @Autowired
    private VidiconService vidiconService;

    @Autowired
    private SiteService siteService;


    /**
     * 地图页面
     *
     * @param userId
     * @param code
     * @return
     */
    @RequestMapping("getMapData")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "code", value = "令牌", paramType = "query", dataType = "String"),
    })
    public Result getMapData(String userId, String code) {
        try {
            boolean checkLogin = apiLoginService.checkLogin(userId, code);
            if (!checkLogin) {
                //用户未登录
                return Result.error(CodeMsg.VALIDATA_ERROR);
            }
            List<String> siteIds = siteService.querySiteByUser(userId);
            if(siteIds == null || siteIds.isEmpty()){
                return Result.success();
            }
            Map<String, Object> params = new HashMap<>(5);
            params.put("siteIds", siteIds);
            return Result.success(desktopService.findMapMarker(params));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(CodeMsg.SERVER_BUSY);
        }
    }

    /**
     * 查看站点详情
     *
     * @param userId   用户id
     * @param code     登录令牌
     * @param siteType 站点类型
     * @param siteId   站点编号
     * @return
     */
    @RequestMapping("querySiteInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "code", value = "令牌", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "siteType", value = "站点类型", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "siteId", value = "站点编号", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "queryTime", value = "查询时间", paramType = "query", dataType = "String"),
    })
    public Result querySiteInfo(@RequestParam("userId") String userId, @RequestParam("code") String code,
                                @RequestParam("siteType") String siteType, @RequestParam("siteId") String siteId,
                                @RequestParam(value = "queryTime", required = false) String queryTime) {
        try {
            boolean checkLogin = apiLoginService.checkLogin(userId, code);
            if (!checkLogin) {
                //用户未登录
                return Result.error(CodeMsg.VALIDATA_ERROR);
            }
            Map<String, Object> resultMap = new HashMap<>(5);
            // 水质自动站
            if (Constant.SITE_WATER.equals(siteType)) {
                //过去24小时水质自动站数据
                resultMap.put("waterHour", waterSiteHourService.getOldHour(siteId, queryTime));
                // 获取最近一条数据
                resultMap.put("waterNewest", waterSiteHourService.getFromDesktop(siteId));
            }
            //空气站
            if (Constant.SITE_AIR.equals(siteType)) {
                List<String> siteIds = Stream.of(siteId).collect(Collectors.toList());
                //过去24小时空气数据
                resultMap.put("airHour", hourStatementsService.getOldHour(siteIds, queryTime));
                // 获取最近一条数据
                resultMap.put("airNewest", hourStatementsService.getFromDesktop(siteId));
            }
            // 污染源
            if (Constant.SITE_POLLUTE.equals(siteType)) {
                resultMap.put("polluteHour", polluteService.getOldHour(siteId, queryTime));
                // 获取最近一条数据
                resultMap.put("polluteNewest", polluteService.getFromDesktop(siteId));
            }
            //污水厂
            if (Constant.SITE_WATER_PLANT.equals(siteType)) {
                //过去24小时涉气污染源
                Map<String, Object> map = new HashMap<>(4);
                //出水口
//                List<PolluteWaterHourStatement> outlets = new ArrayList<>();
                //进水口
//                List<PolluteWaterHourStatement> inlets = new ArrayList<>();
                //获取过去24小时数据
                List<PolluteWaterHourStatement> oldHour = hourPolluteService.getOldHour(siteId, queryTime);
                oldHour.forEach(element -> {
                    if (!map.containsKey(element.getName())) {
                        List<PolluteWaterHourStatement> list = new ArrayList<>();
                        list.add(element);
                        map.put(element.getName(), list);
                    } else {
                        ((List<PolluteWaterHourStatement>) map.get(element.getName())).add(element);
                    }
//                    if (element.getVideoName().indexOf(RoleConstant.OUTLET) != -1) {
//                        outlets.add(element);
//                    } else if (element.getVideoName().indexOf(RoleConstant.INLET) != -1) {
//                        inlets.add(element);
//                    }
                });
//                boolean only = (outlets.isEmpty() && !inlets.isEmpty()) || (inlets.isEmpty() && !outlets.isEmpty());
//                if (only) {
//                    map.put("only", true);
//                } else {
//                    map.put("only", false);
//                }
//                map.put("outlet", outlets);
//                map.put("inlet", inlets);
                resultMap.put("waterPlant", map);
                resultMap.put("waterPlantNewest", hourPolluteService.getFromDesktop(siteId));
            }
            //视频
            List<Vidicon> videos = vidiconService.getVidiconsBySiteId(Integer.parseInt(siteId));
            resultMap.put("video", videos);
            return Result.success(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(CodeMsg.SERVER_BUSY);
        }
    }
}
