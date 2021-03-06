package com.rate.web.desktop.controller;

import com.alibaba.fastjson.JSONObject;
import com.rate.system.rate_system.constant.RoleConstant;
import com.rate.system.rate_system.controller.BaseController;
import com.rate.system.rate_system.utils.DateUtils;
import com.rate.web.alarm.entity.AlarmEntity;
import com.rate.web.desktop.entity.MapMarker;
import com.rate.web.desktop.service.DesktopService;
import com.rate.web.graphic.entity.AirHourStatements;
import com.rate.web.graphic.entity.RealTimeTreatmentPlant;
import com.rate.web.graphic.entity.WaterDataStatments;
import com.rate.web.graphic.service.GraphicAirService;
import com.rate.web.graphic.service.GraphicPolluteService;
import com.rate.web.graphic.service.GraphicPolluteWaterService;
import com.rate.web.graphic.service.GraphicWaterService;
import com.rate.web.site.entity.Site;
import com.rate.web.site.service.SiteService;
import com.rate.web.statement.entity.PolluteRealtime;
import com.rate.web.statement.service.PolluteService;
import com.rate.web.statement.util.TableSelUtil;
import com.rate.web.vidicon.entity.Vidicon;
import com.rate.web.vidicon.service.VidiconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author shuzhangyao
 * @date 2019/5/30 19:15
 **/
@Controller
@RequestMapping("/desktop")
public class DesktopController extends BaseController {

    @Autowired
    private DesktopService desktopService;
    @Autowired
    private SiteService siteService;
    @Autowired
    private VidiconService vidiconService;
    @Autowired
    private GraphicAirService airService;
    @Autowired
    private GraphicWaterService waterService;
    @Autowired
    private GraphicPolluteWaterService polluteWaterService;
    @Autowired
    private GraphicPolluteService polluteService;

    @Autowired
    private PolluteService polluteService2;


    @GetMapping("/standard/latestData")
    @ResponseBody
    public List<AirHourStatements> standardLatestData(@RequestParam Map<String, Object> params) {
        return desktopService.findStandardLatestData(params);
    }

    /**
     * 查询全部站点   附带一条小时数居
     *
     * @param params 参数
     * @return java.util.List<com.rate.web.desktop.entity.MapMarker>
     * @author shuzhangyao
     * @date 2019/6/11 14:47
     **/
    @RequestMapping("/site/marker")
    @ResponseBody
    public List<Map<String, Object>> findMapMarker(@RequestParam Map<String, Object> params) {
        List<Site> all = vidiconService.findSiteListByUserId(getUserId());
        if(all!=null){
            List<String> siteIds = all.stream().map(Site::getId).collect(Collectors.toList());
            params.put("siteIds", siteIds);
        }
//        return desktopService.findMapMarker(params);
        return desktopService.findMapMarkerByOnlineState(params);
    }

    /**
     *查询全部站点
     * @param params 参数
     * @return java.util.List<com.rate.web.desktop.entity.MapMarker>
     * @author shuzhangyao
     * @date 2019/7/6 11:59
     **/
    @RequestMapping("/site/only/marker")
    @ResponseBody
    public List<MapMarker> findMapMarkerNotState(@RequestParam Map<String, Object> params) {
        List<Site> all;
        if (!RoleConstant.ROLE_IDS_INNER.contains(getRoleId())) {
            all = siteService.queryListByUserIdAndSiteType(getUserId().toString(),"");
        } else {
            all = siteService.getAllSite();
        }
        if(all!=null){
            List<String> siteIds = all.stream().map(Site::getId).collect(Collectors.toList());
            params.put("siteIds", siteIds);
        }
        return desktopService.findMapMarkerNotState(params);
    }

    /**
     * 排名
     *
     * @param params
     * @return java.util.List<com.rate.web.graphic.entity.AirHourStatements>
     * @author shuzhangyao
     * @date 2019/6/3 18:25
     **/
    @GetMapping("/rank/list")
    @ResponseBody
    public List<AirHourStatements> getRank(@RequestParam Map<String, Object> params) {
        Long userId = getUserId();
        params.put("userId", userId);
        List<Site> currentSite = siteService.getSiteList(userId.intValue());
        List<String> siteCodes = currentSite.stream().map(Site::getSiteCode).collect(Collectors.toList());
        params.put("siteCodes", siteCodes);
        return desktopService.findDesktopRankData(params);
    }

    @GetMapping("/air/site")
    @ResponseBody
    public JSONObject airSiteInfo(@RequestParam Map<String, Object> params){
        String siteId = (String) params.get("siteId");
        JSONObject result = new JSONObject();
        Map<String, Object> info = siteService.findSiteInfoById(siteId);
        result.put("info", info);
        List<Vidicon> siteVidicons = vidiconService.getVidiconsBySiteId(Integer.valueOf(siteId));
        result.put("videos", siteVidicons);
        long stime = System.currentTimeMillis() - (1000 * 60 * 60 * 24);
        String hourStartDate = DateUtils.format(new Date(stime), DateUtils.DATE_TIME_PATTERN);
        String hourEndDate = DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN);
        params.put("startDate", hourStartDate);
        params.put("endDate", hourEndDate);
        String siteCategory = (String) info.get("siteCategory");

        JSONObject obj = airService.findChangeData(params);
          //24小时内没数据，认为离线，取最近一条数据
        Map<String, Object> newData = desktopService.findNewOneMicroData(siteId,siteCategory);
        result.put("newData", newData);

        for (String key : obj.keySet()) {
            List<AirHourStatements> hour = (List<AirHourStatements>) obj.get(key);
            result.put("hour", hour);
        }


        return result;
    }

    @GetMapping("/polluteWater/site")
    @ResponseBody
    public JSONObject polluteWaterSiteInfo(@RequestParam Map<String, Object> params){
        String siteId = (String) params.get("siteId");
        JSONObject result = new JSONObject();
        Map<String, Object> info = siteService.findSiteInfo(siteId);
        result.put("info", info);
//        Integer siteId = (Integer) info.get("siteId");
        List<Vidicon> siteVidicons = vidiconService.getVidiconsBySiteId(Integer.valueOf(siteId));
        result.put("videos", siteVidicons);
        long stime = System.currentTimeMillis() - (1000 * 60 * 60 * 24);
        String hourStartDate = DateUtils.format(new Date(stime), DateUtils.DATE_TIME_PATTERN);
        String hourEndDate = DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN);
        params.put("startDate", hourStartDate);
        params.put("endDate", hourEndDate);
        JSONObject obj = polluteWaterService.findChangeData(params);
        JSONObject newData = polluteWaterService.findNewMinuteData(params);
        result.put("newData", newData);
        result.put("hour", obj);
//        for (String key : obj.keySet()) {
//            List<RealTimeTreatmentPlant> hour = (List<RealTimeTreatmentPlant>) obj.get(key);
//            result.put("hour", hour);
//        }
        return result;
    }

    @GetMapping("/pollute/site")
    @ResponseBody
    public JSONObject polluteSiteInfo(@RequestParam Map<String, Object> params){
        String siteId = (String) params.get("siteId");
        JSONObject result = new JSONObject();
        Map<String, Object> info = siteService.findSiteInfo(siteId);
        result.put("info", info);
        List<Vidicon> siteVidicons = vidiconService.getVidiconsBySiteId(Integer.valueOf(siteId));
        result.put("videos", siteVidicons);
        long stime = System.currentTimeMillis() - (1000 * 60 * 60 * 24);
        String hourStartDate = DateUtils.format(new Date(stime), DateUtils.DATE_TIME_PATTERN);
        String hourEndDate = DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN);
        String sign = TableSelUtil.getTableSignOfMonth(hourEndDate, DateUtils.DATE_TIME_PATTERN);
//        String table = "miluo_pollutant_realtime_" + sign;
        params.put("startDate", hourStartDate);
        params.put("endDate", hourEndDate);
//        params.put("tableName", table);
        //JSONObject obj = polluteService.findHourData(params);
        List<PolluteRealtime> voList = polluteService.findNewMinuteData(params);
        for (PolluteRealtime polluteRealtime : voList) {
            String equmentId = polluteRealtime.getEqumentId();
            Date recordingTime = polluteRealtime.getRecordingTime();
            HashMap<String, Object> map = new HashMap<>();
            map.put("equmentId",equmentId);
            map.put("recordingTime",recordingTime);
             List<AlarmEntity> alarmList  =  polluteService.sel(map);
            for (AlarmEntity alarmEntity : alarmList) {
                String pollutant = alarmEntity.getPollutant();
                if (pollutant.equals("NOx")){
                    polluteRealtime.setNox2(polluteRealtime.getNox());
                }
                if (pollutant.equals("SO2")){
                    polluteRealtime.setSo22(polluteRealtime.getSo2());
                }

            }

        }
        // 根据站点id查询设备id，做这一步只是为了加快sql查询速度
        ArrayList<String> strings = new ArrayList<>();
        strings.add(siteId);
        List<String> equmentIds = polluteService2.findEqumentIds(strings);
        HashMap<String, Object> map2 = new HashMap<>();
        map2.put("beginTime",hourStartDate);
        map2.put("endTime",hourEndDate);
        map2.put("equmentIds",equmentIds);
        map2.put("dataType","1");
        List<PolluteRealtime> polluteRealtimes = polluteService2.hourList3(map2);//RealTimeTreatmentPlant
        Map<String, List<PolluteRealtime>> collect = polluteRealtimes.stream().collect(Collectors.groupingBy(PolluteRealtime::getEquipmentName));
        result.put("hour", collect);
        result.put("newData", voList);
        //result.put("hour", obj);
        return result;
    }

    @GetMapping("/water/site")
    @ResponseBody
    public JSONObject waterSiteInfo(@RequestParam Map<String, Object> params){
        String siteId = (String) params.get("siteId");
        JSONObject result = new JSONObject();
        Map<String, Object> info = siteService.findSiteInfo(siteId);
        result.put("info", info);
//        Integer siteId = (Integer) info.get("siteId");
        List<Vidicon> siteVidicons = vidiconService.getVidiconsBySiteId(Integer.valueOf(siteId));
        result.put("videos", siteVidicons);
        long stime = System.currentTimeMillis() - (1000 * 60 * 60 * 24);
        String hourStartDate = DateUtils.format(new Date(stime), DateUtils.DATE_TIME_PATTERN);
        String hourEndDate = DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN);
//        params.put("startDate", hourStartDate);
//        params.put("endDate", hourEndDate);
        params.put("startDate", hourStartDate);
        params.put("endDate", hourEndDate);
        JSONObject obj = waterService.findHourData(params);
        result.put("hour", obj.get("data"));
//        for (String key : obj.keySet()) {
//            List<RealTimeTreatmentPlant> hour = (List<RealTimeTreatmentPlant>) obj.get(key);
//            result.put("hour", hour);
//        }
        return result;
    }



    @GetMapping("/site/info")
    @ResponseBody
    public JSONObject getSiteInfo(@RequestParam Map<String, Object> params) {
        String siteType = (String) params.get("siteType");
        String siteId = (String) params.get("siteId");

        Date endDate = new Date();
        Date startDate = new Date(endDate.getTime() - (1000 * 60 * 60 * 24));
        String ed = DateUtils.format(endDate, DateUtils.DATE_TIME_PATTERN);

        JSONObject result = new JSONObject();
        Map<String, Object> info = siteService.findSiteInfo(siteId);
        result.put("info", info);
//        Integer siteId = (Integer) info.get("siteId");
        List<Vidicon> siteVidicons = vidiconService.getVidiconsBySiteId(Integer.valueOf(siteId));
        result.put("videos", siteVidicons);
        long stime = System.currentTimeMillis() - (1000 * 60 * 60 * 24);
        String hourStartDate = DateUtils.format(new Date(stime), DateUtils.DATE_TIME_PATTERN);
        String hourEndDate = DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN);
        params.put("startDate", hourStartDate);
        params.put("endDate", hourEndDate);
        //空气站
        if ("2".equals(siteType)) {
            JSONObject obj = airService.findChangeData(params);
            for (String key : obj.keySet()) {
                List<AirHourStatements> hour = (List<AirHourStatements>) obj.get(key);
                result.put("hour", hour);
            }
            String yearStartDate = ed.substring(0, 4) + "-01-01";
            String yearEndDate = DateUtils.format(endDate, DateUtils.DATE_PATTERN);
            params.put("startDate", yearStartDate);
            params.put("endDate", yearEndDate);
            result.put("startDate", yearStartDate);
            result.put("endDate", yearEndDate);
            List<Map<String, Object>> year = airService.findYearLevelDay(params);
            result.put("year", year);
            return result;
        } else if ("3".equals(siteType)) {
            JSONObject obj = polluteWaterService.findChangeData(params);
            for (String key : obj.keySet()) {
                List<RealTimeTreatmentPlant> hour = (List<RealTimeTreatmentPlant>) obj.get(key);
                result.put("hour", hour);
            }
            return result;
        } else if ("1".equals(siteType)) {
            JSONObject obj = waterService.findHourData(params);
            if (obj != null) {
                Map<String, List<WaterDataStatments>> hour = (Map<String, List<WaterDataStatments>>) obj.get("data");
                for (String key : hour.keySet()) {
                    List<WaterDataStatments> hours = (List<WaterDataStatments>) obj.get(key);
                    result.put("hour", hours);
                }

            }
            return result;
        }
        return null;
    }


    @GetMapping("/site/state")
    @ResponseBody
    public List<Map<String, Object>> getSiteOnlineState(@RequestParam Map<String, Object> params){
        List<Site> all;
        if (!RoleConstant.ROLE_IDS_INNER.contains(getRoleId())) {
            all = siteService.queryListByUserIdAndSiteType(getUserId().toString(),"");
        } else {
            all = siteService.getAllSite();
        }
        if(all!=null){
            List<String> siteIds = all.stream().map(Site::getId).collect(Collectors.toList());
            params.put("siteIds", siteIds);
        }
//        return desktopService.findSiteOnlineState(params);
        return desktopService.findMapMarkerByOnlineState(params);
    }

}
