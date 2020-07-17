package com.rate.web.statement.dao;

import java.util.List;
import java.util.Map;

import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;

import com.rate.web.statement.entity.AirMinuteStatement;
import com.rate.web.statement.vo.AirDailyVO;
import com.rate.web.statement.vo.AirHourVO;

@SqlResource("statement.airCheckCon")
public interface AirCheckConDao extends BaseMapper<AirMinuteStatement> {

	PageQuery<AirMinuteStatement> minuteList(PageQuery<AirMinuteStatement> pageQuery);

	List<AirHourVO> hourList(Map<String, Object> params);

	List<AirDailyVO> dailyList(Map<String, Object> params);

	List<AirHourVO> monthList(Map<String, Object> params);

}
