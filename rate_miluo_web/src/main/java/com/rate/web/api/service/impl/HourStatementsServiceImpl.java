package com.rate.web.api.service.impl;

import com.rate.system.rate_system.utils.StringUtils;
import com.rate.web.api.dao.DailyStatementsDao;
import com.rate.web.api.dao.HourStatementsDao;
import com.rate.web.api.entity.DailyStatements;
import com.rate.web.api.entity.HourStatements;
import com.rate.web.api.service.HourStatementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName HourStatementsServiceImpl
 * @Author liuyong
 * @Date 2019/6/4 13:32
 * @Version 1.0
 **/
@Service("HourStatementsService")
public class HourStatementsServiceImpl implements HourStatementsService {

    @Autowired
    private HourStatementsDao hourStatementsDao;

    @Autowired
    private DailyStatementsDao dailyStatementsDao;

    @Override
    public Map<String, Object> getDataBySiteCategory(String siteCategory, Integer limit) {
        try {
            Map<String, Object> resultMap = new HashMap<>(5);
            List<HourStatements> hourData = hourStatementsDao.getRealHours(siteCategory, limit);
            //拿取其中的站点编码
            List<String> siteIds = new ArrayList<>();
            if (hourData != null && !hourData.isEmpty()) {
                siteIds = hourData.stream().map(HourStatements::getSiteId).collect(Collectors.toList());
            }
            //查询该站点过去24小时数据
            if (siteIds.isEmpty()) {
                return null;
            }
            List<HourStatements> oldHour = hourStatementsDao.getOldHour(siteIds, null);
            //查询该站点过去7天数据
            List<DailyStatements> oldDay = dailyStatementsDao.getOldDay(siteIds);
            resultMap.put("realHour", hourData);
            resultMap.put("oldHour", oldHour);
            resultMap.put("oldDay", oldDay);
            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<HourStatements> getListByTimeAndSiteCode(Map<String, Object> params) {
        return hourStatementsDao.getListByTimeAndSiteCode(params);
    }

    @Override
    public List<HourStatements> queryListHourByCategory(String siteCategory, Integer limit, String queryTime) {
        return hourStatementsDao.queryListHourByCategory(siteCategory, limit, queryTime);
    }

    @Override
    public List<HourStatements> getOldHour(List<String> siteIds, String queryTime) {
        if (StringUtils.isBlank(queryTime)) {
            queryTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH"));
        }
        return hourStatementsDao.getOldHour(siteIds, queryTime);
    }

    @Override
    public List<HourStatements> getFromDesktop(String siteId) {
        return hourStatementsDao.getFromDesktop(siteId);
    }
}
