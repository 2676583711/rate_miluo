package com.rate.system.rate_system.dao;

import com.rate.system.rate_system.entity.SitePower;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**  
* @ClassName: SitePowerDao  
* @Description: 角色与站点对应关系 
* @author jiangya  
* @date 2019年3月29日  
*    
*/
@Repository
@SqlResource("system.sitePower")
public interface SitePowerDao extends BaseMapper<SitePower>{

	List<String> listSiteCodesByRoleId(@Param("roleId") Long roleId);

	void deleteByRoleId(@Param("roleId") Long roleId);

	/**
	* @Title: getSiteCodesByUser
	* @Description: 根据用户的角色获取站点权限
	* @param @param userId
	* @param @return    
	* @return List<String>  
	* @author jiangya 
	*   
	* @throws
	*/
	List<String> getSiteCodesByUser(@Param(value = "userId") Long userId);

	/**
	 * 根据角色id  获得  站点编码列表
	 * @param roleId
	 * @return
	 */
	List<String> getSitePowerList(@Param("roleId") Long roleId);

	/**
	 * 根据用户id获取可见的站点
	 * @param userId
	 * @Return  java.util.List<java.lang.String>
	 * @Author  chenshixue
	 * @Date    2019/7/25 16:42
	 */
	List<String> getSiteCodesByUserId(@Param("userId") Long userId);

	void deleteByUserId(@Param("userId") Long userId);

	int getCountByUserId(@Param("userId") Long userId);

	List<String> getAllSiteId();

	void deleteBySiteId(@Param("siteCode") String siteCode);
}