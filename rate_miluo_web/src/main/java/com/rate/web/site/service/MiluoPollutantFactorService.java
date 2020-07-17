package com.rate.web.site.service;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.stereotype.Service;

import com.rate.web.site.entity.MiluoPollutantFactor;

@Service
public interface MiluoPollutantFactorService {

	//保存
	void save(MiluoPollutantFactor miluoPollutantFactor);

	
	//分页
	PageQuery<MiluoPollutantFactor> list(PageQuery<MiluoPollutantFactor> pageQuery);


	//删除
	int remove(String id);

	//编辑
	MiluoPollutantFactor findMiluoFactorInfoById(String id);


	//批量删除
	int batchRemove(String[] ids);


	//修改
	void updateTemplateById(MiluoPollutantFactor miluoPollutantFactor);
	
	
	
	
	

	
	

	
	
	
	
	
	
	
	
}
