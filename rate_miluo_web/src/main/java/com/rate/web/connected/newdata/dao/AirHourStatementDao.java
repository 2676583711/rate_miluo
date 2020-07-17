package com.rate.web.connected.newdata.dao;


import com.rate.web.connected.newdata.entity.AirHourStatement;
import com.rate.web.dataInter.vo.AirVO;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

@SqlResource("connected.newdata.airhourstatement")
public interface AirHourStatementDao extends BaseMapper<AirHourStatement> {

    List<String> ids(@Param("beginTime") String beginTime,
                     @Param("endTime") String endTime);

    List<AirHourStatement> dataRate(@Param("ids") List<String> ids,
                                    @Param("beginTime") String beginTime,
                                    @Param("endTime") String endTime);

    PageQuery<AirHourStatement> findDetails(PageQuery<AirHourStatement> params);


    List<AirHourStatement> standardSite(Map<String,Object> params);

     /**
      * @Param [siteCode, startTime, endTime]
      * @Return org.beetl.sql.core.engine.PageQuery<com.rate.web.connected.newdata.entity.AirHourStatement>
      * @author xiaoshi
      * @Description
      * @Date 2019/7/9
      * @Time 14:03
      **/
    List<AirVO> hourInter(@Param("siteCode") String siteCode,
                          @Param("startTime") String startTime,
                          @Param("endTime") String endTime);

 }
