package com.rate.web.statistic.dao;

import com.rate.web.desktop.entity.MapMarker;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: rate_miluo_parent
 * @description:
 * @author: chenh
 * @create: 2020-07-16 11:52
 **/
@Repository
@SqlResource("statistic.waterwarn")
public interface WaterWarnDao {
    //查询所有得水质预警站点
    List<MapMarker> findAllWaterWarn(@Param("dateTime")String dateTime);
}
