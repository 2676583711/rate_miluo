package com.rate.web.site.dao;

import com.rate.web.site.entity.FactorAndParam;
import com.rate.web.site.entity.FactorParam;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author chenshixue
 * @Date 2019/12/10
 */
@SqlResource("site.param")
@Repository
public interface ParamDao {
    List<FactorAndParam> findParamList(Map<String, Object> params);

    List<FactorAndParam> findVideoInfo(@Param("siteId") Integer siteId);

    List<FactorParam> findInputFactorList(Map<String, Object> params);

    List<FactorParam> findParamListByType(@Param("type") String type);

    List<FactorAndParam> findParamAndLimit(@Param("type") String type,
                                           @Param("equmentId") String equmentId);
}
