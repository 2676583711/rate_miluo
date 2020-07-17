package com.rate.system.rate_system.service;

import com.rate.system.rate_system.entity.Menu;
import com.rate.system.rate_system.entity.Tree;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface MenuService {
	Tree<Menu> getSysMenuTree(Long id);

	List<Tree<Menu>> listMenuTree(Long id);

	Tree<Menu> getTree();

	Tree<Menu> getTree(Long id);

	List<Menu> list(Map<String, Object> params);

	int remove(Long id);

	void save(Menu menu);

	int update(Menu menu);

	Menu get(Long id);

	Set<String> listPerms(Long userId);

	List<Menu> listById(Long userId,Long deptId);

	List<Menu> getMenuJson(Long roleId);
}
