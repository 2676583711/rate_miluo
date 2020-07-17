package com.rate.web.statement.dao;

import com.rate.web.statement.entity.WaterSite;
import org.beetl.sql.core.annotatoin.SqlResource;

import java.util.List;
import java.util.Map;

@SqlResource("statement.autoSite")
public interface AutoSiteDao {

	List<WaterSite> hourList(Map<String, Object> params);

	List<WaterSite> dailyList(Map<String, Object> params);

	List<WaterSite> minuteList(Map<String, Object> params);
}
