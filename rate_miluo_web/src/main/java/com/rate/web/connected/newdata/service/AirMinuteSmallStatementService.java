package com.rate.web.connected.newdata.service;


import com.rate.web.connected.newdata.entity.AirMinuteSmallStatement;
import org.beetl.sql.core.engine.PageQuery;

public interface AirMinuteSmallStatementService {

    PageQuery<AirMinuteSmallStatement> minuteQueryByCondition(PageQuery<AirMinuteSmallStatement> params);



}
