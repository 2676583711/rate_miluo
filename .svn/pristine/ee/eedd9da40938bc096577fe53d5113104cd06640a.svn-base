package com.rate.web.airparam.service.impl;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rate.web.airparam.dao.MiluoSiteDevicedParamDao;
import com.rate.web.airparam.entity.AirParam;
import com.rate.web.airparam.entity.MiluoSiteDevicedParam;
import com.rate.web.airparam.service.MiluoSiteDevicedParamService;

@Service
public class MiluoSiteDevicedParamServiceImpl implements MiluoSiteDevicedParamService {
	@Autowired
	private MiluoSiteDevicedParamDao miluoSiteDevicedParamDao;
	
	@Override
	public PageQuery<MiluoSiteDevicedParam> airList(PageQuery<MiluoSiteDevicedParam> paras, String pluName,String videoId) {
		// TODO Auto-generated method stub
		return miluoSiteDevicedParamDao.airList(paras,pluName,videoId);
	}

	/**
	 * 删除
	 */
	@Override
	public void removeVideo(Integer id) {
		miluoSiteDevicedParamDao.deleteById(id);
	}
	
	
}
