package com.rate.web.connected.offdata.service.impl;


import com.rate.web.connected.offdata.dao.MiluoStatementTypeDao;
import com.rate.web.connected.offdata.entity.MiluoStatementType;
import com.rate.web.connected.offdata.service.MiluoStatementTypeService;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MiluoStatementTypeServiceImpl implements MiluoStatementTypeService {

   @Autowired
   private MiluoStatementTypeDao miluoStatementTypeDao;

  /**
   * @Param [params]
   * @Return org.beetl.sql.core.engine.PageQuery<com.rate.web.connected.offdata.entity.MiluoStatementType>
   * @author xiaoshi
   * @Description
   * @Date 2019/6/13
   * @Time 11:46
   **/
    @Override
    public PageQuery<MiluoStatementType> lx(PageQuery<MiluoStatementType> params) {
        return miluoStatementTypeDao.lx(params);


}
   /**
    * @Param [params]
    * @Return org.beetl.sql.core.engine.PageQuery<com.rate.web.connected.offdata.entity.MiluoStatementType>
    * @author xiaoshi
    * @Description
    * @Date 2019/6/13
    * @Time 11:46
    **/
    @Override
    public PageQuery<MiluoStatementType> lxPolluteWater(PageQuery<MiluoStatementType> params) {
        return miluoStatementTypeDao.lxPolluteWater(params);
    }
    /**
     * @Param [params]
     * @Return org.beetl.sql.core.engine.PageQuery<com.rate.web.connected.offdata.entity.MiluoStatementType>
     * @author xiaoshi       
     * @Description  
     * @Date 2019/6/13
     * @Time 15:20
     **/
    @Override
    public PageQuery<MiluoStatementType> lxWaterAuto(PageQuery<MiluoStatementType> params) {
        return miluoStatementTypeDao.lxWaterAuto(params);
    }

    /**
     * @Param [id, beginTime, endTime]
     * @Return java.util.List<com.rate.web.connected.offdata.entity.MiluoStatementType>
     * @author xiaoshi
     * @Description
     * @Date 2019/6/13
     * @Time 11:46
     **/
    @Override
    public List<MiluoStatementType> oSList(List<String> id, String beginTime, String endTime) {
        return miluoStatementTypeDao.oSList(id,beginTime,endTime);
    }
}
