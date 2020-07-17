package com.rate.web.statistic.service.impl;

import com.rate.system.rate_system.utils.StringUtils;
import com.rate.web.statistic.service.CompareService;
import com.rate.web.statistic.dao.DailyStatementDao;
import com.rate.web.statistic.entity.AverageCompare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CompareServiceImpl implements CompareService {
    @Autowired
    private DailyStatementDao dailyStatementDao;

    /**
     * 功能描述: 根据站点和时间条件,查询六项参数的平均浓度同比(原始数据)
     *
     * @auther: HuYan
     * @date: 2019/6/25 10:25
     */
    @Override
    public List<AverageCompare> averageCompareList(Map<String, Object> params) {
        // 按指定站点查询
        String siteCodeList = params.get("siteCodes").toString();
        if (siteCodeList == null || siteCodeList.equals("undefined") || siteCodeList == "") {
            params.remove("siteCodes");
        } else {
            params.put("siteCodes", siteCodeList.split(","));
        }
        //查询原始数据
        List<AverageCompare> averageCompares = dailyStatementDao.averageCompareListByPrimeval(params);
        return averageCompares;
    }

    /**
     * 功能描述: 根据站点和时间条件,查询六项参数的平均浓度同比(审核数据)
     *
     * @auther: HuYan
     * @date: 2019/6/25 10:25
     */
    @Override
    public List<AverageCompare> averageCompareListByExamine(Map<String, Object> params) {
        // 按指定站点查询
        String siteCodes = (String) params.get("siteCodes");
        if (StringUtils.isNotBlank(siteCodes)) {
            params.put("siteCodes", siteCodes.split(","));
        }
        //查询原始数据
        List<AverageCompare> averageCompares = dailyStatementDao.averageCompareListByExamine(params);
        return averageCompares;
    }
}
