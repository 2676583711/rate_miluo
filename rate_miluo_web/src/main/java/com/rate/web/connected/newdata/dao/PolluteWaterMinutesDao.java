package com.rate.web.connected.newdata.dao;

import com.rate.web.connected.newdata.entity.PolluteWaterMinutes;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;

@SqlResource("connected.newdata.waterminutes")
public interface PolluteWaterMinutesDao extends BaseMapper<PolluteWaterMinutes> {

    PageQuery<PolluteWaterMinutes> waterLatest(PageQuery<PolluteWaterMinutes> params);
}
