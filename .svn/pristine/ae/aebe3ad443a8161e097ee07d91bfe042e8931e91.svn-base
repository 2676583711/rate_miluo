package com.rate.web.connected.datarate.service;

import com.rate.web.connected.newdata.entity.PolluteWaterHourStatement;
import com.rate.web.site.entity.Site;
import com.rate.web.statement.entity.PolluteRealtime;
import com.rate.web.statement.entity.WaterSite;

import java.util.List;
import java.util.Map;

/**
 * @Author chenshixue
 * @Date 2019/12/4
 */
public interface DataRateService {

    List<Site> getDataRateAirList(Map<String, Object> params);

    List<PolluteRealtime> getDataRatePolluteList(Map<String, Object> params);

    List<WaterSite> getDataRateWaterList(Map<String, Object> params);

    List<PolluteRealtime> get24HourPollutantList(Map<String, Object> params);

    List<PolluteWaterHourStatement> getDataRateWaterPlantList(Map<String, Object> params);

    List<PolluteWaterHourStatement> get24HourWaterPlantList(Map<String, Object> params);

    List<WaterSite> get24HourWaterList(Map<String, Object> params);
}
