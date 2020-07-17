package com.rate.system.rate_system.dao;

import com.rate.system.rate_system.entity.User;
import com.rate.system.rate_system.utils.Query;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 09:45:11
 */
/*@Mapper*/
@Repository
@SqlResource("system.sysUser")
public interface UserDao extends BaseMapper<User>{

	Long[] listAllDept();

	List<User> exitUser(@Param("params") Map<String, Object> params);

	PageQuery<User> queryByCondtion(PageQuery<User> paras, @Param("deptId") Long deptId, @Param("delFlag") Integer delFlag);

	long getCount(Map<String, Object> map,@Param("deptId") Long deptId);

	List<User> getUserList(@Param("deptId") Long deptId, @Param("parentId") Long parentId);

	List<User> getUsersByDeptIdPage(@Param("query") Query query, @Param("deptId") Long deptId);

	long countByDeptId(@Param("query") Query query, @Param("deptId") Long deptId);

	List<User> queryByCondtion2(Map<String, Object> map);

	long getCount2(Map<String, Object> map);

	List<User> getUsersByDeptId(@Param("deptId") Long deptId);
	
	User getUserName(@Param("userName") String userName);
	
	List<User> getCompanyNameByDeptId(@Param("deptId") Long deptId);
	
	int updateUserById(@Param("user") User user);
/**
 * @Param [parentId]
 * @Return java.util.List<com.rate.system.rate_system.entity.User>
 * @author xiaoshi       
 * @Description  
 * @Date 2019/6/3
 * @Time 18:32
 **/
	List<User> findAllByCompany(@Param("parentId") Long parentId);

	 User userById(@Param("id") Long id);

	 User userById(@Param("id") int id);


	User userById(@Param("id") String id);


    List<Integer> listAllSysUser();

    List<User> listAll(Map<String, Object> params, Long deptId, Integer delFlag);

    List<User> getuserByname(@Param("username2") String username2);

    List<User> getAllList();

}
