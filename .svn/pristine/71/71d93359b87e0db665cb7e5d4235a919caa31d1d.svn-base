package com.rate.web.statement.service.impl;

import java.util.List;
import java.util.Map;

import com.rate.system.rate_system.dao.SitePowerDao;
import com.rate.system.rate_system.dao.UserDao;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rate.web.statement.dao.AirCheckConDao;
import com.rate.web.statement.entity.AirMinuteStatement;
import com.rate.web.statement.service.AirCheckConService;
import com.rate.web.statement.vo.AirDailyVO;
import com.rate.web.statement.vo.AirHourVO;

@Service
public class AirCheckConServiceImpl implements AirCheckConService {

	@Autowired
	private AirCheckConDao airCheckConDao;

	@Override
	public PageQuery<AirMinuteStatement> minuteList(PageQuery<AirMinuteStatement> pageQuery) {
		return airCheckConDao.minuteList(pageQuery);
	}

	@Override
	public List<AirHourVO> hourList(Map<String, Object> params) {
		return airCheckConDao.hourList(params);
	}

	@Override
	public List<AirDailyVO> dailyList(Map<String, Object> params) {
		return airCheckConDao.dailyList(params);
	}

	@Override
	public List<AirHourVO> monthList(Map<String, Object> params) {
		return airCheckConDao.monthList(params);
	}

}
