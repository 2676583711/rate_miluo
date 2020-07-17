package com.rate.web.api.service.impl;

import com.rate.web.api.dao.DailyWaterSiteDao;
import com.rate.web.api.entity.DailyWaterSite;
import com.rate.web.api.service.DailyWaterSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName DailyWaterSiteServiceImpl
 * @Author liuyong
 * @Date 2019/6/6 14:26
 * @Version 1.0
 **/
@Service("DailyWaterSiteService")
public class DailyWaterSiteServiceImpl implements DailyWaterSiteService {

    @Autowired
    private DailyWaterSiteDao dailyWaterSiteDao;

    @Override
    public List<DailyWaterSite> queryListByCodeAndTime(List<String> siteCodes, String startTime, String endTime, Integer pageNum, Integer pageSize) {
        return dailyWaterSiteDao.queryListByCodeAndTime(siteCodes, startTime, endTime, pageNum, pageSize);
    }
}
