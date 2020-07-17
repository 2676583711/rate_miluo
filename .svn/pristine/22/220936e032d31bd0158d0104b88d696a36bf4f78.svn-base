package com.rate.web.desktop.dao;

import com.rate.web.desktop.entity.MapMarker;
import com.rate.web.graphic.entity.AirHourStatements;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author shuzhangyao
 * @date 2019/5/30 19:19
 **/
@Repository
@SqlResource("desktop.desktop")
public interface DesktopDao {

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
     * 获取全部空气站点主要信息加上最新小时数据
     * @param params 0
     * @return java.util.List<com.rate.web.desktop.entity.MapMarker>
     * @author shuzhangyao
     * @date 2019/6/11 14:49
     **/
    List<MapMarker> findMapMarkerByAirMicro(Map<String, Object> params);
    /**
     * 获取全部空气站点主要信息加上最新小时数据
     * @param params 0
     * @return java.util.List<com.rate.web.desktop.entity.MapMarker>
     * @author shuzhangyao
     * @date 2019/6/11 14:49
     **/
    List<MapMarker> findMapMarkerByAir(Map<String, Object> params);
    /**
     * 获取全部水质站点主要信息
     * @param params 0
     * @return java.util.List<com.rate.web.desktop.entity.MapMarker>
     * @author shuzhangyao
     * @date 2019/6/11 14:49
     **/
    List<MapMarker> findMapMarkerByWater(Map<String, Object> params);
    /**
     * 获取全部污水厂站点主要信息
     * @param params 0
     * @return java.util.List<com.rate.web.desktop.entity.MapMarker>
     * @author shuzhangyao
     * @date 2019/6/11 14:49
     **/
    List<MapMarker> findMapMarkerByWaterPlant(Map<String, Object> params);

    Map<String, Object> findNewOneMicroData(Map<String, Object> params);
    Map<String, Object> findNewOneStandardData(Map<String, Object> params);

    /**
     * 获取站点在线离线状态
     * @param params 参数
     * @return java.util.List<java.util.Map < java.lang.String , java.lang.Object>>
     * @author shuzhangyao
     * @date 2019/7/3 9:33
     **/
    List<Map<String, Object>> findMicroSiteOnlineState(Map<String, Object> params);
    List<Map<String, Object>> findStandardSiteOnlineState(Map<String, Object> params);
    List<Map<String, Object>> findPolluteSiteOnlineState(Map<String, Object> params);
    List<Map<String, Object>> findWaterSiteOnlineState(Map<String, Object> params);

    List<Map<String, Object>> findMicroSiteOnlineStateByMarker(Map<String, Object> params);
    List<Map<String, Object>> findStandardSiteOnlineStateByMarker(Map<String, Object> params);
    List<Map<String, Object>> findPolluteSiteOnlineStateByMarker(Map<String, Object> params);
    List<Map<String, Object>> findWaterSiteOnlineStateByMarker(Map<String, Object> params);

    List<Map<String, Object>> findWaterSiteOnlineStateByMarkerAll(Map<String, Object> params);
    List<Map<String, Object>> findMarkerOnlineStateByType(Map<String, Object> params);

    List<MapMarker> findMapMarkerNotState(Map<String, Object> params);
    /**
     * 获取全部污染源站点主要信息
     * @param params
     * @Return  java.util.List<com.rate.web.desktop.entity.MapMarker>
     * @Author  chenshixue
     * @Date    2019/12/12 9:51
     */
    List<MapMarker> findMapMarkerByPollute(Map<String, Object> params);
}
