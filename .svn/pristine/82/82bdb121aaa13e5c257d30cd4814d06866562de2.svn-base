package com.rate.system.rate_system.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.lettuce.core.ReadFrom;
import org.apache.commons.lang3.StringUtils;
import org.beetl.sql.core.annotatoin.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rate.system.rate_system.dao.DeptDao;
import com.rate.system.rate_system.dao.MenuDao;
import com.rate.system.rate_system.dao.RoleMenuDao;
import com.rate.system.rate_system.dao.UserDao;
import com.rate.system.rate_system.entity.Dept;
import com.rate.system.rate_system.entity.Menu;
import com.rate.system.rate_system.entity.Tree;
import com.rate.system.rate_system.entity.User;
import com.rate.system.rate_system.service.MenuService;
import com.rate.system.rate_system.utils.BuildTree;

import javax.xml.soap.Node;

@SuppressWarnings("AlibabaRemoveCommentedCode")
@Service
@Transactional(readOnly = true,rollbackFor = Exception.class)
public class MenuServiceImpl implements MenuService {
	@Autowired
	MenuDao menuMapper;
	@Autowired
	RoleMenuDao roleMenuMapper;
	@Autowired
	DeptDao deptdao;
	@Autowired
	UserDao userDao;

/**
	 * @param
	 * @return 树形菜单
	 */
	@Cacheable
	@Override
	public Tree<Menu> getSysMenuTree(Long id) {
		List<Tree<Menu>> trees = new ArrayList<Tree<Menu>>();
		List<Menu> menuDOs = menuMapper.listMenuByUserId(id);
		for (Menu sysMenuDO : menuDOs) {
			Tree<Menu> tree = new Tree<Menu>();
			tree.setId(sysMenuDO.getMenuId().toString());
			tree.setParentId(sysMenuDO.getParentId().toString());
			tree.setText(sysMenuDO.getName());
			Map<String, Object> attributes = new HashMap<>(16);
			attributes.put("url", sysMenuDO.getUrl());
			attributes.put("icon", sysMenuDO.getIcon());
			tree.setAttributes(attributes);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<Menu> t = BuildTree.build(trees);
		return t;
	}

	@Override
	public List<Menu> list(Map<String, Object> params) {
		List<Menu> menus = menuMapper.all();
		return menus;
	}

	@Transactional(readOnly = false,rollbackFor = Exception.class)
	@Override
	public int remove(Long id) {
		int result = menuMapper.deleteById(id);
		return result;
	}
	@Transactional(readOnly = false,rollbackFor = Exception.class)
	@Override
	public void save(Menu menu) {
		menuMapper.insert(menu);
	}

	@Transactional(readOnly = false,rollbackFor = Exception.class)
	@Override
	public int update(Menu menu) {
		int r = menuMapper.updateById(menu);
		return r;
	}

	@Override
	public Menu get(Long id) {
//		Menu menuDO = menuMapper.get(id);
		Menu menuDO = menuMapper.single(id);
		return menuDO;
	}

	@Override
	public Tree<Menu> getTree() {
		List<Tree<Menu>> trees = new ArrayList<Tree<Menu>>();
//		List<Menu> menuDOs = menuMapper.list(new HashMap<>(16));
		List<Menu> menuDOs = menuMapper.all();
		for (Menu sysMenuDO : menuDOs) {
			Tree<Menu> tree = new Tree<Menu>();
			tree.setId(sysMenuDO.getMenuId().toString());
			tree.setParentId(sysMenuDO.getParentId().toString());
			tree.setText(sysMenuDO.getName());
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<Menu> t = BuildTree.build(trees);
		return t;
	}

	@Override
	public Tree<Menu> getTree(Long roleId) {
		// 根据roleId查询权限
//		List<Menu> menus = menuMapper.list(new HashMap<String, Object>(16));
		List<Menu> menus = menuMapper.all();
		List<Long> menuIds = roleMenuMapper.listMenuIdByRoleId(roleId);
		List<Long> temp = menuIds;
		for (Menu menu : menus) {
			if (temp.contains(menu.getParentId())) {
				menuIds.remove(menu.getParentId());
			}
		}
		List<Tree<Menu>> trees = new ArrayList<Tree<Menu>>();
//		List<Menu> menuDOs = menuMapper.list(new HashMap<String, Object>(16));
		List<Menu> menuDOs = menuMapper.all();
		for (Menu sysMenuDO : menuDOs) {
			Tree<Menu> tree = new Tree<Menu>();
			tree.setId(sysMenuDO.getMenuId().toString());
			tree.setParentId(sysMenuDO.getParentId().toString());
			tree.setText(sysMenuDO.getName());
			Map<String, Object> state = new HashMap<>(16);
			Long menuId = sysMenuDO.getMenuId();
			if (menuIds.contains(menuId)) {
				state.put("selected", true);
			} else {
				state.put("selected", false);
			}
			tree.setState(state);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<Menu> t = BuildTree.build(trees);
		return t;
	}

	@Override
	public Set<String> listPerms(Long userId) {
		List<String> perms = menuMapper.listUserPerms(userId);
		Set<String> permsSet = new HashSet<>();
		for (String perm : perms) {
			if (StringUtils.isNotBlank(perm)) {
				permsSet.addAll(Arrays.asList(perm.trim().split(",")));
			}
		}
		return permsSet;
	}

	@Override
	public List<Tree<Menu>> listMenuTree(Long id) {
//		System.out.println(id);
//		Map<String, Object> param = new HashMap<>();
//		param.put("id", id);
		List<Tree<Menu>> trees = new ArrayList<Tree<Menu>>();
//		List<Menu> menuDOs = menuMapper.getSQLManager().select("system.sysMenu.listMenuByUserId", Menu.class, param);
		List<Menu> menuDOs = menuMapper.listMenuByUserId(id);
		for (Menu sysMenuDO : menuDOs) {
			Tree<Menu> tree = new Tree<Menu>();
			tree.setId(sysMenuDO.getMenuId().toString());
			tree.setParentId(sysMenuDO.getParentId().toString());
			tree.setText(sysMenuDO.getName());
			Map<String, Object> attributes = new HashMap<>(16);
			attributes.put("url", sysMenuDO.getUrl());
			attributes.put("icon", sysMenuDO.getIcon());
			tree.setAttributes(attributes);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		List<Tree<Menu>> list = BuildTree.buildList(trees, "0");
		return list;
	}
	
	@Override
	public List<Menu> listById(Long userId,Long deptId) {
		List<Menu> list = menuMapper.listMenuByUserId(userId);
		User user = userDao.single(userId);
		Dept dept = deptdao.single(deptId);
		if(list.size()>0) {
			for(Menu menu:list) {
				if("http://47.101.50.57:8083/bbs/bbs/index".equals(menu.getUrl()) ||
						"http://localhost:8083/bbs/bbs/index".equals(menu.getUrl())) {
					menu.setUrl(menu.getUrl()+"/"+dept.getParentId()+"/"+user.getUsername());
				}
			}
		}
		return list;
	}

	/**
	 * 返回json格式的无限级菜单
	 * @param roleId
	 * @Return  java.util.List<com.rate.system.rate_system.entity.Menu>
	 * @Author  chenshixue
	 * @Date    2019/7/30 10:23
	 */
	@Override
	public List<Menu> getMenuJson(Long roleId) {
		List<Menu> menuList = menuMapper.getMenuByRoleId(roleId);
		List<Menu> treeNodes = new ArrayList<>();
		List<Menu> rootNodes = getRootNodes(menuList);
		for(Menu rootNode : rootNodes){
			buildChildNodes(rootNode, menuList);
			treeNodes.add(rootNode);
		}
		return treeNodes;
	}
	public void buildChildNodes(Menu menu, List<Menu> menuList){
		List<Menu> childMenu = getChildNodes(menu, menuList);
		if(!childMenu.isEmpty()){
			for(Menu child : childMenu){
				buildChildNodes(child, menuList);
			}
			menu.setChildMenu(childMenu);
		}
	}
	public List<Menu> getChildNodes(Menu menu, List<Menu> menuList){
		List<Menu> childMenu = new ArrayList<>();
		for(Menu m : menuList){
			if(menu.getMenuId().equals(m.getParentId())){
				childMenu.add(m);
			}
		}
		return childMenu;
	}
	public List<Menu> getRootNodes(List<Menu> menuList){
		List<Menu> rootNodes = new ArrayList<>();
		for(Menu menu : menuList){
			if(menu.getParentId() == 0){
				rootNodes.add(menu);
			}
		}
		return rootNodes;
	}
}
