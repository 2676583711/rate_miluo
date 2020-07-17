package com.rate.web.airparam.service;

import org.beetl.sql.core.engine.PageQuery;

import com.rate.web.airparam.entity.AirParam;
import com.rate.web.airparam.entity.MiluoSiteDevicedParam;
import com.rate.web.airsite.entity.SiteParam;

import java.util.List;

public interface AirParamService {

	PageQuery<AirParam> list(PageQuery<AirParam> paras, String pluName);

	void save(AirParam airParam);

	AirParam get(String id);

	int update(AirParam airParam);

	int remove(String id);

	int batchremove(String[] ids);

	List<AirParam> searchObject(String searchText);
	
	/**
	 * 功能描述: 根据id获取一个参数
	 * @auther: HuYan
	 * @date: 2019/3/28 14:53
	 */
	AirParam findById(String id);

	//查询所有的站点
	List<AirParam> findAllList();
	
	
	//添加因子
	void saveSiteParam(MiluoSiteDevicedParam miluoSiteDevicedParam);
}
