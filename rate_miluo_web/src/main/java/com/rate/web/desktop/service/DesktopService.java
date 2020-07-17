package com.rate.web.desktop.service;

import com.rate.web.desktop.entity.MapMarker;
import com.rate.web.graphic.entity.AirHourStatements;

import java.util.List;
import java.util.Map;

/**
 * @author shuzhangyao
 * @date 2019/5/30 19:18
 **/
public interface DesktopService {

    /**
     * 获取最新标准站小时数据
     * @param params 条件参数
     * @return java.util.List<com.rate.web.graphic.entity.AirHourStatements>
     * @author shuzhangyao
     * @date 2019/5/30 19:10
     **/
    List<AirHourStatements> findStandardLatestData(Map<String, Object> params);

    /**
     * 获取最新小时数据
     * @param params
     * @return java.util.List<com.rate.web.graphic.entity.AirHourStatements>
     * @author shuzhangyao
     * @date 2019/6/3 17:55
     **/
    List<AirHourStatements> findDesktopRankData(Map<String, Object> params);

    /**
     * 获取全部站点主要信息加上最新小时数据
     * @return java.util.List<com.rate.web.desktop.entity.MapMarker>
     * @author shuzhangyao
     * @date 2019/6/11 14:49
     **/
    List<MapMarker> findMapMarker(Map<String, Object> params);
    List<MapMarker> findMapMarkerNotState(Map<String, Object> params);
    List<Map<String, Object>> findMapMarkerByOnlineState(Map<String, Object> params);
    Map<String, Object> findNewOneMicroData(String siteId, String siteCategory);
    /**
     * 获取站点在线离线状态
     * @param params 参数
     * @return java.util.List<java.util.Map < java.lang.String , java.lang.Object>>
     * @author shuzhangyao
     * @date 2019/7/3 9:33
     **/
    List<Map<String, Object>> findSiteOnlineState(Map<String, Object> params);
}
