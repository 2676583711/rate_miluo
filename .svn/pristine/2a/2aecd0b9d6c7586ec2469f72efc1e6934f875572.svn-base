package com.rate.web.connected.datarate.service.impl;

import com.rate.web.connected.datarate.dao.DataRateDao;
import com.rate.web.connected.datarate.service.DataRateService;
import com.rate.web.connected.newdata.entity.PolluteWaterHourStatement;
import com.rate.web.site.entity.Site;
import com.rate.web.statement.entity.PolluteRealtime;
import com.rate.web.statement.entity.WaterSite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author chenshixue
 * @Date 2019/12/4
 */
@Service
public class DataRateServiceImpl implements DataRateService {

    @Autowired
    DataRateDao dataRateDao;

    @Override
    public List<Site> getDataRateAirList(Map<String, Object> params) {
        return dataRateDao.getDataRateAirList(params);
    }

    @Override
    public List<PolluteRealtime> getDataRatePolluteList(Map<String, Object> params) {
        return dataRateDao.getDataRatePolluteList(params);
    }

    @Override
    public List<WaterSite> getDataRateWaterList(Map<String, Object> params) {
        return dataRateDao.getDataRateWaterList(params);
    }

    @Override
    public List<PolluteRealtime> get24HourPollutantList(Map<String, Object> params) {
        return dataRateDao.get24HourPollutantList(params);
    }

    @Override
    public List<PolluteWaterHourStatement> getDataRateWaterPlantList(Map<String, Object> params) {
        return dataRateDao.getDataRateWaterPlantList(params);
    }

    @Override
    public List<PolluteWaterHourStatement> get24HourWaterPlantList(Map<String, Object> params) {
        return dataRateDao.get24HourWaterPlantList(params);
    }

    @Override
    public List<WaterSite> get24HourWaterList(Map<String, Object> params) {
        return dataRateDao.get24HourWaterList(params);
    }
}
