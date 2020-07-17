package com.rate.web.statement.service;

import com.rate.web.dataInter.vo.PolluteVO;
import com.rate.web.statement.entity.PolluteRealtime;
import org.beetl.sql.core.engine.PageQuery;

import java.util.List;
import java.util.Map;

/**
 * @Author chenshixue
 * @Date 2019/8/30
 */
public interface PolluteService {

    PageQuery<PolluteRealtime> minuteList(PageQuery<PolluteRealtime> pageQuery);
    List<PolluteRealtime> minuteList2(Map<String, Object> params);

    List<PolluteVO> minutePolluteInter(String siteCode, String startTime, String endTime);

    List<String> findEqumentIds(List<String> siteIds);

    List<PolluteRealtime> appMinuteList(Map<String, Object> params);

    List<PolluteRealtime> appHourList(Map<String, Object> params);

    List<PolluteRealtime> appDailyList(Map<String, Object> params);

    void insert(List<PolluteRealtime> list);

    PageQuery<PolluteRealtime> hourList(PageQuery<PolluteRealtime> pageQuery);
    List<PolluteRealtime> hourList2(Map<String, Object> params);
    List<PolluteRealtime> hourList3(Map<String, Object> params);
    PageQuery<PolluteRealtime> dayList(PageQuery<PolluteRealtime> pageQuery);
    List<PolluteRealtime> dayList2(Map<String, Object> params);

    List<PolluteRealtime> getOldHour(String siteId, String queryTime);

    List<PolluteRealtime> getFromDesktop(String siteId);
}
