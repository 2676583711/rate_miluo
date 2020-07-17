package com.rate.web.statement.service.impl;

import java.util.List;
import java.util.Map;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rate.web.statement.dao.WaterPlantDao;
import com.rate.web.statement.entity.RealtimeTreatmentPlant;
import com.rate.web.statement.service.WaterPlantService;

@Service
public class WaterPlantServiceImpl implements WaterPlantService {
	
	@Autowired
	private WaterPlantDao waterPlantDao;

	@Override
	public PageQuery<RealtimeTreatmentPlant> autoSiteList(PageQuery<RealtimeTreatmentPlant> pageQuery) {
		return waterPlantDao.autoSiteList(pageQuery);
	}

	@Override
	public PageQuery<RealtimeTreatmentPlant> pollutList(PageQuery<RealtimeTreatmentPlant> pageQuery) {
		return waterPlantDao.pollutList(pageQuery);
	}

	@Override
	public List<RealtimeTreatmentPlant> hourList(Map<String, Object> params) {
		return waterPlantDao.hourList(params);
	}

	@Override
	public List<RealtimeTreatmentPlant> minuteList(Map<String, Object> params) {
		return waterPlantDao.minuteList(params);
	}

	@Override
	public List<RealtimeTreatmentPlant> dailyList(Map<String, Object> params) {
		return waterPlantDao.dailyList(params);
	}

	

}
