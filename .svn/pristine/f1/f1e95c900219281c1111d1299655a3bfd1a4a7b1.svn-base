package com.rate.web.history.dao;


import java.util.Date;
import java.util.List;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import com.rate.web.history.entity.PollutantMinuteData;

/**
 * 

* <p>Title: MinuteHistoryDataDao</p>  

* <p>Description: </p>  

* @author chenh  

* @date 2019年5月27日
 */
@SqlResource("history.pollutantMinuteData")
public interface MinuteHistoryDataDao extends BaseMapper<PollutantMinuteData> {
	List<PollutantMinuteData> getFactorData(@Param("siteCode")String siteCode, @Param("startDate")Date startDate, @Param("endDate")Date endDate);
}
