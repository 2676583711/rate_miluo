package com.rate.web.history.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.rate.web.history.entity.PollutantMinuteData;

/**
 * 

* <p>Title: MinuteHistoryDataService</p>  

* <p>Description: </p>  

* @author chenh  

* @date 2019年5月27日
 */
public interface MinuteHistoryDataService {
	
	public List<PollutantMinuteData> findByDate(String siteId, Date startDate, Date endDate);
	
	public void save(PollutantMinuteData pollutantMinuteData);
	
	public void saveList(List<PollutantMinuteData> pollutantMinuteDatas);

	public List<Map<String,Object>> getMinData(String siteCode, Date stm, Date etm);
	
}
