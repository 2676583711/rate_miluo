package com.rate.web.api.controller;

import com.rate.system.rate_system.api.service.ApiLoginService;
import com.rate.system.rate_system.utils.CodeMsg;
import com.rate.system.rate_system.utils.Result;
import com.rate.system.rate_system.utils.StringUtils;
import com.rate.web.api.constant.Constant;
import com.rate.web.api.service.DailyStatementsService;
import com.rate.web.api.service.DailyWaterSiteService;
import com.rate.web.api.service.HourStatementsService;
import com.rate.web.api.service.WaterSiteHourService;
import com.rate.web.site.service.SiteService;
import com.rate.web.statement.service.PolluteService;
import com.rate.web.statement.service.WaterPlantService;
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

/**
 * APP统计页面接口
 *
 * @ClassName StatisticsController
 * @Author liuyong
 * @Date 2019/6/5 17:12
 * @Version 1.0
 **/
@RestController
@RequestMapping("api/statistics")
public class StatisticsController {

    /**
     * 登录验证
     */
    @Autowired
    private ApiLoginService apiLoginService;

    /**
     * 空气小时
     */
    @Autowired
    private HourStatementsService hourStatementsService;

    /**
     * 空气日
     */
    @Autowired
    private DailyStatementsService dailyStatementsService;

    /**
     * 站点
     */
    @Autowired
    private SiteService siteService;

    /**
     * 涉水污染源
     */
    @Autowired
    private WaterPlantService waterPlantService;

    /**
     * 污染源
     */
    @Autowired
    private PolluteService polluteService;

    /**
     * 水质自动站小时
     */
    @Autowired
    private WaterSiteHourService waterSiteHourService;

    /**
     * 水质自动站日
     */
    @Autowired
    private DailyWaterSiteService dailyWaterSiteService;

    /**
     * 获取水自动站的数据
     *
     * @param userId
     * @param code
     * @param startTime
     * @param endTime
     * @param siteCode
     * @param dataType
     * @return
     */
    @RequestMapping("getWaterData")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "code", value = "令牌", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "siteCode", value = "站点编号", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "dataType", value = "数据类型", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pageNum", value = "起始值", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示的数量", paramType = "query", dataType = "Integer"),
    })
    public Result getWaterData(String userId, String code, String startTime, String endTime, String siteCode, String dataType, Integer pageNum, Integer pageSize) {
        try {
            if (!apiLoginService.checkLogin(userId, code)) {
                //用户未登录
                return Result.error(CodeMsg.VALIDATA_ERROR);
            }
            List<String> codes = new ArrayList<>();
            if (StringUtils.isNotBlank(siteCode)) {
                codes.add(siteCode);
            } else {
                codes = getSite(userId, Constant.SITE_WATER);
            }
            if (codes == null || codes.isEmpty()) {
                return Result.success();
            }
            if (StringUtils.isNotBlank(dataType)) {
                String tableName = null;
                if (dataType.equals(Constant.DATA_REAL)) {
                    tableName = "miluo_water_site";
                }
                if (dataType.equals(Constant.DATA_HOUR)) {
                    // 水质自动站小时数据
                    tableName = "miluo_hour_water_site";
                }
                if (dataType.equals(Constant.DATA_DAY))
                    // 水质自动站日数据
                    tableName = "miluo_days_water_site";{
                }
                if (tableName != null) {
                    return Result.success(waterSiteHourService.queryListByCodeAndTime(tableName, codes, startTime, endTime, pageNum, pageSize));
                }
            }
            return Result.error(CodeMsg.PARAMETER_ERROE);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(CodeMsg.SERVER_BUSY);
        }
    }

    /**
     * 获取空气的数据
     *
     * @param params
     * @return
     */
    @RequestMapping("getAirData")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "code", value = "令牌", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "dataType", value = "数据类型", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "siteId", value = "站点编号", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pageNum", value = "起始值", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示的数量", paramType = "query", dataType = "Integer"),
    })
    public Result getAirData(@RequestParam Map<String, Object> params, Integer pageNum, Integer pageSize) {
        try {
            if (params == null) {
                return Result.error(CodeMsg.PARAMETER_ISNULL);
            }
            String userId = (String) params.get("userId");
            boolean checkLogin = apiLoginService.checkLogin(userId, (String) params.get("code"));
            if (!checkLogin) {
                //用户未登录
                return Result.error(CodeMsg.VALIDATA_ERROR);
            }
            // 数据类型
            String dataType = (String) params.get("dataType");
            String siteId = (String) params.get("siteId");
            List<String> codes = new ArrayList<>();
            if (StringUtils.isNotBlank(siteId)) {
                codes.add(siteId);
            } else {
                codes = getSite(userId, Constant.SITE_AIR);
            }
            if (codes == null || codes.isEmpty()) {
                return Result.success();
            }
            //站点编码的集合
            params.put("siteIds", codes);
            params.put("pageNum", pageNum);
            params.put("pageSize", pageSize);
            //小时数据
            if (Constant.DATA_HOUR.equals(dataType)) {
                return Result.success(hourStatementsService.getListByTimeAndSiteCode(params));
            }
            //日数据
            if (Constant.DATA_DAY.equals(dataType)) {
                return Result.success(dailyStatementsService.getListByTimeAndSiteCode(params));
            }
            return Result.error(CodeMsg.PARAMETER_ERROE);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(CodeMsg.SERVER_BUSY);
        }
    }

    /**
     * 获取涉水污染源的数据
     *
     * @param userId
     * @param code
     * @param startTime
     * @param endTime
     * @param siteId
     * @param dataType
     * @return
     */
    @RequestMapping("getPolluteWaterData")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "code", value = "令牌", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "siteId", value = "站点编号", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "dataType", value = "数据类型", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pageNum", value = "起始值", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示的数量", paramType = "query", dataType = "Integer"),
    })
    public Result getPolluteWaterData(String userId, String code, String startTime, String endTime, String siteId,
                                 String dataType, Integer pageNum, Integer pageSize) {
        try {
            boolean checkLogin = apiLoginService.checkLogin(userId, code);
            if (!checkLogin) {
                //用户未登录
                return Result.error(CodeMsg.VALIDATA_ERROR);
            }

            List<String> siteIds = new ArrayList<>();
            if (StringUtils.isNotBlank(siteId)) {
                siteIds.add(siteId);
            } else {
                siteIds = getSite(userId, Constant.SITE_WATER_PLANT);
            }
            if (siteIds == null || siteIds.isEmpty()) {
                return Result.success();
            }
            Map<String, Object> params = new HashMap<>(8);
            params.put("beginTime", startTime);
            params.put("endTime", endTime);
            params.put("siteIds", siteIds);
            params.put("pageNum", pageNum);
            params.put("pageSize", pageSize);
            if (dataType != null) {
                // 实时分钟数据
                if (dataType.equals(Constant.DATA_REAL)) {
                    return Result.success(waterPlantService.minuteList(params));
                }
                // 小时数据
                if (dataType.equals(Constant.DATA_HOUR)) {
                    return Result.success(waterPlantService.hourList(params));
                }
                // 日数据
                if (dataType.equals(Constant.DATA_DAY)) {
                    return Result.success(waterPlantService.dailyList(params));
                }
            }
            return Result.error(CodeMsg.PARAMETER_ERROE);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(CodeMsg.SERVER_BUSY);
        }
    }

    /**
     * 获取涉气污染源
     * @param params
     * @param pageNum
     * @param pageSize
     * @Return  com.rate.system.rate_system.utils.Result
     * @Author  chenshixue
     * @Date    2019/11/27 15:16
     */
    @RequestMapping("getPolluteData")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "code", value = "令牌", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "siteId", value = "站点编号", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "dataType", value = "数据类型", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pageNum", value = "起始值", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示的数量", paramType = "query", dataType = "Integer"),
    })
    public Result getPolluteData(@RequestParam Map<String, Object> params, Integer pageNum, Integer pageSize) {
        try {
            if (params == null) {
                return Result.error(CodeMsg.PARAMETER_ISNULL);
            }
            String userId = (String) params.get("userId");
            boolean checkLogin = apiLoginService.checkLogin(userId, (String) params.get("code"));
            if (!checkLogin) {
                // 用户未登录
                return Result.error(CodeMsg.VALIDATA_ERROR);
            }
            // 数据类型
            String dataType = (String) params.get("dataType");
            String siteId = (String) params.get("siteId");
            List<String> siteIds = new ArrayList<>();
            if (StringUtils.isNotBlank(siteId)) {
                siteIds.add(siteId);
            } else {
                siteIds = getSite(userId, Constant.SITE_POLLUTE);
            }
            if (siteIds == null || siteIds.isEmpty()) {
                return Result.success();
            }
//            List<String> equmentIds = polluteService.findEqumentIds(siteIds);
            params.put("siteIds", siteIds);
            params.put("siteIdStr", String.join(",", siteIds));
//            params.put("equmentIds", String.join(",", equmentIds));
            params.put("pageNum", pageNum);
            params.put("pageSize", pageSize);
            if (dataType != null) {
                // 实时分钟数据
                if (dataType.equals(Constant.DATA_REAL)) {
                    return Result.success(polluteService.appMinuteList(params));
                }
                // 小时数据
                if (dataType.equals(Constant.DATA_HOUR)) {
                    return Result.success(polluteService.appHourList(params));
                }
                // 日数据
                if (dataType.equals(Constant.DATA_DAY)) {
                    return Result.success(polluteService.appDailyList(params));
                }
            }
            return Result.error(CodeMsg.PARAMETER_ERROE);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(CodeMsg.SERVER_BUSY);
        }
    }

    /**
     * 根据userId type 获取站点集合
     *
     * @param userId
     * @param type
     * @return
     * @author LiuYong
     */
    private List<String> getSite(String userId, String type) {
        return siteService.querySiteByUserAndType(userId, type);
    }
}
