package com.rate.web.connected.newdata.dao;

import com.rate.web.connected.newdata.entity.WaterAuto;
import com.rate.web.dataInter.vo.WaterAutoVO;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;

@SqlResource("connected.newdata.waterauto")
public interface WaterAutoDao extends BaseMapper<WaterAuto> {
/**
 * @Param [params]
 * @Return org.beetl.sql.core.engine.PageQuery<com.rate.web.connected.newdata.entity.WaterAuto>
 * @author xiaoshi       
 * @Description  
 * @Date 2019/6/1
 * @Time 10:42
 **/
    PageQuery<WaterAuto> waterAutoLatest(PageQuery<WaterAuto> params);
/**
 * @Param [ids, beginTime, endTime]
 * @Return java.util.List<com.rate.web.connected.newdata.entity.AirHourStatement>
 * @author xiaoshi       
 * @Description  
 * @Date 2019/6/3
 * @Time 8:49
 **/
    List<WaterAuto> waterAutoRate(@Param("ids") List<String> ids,
                                  @Param("beginTime") String beginTime,
                                  @Param("endTime") String endTime);
    /**
     * @Param [beginTime, endTime]
     * @Return java.util.List<java.lang.String>
     * @author xiaoshi       
     * @Description  
     * @Date 2019/6/3
     * @Time 10:22
     **/
    List<String> waterAutoIds(@Param("beginTime") String beginTime,
                              @Param("endTime") String endTime);

/**
 * @Param [params]
 * @Return org.beetl.sql.core.engine.PageQuery<com.rate.web.connected.newdata.entity.WaterAuto>
 * @author xiaoshi
 * @Description
 * @Date 2019/6/3
 * @Time 13:03
 **/

    PageQuery<WaterAuto> waterAutoDetails(PageQuery<WaterAuto> params);

     /**
      * @Param [params]
      * @Return org.beetl.sql.core.engine.PageQuery<com.rate.web.connected.newdata.entity.WaterAuto>
      * @author xiaoshi
      * @Description
      * @Date 2019/7/8
      * @Time 23:30
      **/
    List<WaterAutoVO> minuteWaterInter(@Param("siteCode") String siteCode,
                                       @Param("startTime") String startTime,
                                       @Param("endTime") String endTime);



}
