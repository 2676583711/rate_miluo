package com.rate.web.graphic.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.rate.system.rate_system.utils.DateUtils;
import com.rate.system.rate_system.utils.StringUtils;
import com.rate.web.alarm.entity.AlarmEntity;
import com.rate.web.graphic.dao.GraphicPolluteWaterDao;
import com.rate.web.graphic.entity.RealTimeTreatmentPlant;
import com.rate.web.graphic.service.GraphicPolluteService;
import com.rate.web.graphic.service.GraphicPolluteWaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author shuzhangyao
 * @date 2019/5/31 17:05
 **/
@Service
public class GraphicPolluteWaterServiceImpl implements GraphicPolluteWaterService {

    @Autowired
    private GraphicPolluteWaterDao graphicPolluteWaterDao;

    @Autowired
    private GraphicPolluteService polluteService;

    /**
     * 获取小时数据
     * @param params 条件参数
     * @return java.util.List<com.rate.web.graphic.entity.AirHourStatements>
     * @author shuzhangyao
     * @date 2019/5/31 9:30
     **/
    @Override
    public JSONObject findHourData(Map<String, Object> params){
        List<RealTimeTreatmentPlant> all = graphicPolluteWaterDao.findHourData(params);
        Map<String, List<RealTimeTreatmentPlant>> result = new HashMap<>();
        Set<String> x = new HashSet<>();
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.DATE_TIME_PATTERN3);
        for (int i = 0; i< all.size();i++){
            RealTimeTreatmentPlant hour = all.get(i);
            String siteName = hour.getSiteName();
            if(StringUtils.isBlank(siteName) || "null".equals(siteName)){
                continue;
            }
            if(result.containsKey(siteName)){
                result.get(siteName).add(hour);
            }else{
                List<RealTimeTreatmentPlant> list = new ArrayList<>();
                list.add(hour);
                result.put(siteName, list);
            }
            x.add(sdf.format(hour.getRecordingTime()));
        }
        List<String> xs = new ArrayList<>(x);
        Collections.sort(xs);
        JSONObject obj = new JSONObject();
        obj.put("data", result);
        obj.put("xDate", xs);
        return obj;
    }
    /**
     * 获取天数据
     * @param params 条件参数
     * @return java.util.List<com.rate.web.graphic.entity.AirHourStatements>
     * @author shuzhangyao
     * @date 2019/5/31 11:57
     **/
    @Override
    public JSONObject findDayData(Map<String, Object> params){
        List<RealTimeTreatmentPlant> all = graphicPolluteWaterDao.findDayData(params);
        Map<String, List<RealTimeTreatmentPlant>> result = new HashMap<>();
        Set<String> x = new HashSet<>();
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.DATE_TIME_PATTERN3);
        for (int i = 0; i< all.size();i++){
            RealTimeTreatmentPlant day = all.get(i);
            String siteName = day.getSiteName();
            if(result.containsKey(siteName)){
                result.get(siteName).add(day);
            }else{
                List<RealTimeTreatmentPlant> list = new ArrayList<>();
                list.add(day);
                result.put(siteName, list);
            }
            x.add(sdf.format(day.getRecordingTime()));
        }
        List<String> xs = new ArrayList<>(x);
        Collections.sort(xs);
        JSONObject obj = new JSONObject();
        obj.put("data", result);
        obj.put("xDate", xs);
        return obj;
    }
    /**
     *
     * @param param 站点编号 时间(yyyy-MM-dd)
     * @return java.util.List<com.rate.grid.web.TimedTask.entity.HourStatement>
     * @author shuzhangyao
     * @date 2019/5/31 13:57
     **/
    @Override
    public JSONObject findChangeData(Map<String, Object> param) {
        List<RealTimeTreatmentPlant> all = graphicPolluteWaterDao.findHourData(param);
        JSONObject result = new JSONObject();
        for (RealTimeTreatmentPlant hour: all){
            String siteName = hour.getSiteName();
            if(result.containsKey(siteName)){
                ((List<RealTimeTreatmentPlant>)result.get(siteName)).add(hour);
            }else{
                List<RealTimeTreatmentPlant> list = new ArrayList<>();
                list.add(hour);
                result.put(siteName, list);
            }
        }
        return result;
    }

    /**
     * 查询最新分钟数据
     * 当前时间前一分钟
     * @param param 站点id
     * @return com.alibaba.fastjson.JSONObject
     * @author shuzhangyao
     * @date 2019/7/15 17:54
     **/
    @Override
    public JSONObject findNewMinuteData(Map<String, Object> param) {
        String minuteDate = DateUtils.format(new Date(System.currentTimeMillis() - 1000 * 60 * 5), DateUtils.DATE_TIME_PATTERN3) + ":00";
        param.put("minuteDate", minuteDate);
        List<RealTimeTreatmentPlant> all = graphicPolluteWaterDao.findNewMinuteData(param);
        JSONObject result = new JSONObject();
        for (RealTimeTreatmentPlant hour: all){
            String equmentId = hour.getEqumentId();

            Date recordingTime = hour.getRecordingTime();
            HashMap<String, Object> map = new HashMap<>();
            map.put("equmentId",equmentId);
            map.put("recordingTime",recordingTime);
            List<AlarmEntity> alarmList  =  polluteService.sel(map);

            for (AlarmEntity alarmEntity : alarmList) {
                String pollutant = alarmEntity.getPollutant();
                if (pollutant.equals("Cd")){
                    hour.setCd2(hour.getCd());
                }
                if (pollutant.equals("PH")){
                    hour.setPh2(hour.getPh());
                }
                if (pollutant.equals("TP")){
                    hour.setTp2(hour.getTp());
                }

                if (pollutant.equals("TN")){
                    hour.setTn2(hour.getTn());
                }

            }

            String siteName = hour.getSiteName();
            if(result.containsKey(siteName)){
                ((List<RealTimeTreatmentPlant>)result.get(siteName)).add(hour);
            }else{
                List<RealTimeTreatmentPlant> list = new ArrayList<>();
                list.add(hour);
                result.put(siteName, list);
            }
        }
        return result;
    }

}
