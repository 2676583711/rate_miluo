package com.rate.web.statement.dao;

import java.util.List;
import java.util.Map;

import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;

import com.rate.web.statement.entity.RealtimeTreatmentPlant;
@SqlResource("statement.waterPlant")
public interface WaterPlantDao extends BaseMapper<RealtimeTreatmentPlant> {

	PageQuery<RealtimeTreatmentPlant> autoSiteList(PageQuery<RealtimeTreatmentPlant> pageQuery);

	PageQuery<RealtimeTreatmentPlant> pollutList(PageQuery<RealtimeTreatmentPlant> pageQuery);

	List<RealtimeTreatmentPlant> hourList(Map<String, Object> params);

	List<RealtimeTreatmentPlant> minuteList(Map<String, Object> params);

	List<RealtimeTreatmentPlant> dailyList(Map<String, Object> params);

}
