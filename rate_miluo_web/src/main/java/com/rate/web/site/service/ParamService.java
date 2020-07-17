package com.rate.web.site.service;

import com.rate.web.site.entity.FactorAndParam;
import com.rate.web.site.entity.FactorParam;
import com.rate.web.site.entity.FactorParamsVideo;

import java.util.List;
import java.util.Map;

/**
 * @Author chenshixue
 * @Date 2019/12/10
 */
public interface ParamService {
    List<FactorAndParam> findParamList(Map<String, Object> params);

    List<FactorAndParam> findVideoInfo(Integer siteId);

    List<FactorParam> findInputFactorList(Map<String, Object> params);

    List<FactorParam> findParamListByType(String type);

    List<FactorAndParam> findParamAndLimit(String type, String equmentId);

    void listUpdateObject(List<FactorParamsVideo> paramList, String videoId);
}
