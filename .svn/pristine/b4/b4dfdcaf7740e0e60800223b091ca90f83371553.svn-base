package com.rate.web.api.dao;

import com.rate.web.api.entity.HourWaterSite;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 水质自动站小时
 *
 * @Author liuyong
 * @Date 2019/6/6 11:19
 * @Version 1.0
 **/
@Repository
@SqlResource("api/waterSiteHour")
public interface WaterSiteHourDao extends BaseMapper<HourWaterSite> {

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
    List<HourWaterSite> queryListByCodeAndTime(@Param("tableName") String tableName,
                                               @Param("siteCodes") List<String> siteCodes,
                                               @Param("startTime") String startTime,
                                               @Param("endTime") String endTime,
                                               @Param("pageNum") Integer pageNum,
                                               @Param("pageSize") Integer pageSize);

    /**
     * 根据站点以及时间查询该时间点最近24小时的数据
     *
     * @param siteId 站点编号
     * @param queryTime 查询时间
     * @return
     * @Author LiuYong
     */
    List<HourWaterSite> getOldHour(@Param("siteId") String siteId,
                                   @Param("queryTime") String queryTime);

    List<HourWaterSite> getFromDesktop(@Param("siteId") String siteId);
}
