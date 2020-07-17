package com.rate.web.statement.dao;

import com.rate.web.dataInter.vo.PolluteVO;
import com.rate.web.statement.entity.PolluteRealtime;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author chenshixue
 * @Date 2019/8/30
 */
@SqlResource("statement.pollutant")
@Repository
public interface PolluteDao extends BaseMapper<PolluteRealtime> {

    PageQuery<PolluteRealtime> minuteList(PageQuery<PolluteRealtime> pageQuery);
    List<PolluteRealtime> minuteList2(Map<String, Object> params);

    List<PolluteVO> minutePolluteInter(Map<String, Object> params);

    List<String> findEqumentIds(@Param("siteIds")List<String> siteIds);

    List<String> findPolluteData();

    void insertPollutantEntity(@Param("entity") PolluteRealtime entity);

    PageQuery<PolluteRealtime> hourList(PageQuery<PolluteRealtime> pageQuery);
    List<PolluteRealtime> hourList2(Map<String, Object> params);
    List<PolluteRealtime> hourList3(Map<String, Object> params);
    PageQuery<PolluteRealtime> dayList(PageQuery<PolluteRealtime> pageQuery);
    List<PolluteRealtime> dayList2(Map<String, Object> params);

    List<PolluteRealtime> appMinuteList(Map<String, Object> params);

    List<PolluteRealtime> appHourList(Map<String, Object> params);

    List<PolluteRealtime> appDailyList(Map<String, Object> params);

    List<PolluteRealtime> getOldHour(@Param("siteId")String siteId,
                                     @Param("queryTime") String queryTime);

    List<PolluteRealtime> getFromDesktop(@Param("siteId") String siteId);
}
