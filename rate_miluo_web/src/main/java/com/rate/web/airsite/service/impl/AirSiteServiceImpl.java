package com.rate.web.airsite.service.impl;

import com.rate.system.rate_system.dao.SitePowerDao;
import com.rate.system.rate_system.dao.UserDao;
import com.rate.system.rate_system.entity.SitePower;
import com.rate.system.rate_system.utils.IdGen;
import com.rate.web.airsite.dao.AirSiteDao;
import com.rate.web.airsite.dao.PollutantFactoryDao;
import com.rate.web.airsite.entity.AirSite;
import com.rate.web.airsite.entity.PollutantFactory;
import com.rate.web.airsite.service.AirSiteService;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xxx
 * @date 2019/5/27 16:13
 **/
@Service
public class AirSiteServiceImpl implements AirSiteService {
    @Autowired
    AirSiteDao airSiteDao;
    @Autowired
    UserDao userDao;
    @Autowired
    PollutantFactoryDao pollutantFactoryDao;
    @Autowired
    SitePowerDao sitePowerDao;

    @Override
    public PageQuery<AirSite> getListByQueryCondition(PageQuery<AirSite> params) {
        return airSiteDao.getListByQueryCondition(params);
    }

    /**
     * 新增站点，并给所有系统管理员等用户添加该站点权限
     * @param airSite
     * @Return  void
     * @Author  chenshixue
     * @Date    2019/12/12 16:48
     */
    @Transactional
    @Override
    public void saveSite(AirSite airSite) {
        int siteId = airSiteDao.insertReturnKey(airSite).getInt();
        List<Integer> userIds = userDao.listAllSysUser();
        userIds.forEach(u -> {
            SitePower sitePower = new SitePower();
            sitePower.setId(IdGen.uuid());
            sitePower.setSiteCode(String.valueOf(siteId));
            sitePower.setUserId((long)u);
            sitePowerDao.insert(sitePower);
        });
    }

    /**
     * 删除站点，并删除权限表中的站点
     * @param id
     * @Return  void
     * @Author  chenshixue
     * @Date    2019/12/12 16:50
     */
    @Transactional
    @Override
    public void remove(Integer id) {
        airSiteDao.updateStatusById(id);
        sitePowerDao.deleteBySiteId(String.valueOf(id));
    }

    @Override
    public void updateSite(AirSite airSite) {
        airSiteDao.updateTemplateById(airSite);
    }

    @Override
    public void saveSite(PollutantFactory pollutantFactory) {
        pollutantFactoryDao.insertTemplate(pollutantFactory);
    }

    @Transactional
    @Override
    public void batchRemove(Integer[] ids) {
        for (Integer id:ids){
//            airSiteDao.deleteById(id);
            // 改成伪删除
            airSiteDao.updateStatusById(id);
            // 删除用户权限，真删除
            sitePowerDao.deleteBySiteId(String.valueOf(id));
        }
        //airSiteDao.deleteBySiteCodes(siteCodes);

    }

    @Override
    public AirSite findById(String id) {
        return airSiteDao.findById(id);
    }


}
