package com.rate.web.airsite.dao;

import com.rate.web.airsite.entity.SiteParam;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author xxx
 * @date 2019/5/30 9:38
 **/
@SqlResource("airSite.siteParam")
public interface SiteParamDao extends BaseMapper<SiteParam> {

    List<SiteParam> getParamListByVideoId(@Param("videoId") Integer videoId);

    public void deleteByEquId(@Param("equId")Integer id);
}
