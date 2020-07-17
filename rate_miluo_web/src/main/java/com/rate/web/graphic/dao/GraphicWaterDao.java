package com.rate.web.graphic.dao;

import com.rate.web.graphic.entity.WaterDataStatments;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * @author shuzhangyao
 * @date 2019/5/30 18:30
 **/
@SqlResource("graphic.water.water")
public interface GraphicWaterDao extends BaseMapper<WaterDataStatments> {

    /**
     * 获取小时数据
     * @param params 条件参数
     * @return java.util.List<com.rate.web.graphic.entity.WaterDataStatments>
     * @author shuzhangyao
     * @date 2019/5/31 9:30
     **/
    List<WaterDataStatments> findHourData(Map<String, Object> params);
    /**
     * 获取天数据
     * @param params 条件参数
     * @return java.util.List<com.rate.web.graphic.entity.AirDayStatements>
     * @author shuzhangyao
     * @date 2019/5/31 12:03
     **/
    List<WaterDataStatments> findDayData(Map<String, Object> params);

    /**
     * 获取小时数据
     * @param params
     * @Return  java.util.List<com.rate.web.graphic.entity.WaterDataStatments>
     * @Author  chenshixue
     * @Date    2019/12/5 17:04
     */
    List<WaterDataStatments> findMinuteData(Map<String, Object> params);
}
