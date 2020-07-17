package com.rate.system.rate_system.dao;

import java.util.List;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import com.rate.system.rate_system.entity.Area;

@SqlResource("commons.sysArea")
public interface AreaDao extends BaseMapper<Area>{
	List<Area> queryByCondition(@Param("query")Area query);
}
