package com.rate.web.graphic.service;

import com.alibaba.fastjson.JSONObject;
import com.rate.web.graphic.entity.AirHourStatements;

import java.util.List;
import java.util.Map;

/**
 * @author shuzhangyao
 * @date 2019/5/30 19:12
 **/
public interface GraphicAirService {

    /**
     * 获取最新小时数据
     * @param params 条件参数
     * @return java.util.List<com.rate.web.graphic.entity.AirHourStatements>
     * @author shuzhangyao
     * @date 2019/5/30 19:10
     **/
    List<AirHourStatements> findLatestData(Map<String, Object> params);
    /**
     * 获取小时数据
     * @param params 条件参数
     * @return java.util.List<com.rate.web.graphic.entity.AirHourStatements>
     * @author shuzhangyao
     * @date 2019/5/31 9:30
     **/
    JSONObject findHourData(Map<String, Object> params);
    /**
     * 获取天数据
     * @param params 条件参数
     * @return java.util.List<com.rate.web.graphic.entity.AirDayStatements>
     * @author shuzhangyao
     * @date 2019/5/31 11:56
     **/
    JSONObject findDayData(Map<String, Object> params);
    /**
     * 获取风向数据
     * @param params 条件参数
     * @return java.util.List<com.rate.web.graphic.entity.AirHourStatements>
     * @author shuzhangyao
     * @date 2019/5/31 13:56
     **/
    JSONObject findWindData(Map<String, Object> params);
    /**
     * 获取小时数据
     * @param params 条件参数
     * @return java.util.List<com.rate.web.graphic.entity.AirHourStatements>
     * @author shuzhangyao
     * @date 2019/5/31 14:59
     **/
    JSONObject findChangeData(Map<String, Object> params);
    /**
     * 获取当年到现在污染程度级别天数
     * @param params 站点编号 起始时间
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @author shuzhangyao
     * @date 2019/6/4 18:07
     **/
    List<Map<String, Object>> findYearLevelDay(Map<String, Object> params);

}
