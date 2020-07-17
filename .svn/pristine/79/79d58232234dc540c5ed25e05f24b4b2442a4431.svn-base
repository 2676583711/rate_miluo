package com.rate.web.api.service.impl;

import com.rate.system.rate_system.utils.StringUtils;
import com.rate.web.api.dao.HourPolluteDao;
import com.rate.web.api.service.HourPolluteService;
import com.rate.web.connected.newdata.entity.PolluteWaterHourStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @ClassName HourPolluteServiceImpl
 * @Author liuyong
 * @Date 2019/6/14 17:53
 * @Version 1.0
 **/
@Service("HourPolluteService")
public class HourPolluteServiceImpl implements HourPolluteService {

    @Autowired
    private HourPolluteDao hourPolluteDao;

    @Override
    public List<PolluteWaterHourStatement> getOldHour(String siteId, String queryTime) {
        if (StringUtils.isBlank(queryTime)) {
            queryTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH"));
        }
        return hourPolluteDao.getOldHour(siteId, queryTime);
    }

    @Override
    public List<PolluteWaterHourStatement> getFromDesktop(String siteId) {
        return hourPolluteDao.getFromDesktop(siteId);
    }
}
