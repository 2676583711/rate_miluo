package com.rate.web.api.service;

import com.rate.web.api.entity.HourStatements;

import java.util.List;
import java.util.Map;

/**
 * @Author liuyong
 * @Date 2019/6/4 13:31
 * @Version 1.0
 **/
public interface HourStatementsService {

    /**
     * 根据站点类别，查询前几条数据（时间降序排序）
     *
     * @param siteCategory 站点类别
     * @param limit        条数
     * @return
     * @Author LiuYong
     */
    Map<String, Object> getDataBySiteCategory(String siteCategory, Integer limit);

    /**
     * 根据时间以及用户id查询数据
     *
     * @param params 用户id 、起始时间、结束时间、站点编号
     * @return
     */
    List<HourStatements> getListByTimeAndSiteCode(Map<String, Object> params);

    /**
     * 查询空气小时数据
     *
     * @param siteCategory 站点类别
     * @param limit        几条
     * @param queryTime    时间
     * @return
     * @author LiuYong
     */
    List<HourStatements> queryListHourByCategory(String siteCategory, Integer limit, String queryTime);

    /**
     * 根据站点以及时间查询该时间点最近24小时的数据
     *
     * @param siteIds 站点编号集合
     * @param queryTime 查询时间
     * @return
     * @Author LiuYong
     */
    List<HourStatements> getOldHour(List<String> siteIds, String queryTime);

    /**
     * 获取最新一条数据
     * @param siteId
     * @Return  java.util.List<com.rate.web.api.entity.HourStatements>
     * @Author  chenshixue
     * @Date    2019/12/17 9:22
     */
    List<HourStatements> getFromDesktop(String siteId);
}
