package com.rate.web.alarm.waterauto.service;


import com.rate.web.alarm.entity.AlarmEntity;
import com.rate.web.alarm.waterauto.entity.Water;
import org.beetl.sql.core.engine.PageQuery;

public interface WaterService {
    /**
     * @Param [params]
     * @Return org.beetl.sql.core.engine.PageQuery<com.rate.web.alarm.waterauto.entity.Water>
     * @author xiaoshi
     * @Description
     * @Date 2019/6/10
     * @Time 22:20
     **/
    PageQuery<AlarmEntity> waterAlarm(PageQuery<AlarmEntity> params);


    /**
     * @Param [params]
     * @Return org.beetl.sql.core.engine.PageQuery<com.rate.web.alarm.waterauto.entity.Water>
     * @author xiaoshi
     * @Description
     * @Date 2019/6/10
     * @Time 22:21
     **/
    PageQuery<Water> buttonWaterAlarm(PageQuery<Water> params);



}
