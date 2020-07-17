package com.rate.web.api.service.impl;

import com.rate.system.rate_system.utils.StringUtils;
import com.rate.web.api.dao.WaterSiteHourDao;
import com.rate.web.api.entity.HourWaterSite;
import com.rate.web.api.service.WaterSiteHourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @ClassName WaterSiteHourServiceImpl
 * @Author liuyong
 * @Date 2019/6/6 11:39
 * @Version 1.0
 **/
@Service("WaterSiteHourService")
public class WaterSiteHourServiceImpl implements WaterSiteHourService {

    @Autowired
    private WaterSiteHourDao waterSiteHourDao;

    @Override
    public List<HourWaterSite> queryListByCodeAndTime(String tableName, List<String> siteCodes, String startTime, String endTime, Integer pageNum, Integer pageSize) {
        return waterSiteHourDao.queryListByCodeAndTime(tableName, siteCodes, startTime, endTime, pageNum, pageSize);
    }

    @Override
    public List<HourWaterSite> getOldHour(String siteId, String queryTime) {
        if (StringUtils.isBlank(queryTime)) {
            queryTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH"));
        }
        return waterSiteHourDao.getOldHour(siteId, queryTime);
    }

    @Override
    public List<HourWaterSite> getFromDesktop(String siteId) {
        return waterSiteHourDao.getFromDesktop(siteId);
    }
}
