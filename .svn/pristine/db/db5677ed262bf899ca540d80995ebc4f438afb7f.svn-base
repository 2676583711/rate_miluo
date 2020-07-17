package com.rate.system.rate_system.dao;

import com.rate.system.rate_system.entity.UserRole;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户与角色对应关系
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 11:08:59
 */
@SqlResource("system.sysUserRole")
@Repository
public interface UserRoleDao extends BaseMapper<UserRole> {
	List<Long> listRoleId(@Param("userId") Long userId);
	void removeByUserId(@Param("userId") Long userId);
	void batchSave(@Param("userRoles")List<UserRole> list);

	void updateByUserId(@Param("userId") Long userId, @Param("roleId") Long roleId);

	Long findRoleIdByUserId(@Param("userId") Long userId);

	List<Long> findUserIdsByRoleId(@Param("roleId") Long roleId);
}
