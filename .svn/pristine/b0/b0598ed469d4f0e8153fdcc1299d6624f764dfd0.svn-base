package com.rate.web.airsite.dao;


import com.rate.web.airsite.entity.AirSite;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;

/**
 * @author xxx
 * @date 2019/5/27 16:13
 **/
@SqlResource("airSite.airSite")
public interface AirSiteDao extends BaseMapper<AirSite> {
    /**
     * bootstrap table  空气站点分页查询
     * @param params
     * @return
     */
    PageQuery<AirSite> getListByQueryCondition(PageQuery<AirSite> params);

     public  AirSite findById(@Param("id") String id);

     int deleteBySiteCodes(@Param("siteCodes")String[] siteCodes);

     public  String bySiteCode(@Param("siteCode") String siteCode);


    void updateStatusById(@Param("id") Integer id);
}
