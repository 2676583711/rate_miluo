package com.rate.system.rate_system.dao;

import com.rate.system.rate_system.entity.LogInfo;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * 系统日志
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 15:45:42
 */
@SqlResource("system.sysLog")
public interface LogDao extends BaseMapper<LogInfo>{

	LogInfo get(@Param("id") Long id);
	
	List<LogInfo> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
//	int save(LogInfo log);
	
	int update(@Param("log") LogInfo log);
	
	int remove(@Param("id") Long id);
	
	int batchRemove(@Param("ids")String[] ids);
}
