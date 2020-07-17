package com.rate.web.api.dao;

import com.rate.web.connected.newdata.entity.PolluteWaterHourStatement;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 涉水污染源小时数据
 *
 * @ClassName HourPolluteDao
 * @Author liuyong
 * @Date 2019/6/14 17:19
 * @Version 1.0
 **/
@Repository
@SqlResource("api/hourPollute")
public interface HourPolluteDao {

    /**
     * 根据站点以及时间查询该时间点最近24小时的数据
     *
     * @param siteId  站点编号
     * @param queryTime 查询时间
     * @return
     * @Author LiuYong
     */
    List<PolluteWaterHourStatement> getOldHour(@Param("siteId") String siteId,
                                               @Param("queryTime") String queryTime);

    List<PolluteWaterHourStatement> getFromDesktop(@Param("siteId") String siteId);
}
