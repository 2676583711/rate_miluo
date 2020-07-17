package com.rate.web.connected.newdata.service;

import com.rate.web.connected.newdata.entity.PolluteWaterHourStatement;
import com.rate.web.dataInter.vo.PolluteWaterVO;
import org.beetl.sql.core.engine.PageQuery;

import java.util.List;

public interface PolluteWaterHourStatementService {
/**
 * @Param [params]
 * @Return org.beetl.sql.core.engine.PageQuery<com.rate.web.connected.newdata.entity.PolluteWaterHourStatement>
 * @author xiaoshi       
 * @Description  
 * @Date 2019/6/3
 * @Time 15:50
 **/

    PageQuery<PolluteWaterHourStatement> polluteWaterDetails(PageQuery<PolluteWaterHourStatement> params);

   /**
    * @Param [params]
    * @Return org.beetl.sql.core.engine.PageQuery<com.rate.web.connected.newdata.entity.PolluteWaterHourStatement>
    * @author xiaoshi
    * @Description
    * @Date 2019/7/8
    * @Time 23:35
    **/
   List<PolluteWaterVO> hourPolluteInter(String siteCode, String startTime, String endTime);


}







