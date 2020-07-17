package com.rate.web.site.dao;

import com.rate.web.site.entity.MiluoPollutantFactor;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;

@SqlResource("site.miluopollutantfactor")
public interface MiluoPollutantFactorDao extends BaseMapper<MiluoPollutantFactor>{

	//分页
	PageQuery<MiluoPollutantFactor> list(PageQuery<MiluoPollutantFactor> pageQuery);

	//通过id查因子
	MiluoPollutantFactor findMiluoFactorInfoById(@Param("id")String id);

}
