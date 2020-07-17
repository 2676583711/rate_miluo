package com.rate.web.history.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;
import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.rate.web.factory.dao.FactorDao;
import com.rate.web.factory.entity.Factor;
import com.rate.web.history.dao.MinuteHistoryDataDao;
import com.rate.web.history.entity.PollutantMinuteData;
import com.rate.web.history.service.MinuteHistoryDataService;


/**
* <p>Title: MinuteHistoryDataServiceImpl</p>  

* <p>Description: </p>  

* @author chenh  

* @date 2019年5月27日
 */
@Service
public class MinuteHistoryDataServiceImpl implements MinuteHistoryDataService {
	@Autowired
	private SQLManager sqlManager;
	@Autowired
	private MinuteHistoryDataDao minutehistoryDataDao;
	@Autowired
	private FactorDao factorDao;
	
	@Override
	public List<PollutantMinuteData> findByDate(String siteId, Date startDate, Date endDate) {
		List<PollutantMinuteData> minuteDatas = sqlManager.query(PollutantMinuteData.class)
				.andEq("site_code", siteId).andBetween("data_time", startDate, endDate)
				.desc("data_time").select();
		return minuteDatas;
	}

	@Override
	public void save(PollutantMinuteData minuteData) {
		try {
			minutehistoryDataDao.insertTemplate(minuteData);
		} catch (Exception e) {
			if(e.getMessage().contains("unq_site_date")) {
				//System.out.println("*********唯一约束错误，重复插入数据************");
			}
		}
	}

	@Override
	public void saveList(List<PollutantMinuteData> minuteDatas) {
		try {
			minutehistoryDataDao.insertBatch(minuteDatas);
		} catch (Exception e) {
			if(e.getMessage().contains("unq_site_date")) {
				//System.out.println("*********唯一约束错误，重复插入数据************");
			}
		}
	}

	@Override
	public List<Map<String,Object>> getMinData(String siteCode, Date stm, Date etm) {
		if(stm==null || etm==null) {
			stm = DateUtils.addMinutes(new Date(), -60);
			etm =  DateUtils.addMinutes(new Date(), 0);
		}
		List<PollutantMinuteData> pddlist = minutehistoryDataDao.getFactorData(siteCode,stm,etm);
		List<Factor> factorList = factorDao.all();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<String> keylist = null;
		List<String> objkey = null;
		for(PollutantMinuteData pdd:pddlist) {
			map = new HashMap<String,Object>();
			map.put("时间", sdf.format(pdd.getDataTime()));
			JSONObject jsStr = JSONObject.parseObject(pdd.getSiteDataStr());
			keylist = new ArrayList<String>();
			for(String key:jsStr.keySet()) {
				keylist.add(key);
			}
			for(int i=0;i<keylist.size();i++) {
				String obj = jsStr.get(keylist.get(i)).toString();
				JSONObject obgStr = JSONObject.parseObject(obj);
				objkey = new ArrayList<String>();
				for(String obkey:obgStr.keySet()) {
					objkey.add(obkey);
				}
				for(int j=0;j<objkey.size();j++) {
					String bj = obgStr.get(objkey.get(j)).toString();
					JSONObject bjStr = JSONObject.parseObject(bj);
					String str = "";
					for(Factor factor:factorList) {
						if(factor.getCode2005().equals(objkey.get(j)) || factor.getCode2017().equals(objkey.get(j))) {
							str = factor.getName()+"("+factor.getUnitConc()+")"+factor.getId();
							break;
						}
					}
					String d = bjStr.get("xxx-Avg").toString();
					Double dd = Double.valueOf(d);
					map.put(str, String.format("%.2f", dd));
				}
			}
			list.add(map);
		}
		return list;
	}
	public boolean compare_date(Date DATE1, Date DATE2) {
        Date dt1 = DATE1;
        Date dt2 = DATE2;
        if (dt1.getTime() > dt2.getTime()) {
            return false;
        } else if (dt1.getTime() < dt2.getTime()) {
            return false;
        } else {
            return true;
        }
    }
}
