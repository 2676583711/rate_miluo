package com.rate.system.rate_system.service;

import java.util.List;
import java.util.Map;

import org.quartz.SchedulerException;

import com.rate.system.rate_system.entity.TaskDO;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-26 20:53:48
 */
public interface JobService {
	
	TaskDO get(Long id);
	
	List<TaskDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	void save(TaskDO taskScheduleJob);
	
	int update(TaskDO taskScheduleJob);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	void initSchedule() throws SchedulerException;

	void changeStatus(Long jobId, String cmd) throws SchedulerException;

	void updateCron(Long jobId) throws SchedulerException;
}
