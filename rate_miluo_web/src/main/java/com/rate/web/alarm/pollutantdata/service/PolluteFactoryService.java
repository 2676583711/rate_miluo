package com.rate.web.alarm.pollutantdata.service;


import com.rate.web.alarm.entity.AlarmEntity;
import com.rate.web.alarm.entity.AlarmEntityEpx;
import com.rate.web.alarm.pollutantdata.entity.PolluteFactory;
import com.rate.web.statement.entity.PolluteRealtime;
import com.rate.web.statement.entity.PolluteRealtimeExp;
import org.beetl.sql.core.engine.PageQuery;

import java.util.List;
import java.util.Map;

public interface PolluteFactoryService {

    List<PolluteFactory> factoryAlarmExp(Map<String,Object> map);

    PageQuery<AlarmEntity> polluteFactoryAlarmQuery(PageQuery<AlarmEntity> paras);

    PageQuery<AlarmEntityEpx> polluteFactoryAlarmQueryEpx(PageQuery<AlarmEntityEpx> paras);

    PolluteRealtime selHourPollutant(Map<String, Object> map);

    List<PolluteRealtimeExp> selHourPollutantList(Map<String, Object> map);
}
