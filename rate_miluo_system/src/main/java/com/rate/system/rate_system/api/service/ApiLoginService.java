package com.rate.system.rate_system.api.service;

import org.springframework.stereotype.Service;

import com.rate.system.rate_system.api.entity.Verification;
/**
 * @author	zhangbiao
 * @date	2018-09-11
 */
@Service
public interface ApiLoginService {
	
	void insert(Verification verification);
	
	Verification selectByUserId(Long userId);
	
	void updateType(Verification verification);
	
	/**
	 * 自动验证code是否存在
	 * @param code
	 * @return
	 */
	boolean appAutoCode(String code,Long userId);

	/**
	 *  判断用户是否登录
	 * @param userId 用户id
	 * @param token
	 * @Author LiuYong
	 * @return
	 */
	boolean checkLogin(String userId, String token);

}
