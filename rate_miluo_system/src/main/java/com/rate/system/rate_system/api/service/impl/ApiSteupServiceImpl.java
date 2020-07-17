package com.rate.system.rate_system.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rate.system.rate_system.api.dao.ApiSteupDao;
import com.rate.system.rate_system.api.entity.ApiSteup;
import com.rate.system.rate_system.api.service.ApiSteupService;
/**
 * 开关按钮
 * @author zhangbiao
 * date:2018-09-12
 *
 */
@Service
public class ApiSteupServiceImpl implements ApiSteupService{
	
	@Autowired
	private ApiSteupDao apiSteupDao;
	
	@Override
	public void updateOverrunOnOff(Integer overrun,Long userId){
		apiSteupDao.updateOverrunOnOff(overrun, userId);
	}
	
	@Override
	public void updateOperationOnOff(Integer operation,Long userId){
		apiSteupDao.updateOperationOnOff(operation, userId);
	}
	
	@Override
	public void updateSosOnOff(Integer sos,Long userId){
		apiSteupDao.updateSosOnOff(sos, userId);
	}
	
	@Override
	public ApiSteup getByUserId(Long userId){
		return apiSteupDao.getByUserId(userId);
	}
	
}
