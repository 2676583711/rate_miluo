package com.rate.system.rate_system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.rate.system.rate_system.dao.LogDao;
import com.rate.system.rate_system.entity.LogInfo;
import com.rate.system.rate_system.entity.Page;
import com.rate.system.rate_system.service.LogService;
import com.rate.system.rate_system.utils.IdGen;
import com.rate.system.rate_system.utils.Query;

@Service
public class LogServiceImpl implements LogService {
	@Autowired
	LogDao logMapper;

	@Async
	@Override
	public void save(LogInfo log) {
		if (log.getTime() == null)
			log.setTime(0);
		String id = IdGen.uuid();
		log.setId(id);
		logMapper.insert(log);
	}

	@Override
	public Page<LogInfo> queryList(Query query) {
		int total = logMapper.count(query);
		List<LogInfo> logs = logMapper.list(query);
		Page<LogInfo> page = new Page<>();
		page.setTotal(total);
		page.setRows(logs);
		return page;
	}

	@Override
	public int remove(String id) {
//		int count = logMapper.remove(id);
		int count = logMapper.deleteById(id);
		return count;
	}

	@Override
	public int batchRemove(String[] ids){
		return logMapper.batchRemove(ids);
	}
}
