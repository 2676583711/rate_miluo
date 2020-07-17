package com.rate.web.airparam.dao;

import com.rate.web.airparam.entity.AirParam;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;


@SqlResource("airparam.conLimit")
public interface AirParamDao extends BaseMapper<AirParam> {
	
	int findLastId();

	void insertObject(@Param("airParam") AirParam airParam);

	int updateObject(@Param("airParam") AirParam airParam);

	int batchRemove(@Param("ids")String[] ids);

	List<AirParam> searchObjectByText(@Param("searchText")String searchText);

	PageQuery<AirParam> queryBycondition(PageQuery<AirParam> paras, @Param("pluName") String pluName);
}
