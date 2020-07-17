package com.rate.web.graphic.dao;

import com.rate.web.graphic.entity.AirDayStatements;
import com.rate.web.graphic.entity.AirHourStatements;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * @author shuzhangyao
 * @date 2019/5/30 18:30
 **/
@SqlResource("graphic.air.air")
public interface GraphicAirDao extends BaseMapper<AirHourStatements> {

    /**
     * 获取最新小时数据
     * @param params 条件参数
     * @return java.util.List<com.rate.web.graphic.entity.AirHourStatements>
     * @author shuzhangyao
     * @date 2019/5/30 19:10
     **/
    List<AirHourStatements> findStandardLatestData(Map<String, Object> params);
    /**
     * 获取小时数据
     * @param params 条件参数
     * @return java.util.List<com.rate.web.graphic.entity.AirHourStatements>
     * @author shuzhangyao
     * @date 2019/5/31 9:30
     **/
    List<AirHourStatements> findHourData(Map<String, Object> params);
    /**
     * 获取天数据
     * @param params 条件参数
     * @return java.util.List<com.rate.web.graphic.entity.AirDayStatements>
     * @author shuzhangyao
     * @date 2019/5/31 12:03
     **/
    List<AirDayStatements> findDayData(Map<String, Object> params);
    /**
     * 获取风向数据
     * @param params 条件参数
     * @return java.util.List<com.rate.web.graphic.entity.AirDayStatements>
     * @author shuzhangyao
     * @date 2019/5/31 12:03
     **/
    List<AirHourStatements> findWindData(Map<String, Object> params);
    /**
     * 获取风向数据
     * @param params 条件参数
     * @return java.util.List<com.rate.web.graphic.entity.AirDayStatements>
     * @author shuzhangyao
     * @date 2019/5/31 12:03
     **/
    List<AirHourStatements> findChangeData(Map<String, Object> params);
    /**
     * 获取当年到现在污染程度级别天数
     * @param params 站点编号 起始时间
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @author shuzhangyao
     * @date 2019/6/4 18:07
     **/
    List<Map<String, Object>> findYearLevelDay(Map<String, Object> params);


}
