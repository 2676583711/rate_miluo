package com.rate.web.factory.dao;


import java.util.List;
import java.util.Map;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import com.rate.system.rate_system.utils.Query;
import com.rate.web.factory.entity.Factor;

/**
 * 
 * @author jinjichang
 * @date 2018-06-01
 */
@SqlResource("factor.factor")
public interface FactorDao extends BaseMapper<Factor> {

	List<Factor> queryByCondition(@Param("params")Query query);

	int getCount(@Param("params")Query query);

	//List<Factor> findNameById(@Param("params")List<String> listEquId);

	List<Factor> findNameById(@Param("equId")String equId);

	List<Factor> getFactorByCode(Map<String, Object> params);
	
	
	List<Factor> getFactorFindBySiteCode(@Param("siteCode")String siteCode);
	
}
