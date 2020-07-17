package com.rate.web.airsite.service.impl;

import com.rate.web.airsite.dao.SiteEquipmentDao;
import com.rate.web.airsite.entity.SiteEquipment;
import com.rate.web.airsite.service.SiteEquipmentService;
import com.rate.web.site.dao.FactorParamsVideoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xxx
 * @date 2019/5/30 9:22
 **/
@Service
public class SiteEquipmentServiceImpl implements SiteEquipmentService {
    @Autowired
    SiteEquipmentDao siteEquipmentDao;
    @Autowired
    FactorParamsVideoDao factorParamsVideoDao;

    @Override
    public List<SiteEquipment> getEquBySiteId(Integer siteId) {

        return siteEquipmentDao.getEquBySiteId(siteId);
    }

    @Override
    public List<SiteEquipment> getEquBySiteIdAndName(Integer siteId, String name) {
        return siteEquipmentDao.getEquBySiteIdAndName(siteId,name);
    }

    @Override
    public void insert(SiteEquipment siteEquipment) {
        siteEquipmentDao.insertM(siteEquipment);

    }

    @Override
    public int countEquipments(String equmentId) {
        return siteEquipmentDao.countEquipments(equmentId);
    }

    @Override
    public SiteEquipment getById(Integer id) {
        return siteEquipmentDao.single(id);
    }

    @Transactional
    @Override
    public void deleteAll(SiteEquipment siteEquipment) {
//        siteEquipmentDao.deleteById(siteEquipment.getId());
        factorParamsVideoDao.deleteByVideoId(siteEquipment.getEqumentId());
    }
}
