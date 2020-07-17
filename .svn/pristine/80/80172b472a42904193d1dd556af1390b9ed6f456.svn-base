package com.rate.web.alarm.pollutantwater.service.impl;

import com.rate.web.alarm.entity.AlarmEntity;
import com.rate.web.alarm.pollutantwater.dao.PolluteWaterDao;
import com.rate.web.alarm.pollutantwater.entity.PolluteWater;
import com.rate.web.alarm.pollutantwater.service.PolluteWaterService;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PolluteWaterServiceImpl implements PolluteWaterService {


    @Autowired
    private PolluteWaterDao polluteWaterDao;

    @Override
    public PageQuery<AlarmEntity> polluteWaterAlarm(PageQuery<AlarmEntity> params) {
        return polluteWaterDao.polluteWaterAlarm(params);
    }

    @Override
    public PageQuery<PolluteWater> buttonPolluteWaterAlarm(PageQuery<PolluteWater> params) {
        return polluteWaterDao.buttonPolluteWaterAlarm(params);
    }
}
