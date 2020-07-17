package com.rate.web.statement.service.impl;

import com.rate.system.rate_system.utils.DateUtils;
import com.rate.system.rate_system.utils.StringUtils;
import com.rate.web.dataInter.vo.PolluteVO;
import com.rate.web.site.dao.MiluoPollutantFactorDao;
import com.rate.web.statement.dao.PolluteDao;
import com.rate.web.statement.entity.PolluteRealtime;
import com.rate.web.statement.service.PolluteService;
import com.rate.web.statement.util.TableSelUtil;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author chenshixue
 * @Date 2019/8/30
 */
@Service
public class PolluteServiceImpl implements PolluteService {

    @Autowired
    private PolluteDao polluteDao;
    @Autowired
    private MiluoPollutantFactorDao polluteFactoryDao;

    @Override
    public PageQuery<PolluteRealtime> minuteList(PageQuery<PolluteRealtime> pageQuery) {
        PageQuery<PolluteRealtime> page = polluteDao.minuteList(pageQuery);
        return page;
    }
    @Override
    public List<PolluteRealtime> minuteList2(Map<String,Object> map) {
        List<PolluteRealtime> page = polluteDao.minuteList2(map);
        return page;
    }

    @Override
    public List<PolluteVO> minutePolluteInter(String siteCode, String startTime, String endTime) {
        // 获取表名
        List<String> signList = TableSelUtil.getTableSignListOfMonth(startTime,endTime, DateUtils.DATE_TIME_PATTERN);
        Map<String, Object> params = new HashMap<>();
        List<String> sqlList = new ArrayList<>();
        for (int i=0; i<signList.size(); i++) {
            String tableName = "miluo_pollutant_realtime_" + signList.get(i);
            String sql = " pr.*,s.`name` FROM "+tableName+ " pr" +
                    " LEFT JOIN `miluo_video` v ON v.`equment_id` = pr.`equment_id`" +
                    " LEFT JOIN `miluo_site` s ON s.`id` = v.`site_id`" +
                    " WHERE pr.equment_id = "+siteCode +
                    " AND pr.data_type = 1" +
                    " AND pr.recording_time BETWEEN '"+startTime+"' AND '"+endTime+"'";
            sqlList.add(sql);
        }
        String sqlAll = String.join("\nUNION\nSELECT\n", sqlList);
        params.put("sqlAll", sqlAll);
        // 获取所有数据
        List<PolluteVO> list = polluteDao.minutePolluteInter(params);
        return list;
    }

    @Override
    public List<String> findEqumentIds(List<String> siteIds) {
        return polluteDao.findEqumentIds(siteIds);
    }

    /**
     * 污染源分钟数据app接口， 将污染源中的多个因子合成一条数据返回
     * @param params
     * @Return  java.util.List<com.rate.web.api.entity.PollutantStatement>
     * @Author  chenshixue
     * @Date    2019/11/27 15:44
     */
    @Override
    public List<PolluteRealtime> appMinuteList(Map<String, Object> params) {
        String startTimeStr = (String) params.get("startTime");
        String endTimeStr = (String) params.get("endTime");
        String siteIdStr = (String) params.get("siteIdStr");
        // 获取所有表标识
        List<String> signList = TableSelUtil.getTableSignListOfMonth(startTimeStr, endTimeStr, "yyyy-MM-dd HH:mm");
        List<String> sqlList = new ArrayList<>();
        for (int i=0; i<signList.size(); i++) {
            String sql = " pr.*,s.`name` siteName,s.id site_id,v.`name` equipmentName FROM " +
                    " miluo_pollutant_realtime_" + signList.get(i) + " pr" +
                    " LEFT JOIN miluo_video v ON v.equment_id = pr.equment_id" +
                    " LEFT JOIN miluo_site s ON s.`id` = v.`site_id`" +
                    " WHERE s.status = 1" +
                    " AND s.id IN ("+siteIdStr+") AND pr.recording_time>='"+startTimeStr+"' AND pr.recording_time<='"+endTimeStr+"'" +
                    " AND pr.data_type = '1'";
            sqlList.add(sql);
        }
        // 用 UNION 拼接多张表查询
        String sqlAll = String.join("\nUNION\nSELECT\n", sqlList);
        params.put("sqlAll", sqlAll);
        List<PolluteRealtime> minuteList = polluteDao.appMinuteList(params);
        return minuteList;
    }

    @Override
    public List<PolluteRealtime> appHourList(Map<String, Object> params) {
        return polluteDao.appHourList(params);
    }

    @Override
    public List<PolluteRealtime> appDailyList(Map<String, Object> params) {
        return polluteDao.appDailyList(params);
    }

    @Transactional
    @Override
    public void
    insert(List<PolluteRealtime> list) {
        for (PolluteRealtime realtime : list) {
            polluteDao.insertPollutantEntity(realtime);
        }
    }

    @Override
    public PageQuery<PolluteRealtime> hourList(PageQuery<PolluteRealtime> pageQuery) {
        PageQuery<PolluteRealtime> page = polluteDao.hourList(pageQuery);
        return page;
    }
    @Override
    public List<PolluteRealtime> hourList2(Map<String,Object> map) {
        List<PolluteRealtime> page = polluteDao.hourList2(map);
        return page;
    }
    @Override
    public List<PolluteRealtime> hourList3(Map<String,Object> map) {
        List<PolluteRealtime> page = polluteDao.hourList3(map);
        return page;
    }
    @Override
    public PageQuery<PolluteRealtime> dayList(PageQuery<PolluteRealtime> pageQuery) {
        PageQuery<PolluteRealtime> page = polluteDao.dayList(pageQuery);
        return page;
    }
    @Override
    public List<PolluteRealtime> dayList2(Map<String,Object> map) {
        List<PolluteRealtime> page = polluteDao.dayList2(map);
        return page;
    }

    @Override
    public List<PolluteRealtime> getOldHour(String siteId, String queryTime) {
        if (StringUtils.isBlank(queryTime)) {
            queryTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH"));
        }
        return polluteDao.getOldHour(siteId, queryTime);
    }

    @Override
    public List<PolluteRealtime> getFromDesktop(String siteId) {
        return polluteDao.getFromDesktop(siteId);
    }
}
