package com.rate.web.airsite.service;

import com.rate.web.airsite.entity.SiteEquipment;

import java.util.List;

/**
 * @author xxx
 * @date 2019/5/30 9:22
 **/
public interface SiteEquipmentService {

    List<SiteEquipment> getEquBySiteId(Integer siteId);

    List<SiteEquipment> getEquBySiteIdAndName(Integer siteId,String name);


    void  insert(SiteEquipment siteEquipment);

    int countEquipments(String equmentId);

    SiteEquipment getById(Integer id);

    void deleteAll(SiteEquipment siteEquipment);
}
