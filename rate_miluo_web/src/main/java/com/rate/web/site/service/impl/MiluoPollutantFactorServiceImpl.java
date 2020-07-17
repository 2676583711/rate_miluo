package com.rate.web.site.service.impl;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rate.web.site.dao.MiluoPollutantFactorDao;
import com.rate.web.site.entity.MiluoPollutantFactor;
import com.rate.web.site.service.MiluoPollutantFactorService;

@Service
public class MiluoPollutantFactorServiceImpl implements MiluoPollutantFactorService{

	@Autowired
	private MiluoPollutantFactorDao miluoPollutantFactorDao;
	
	
	/**
	 * 保存
	 */
	@Override
	public void save(MiluoPollutantFactor miluoPollutantFactor) {
		
		miluoPollutantFactorDao.insertTemplate(miluoPollutantFactor,true);
	}

	/**
	 * 首页列表显示
	 */
	@Override
	public PageQuery<MiluoPollutantFactor> list(PageQuery<MiluoPollutantFactor> pageQuery) {
		return miluoPollutantFactorDao.list(pageQuery);
	}

	
	/**
	 * 通過id刪除
	 */
	@Override
	public int remove(String id) {
		// TODO Auto-generated method stub
		return miluoPollutantFactorDao.deleteById(id);
	}

	@Override
	public MiluoPollutantFactor findMiluoFactorInfoById(String id) {
		return miluoPollutantFactorDao.findMiluoFactorInfoById(id);
	}

	/**
	 * 批量删除
	 */
	@Transactional
	@Override
	public int batchRemove(String[] ids) {
		int count = 0;
		for(int i=0;i<ids.length;i++) {
			miluoPollutantFactorDao.deleteById(ids[i]);
			count++;
		}
		return count;
	}

	
	/**
	 * 修改
	 */
	@Override
	public void updateTemplateById(MiluoPollutantFactor miluoPollutantFactor) {
		miluoPollutantFactorDao.updateById(miluoPollutantFactor);
	}

	
	
}
