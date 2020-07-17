package com.rate.web.api.dao;

import com.rate.web.api.entity.DailyStatements;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author liuyong
 * @Date 2019/6/4 14:59
 * @Version 1.0
 **/
@Repository
@SqlResource("api/dailyStatements")
public interface DailyStatementsDao extends BaseMapper<DailyStatements> {

    /**
     * 根据站点查询最近7天的数据
     *
     * @param siteIds 站点id集合
     * @return
     * @Author LiuYong
     */
    List<DailyStatements> getOldDay(@Param("siteIds") List<String> siteIds);

    /**
     * 根据时间以及用户id查询日数据
     *
     * @param params 用户id 、起始时间、结束时间、站点编号
     * @return
     * @Author LiuYong
     */
    List<DailyStatements> getListByTimeAndSiteCode(Map<String, Object> params);
}
