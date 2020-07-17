package com.rate.web.connected.newdata.service.impl;

import com.rate.web.connected.newdata.dao.PolluteWaterHourStatementDao;
import com.rate.web.connected.newdata.entity.PolluteWaterHourStatement;
import com.rate.web.connected.newdata.service.PolluteWaterHourStatementService;
import com.rate.web.dataInter.vo.PolluteWaterVO;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolluteWaterHourStatementServiceImpl implements PolluteWaterHourStatementService {

    @Autowired
    private PolluteWaterHourStatementDao polluteWaterHourStatementDao;

    @Override
    public PageQuery<PolluteWaterHourStatement> polluteWaterDetails(PageQuery<PolluteWaterHourStatement> params) {
        return polluteWaterHourStatementDao.polluteWaterDetails(params);
    }
    /**
     * @Param [siteCode, startTime, endTime]
     * @Return java.util.List<com.rate.web.connected.newdata.entity.PolluteWaterHourStatement>
     * @author xiaoshi
     * @Description
     * @Date 2019/7/9
     * @Time 14:14
     **/
    @Override
    public List<PolluteWaterVO> hourPolluteInter(String siteCode, String startTime, String endTime) {
        return polluteWaterHourStatementDao.hourPolluteInter(siteCode,startTime,endTime);
    }


}
