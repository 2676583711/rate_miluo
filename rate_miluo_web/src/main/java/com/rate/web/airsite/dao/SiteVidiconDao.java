package com.rate.web.airsite.dao;

import com.rate.web.airsite.entity.SiteEquipment;
import com.rate.web.airsite.entity.SiteVidicon;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author xxx
 * @date 2019/5/30 9:19
 **/
@SqlResource("airSite.siteVidicon")
public interface SiteVidiconDao extends BaseMapper<SiteVidicon> {
    /**
     * 根据siteId查询设备列表
     * @param siteId
     * @return
     */
    List<SiteVidicon> getListBySiteId(@Param("siteId") Integer siteId);


}
