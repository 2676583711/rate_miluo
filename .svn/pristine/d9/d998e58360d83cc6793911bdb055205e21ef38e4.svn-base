package com.rate.web.site.service.impl;

import com.rate.web.site.dao.FactorParamsVideoDao;
import com.rate.web.site.dao.ParamDao;
import com.rate.web.site.entity.FactorAndParam;
import com.rate.web.site.entity.FactorParam;
import com.rate.web.site.entity.FactorParamsVideo;
import com.rate.web.site.service.ParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author chenshixue
 * @Date 2019/12/10
 */
@Service
public class ParamServiceImpl implements ParamService {

    private final ParamDao paramDao;
    private final FactorParamsVideoDao factorParamsVideoDao;

    @Autowired
    public ParamServiceImpl(ParamDao paramDao,FactorParamsVideoDao factorParamsVideoDao) {
        this.paramDao = paramDao;
        this.factorParamsVideoDao = factorParamsVideoDao;
    }
    @Override
    public List<FactorAndParam> findParamList(Map<String, Object> params) {
        return paramDao.findParamList(params);
    }

    @Override
    public List<FactorAndParam> findVideoInfo(Integer siteId) {
        return paramDao.findVideoInfo(siteId);
    }

    @Override
    public List<FactorParam> findInputFactorList(Map<String, Object> params) {
        return paramDao.findInputFactorList(params);
    }

    @Override
    public List<FactorParam> findParamListByType(String type) {
        return paramDao.findParamListByType(type);
    }

    @Override
    public List<FactorAndParam> findParamAndLimit(String type, String equmentId) {
        return paramDao.findParamAndLimit(type, equmentId);
    }

    @Transactional
    @Override
    public void listUpdateObject(List<FactorParamsVideo> paramList, String videoId) {
        for (FactorParamsVideo paramsVideo : paramList) {
            paramsVideo.setUpdateTime(new Date());
            if (paramsVideo.getId() == null) {
                // 第一次配置，新增操作
                paramsVideo.setVideoId(videoId);
                factorParamsVideoDao.insert(paramsVideo);
            } else {
                // 修改操作
                factorParamsVideoDao.updateById(paramsVideo);
            }
        }
    }

}
