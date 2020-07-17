package com.rate.web.alarm.waterauto.dao;

import com.rate.web.alarm.entity.AlarmEntity;
import com.rate.web.alarm.monitor.entity.Exceed;
import com.rate.web.alarm.waterauto.entity.Water;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@SqlResource("alarm.waterauto.water")
@Repository
public interface WaterDao extends BaseMapper<Exceed> {
    /**
     * @Param [params]
     * @Return org.beetl.sql.core.engine.PageQuery<com.rate.web.alarm.waterauto.entity.Water>
     * @author xiaoshi
     * @Description
     * @Date 2019/6/10
     * @Time 22:19
     **/
    PageQuery<AlarmEntity> waterAlarm(PageQuery<AlarmEntity> params);
    List<Water> waterAlarmExp(Map<String,Object> params);
    /**
     * @Param [id]
     * @Return com.rate.web.alarm.waterauto.entity.Water
     * @author xiaoshi
     * @Description
     * @Date 2019/6/10
     * @Time 22:19
     **/
    Water WaterExceedById(@Param("id") String id);
    /**
     * @Param [id]
     * @Return void
     * @author xiaoshi
     * @Description
     * @Date 2019/6/10
     * @Time 22:19
     **/
    void updateWater(@Param("id") String id);
    /**
     * @Param [id]
     * @Return void
     * @author xiaoshi
     * @Description
     * @Date 2019/6/10
     * @Time 22:19
     **/
    void updateWaterReturn(@Param("id") String id);
    /**
     * @Param [id]
     * @Return void
     * @author xiaoshi
     * @Description
     * @Date 2019/6/10
     * @Time 22:19
     **/

    void updateWaterTask(@Param("id") String id);
    /**
     * @Param [params]
     * @Return org.beetl.sql.core.engine.PageQuery<com.rate.web.alarm.waterauto.entity.Water>
     * @author xiaoshi
     * @Description
     * @Date 2019/6/10
     * @Time 22:20
     **/
    PageQuery<Water> buttonWaterAlarm(PageQuery<Water> params);


}
