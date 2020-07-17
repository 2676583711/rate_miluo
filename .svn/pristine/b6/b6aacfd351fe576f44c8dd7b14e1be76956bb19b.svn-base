package com.rate.web.graphic.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.rate.system.rate_system.utils.DateUtils;
import com.rate.system.rate_system.utils.StringUtils;
import com.rate.web.alarm.entity.AlarmEntity;
import com.rate.web.graphic.dao.GraphicWaterDao;
import com.rate.web.graphic.entity.WaterDataStatments;
import com.rate.web.graphic.service.GraphicPolluteService;
import com.rate.web.graphic.service.GraphicWaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author shuzhangyao
 * @date 2019/5/31 17:05
 **/
@Service
public class GraphicWaterServiceImpl implements GraphicWaterService {

    @Autowired
    private GraphicWaterDao graphicWaterDao;
    @Autowired
    private GraphicPolluteService polluteService;

    /**
     * 获取小时数据
     * @param params 条件参数
     * @return java.util.List<com.rate.web.graphic.entity.WaterDataStatments>
     * @author shuzhangyao
     * @date 2019/5/31 9:30
     **/
    @Override
    public JSONObject findHourData(Map<String, Object> params){
        List<WaterDataStatments> all = graphicWaterDao.findHourData(params);
        Map<String, List<WaterDataStatments>> result = new HashMap<>();
        Set<String> x = new HashSet<>();
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.DATE_TIME_PATTERN3);
        for (int i = 0; i< all.size();i++){
            WaterDataStatments hour = all.get(i);
            String siteName = hour.getSiteName();
            if(StringUtils.isBlank(siteName) || "null".equals(siteName)){
                continue;
            }
            if(result.containsKey(siteName)){
                result.get(siteName).add(hour);
            }else{
                List<WaterDataStatments> list = new ArrayList<>();
                list.add(hour);
                result.put(siteName, list);
            }
            x.add(sdf.format(hour.getDateTime()));
        }
        List<String> xs = new ArrayList<>(x);
        Collections.sort(xs);
        JSONObject obj = new JSONObject();
        Set<String> strings = result.keySet();
        for (String string : strings) {
            List<WaterDataStatments> waterDataStatments = result.get(string);
           if (waterDataStatments!=null && waterDataStatments.size()>0){
               WaterDataStatments waterDataStatments1 = waterDataStatments.get(waterDataStatments.size() - 1);
               Date dateTime = waterDataStatments1.getDateTime();
               String stationCode = waterDataStatments1.getStationCode();
               HashMap<String, Object> map = new HashMap<>();
               map.put("equmentId",stationCode);
               map.put("recordingTime",dateTime);
               List<AlarmEntity> alarmList  =  polluteService.sel(map);

               for (AlarmEntity alarmEntity : alarmList) {
                   String pollutant = alarmEntity.getPollutant();
                   if (pollutant.equals("rjy")){
                       waterDataStatments1.setRjy2(waterDataStatments1.getRjy());
                   }
                   if (pollutant.equals("PH")){
                       waterDataStatments1.setPh2(waterDataStatments1.getPh());
                   }
                   if (pollutant.equals("TP")){
                       waterDataStatments1.setTp2(waterDataStatments1.getTp());
                   }

               }
           }
        }
        obj.put("data", result);
        obj.put("xDate", xs);
        return obj;
    }
    /**
     * 获取天数据
     * @param params 条件参数
     * @return java.util.List<com.rate.web.graphic.entity.WaterDataStatments>
     * @author shuzhangyao
     * @date 2019/5/31 11:57
     **/
    @Override
    public JSONObject findDayData(Map<String, Object> params){
        List<WaterDataStatments> all = graphicWaterDao.findDayData(params);
        Map<String, List<WaterDataStatments>> result = new HashMap<>();
        Set<String> x = new HashSet<>();
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.DATE_TIME_PATTERN3);
        for (int i = 0; i< all.size();i++){
            WaterDataStatments day = all.get(i);
            String siteName = day.getSiteName();
            if(StringUtils.isBlank(siteName) || "null".equals(siteName)){
                continue;
            }
            if(result.containsKey(siteName)){
                result.get(siteName).add(day);
            }else{
                List<WaterDataStatments> list = new ArrayList<>();
                list.add(day);
                result.put(siteName, list);
            }
            x.add(sdf.format(day.getDateTime()));
        }
        List<String> xs = new ArrayList<>(x);
        Collections.sort(xs);
        JSONObject obj = new JSONObject();
        obj.put("data", result);
        obj.put("xDate", xs);
        return obj;
    }

    @Override
    public JSONObject findMinuteData(Map<String, Object> params) {
        List<WaterDataStatments> all = graphicWaterDao.findMinuteData(params);
        Map<String, List<WaterDataStatments>> result = new HashMap<>();
        Set<String> x = new HashSet<>();
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.DATE_TIME_PATTERN3);
        for (int i = 0; i< all.size();i++){
            WaterDataStatments hour = all.get(i);
            String siteName = hour.getSiteName();
            if(StringUtils.isBlank(siteName) || "null".equals(siteName)){
                continue;
            }
            if(result.containsKey(siteName)){
                result.get(siteName).add(hour);
            }else{
                List<WaterDataStatments> list = new ArrayList<>();
                list.add(hour);
                result.put(siteName, list);
            }
            x.add(sdf.format(hour.getDateTime()));
        }
        List<String> xs = new ArrayList<>(x);
        Collections.sort(xs);
        JSONObject obj = new JSONObject();
        obj.put("data", result);
        obj.put("xDate", xs);
        return obj;
    }

}
