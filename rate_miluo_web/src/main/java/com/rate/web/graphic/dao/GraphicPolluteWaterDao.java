package com.rate.web.graphic.dao;

import com.rate.web.graphic.entity.RealTimeTreatmentPlant;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * @author shuzhangyao
 * @date 2019/5/30 18:30
 **/
@SqlResource("graphic.polluteWater.polluteWater")
public interface GraphicPolluteWaterDao extends BaseMapper<RealTimeTreatmentPlant> {

    /**
     * 获取小时数据
     * @param params 条件参数
     * @return java.util.List<com.rate.web.graphic.entity.AirHourStatements>
     * @author shuzhangyao
     * @date 2019/5/31 9:30
     **/
    List<RealTimeTreatmentPlant> findHourData(Map<String, Object> params);
    /**
     * 获取天数据
     * @param params 条件参数
     * @return java.util.List<com.rate.web.graphic.entity.AirDayStatements>
     * @author shuzhangyao
     * @date 2019/5/31 12:03
     **/
    List<RealTimeTreatmentPlant> findDayData(Map<String, Object> params);

    /**
     * 查询最新分钟数据
     * 当前时间前一分钟
     * @param params 站点id
     * @return com.alibaba.fastjson.JSONObject
     * @author shuzhangyao
     * @date 2019/7/15 17:53
     **/
    List<RealTimeTreatmentPlant> findNewMinuteData(Map<String, Object> params);

}
