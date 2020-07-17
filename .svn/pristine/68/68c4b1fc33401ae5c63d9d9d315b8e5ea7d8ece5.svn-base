package com.rate.web.connected.newdata.dao;


import com.rate.web.connected.newdata.entity.AirHourStatement;
import com.rate.web.connected.newdata.entity.PolluteWaterHourStatement;
import com.rate.web.dataInter.vo.PolluteWaterVO;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;

@SqlResource("connected.newdata.pollutewaterhourstatement")
public interface PolluteWaterHourStatementDao extends BaseMapper<AirHourStatement> {

    /**
     * @Param [beginTime, endTime]
     * @Return java.util.List<java.lang.String>
     * @author xiaoshi
     * @Description
     * @Date 2019/6/3
     * @Time 15:38
     **/
    List<String> polluteWaterIds(@Param("beginTime") String beginTime,
                                 @Param("endTime") String endTime);

    /**
     * @Param [ids, beginTime, endTime]
     * @Return java.util.List<com.rate.web.connected.newdata.entity.PolluteWaterHourStatement>
     * @author xiaoshi
     * @Description
     * @Date 2019/6/3
     * @Time 15:38
     **/
    List<PolluteWaterHourStatement> polluteWaterRate(@Param("ids") List<String> ids,
                                                     @Param("beginTime") String beginTime,
                                                     @Param("endTime") String endTime);
/**
 * @Param [params]
 * @Return org.beetl.sql.core.engine.PageQuery<com.rate.web.connected.newdata.entity.PolluteWaterHourStatement>
 * @author xiaoshi
 * @Description
 * @Date 2019/6/3
 * @Time 15:41
 **/

    PageQuery<PolluteWaterHourStatement> polluteWaterDetails(PageQuery<PolluteWaterHourStatement> params);


    /***
     * @Param [params]
     * @Return org.beetl.sql.core.engine.PageQuery<com.rate.web.connected.newdata.entity.PolluteWaterHourStatement>
     * @author xiaoshi
     * @Description
     * @Date 2019/7/8
     * @Time 23:32
     **/
    List<PolluteWaterVO> hourPolluteInter(@Param("siteCode") String siteCode,
                                          @Param("startTime") String startTime,
                                          @Param("endTime") String endTime);



}
