package com.rate.web.airparam.dao;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;

import com.rate.web.airparam.entity.AirParam;
import com.rate.web.airparam.entity.MiluoSiteDevicedParam;

@SqlResource("airparam.miluopram")
public interface MiluoSiteDevicedParamDao extends BaseMapper<MiluoSiteDevicedParam>{
	//分页查询
	PageQuery<MiluoSiteDevicedParam> airList(PageQuery<MiluoSiteDevicedParam> paras, @Param("pluName")String pluName,@Param("videoId")String videoId);
}
