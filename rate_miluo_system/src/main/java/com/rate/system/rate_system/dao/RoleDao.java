package com.rate.system.rate_system.dao;

import com.rate.system.rate_system.entity.Role;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;

/**
 * 角色
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-02 20:24:47
 */
@SqlResource("system.sysRole")
public interface RoleDao extends BaseMapper<Role>{

	/**
	 * <p>Title: findRoleList</p>  
	
	 * <p>Description:通过用户找所有的角色 </p>  
	
	 * @param userId
	 * @return
	 */
	List<Long> findRoleList(@Param("userId")Long userId);

    List<Long> getRoleByname(@Param("roleName") String roleName);
}