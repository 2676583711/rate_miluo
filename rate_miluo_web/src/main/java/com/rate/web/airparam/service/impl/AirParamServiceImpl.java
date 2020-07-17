package com.rate.web.airparam.service.impl;

import java.util.List;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rate.web.airparam.dao.AirParamDao;
import com.rate.web.airparam.dao.MiluoSiteDevicedParamDao;
import com.rate.web.airparam.entity.AirParam;
import com.rate.web.airparam.entity.MiluoSiteDevicedParam;
import com.rate.web.airparam.service.AirParamService;

@Service
public class AirParamServiceImpl implements AirParamService {
	
	@Autowired
	private AirParamDao airParamDao;
	
	@Autowired
	private MiluoSiteDevicedParamDao miluoSiteDevicedParamDao;
	
	@Override
	public PageQuery<AirParam> list(PageQuery<AirParam> paras, String pluName) {
//		String[] strs = null;
//		if (pluName != null && pluName != "") {
//			strs = pluName.split(",");
//
//		}
		return airParamDao.queryBycondition(paras, pluName);
	}
	
//	@Override
//	public List<AirParam> list() {
//		return airParamDao.all();
//	}
	
	@Transactional
	@Override
	public void save(AirParam airParam) {
		// 找到最大的一个id
		int lastId = airParamDao.findLastId();
		airParam.setId(String.valueOf(lastId + 1));
		airParamDao.insertObject(airParam);
	}

	@Override
	public AirParam get(String id) {
		return airParamDao.single(id);
	}

	@Transactional
	@Override
	public int update(AirParam airParam) {
		return airParamDao.updateObject(airParam);
	}

	@Transactional
	@Override
	public int remove(String id) {
		return airParamDao.deleteById(id);
	}

	@Transactional
	@Override
	public int batchremove(String[] ids) {
		return airParamDao.batchRemove(ids);
	}

	@Override
	public List<AirParam> searchObject(String searchText) {
		return airParamDao.searchObjectByText(searchText);
	}
	/**
	 * 功能描述: 根据id获取一个参数
	 * @auther: HuYan
	 * @date: 2019/3/28 14:53
	 */
	@Override
	public AirParam findById(String id) {
		return airParamDao.single(id);
	}

	/**
	 * 查所有的miluo_air_pram
	 */
	@Override
	public List<AirParam> findAllList() {
		return airParamDao.all();
	}

	/**
	 * 
	 */
	@Override
	public void saveSiteParam(MiluoSiteDevicedParam miluoSiteDevicedParam) {
		miluoSiteDevicedParamDao.insert(miluoSiteDevicedParam);
	}

}
