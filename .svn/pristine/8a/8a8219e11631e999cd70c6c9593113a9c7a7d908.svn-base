package com.rate.web.alarm.waterauto.service.impl;

import com.rate.web.alarm.entity.AlarmEntity;
import com.rate.web.alarm.waterauto.dao.WaterDao;
import com.rate.web.alarm.waterauto.entity.Water;
import com.rate.web.alarm.waterauto.service.WaterService;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WaterServiceImpl implements WaterService {

    @Autowired
    private WaterDao waterDao;
    /**
     * @Param [params]
     * @Return org.beetl.sql.core.engine.PageQuery<com.rate.web.alarm.waterauto.entity.Water>
     * @author xiaoshi
     * @Description
     * @Date 2019/6/10
     * @Time 22:21
     **/
    @Override
    public PageQuery<AlarmEntity> waterAlarm(PageQuery<AlarmEntity> params) {
        return waterDao.waterAlarm(params);
    }
   /**
    * @Param [params]
    * @Return org.beetl.sql.core.engine.PageQuery<com.rate.web.alarm.waterauto.entity.Water>
    * @author xiaoshi
    * @Description
    * @Date 2019/6/10
    * @Time 22:23
    **/
    @Override
    public PageQuery<Water> buttonWaterAlarm(PageQuery<Water> params) {
        return waterDao.buttonWaterAlarm(params);
    }


}
