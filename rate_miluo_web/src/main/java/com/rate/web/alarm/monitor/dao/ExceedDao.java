package com.rate.web.alarm.monitor.dao;

import com.rate.web.alarm.entity.AlarmEntity;
import com.rate.web.alarm.entity.AlarmEntityEpx;
import com.rate.web.alarm.monitor.entity.Exceed;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@SqlResource("alarm.monitor.exceed")
public interface ExceedDao extends BaseMapper<AlarmEntity> {
    /**
     * @Param [params]
     * @Return org.beetl.sql.core.engine.PageQuery<com.rate.web.alarm.monitor.entity.Exceed>
     * @author xiaoshi
     * @Description
     * @Date 2019/6/10
     * @Time 17:22
     **/
    PageQuery<AlarmEntity> airAlarmQuery(PageQuery<AlarmEntity> params);
    List<AlarmEntity> airAlarmQueryExp(Map<String, Object> params);

    /**
     * 根据 时间 状态 站点编号查询数据
     *
     * @param params   参数
     * @param pageNum  起始值
     * @param pageSize 每页值
     * @return
     * @author LiuYong
     */
    List<Exceed> getAlertListByParams(Map<String, Object> params, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);


    /**
     * 根据时间,站点编号
     *
     * @param params
     * @param pageNum
     * @param pageSize
     * @return
     * @author LiuYong
     */
    List<Exceed> getAlertPollutantWater(Map<String, Object> params, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    /**
     * @Param [id]
     * @Return com.rate.web.alarm.monitor.entity.Exceed
     * @author xiaoshi
     * @Description
     * @Date 2019/6/10
     * @Time 11:12
     **/
    Exceed airExceedById(@Param("id") String id);

    /**
     * @Param [id]
     * @Return void
     * @author xiaoshi
     * @Description
     * @Date 2019/6/10
     * @Time 11:30
     **/

    void updateTask(@Param("id") String id);

    /**
     * @Param [params]
     * @Return org.beetl.sql.core.engine.PageQuery<com.rate.web.alarm.monitor.entity.Exceed>
     * @author xiaoshi
     * @Description
     * @Date 2019/6/10
     * @Time 17:44
     **/
    PageQuery<Exceed> buttonAirAlarm(PageQuery<Exceed> params);
    
    /**
     * @Param [startDate, endDDate]
     * @Return java.util.List<com.rate.web.alarm.monitor.entity.Exceed>
     * @author xiaoshi       
     * @Description  
     * @Date 2019/6/19
     * @Time 18:38
     **/
    List<Exceed>  latestTiming(@Param("startDate") String startDate,@Param("endDate")String endDate);

    
    /**
     * @Param [equipmentId]
     * @Return java.util.List<com.rate.web.alarm.monitor.entity.Exceed>
     * @author xiaoshi       
     * @Description  
     * @Date 2019/6/19
     * @Time 22:30
     **/
    List<Exceed> siteByEquipment(@Param("equipmentId") List<String> equipmentId);


    AlarmEntity findInfoById(@Param("id") Long id);

    List<AlarmEntityEpx> airAlarmQuerySituationExp(Map<String, Object> params);

    List<AlarmEntity> getAlertListByParams2(Map<String, Object> params, Integer pageNum, Integer pageSize);
}
