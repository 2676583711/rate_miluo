package com.rate.system.rate_system.service;

import com.rate.system.rate_system.entity.Role;
import com.rate.system.rate_system.entity.SitePower;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {

	Role get(Long id);

	List<Role> list();

	void save(Role role);

	int update(Role role);

	int remove(Long id);

	List<Role> list(Long userId);

	/**
	 * 根据用户ID获取Role ID
	 * @param userId 用户ID
	 */
	Long getRoleIdByUserId(Long userId);

	int batchremove(Long[] ids);
	int updateSitePower(SitePower sitePower);

	/**
	 * 根据roleId 查询 角色下面的站点编码 集合
	 * @param roleId
	 * @return
	 */
	List<String> getSitePowerList(Long  roleId);

    List<Long> getRoleByname(String roleName);
}