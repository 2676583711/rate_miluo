package com.rate.web.api.dao;

import com.rate.web.api.entity.HourStatements;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author liuyong
 * @Date 2019/6/4 13:31
 * @Version 1.0
 **/
@Repository
@SqlResource("api/hourStatements")
public interface HourStatementsDao extends BaseMapper<HourStatements> {

    /**
     * 根据站点类别，查询前几条数据（时间降序排序）
     *
     * @param siteCategory 站点类别
     * @param limit        条数
     * @return
     * @Author LiuYong
     */
    List<HourStatements> getRealHours(@Param("siteCategory") String siteCategory,
                                      @Param("limit") Integer limit);

    /**
     * 根据站点以及时间查询该时间点最近24小时的数据
     *
     * @param siteIds 站点编号集合
     * @param queryTime 查询时间
     * @return
     * @Author LiuYong
     */
    List<HourStatements> getOldHour(@Param("siteIds") List<String> siteIds,
                                    @Param("queryTime") String queryTime);

    /**
     * 根据时间以及用户id查询小时数据
     *
     * @param params 用户id 、起始时间、结束时间、站点编号
     * @Author LiuYong
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
    List<HourStatements> queryListHourByCategory(@Param("siteCategory") String siteCategory,
                                                 @Param("limit") Integer limit,
                                                 @Param("queryTime") String queryTime);

    /**
     * 获取最新一条数据
     * @param siteId
     * @Return  java.util.List<com.rate.web.api.entity.HourStatements>
     * @Author  chenshixue
     * @Date    2019/12/17 9:22
     */
    List<HourStatements> getFromDesktop(@Param("siteId") String siteId);
}
