package com.rate.web.airsite.service.impl;

import com.rate.web.airsite.dao.JianCeParamDao;
import com.rate.web.airsite.dao.SiteParamDao;
import com.rate.web.airsite.entity.JianCeParam;
import com.rate.web.airsite.entity.SiteParam;
import com.rate.web.airsite.service.SiteParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xxx
 * @date 2019/5/30 9:39
 **/
@Service
public class SiteParamServiceImpl implements SiteParamService {
    @Autowired
    SiteParamDao siteParamDao;
    @Autowired
    JianCeParamDao jianCeParamDao;
    @Override
    public List<SiteParam> getParamListByVideoId(Integer videoId) {
        return siteParamDao.getParamListByVideoId(videoId);
    }

    @Override
    public List<JianCeParam> getJianCeParamList() {
        return jianCeParamDao.getJianCeParamList();
    }

    @Override
    public void insert(SiteParam siteParam) {
        siteParamDao.insertTemplate(siteParam);
    }

}
