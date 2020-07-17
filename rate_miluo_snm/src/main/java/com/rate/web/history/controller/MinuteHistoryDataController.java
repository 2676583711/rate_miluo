package com.rate.web.history.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rate.web.history.entity.PollutantMinuteData;
import com.rate.web.history.service.MinuteHistoryDataService;

/**
* <p>Title: MinuteHistoryDataController</p>  

* <p>Description: </p>  

* @author chenh  

* @date 2019年5月27日
 */
@Controller
@RequestMapping("/history/minute")
public class MinuteHistoryDataController {
	@Autowired
	private MinuteHistoryDataService minuteHistoryDataService;
	private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	@RequestMapping("/findDataByDate")
	@ResponseBody
	public List<PollutantMinuteData> getData(String siteId, String startDate, String endDate) throws ParseException {
		return minuteHistoryDataService.findByDate(siteId, SDF.parse(startDate), SDF.parse(endDate));
	}
	
}
