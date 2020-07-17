package com.rate.system.rate_system.service;


import org.springframework.stereotype.Service;

import com.rate.system.rate_system.utils.Query;
import com.rate.system.rate_system.entity.LogInfo;
import com.rate.system.rate_system.entity.Page;
@Service
public interface LogService {
	void save(LogInfo logInfo);
	Page<LogInfo> queryList(Query query);
	int remove(String id);
	int batchRemove(String[] ids);
}
