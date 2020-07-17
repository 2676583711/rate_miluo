package com.rate.web.connected.newdata.service.impl;

import com.rate.web.connected.newdata.dao.AirHourStatementDao;
import com.rate.web.connected.newdata.entity.AirHourStatement;
import com.rate.web.connected.newdata.service.AirHourStatementService;
import com.rate.web.dataInter.vo.AirVO;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirHourStatementServiceImpl implements AirHourStatementService {


    @Autowired
    private AirHourStatementDao airHourStatementDao;

    @Override
    public PageQuery<AirHourStatement> findDetails(PageQuery<AirHourStatement> params) {
        return airHourStatementDao.findDetails(params);
    }

    @Override
    public List<AirVO> hourInter(String siteCode, String startTime, String endTime) {
        return airHourStatementDao.hourInter(siteCode,startTime,endTime);
    }
}
