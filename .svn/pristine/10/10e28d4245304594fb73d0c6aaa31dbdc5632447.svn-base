package com.rate.web.connected.newdata.service.impl;

import com.rate.web.connected.newdata.dao.AirMinuteSmallStatementDao;
import com.rate.web.connected.newdata.entity.AirMinuteSmallStatement;
import com.rate.web.connected.newdata.service.AirMinuteSmallStatementService;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirMinuteSmallStatementServiceImpl implements AirMinuteSmallStatementService {


    @Autowired
    private AirMinuteSmallStatementDao airMinuteSmallStatementDao;


    @Override
    public PageQuery<AirMinuteSmallStatement> minuteQueryByCondition(PageQuery<AirMinuteSmallStatement> params) {

     return airMinuteSmallStatementDao.queryByCondition(params);
    }

}
