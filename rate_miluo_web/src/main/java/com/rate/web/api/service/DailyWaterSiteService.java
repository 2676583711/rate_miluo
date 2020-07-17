package com.rate.web.api.service;

import com.rate.web.api.entity.DailyWaterSite;

import java.util.List;

/**
 * @ClassName DailyWaterSiteService
 * @Author liuyong
 * @Date 2019/6/6 14:25
 * @Version 1.0
 **/
public interface DailyWaterSiteService {

    /**
     * 获取水质自动站小时数据 通过站点编号 以及时间
     *
     * @param siteCodes 站点
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param pageNum   起始
     * @param pageSize  每页数
     * @return
     * @author LiuYong
     */
    List<DailyWaterSite> queryListByCodeAndTime(List<String> siteCodes, String startTime, String endTime, Integer pageNum, Integer pageSize);
}
