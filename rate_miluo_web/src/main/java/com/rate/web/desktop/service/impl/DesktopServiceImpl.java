package com.rate.web.desktop.service.impl;

import com.google.common.collect.Maps;
import com.rate.system.rate_system.utils.DateUtils;
import com.rate.web.desktop.dao.DesktopDao;
import com.rate.web.desktop.entity.MapMarker;
import com.rate.web.desktop.service.DesktopService;
import com.rate.web.graphic.entity.AirHourStatements;
import com.rate.web.site.dao.SiteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author shuzhangyao
 * @date 2019/5/30 19:18
 **/
@Service
public class DesktopServiceImpl implements DesktopService {

    @Autowired
    private DesktopDao desktopDao;

    @Autowired
    private SiteDao siteDao;

    /**
     * 获取最新标准站小时数据
     * @param params 条件参数
     * @return java.util.List<com.rate.web.graphic.entity.AirHourStatements>
     * @author shuzhangyao
     * @date 2019/5/30 19:10
     **/
    @Override
    public List<AirHourStatements> findStandardLatestData(Map<String, Object> params){
        return desktopDao.findStandardLatestData(params);
    }

    @Override
    public List<AirHourStatements> findDesktopRankData(Map<String, Object> params) {
        return desktopDao.findDesktopRankData(params);
    }
    /**
     * 获取全部站点主要信息加上最新小时数据
     * @return java.util.List<com.rate.web.desktop.entity.MapMarker>
     * @author shuzhangyao
     * @date 2019/6/11 14:49
     **/
    @Override
    public List<MapMarker> findMapMarker(Map<String, Object> params){
//        Date currentDate = new Date();
//        String hourAir = DateUtils.format(currentDate, DateUtils.DATE_TIME_HOUR) + ":00:00";
//        String hourWater = DateUtils.format(currentDate, DateUtils.DATE_PATTERN);
//        String microDate = DateUtils.format(currentDate, DateUtils.DATE_TIME_PATTERN3) + ":00";
//        String polluteWater;
        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(currentDate);
        calendar.add(Calendar.MINUTE, -10);
        String tenMinuteStr = DateUtils.format(calendar.getTime(), DateUtils.DATE_TIME_PATTERN3) + ":00";
//        int hour = calendar.get(Calendar.HOUR_OF_DAY);
//        int minute = calendar.get(Calendar.MINUTE);
//        if(hour >=0 && hour < 4){
//            hourWater += " 00:00:00";
//        }else if(hour >=4 && hour < 8){
//            hourWater += " 04:00:00";
//        }else if(hour >=8 && hour < 12){
//            hourWater += " 08:00:00";
//        }else if(hour >=12 && hour < 16){
//            hourWater += " 12:00:00";
//        }else if(hour >=16 && hour < 20){
//            hourWater += " 16:00:00";
//        }else if(hour >=20){
//            hourWater += " 20:00:00";
//        }
//        long time = currentDate.getTime()-(1000*60*60);
//        polluteWater = DateUtils.format(new Date(time), DateUtils.DATE_TIME_HOUR) + ":00:00";
//        if(minute<30){
//            long timeC = currentDate.getTime()-(1000*60*60);
//            hourAir = DateUtils.format(new Date(timeC), DateUtils.DATE_TIME_HOUR) + ":00:00";
//        }
        params.put("date", tenMinuteStr);
        List<MapMarker> airsStandard = desktopDao.findMapMarkerByAir(params);  // 空气标准站  小时
        List<MapMarker> waterPlants = desktopDao.findMapMarkerByWaterPlant(params);  // 污水厂
        List<MapMarker> airs = desktopDao.findMapMarkerByAirMicro(params);  // 空气小型站  分钟
        List<MapMarker> waters = desktopDao.findMapMarkerByWater(params); // 自动站
        List<MapMarker> pollutes = desktopDao.findMapMarkerByPollute(params); // 涉气污染源
        airsStandard.addAll(airs);
        airsStandard.addAll(waters);
        airsStandard.addAll(waterPlants);
        airsStandard.addAll(pollutes);
        return airsStandard;
    }

    @Override
    public List<MapMarker> findMapMarkerNotState(Map<String, Object> params){
        return desktopDao.findMapMarkerNotState(params);
    }

    @Override
    public Map<String, Object> findNewOneMicroData(String siteId, String siteCategory){
        Map<String, Object> params = Maps.newHashMapWithExpectedSize(3);
        params.put("siteId", siteId);
//        params.put("siteCategory", siteCategory);
//        Date currentDate = new Date();
//        String startDate = DateUtils.format(new Date(currentDate.getTime() - 1000*60*60), DateUtils.DATE_TIME_PATTERN3) + ":00";
//        String endDate = DateUtils.format(currentDate, DateUtils.DATE_TIME_PATTERN3) + ":00";
//        params.put("startDate", startDate);
//        params.put("endDate", endDate);
        if("21".equals(siteCategory)){
            return desktopDao.findNewOneMicroData(params);
        }else{
            return desktopDao.findNewOneStandardData(params);
        }
    }


    /**
     * 获取站点在线离线状态
     * @param params 参数
     * @return java.util.List<java.util.Map < java.lang.String , java.lang.Object>>
     * @author shuzhangyao
     * @date 2019/7/3 9:33
     **/
    @Override
    public List<Map<String, Object>> findSiteOnlineState(Map<String, Object> params){
        Date currentDate = new Date();
        Date beforeTwoMinute = new Date(currentDate.getTime() - 1000*60*3);
        Date beforeTwoHour = new Date(currentDate.getTime() - 1000*60*60*2);
        Date beforeFourHour = new Date(currentDate.getTime() - 1000*60*60*4);
        String currentDateStr = DateUtils.format(currentDate, DateUtils.DATE_TIME_PATTERN3);
        String beforeTwoMinuteDateStr = DateUtils.format(beforeTwoMinute, DateUtils.DATE_TIME_PATTERN3);
        params.put("startDate", beforeTwoMinuteDateStr);
        params.put("endDate", currentDateStr);
        List<Map<String, Object>> microState = desktopDao.findMicroSiteOnlineState(params);
        List<Map<String, Object>> polluteState = desktopDao.findPolluteSiteOnlineState(params);
        String currentHourDate = DateUtils.format(currentDate, DateUtils.DATE_TIME_HOUR) + ":00:00";
        String beforeTwoHourDateStr = DateUtils.format(beforeTwoHour, DateUtils.DATE_TIME_HOUR) + ":00:00";
        params.put("startDate", beforeTwoHourDateStr);
        params.put("endDate", currentHourDate);
        List<Map<String, Object>> standardState = desktopDao.findStandardSiteOnlineState(params);
        String beforeFourHourDateStr = DateUtils.format(beforeFourHour, DateUtils.DATE_TIME_HOUR) + ":00:00";
        params.put("startDate", beforeFourHourDateStr);
        List<Map<String, Object>> waterState = desktopDao.findWaterSiteOnlineState(params);
        microState.addAll(standardState);
        microState.addAll(polluteState);
        microState.addAll(waterState);
        return microState;
    }

    /**
     *
     * @param params
     * @return java.util.List<com.rate.web.desktop.entity.MapMarker>
     * @author shuzhangyao
     * @date 2019/7/5 14:27
     **/
    @Override
    public List<Map<String, Object>> findMapMarkerByOnlineState(Map<String, Object> params){
        Date currentDate = new Date();
        Date beforeTwentyMinute = new Date(currentDate.getTime() - 1000 * 60 * 20); // 20分钟
        Date beforeTwoHour = new Date(currentDate.getTime() - 1000 * 60 * 60 * 2);  // 2小时
        Date beforeFourHour = new Date(currentDate.getTime() - 1000 * 60 * 60 * 4); // 4小时
        Date beforeTenMinute = new Date(currentDate.getTime() - 1000 * 60 * 10);    // 10分钟
        String currentDateStr = DateUtils.format(currentDate, DateUtils.DATE_TIME_PATTERN3);
        String beforeTwentyMinuteDateStr = DateUtils.format(beforeTwentyMinute, DateUtils.DATE_TIME_PATTERN3);
//        params.put("startDate", beforeTwoMinuteDateStr);
//        params.put("endDate", currentDateStr);
//        List<Map<String, Object>> micro = desktopDao.findMicroSiteOnlineStateByMarker(params);
//        List<Map<String, Object>> pollute = desktopDao.findPolluteSiteOnlineStateByMarker(params);
        String currentHourDate = DateUtils.format(currentDate, DateUtils.DATE_TIME_HOUR) + ":00:00";
        String beforeTwoHourDateStr = DateUtils.format(beforeTwoHour, DateUtils.DATE_TIME_HOUR) + ":00:00";
//        params.put("startDate", beforeTwoHourDateStr);
//        params.put("endDate", currentHourDate);
//        List<Map<String, Object>> standard = desktopDao.findStandardSiteOnlineStateByMarker(params);
        String beforeFourHourDateStr = DateUtils.format(beforeFourHour, DateUtils.DATE_TIME_HOUR) + ":00:00";
//        params.put("startDate", beforeFourHourDateStr);
//        List<Map<String, Object>> water = desktopDao.findWaterSiteOnlineStateByMarker(params);
//        micro.addAll(standard);
//        micro.addAll(pollute);
//        micro.addAll(water);
        String beforeTenMinuteStr = DateUtils.format(beforeTenMinute, DateUtils.DATE_TIME_PATTERN3);
        //查询所有记录
        params.put("beforeTwentyMinuteDateStr", beforeTwentyMinuteDateStr);    // 空气微型站，涉水污染源，20分钟内
//        params.put("microEndDate", currentDateStr);
        params.put("beforeTwoHourDateStr", beforeTwoHourDateStr);      // 空气标准站，2小时内
//        params.put("standardEndDate", currentHourDate);
//        params.put("waterStartDate", beforeFourHourDateStr);
//        params.put("waterEndDate", currentHourDate);
        params.put("beforeTenMinuteStr", beforeTenMinuteStr);       // 水质站，污染源企业，10分钟
//        params.put("pollutantEndDate", currentDateStr);

        // 所有设备，及设备所属的站点信息
        List<Map<String, Object>> all = desktopDao.findWaterSiteOnlineStateByMarkerAll(params);

        // 除去涉水污染源国祯站点，其他的按10分钟
        for (Map<String, Object> map : all) {
            if (53 != (Integer)map.get("siteId") && "4".equals(map.get("siteType").toString())) {
                Object obj = map.get("dateTime");
                if (obj != null) {
                    Date dataTime = DateUtils.format(obj.toString(), DateUtils.DATE_TIME_PATTERN);
                    if ((new Date().getTime() - dataTime.getTime()) > 1000*60*10) {
                        map.put("dateTime", null);
                    }
                }
            }
        }
        // 查询所有报警信息，时间和查询最新数据时间一样
        List<Map<String, Object>> warnList = desktopDao.findMarkerOnlineStateByType(params);

        // 判断报警，合在一起，如果有一个设备报警，就是报警状态
        return putWarn(all, warnList);
    }

    /**
     * 一个站点在指定的在线时间范围内，有一个设备报警，则被判定为报警状态
     * 所以当前报警数据，不一定是最新的那条
     */
    private List<Map<String, Object>> putWarn(List<Map<String, Object>> all, List<Map<String, Object>> warn) {

        List<Map<String, Object>> siteList = new LinkedList<>();

        Map<String, Object> map = new HashMap<>();
        Map<String, Object> ids = new HashMap<>();
        for(Map<String, Object> equ : all){
            String siteId = equ.get("siteId").toString();
            String equId = (String) equ.get("equmentId");
            boolean flag = false;
            if (equ.get("dateTime") != null) {
                ids.put(siteId, equ.get("dateTime"));
            }
            for (Map<String, Object> item : warn) {
                String equipmentId = item.get("equipmentId").toString();
                if (equipmentId.equals(equId)) {
                    // 说明有报警的
                    equ.put("pollutant", true);
                    flag = true;
                    map.put(siteId, equ);
                    break;
                }
            }
            if (!flag) {
                HashMap map1 = (HashMap) map.get(siteId);
                if (map1 == null || !Boolean.valueOf(map1.get("pollutant").toString())) {
                    equ.put("pollutant", false);
                    map.put(siteId, equ);
                }
            }
        }

        map.forEach((k,v) -> {
            Map<String, Object> site = (HashMap)v;
            if (ids.containsKey(k)) {
                site.put("dateTime", ids.get(k));
            }
            siteList.add(site);
        });
        return siteList;
    }

}
