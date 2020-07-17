package com.rate.system.rate_system.dao;
import com.rate.system.rate_system.entity.Menu;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * 菜单管理
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 09:45:09
 */
@SqlResource("system.sysMenu")
public interface MenuDao extends BaseMapper<Menu> {

	Menu get(@Param("menuId") Long menuId);
	
	List<Menu> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
//	int save(Menu menu);
	
	int update(@Param("menu") Menu menu);
	
//	int remove(Long menuId);
	
	int batchRemove(@Param("menuIds") Long[] menuIds);
	
	List<Menu> listMenuByUserId(@Param("id")Long id);
	
	List<String> listUserPerms(@Param("userId")Long userId);

	List<Menu> getMenuByRoleId(@Param("roleId") Long roleId);
}
