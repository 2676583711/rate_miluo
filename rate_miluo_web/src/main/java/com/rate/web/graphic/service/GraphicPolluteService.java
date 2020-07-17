package com.rate.web.graphic.service;

import com.alibaba.fastjson.JSONObject;
import com.rate.web.alarm.entity.AlarmEntity;
import com.rate.web.statement.entity.PolluteRealtime;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shuzhangyao
 * @date 2019/5/31 17:04
 **/
public interface GraphicPolluteService {

    /**
     * 获取小时数据
     * @param params 条件参数
     * @return java.util.List<com.rate.web.graphic.entity.RealtimeTreatmentPlant>
     * @author shuzhangyao
     * @date 2019/5/31 9:30
     **/
    JSONObject findHourData(Map<String, Object> params);
    /**
     * 获取天数据
     * @param params 条件参数
     * @return java.util.List<com.rate.web.graphic.entity.RealtimeTreatmentPlant>
     * @author shuzhangyao
     * @date 2019/5/31 11:56
     **/
    JSONObject findDayData(Map<String, Object> params);
    /**
     * 获取小时数据
     * @param params 条件参数
     * @return java.util.List<com.rate.web.graphic.entity.AirHourStatements>
     * @author shuzhangyao
     * @date 2019/5/31 14:59
     **/
    JSONObject findChangeData(Map<String, Object> params);

    /**
     * 获取最新一条数据
     * @param params
     * @Return  PolluteRealtime
     * @Author  chenshixue
     * @Date    2019/8/28 18:10
     */
    List<PolluteRealtime> findNewMinuteData(Map<String, Object> params);

    /**
     * 获取分钟数据
     * @param params
     * @Return  com.alibaba.fastjson.JSONObject
     * @Author  chenshixue
     * @Date    2019/9/26 17:49
     */
    JSONObject findMinuteData(Map<String, Object> params);

    List<String> findTableList();


    List<AlarmEntity> sel(HashMap<String, Object> map);
}
