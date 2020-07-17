package com.rate.web.graphic.service;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * @author shuzhangyao
 * @date 2019/5/31 17:04
 **/
public interface GraphicWaterService {

    /**
     * 获取小时数据
     * @param params 条件参数
     * @return java.util.List<com.rate.web.graphic.entity.WaterDataStatments>
     * @author shuzhangyao
     * @date 2019/5/31 9:30
     **/
    JSONObject findHourData(Map<String, Object> params);
    /**
     * 获取天数据
     * @param params 条件参数
     * @return java.util.List<com.rate.web.graphic.entity.WaterDataStatments>
     * @author shuzhangyao
     * @date 2019/5/31 11:56
     **/
    JSONObject findDayData(Map<String, Object> params);

    JSONObject findMinuteData(Map<String, Object> sqlParam);
}
