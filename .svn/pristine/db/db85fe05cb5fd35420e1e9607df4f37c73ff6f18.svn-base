package com.rate.system.rate_system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rate.system.rate_system.dao.AreaDao;
import com.rate.system.rate_system.entity.Area;
import com.rate.system.rate_system.service.AreaService;

@Service
public class AreaServiceImpl implements AreaService {
	@Autowired
	private AreaDao areaDao;

	@Override
	public List<Area> queryByCondition(Area query) {
		List<Area> list = areaDao.queryByCondition(query);
		return list;
	}

}
