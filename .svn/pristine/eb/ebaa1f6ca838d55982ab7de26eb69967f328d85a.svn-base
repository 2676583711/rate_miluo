package com.rate.web.alarm.pollutantwater.dao;

import com.rate.web.alarm.entity.AlarmEntity;
import com.rate.web.alarm.pollutantwater.entity.PolluteWater;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@SqlResource("alarm.pollutantwater.pollutewater")
public interface PolluteWaterDao extends BaseMapper<PolluteWater> {

    /**
     * @Param [params]
     * @Return org.beetl.sql.core.engine.PageQuery<com.rate.web.alarm.pollutantwater.entity.PolluteWater>
     * @author xiaoshi
     * @Description
     * @Date 2019/6/10
     * @Time 19:17
     **/
    PageQuery<AlarmEntity> polluteWaterAlarm(PageQuery<AlarmEntity> params);
    List<PolluteWater> polluteWaterAlarmExp(Map<String,Object> params);
   /**
    * @Param [id]
    * @Return com.rate.web.alarm.pollutantwater.entity.PolluteWater
    * @author xiaoshi
    * @Description
    * @Date 2019/6/10
    * @Time 19:18
    **/
    PolluteWater polluteWaterExceedById(@Param("id") String id);

   /**
    * @Param [id]
    * @Return void
    * @author xiaoshi
    * @Description
    * @Date 2019/6/10
    * @Time 19:19
    **/
    void updatePolluteWaterTask(@Param("id") String id);

  /**
   * @Param [params]
   * @Return org.beetl.sql.core.engine.PageQuery<com.rate.web.alarm.pollutantwater.entity.PolluteWater>
   * @author xiaoshi
   * @Description
   * @Date 2019/6/10
   * @Time 19:17
   **/
    PageQuery<PolluteWater> buttonPolluteWaterAlarm(PageQuery<PolluteWater> params);

}
