package com.rate.web.api.service;

import com.rate.web.api.entity.HourWaterSite;

import java.util.List;

/**
 * 水质自动站
 *
 * @ClassName WaterSiteHourService
 * @Author liuyong
 * @Date 2019/6/6 11:19
 * @Version 1.0
 **/
public interface WaterSiteHourService {

    /**
     * 获取水质自动站数据 通过站点编号 以及时间
     *
     * @param siteCodes 站点
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param pageNum   起始
     * @param pageSize  每页数
     * @return
     * @author LiuYong
     */
    List<HourWaterSite> queryListByCodeAndTime(String tableName, List<String> siteCodes, String startTime, String endTime, Integer pageNum, Integer pageSize);

    /**
     * 根据站点以及时间查询该时间点最近24小时的数据
     *
     * @param siteId 站点编号
     * @param queryTime 查询时间
     * @return
     * @Author LiuYong
     */
    List<HourWaterSite> getOldHour(String siteId, String queryTime);

    /**
     * 获取最新分钟数据
     * @param siteId
     * @Return  java.util.List<com.rate.web.api.entity.HourWaterSite>
     * @Author  chenshixue
     * @Date    2019/12/17 9:17
     */
    List<HourWaterSite> getFromDesktop(String siteId);
}
