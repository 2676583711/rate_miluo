package com.rate.web.connected.newdata.service.impl;

import com.rate.web.connected.newdata.dao.WaterAutoDao;
import com.rate.web.connected.newdata.entity.WaterAuto;
import com.rate.web.connected.newdata.service.WaterAutoService;
import com.rate.web.dataInter.vo.WaterAutoVO;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaterAutoServiceImpl implements WaterAutoService {

   @Autowired
   private WaterAutoDao waterAutoDao;
/**
 * @Param [params]
 * @Return org.beetl.sql.core.engine.PageQuery<com.rate.web.connected.newdata.entity.WaterAuto>
 * @author xiaoshi
 * @Description
 * @Date 2019/6/1
 * @Time 10:37
 **/
    @Override
    public PageQuery<WaterAuto> waterAutoLatest(PageQuery<WaterAuto> params) {
        return waterAutoDao.waterAutoLatest(params);
    }
   /**
    * @Param [params]
    * @Return org.beetl.sql.core.engine.PageQuery<com.rate.web.connected.newdata.entity.WaterAuto>
    * @author xiaoshi
    * @Description
    * @Date 2019/6/3
    * @Time 13:03
    **/
    @Override
    public PageQuery<WaterAuto> waterAutoDetails(PageQuery<WaterAuto> params) {
        return waterAutoDao.waterAutoDetails(params);
    }

    @Override
    public List<WaterAutoVO> minuteWaterInter(String siteCode, String startTime, String endTime) {
        return waterAutoDao.minuteWaterInter(siteCode,startTime,endTime);
    }


}
