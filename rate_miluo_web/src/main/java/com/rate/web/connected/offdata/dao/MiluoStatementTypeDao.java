package com.rate.web.connected.offdata.dao;


import com.rate.web.connected.offdata.entity.MiluoStatementType;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;


@SqlResource("connected.offline.statement")
public interface MiluoStatementTypeDao extends BaseMapper<MiluoStatementType> {

    /**
     * @Param [params]
     * @Return org.beetl.sql.core.engine.PageQuery<com.rate.web.connected.offdata.entity.MiluoStatementType>
     * @author xiaoshi
     * @Description
     * @Date 2019/6/13
     * @Time 11:43
     **/
    PageQuery<MiluoStatementType> lx(PageQuery<MiluoStatementType> params);

    /**
     * @Param [beginTime, endTime]
     * @Return java.util.List<java.lang.String>
     * @author xiaoshi
     * @Description
     * @Date 2019/6/13
     * @Time 11:43
     **/
    List<String> idList(@Param("beginTime") String beginTime,
                        @Param("endTime") String endTime);
    /**
     * @Param [params]
     * @Return org.beetl.sql.core.engine.PageQuery<com.rate.web.connected.offdata.entity.MiluoStatementType>
     * @author xiaoshi
     * @Description
     * @Date 2019/6/13
     * @Time 11:44
     **/
    PageQuery<MiluoStatementType> lxPolluteWater(PageQuery<MiluoStatementType> params);
    /**
     * @Param [beginTime, endTime]
     * @Return java.util.List<java.lang.String>
     * @author xiaoshi
     * @Description
     * @Date 2019/6/13
     * @Time 11:46
     **/
    List<String> idPolluteWaterList(@Param("beginTime") String beginTime,
                                    @Param("endTime") String endTime);
    
    /**
     * @Param [params]
     * @Return org.beetl.sql.core.engine.PageQuery<com.rate.web.connected.offdata.entity.MiluoStatementType>
     * @author xiaoshi       
     * @Description  
     * @Date 2019/6/13
     * @Time 15:18
     **/
    PageQuery<MiluoStatementType> lxWaterAuto(PageQuery<MiluoStatementType> params);
    
    /**
     * @Param [beginTime, endTime]
     * @Return java.util.List<java.lang.String>
     * @author xiaoshi       
     * @Description  
     * @Date 2019/6/13
     * @Time 15:18
     **/

    List<String> idWaterAutoList(@Param("beginTime") String beginTime,
                                 @Param("endTime") String endTime);

    List<MiluoStatementType> oSList(@Param("id") List<String> id,
                                    @Param("beginTime") String beginTime,
                                    @Param("endTime") String endTime);
}
