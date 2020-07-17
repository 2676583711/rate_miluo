package com.rate.web.alarm.monitor.service;


import com.rate.web.alarm.entity.AlarmEntity;
import com.rate.web.alarm.entity.AlarmEntityEpx;
import com.rate.web.alarm.monitor.entity.Exceed;
import org.beetl.sql.core.engine.PageQuery;

import java.util.List;
import java.util.Map;

public interface ExceedService {
    /**
     * @Param [params]
     * @Return org.beetl.sql.core.engine.PageQuery<com.rate.web.alarm.monitor.entity.Exceed>
     * @author xiaoshi       
     * @Description  
     * @Date 2019/6/10
     * @Time 19:18
     **/
    PageQuery<Exceed> buttonAirAlarm(PageQuery<Exceed> params);
    /**
     * @Param [params]
     * @Return org.beetl.sql.core.engine.PageQuery<com.rate.web.alarm.monitor.entity.Exceed>
     * @author xiaoshi       
     * @Description  
     * @Date 2019/6/10
     * @Time 20:17
     **/

    PageQuery<AlarmEntity> airAlarmQuery(PageQuery<AlarmEntity> params);
    List<AlarmEntity> airAlarmQueryExp(Map<String,Object> params);


    /**
     * 根据 时间 状态 站点编号查询数据
     *
     * @param params
     * @param pageNum
     * @param pageSize
     * @return
     * @author LiuYong
     */
    List<Exceed> getAlertListByParams(Map<String,Object> params, Integer pageNum, Integer pageSize);


    /**
     * 根据 时间 状态 站点编号查询数据
     *
     * @param params
     * @param pageNum
     * @param pageSize
     * @return
     * @author LiuYong
     */
    List<AlarmEntity> getAlertListByParams2(Map<String,Object> params, Integer pageNum, Integer pageSize);
    AlarmEntity findInfoById(Long id);

    List<AlarmEntityEpx> airAlarmQuerySituationExp(Map<String, Object> params);
}
