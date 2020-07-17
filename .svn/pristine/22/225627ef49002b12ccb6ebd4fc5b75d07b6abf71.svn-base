package com.rate.web.statement.service.impl;

import com.rate.web.statement.dao.AutoSiteDao;
import com.rate.web.statement.entity.WaterSite;
import com.rate.web.statement.service.AutoSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AutoSiteServiceImpl implements AutoSiteService {

	@Autowired
	private AutoSiteDao autoSiteDao;

	@Override
	public List<WaterSite> hourList(Map<String, Object> params) {
		return autoSiteDao.hourList(params);
	}

	@Override
	public List<WaterSite> dailyList(Map<String, Object> params) {
		return autoSiteDao.dailyList(params);
	}

	@Override
	public List<WaterSite> minuteList(Map<String, Object> params) {
		return autoSiteDao.minuteList(params);
	}
}
