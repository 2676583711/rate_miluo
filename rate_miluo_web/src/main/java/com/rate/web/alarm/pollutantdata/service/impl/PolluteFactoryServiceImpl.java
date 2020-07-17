package com.rate.web.alarm.pollutantdata.service.impl;

import com.rate.web.alarm.entity.AlarmEntity;
import com.rate.web.alarm.entity.AlarmEntityEpx;
import com.rate.web.alarm.pollutantdata.dao.PolluteFactoryDao;
import com.rate.web.alarm.pollutantdata.entity.PolluteFactory;
import com.rate.web.alarm.pollutantdata.service.PolluteFactoryService;
import com.rate.web.statement.entity.PolluteRealtime;
import com.rate.web.statement.entity.PolluteRealtimeExp;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PolluteFactoryServiceImpl implements PolluteFactoryService {

    @Autowired
    private PolluteFactoryDao polluteFactoryDao;

    @Override
    public List<PolluteFactory> factoryAlarmExp(Map<String,Object> map) {
        return polluteFactoryDao.factoryAlarmExp(map);
    }

    @Override
    public PageQuery<AlarmEntity> polluteFactoryAlarmQuery(PageQuery<AlarmEntity> params) {
        return polluteFactoryDao.polluteFactoryAlarmQuery(params);
    }

    @Override
    public PageQuery<AlarmEntityEpx> polluteFactoryAlarmQueryEpx(PageQuery<AlarmEntityEpx> paras) {
        return polluteFactoryDao.polluteFactoryAlarmQueryEpx(paras);
    }

    @Override
    public PolluteRealtime selHourPollutant(Map<String, Object> map) {
        return polluteFactoryDao.selHourPollutant(map);
    }

    @Override
    public List<PolluteRealtimeExp> selHourPollutantList(Map<String, Object> map) {

        return polluteFactoryDao.selHourPollutantList(map);
    }

}
