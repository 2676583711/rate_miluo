package com.rate.web.connected.newdata.service;

import com.rate.web.connected.newdata.entity.WaterAuto;
import com.rate.web.dataInter.vo.WaterAutoVO;
import org.beetl.sql.core.engine.PageQuery;

import java.util.List;

public interface WaterAutoService {
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
     * @Param [params]
     * @Return org.beetl.sql.core.engine.PageQuery<com.rate.web.connected.newdata.entity.WaterAuto>
     * @author xiaoshi       
     * @Description  
     * @Date 2019/6/3
     * @Time 13:02
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
  List<WaterAutoVO> minuteWaterInter(String siteCode, String startTime, String endTime);

}
