package com.rate.web.statement.service;

import com.rate.web.statement.entity.RealtimeTreatmentPlant;
import org.beetl.sql.core.engine.PageQuery;

import java.util.List;
import java.util.Map;

public interface WaterPlantService {

	PageQuery<RealtimeTreatmentPlant> autoSiteList(PageQuery<RealtimeTreatmentPlant> pageQuery);

	PageQuery<RealtimeTreatmentPlant> pollutList(PageQuery<RealtimeTreatmentPlant> pageQuery);

	List<RealtimeTreatmentPlant> hourList(Map<String, Object> params);

	List<RealtimeTreatmentPlant> minuteList(Map<String, Object> params);

	List<RealtimeTreatmentPlant> dailyList(Map<String, Object> params);

}
