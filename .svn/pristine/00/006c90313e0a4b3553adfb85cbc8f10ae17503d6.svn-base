package com.rate.system.rate_system.dao;

import com.rate.system.rate_system.entity.RoleMenu;

import java.util.List;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

/**
 * 角色与菜单对应关系
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 11:08:59
 */
@SqlResource("system.sysRoleMenu")
public interface RoleMenuDao extends BaseMapper<RoleMenu>{

	List<Long> listMenuIdByRoleId(@Param("roleId") Long roleId);

	void deleteByRoleId(@Param("roleId")Long roleId);

}