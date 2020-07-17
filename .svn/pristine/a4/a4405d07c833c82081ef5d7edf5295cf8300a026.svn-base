package com.rate.web.graphic.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.rate.system.rate_system.utils.DateUtils;
import com.rate.system.rate_system.utils.StringUtils;
import com.rate.web.alarm.entity.AlarmEntity;
import com.rate.web.graphic.dao.GraphicPolluteDao;
import com.rate.web.graphic.service.GraphicPolluteService;
import com.rate.web.site.dao.MiluoPollutantFactorDao;
import com.rate.web.site.entity.MiluoPollutantFactor;
import com.rate.web.statement.entity.PolluteRealtime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author shuzhangyao
 * @date 2019/5/31 17:05
 **/
@Service
public class GraphicPolluteServiceImpl implements GraphicPolluteService {

    @Autowired
    private GraphicPolluteDao graphicPolluteDao;

    @Autowired
    private MiluoPollutantFactorDao pollutantFactorDao;

    /**
     * 获取小时数据
     * @param params 条件参数
     * @return java.util.List<com.rate.web.graphic.entity.AirHourStatements>
     * @author shuzhangyao
     * @date 2019/5/31 9:30
     **/
    @Override
    public JSONObject findHourData(Map<String, Object> params){
        List<PolluteRealtime> all = graphicPolluteDao.findHourData(params);
        Map<String, List<PolluteRealtime>> result = new HashMap<>();
        Set<String> x = new HashSet<>();
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.DATE_TIME_PATTERN3);
        for (int i = 0; i< all.size();i++){
            PolluteRealtime hour = all.get(i);
            String siteName = hour.getSiteName() + "(" +hour.getEquipmentName() + ")";
            if(StringUtils.isBlank(siteName) || "null".equals(siteName)){
                continue;
            }
            if(result.containsKey(siteName)){
                result.get(siteName).add(hour);
            }else{
                List<PolluteRealtime> list = new ArrayList<>();
                list.add(hour);
                result.put(siteName, list);
            }
            x.add(sdf.format(hour.getRecordingTime()));
        }
        List<String> xs = new ArrayList<>(x);
        Collections.sort(xs);
        JSONObject obj = new JSONObject();
        obj.put("data", result);
        obj.put("xItem", xs);
        return obj;
    }
    /**
     * 获取天数据
     * @param params 条件参数
     * @return java.util.List<com.rate.web.graphic.entity.AirHourStatements>
     * @author shuzhangyao
     * @date 2019/5/31 11:57
     **/
    @Override
    public JSONObject findDayData(Map<String, Object> params){
        List<PolluteRealtime> all = graphicPolluteDao.findDayData(params);
        Map<String, List<PolluteRealtime>> result = new HashMap<>();
        Set<String> x = new HashSet<>();
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.DATE_PATTERN);
        for (int i = 0; i< all.size();i++){
            PolluteRealtime day = all.get(i);
            String siteName = day.getSiteName() + "(" +day.getEquipmentName() + ")";
            if(StringUtils.isBlank(siteName) || "null".equals(siteName)){
                continue;
            }
            if(result.containsKey(siteName)){
                result.get(siteName).add(day);
            }else{
                List<PolluteRealtime> list = new ArrayList<>();
                list.add(day);
                result.put(siteName, list);
            }
            x.add(sdf.format(day.getRecordingTime()));
        }
        List<String> xs = new ArrayList<>(x);
        Collections.sort(xs);
        JSONObject obj = new JSONObject();
        obj.put("data", result);
        obj.put("xItem", xs);
        return obj;
    }
    /**
     *
     * @param param 站点编号 时间(yyyy-MM-dd)
     * @return java.util.List<com.rate.grid.web.TimedTask.entity.HourStatement>
     * @author shuzhangyao
     * @date 2019/5/31 13:57
     **/
    @Override
    public JSONObject findChangeData(Map<String, Object> param) {
//        List<PollutantHour> all = graphicPolluteDao.findHourData(param);
        JSONObject result = new JSONObject();
//        for (PollutantHour hour: all){
//            String siteName = hour.getSiteName();
//            if(result.containsKey(siteName)){
//                ((List<PollutantHour>)result.get(siteName)).add(hour);
//            }else{
//                List<PollutantHour> list = new ArrayList<>();
//                list.add(hour);
//                result.put(siteName, list);
//            }
//        }
        return result;
    }

    /**
     * 获取最新一条记录
     * @param params
     * @Return  com.alibaba.fastjson.JSONObject
     * @Author  chenshixue
     * @Date    2019/8/28 18:13
     */
    @Override
    public List<PolluteRealtime> findNewMinuteData(Map<String, Object> params) {
        List<PolluteRealtime> all = graphicPolluteDao.findNewMinuteData(params);
//        List<MiluoPollutantFactor> factors = pollutantFactorDao.all();
//        List<PolluteVO> voList = new ArrayList<>();

        // 先根据equmentId 分组

        return all;
    }

    @Override
    public JSONObject findMinuteData(Map<String, Object> params) {
        JSONObject result = new JSONObject();
        Map<String, List<PolluteRealtime>> voMap = new LinkedHashMap<>();
        String startTime = (String) params.get("startTime");
        String endTime = (String) params.get("endTime");
        List<String> tableNames = (List<String>) params.get("tableNames");
        List<String> siteIds = (List<String>) params.get("siteIds");
        String siteCode = String.join(",", siteIds);
        List<String> xItems = DateUtils.getBetweenDate(startTime, endTime);
        result.put("xTime", xItems);
        List<String> sqlList = new ArrayList<>();
        for (int i=0; i<tableNames.size(); i++) {
            String sql = " mpr.*,s.name siteName,v.`name` equipmentName from "+tableNames.get(i)+" mpr" +
                    " left join miluo_video v on v.equment_id = mpr.equment_id" +
                    " left join miluo_site s on s.id = v.site_id" +
                    " where mpr.recording_time BETWEEN '"+startTime+"' and '"+endTime+"'" +
                    " and s.id in ("+siteCode+") and s.status = 1 and mpr.data_type = 1";
            sqlList.add(sql);
        }
        String sqlAll = String.join("\nUNION\nSELECT", sqlList);
        params.put("sqlAll", sqlAll);
        List<PolluteRealtime> all = graphicPolluteDao.findMinuteData(params);
        for (PolluteRealtime realtime : all) {
            if (!voMap.containsKey(realtime.getEqumentId())) {
                List<PolluteRealtime> list = new ArrayList<>();
                list.add(realtime);
                voMap.put(realtime.getEqumentId(), list);
            } else {
                voMap.get(realtime.getEqumentId()).add(realtime);
            }
        }
        result.put("data", voMap);
        return result;
    }

    @Override
    public List<String> findTableList() {
        return graphicPolluteDao.findTableList();
    }

    @Override
    public List<AlarmEntity> sel(HashMap<String, Object> map) {
        return graphicPolluteDao.sel(map);
    }

}
