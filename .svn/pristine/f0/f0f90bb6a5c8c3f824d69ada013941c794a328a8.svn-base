package com.rate.web.graphic.dao;

import com.rate.web.alarm.entity.AlarmEntity;
import com.rate.web.graphic.entity.PollutantHour;
import com.rate.web.statement.entity.PolluteRealtime;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shuzhangyao
 * @date 2019/5/30 18:30
 **/
@SqlResource("graphic.pollute.pollute")
public interface GraphicPolluteDao extends BaseMapper<PollutantHour> {

    /**
     * 获取小时数据
     * @param params 条件参数
     * @return java.util.List<com.rate.web.graphic.entity.PollutantHour>
     * @author shuzhangyao
     * @date 2019/6/1 19:26
     **/
    List<PolluteRealtime> findHourData(Map<String, Object> params);
    /**
     * 获取天数据
     * @param params 条件参数
     * @return java.util.List<com.rate.web.graphic.entity.PollutantHour>
     * @author shuzhangyao
     * @date 2019/6/1 19:26
     **/
    List<PolluteRealtime> findDayData(Map<String, Object> params);

    /**
     * 获取单个污染源站最新数据
     * @param params
     * @Return  java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @Author  chenshixue
     * @Date    2019/8/29 10:47
     */
    List<PolluteRealtime> findNewMinuteData(Map<String, Object> params);

    /**
     * 获取分钟数据
     * @param params
     * @Return  java.util.List<com.rate.web.graphic.entity.MiluoPollutantRealtime>
     * @Author  chenshixue
     * @Date    2019/9/26 17:58
     */
    List<PolluteRealtime> findMinuteData(Map<String, Object> params);

    List<Map<String, String>> findSiteNamesBySiteIds(Map<String, Object> params);

    List<String> findTableList();

    List<AlarmEntity> sel(HashMap<String, Object> map);
}
