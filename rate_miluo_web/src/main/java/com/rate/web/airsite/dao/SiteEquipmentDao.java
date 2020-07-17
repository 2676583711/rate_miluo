package com.rate.web.airsite.dao;

import com.rate.web.airsite.entity.SiteEquipment;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author xxx
 * @date 2019/5/30 9:19
 **/
@SqlResource("airSite.siteEquipment")
public interface SiteEquipmentDao extends BaseMapper<SiteEquipment> {
    /**
     * 根据siteId查询设备列表
     * @param siteId
     * @return
     */
    List<SiteEquipment> getEquBySiteId(@Param("siteId") Integer siteId);

    List<SiteEquipment> getEquBySiteIdAndName(@Param("siteId") Integer siteId,
                                              @Param("name") String name);

    void  insertM(@Param("siteEquipment") SiteEquipment siteEquipment);

    int countEquipments(@Param("equmentId") String equmentId);
}
