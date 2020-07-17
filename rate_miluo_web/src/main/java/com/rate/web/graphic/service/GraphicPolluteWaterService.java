package com.rate.web.graphic.service;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @author shuzhangyao
 * @date 2019/5/31 17:04
 **/
public interface GraphicPolluteWaterService {

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
     * 查询最新分钟数据
     * 当前时间前一分钟
     * @param params 站点id
     * @return com.alibaba.fastjson.JSONObject
     * @author shuzhangyao
     * @date 2019/7/15 17:53
     **/
    JSONObject findNewMinuteData(Map<String, Object> params);


}
