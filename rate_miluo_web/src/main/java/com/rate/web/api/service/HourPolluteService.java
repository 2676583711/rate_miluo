package com.rate.web.api.service;

import com.rate.web.connected.newdata.entity.PolluteWaterHourStatement;

import java.util.List;

/**
 * @Author liuyong
 * @Date 2019/6/14 17:52
 * @Version 1.0
 **/
public interface HourPolluteService {

    /**
     * 根据站点以及时间查询该时间点最近24小时的数据
     *
     * @param siteId  站点编号
     * @param queryTime 查询时间
     * @return
     * @Author LiuYong
     */
    List<PolluteWaterHourStatement> getOldHour(String siteId, String queryTime);

    /**
     * 获取最新一条记录
     * @param siteId
     * @Return  java.util.List<com.rate.web.connected.newdata.entity.PolluteWaterHourStatement>
     * @Author  chenshixue
     * @Date    2019/12/17 10:04
     */
    List<PolluteWaterHourStatement> getFromDesktop(String siteId);
}
