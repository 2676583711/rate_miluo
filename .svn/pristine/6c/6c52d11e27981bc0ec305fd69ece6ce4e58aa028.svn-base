package com.rate.system.rate_system.api.service;

import org.springframework.stereotype.Service;

import com.rate.system.rate_system.api.entity.ApiSteup;

/**
 * app系统提示设置开关0或者null：开启，1：关闭
 * @author	zhangbiao
 * @date	2018-09-11
 */
@Service
public interface ApiSteupService {
	
    void updateOverrunOnOff(Integer overrun,Long userId);
	
	void updateOperationOnOff(Integer operation,Long userId);
	
	void updateSosOnOff(Integer sos,Long userId);
	
	ApiSteup getByUserId(Long userId);

}
