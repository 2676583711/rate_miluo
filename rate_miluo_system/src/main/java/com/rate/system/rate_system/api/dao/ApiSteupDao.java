package com.rate.system.rate_system.api.dao;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import com.rate.system.rate_system.api.entity.ApiSteup;
/**
 * @author	zhangbiao
 * @date	2018-09-11
 */
@SqlResource("system.appSteup")
public interface ApiSteupDao extends BaseMapper<ApiSteup>{
   
	void updateOverrunOnOff(@Param("overrun") Integer overrun, @Param("userId") Long userId);
	
	void updateOperationOnOff(@Param("operation") Integer operation,@Param("userId") Long userId);
	
	void updateSosOnOff(@Param("sos") Integer sos,@Param("userId") Long userId);
	
	ApiSteup getByUserId(@Param("userId") Long userId);
}
