package com.rate.system.rate_system.dao;


import java.util.List;
import java.util.Map;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import com.rate.system.rate_system.entity.TaskDO;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 15:45:42
 */
@SqlResource("system.sysTask")
public interface TaskDao extends BaseMapper<TaskDO>{

//	TaskDO get(Long id);
	
//	List<TaskDO> list(Map<String,Object> map);
	
//	int count(@Param("params")Map<String,Object> map);
	
//	int save(TaskDO task);
	
//	int update(TaskDO task);
	
//	int remove(Long id);
	
	int batchRemove(@Param("ids")Long[] ids);

	List<TaskDO> queryByCondition(@Param("params")Map<String, Object> map);
}
