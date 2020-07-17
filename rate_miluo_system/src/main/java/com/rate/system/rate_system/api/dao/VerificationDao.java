package com.rate.system.rate_system.api.dao;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import com.rate.system.rate_system.api.entity.Verification;
import org.springframework.stereotype.Repository;

/**
 * @author	zhangbiao
 * @date	2018-09-11
 */
@Repository
@SqlResource("system.appLogin")
public interface VerificationDao extends BaseMapper<Verification>{

	/**
	 *  根据userId 查询对应得登录记录
	 * @param userId
	 * @return
	 */
	Verification selectByUserId(@Param("userId") Long userId);
	
	void updateType(@Param("userId") Long userId);
	
	Verification appAutoCode(@Param("code") String code);



}
