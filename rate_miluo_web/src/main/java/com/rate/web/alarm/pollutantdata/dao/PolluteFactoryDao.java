package com.rate.web.alarm.pollutantdata.dao;

import com.rate.web.alarm.entity.AlarmEntity;
import com.rate.web.alarm.entity.AlarmEntityEpx;
import com.rate.web.alarm.pollutantdata.entity.PolluteFactory;
import com.rate.web.statement.entity.PolluteRealtime;
import com.rate.web.statement.entity.PolluteRealtimeExp;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@SqlResource("alarm.pollutantdata.pollutefactory")
@Repository
public interface PolluteFactoryDao extends BaseMapper<PolluteFactory> {

    List<PolluteFactory> factoryAlarmExp(Map<String,Object> params);
    PageQuery<AlarmEntity> polluteFactoryAlarmQuery(PageQuery<AlarmEntity> params);

    PageQuery<AlarmEntityEpx> polluteFactoryAlarmQueryEpx(PageQuery<AlarmEntityEpx> paras);

    PolluteRealtime selHourPollutant(Map<String, Object> map);

    List<PolluteRealtimeExp> selHourPollutantList(Map<String, Object> map);
}
