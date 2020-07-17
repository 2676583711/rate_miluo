package com.rate.web.connected.offdata.service;


import com.rate.web.connected.offdata.entity.MiluoStatementType;
import org.beetl.sql.core.engine.PageQuery;

import java.util.List;

public interface MiluoStatementTypeService {
   /**
    * @Param [params]
    * @Return org.beetl.sql.core.engine.PageQuery<com.rate.web.connected.offdata.entity.MiluoStatementType>
    * @author xiaoshi
    * @Description
    * @Date 2019/6/13
    * @Time 11:44
    **/
    PageQuery<MiluoStatementType> lx(PageQuery<MiluoStatementType> params);

    /**
     * @Param [params]
     * @Return org.beetl.sql.core.engine.PageQuery<com.rate.web.connected.offdata.entity.MiluoStatementType>
     * @author xiaoshi
     * @Description
     * @Date 2019/6/13
     * @Time 11:46
     **/

    PageQuery<MiluoStatementType> lxPolluteWater(PageQuery<MiluoStatementType> params);

    /**
     * @Param [params]
     * @Return org.beetl.sql.core.engine.PageQuery<com.rate.web.connected.offdata.entity.MiluoStatementType>
     * @author xiaoshi
     * @Description
     * @Date 2019/6/13
     * @Time 15:19
     **/
    PageQuery<MiluoStatementType> lxWaterAuto(PageQuery<MiluoStatementType> params);

 /**
  * @Param [id, beginTime, endTime]
  * @Return java.util.List<com.rate.web.connected.offdata.entity.MiluoStatementType>
  * @author xiaoshi
  * @Description
  * @Date 2019/6/13
  * @Time 11:44
  **/
 List<MiluoStatementType> oSList(List<String> id, String beginTime, String endTime);


}
