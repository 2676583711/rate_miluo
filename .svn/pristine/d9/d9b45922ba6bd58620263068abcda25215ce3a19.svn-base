package com.rate.web.connected.newdata.service.impl;

import com.rate.web.connected.newdata.dao.PolluteWaterMinutesDao;
import com.rate.web.connected.newdata.entity.PolluteWaterMinutes;
import com.rate.web.connected.newdata.service.PolluteWaterMinutesService;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PolluteWaterMinutesServiceImpl implements PolluteWaterMinutesService {

    @Autowired
    private PolluteWaterMinutesDao polluteWaterMinutesDao;

    @Override
    public PageQuery<PolluteWaterMinutes> waterMinutes(PageQuery<PolluteWaterMinutes> params) {
        return polluteWaterMinutesDao.waterLatest(params);
    }
}
