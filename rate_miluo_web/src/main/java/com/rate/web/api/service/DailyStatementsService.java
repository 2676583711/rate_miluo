package com.rate.web.api.service;

import com.rate.web.api.entity.DailyStatements;

import java.util.List;
import java.util.Map;

/**
 * @Author liuyong
 * @Date 2019/6/4 14:59
 * @Version 1.0
 **/
public interface DailyStatementsService {

    /**
     * 根据时间以及用户id查询日数据
     *
     * @param params 用户id 、起始时间、结束时间、站点编号
     * @return
     * @Author LiuYong
     */
    List<DailyStatements> getListByTimeAndSiteCode(Map<String, Object> params);

}
