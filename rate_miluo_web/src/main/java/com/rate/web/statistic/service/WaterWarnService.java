package com.rate.web.statistic.service;

import com.rate.web.desktop.entity.MapMarker;

import java.util.List;

/**
 * @program: rate_miluo_parent
 * @description:
 * @author: chenh
 * @create: 2020-07-16 11:49
 **/
public interface WaterWarnService {
    //查询所有得水质预警等级
    List<MapMarker> findAllWaterWarn(String dateTime);
}
