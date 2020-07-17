package com.rate.web.api.service.impl;

import com.rate.web.api.dao.DailyStatementsDao;
import com.rate.web.api.entity.DailyStatements;
import com.rate.web.api.service.DailyStatementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName DailyStatementsServiceImpl
 * @Author liuyong
 * @Date 2019/6/4 14:59
 * @Version 1.0
 **/
@Service("DailyStatementsService")
public class DailyStatementsServiceImpl implements DailyStatementsService {

    @Autowired
    private DailyStatementsDao dailyStatementsDao;

    @Override
    public List<DailyStatements> getListByTimeAndSiteCode(Map<String, Object> params) {
        List<DailyStatements> listData = dailyStatementsDao.getListByTimeAndSiteCode(params);
        if (listData == null || listData.isEmpty()) {
            return null;
        }
        return listData;
    }
}
