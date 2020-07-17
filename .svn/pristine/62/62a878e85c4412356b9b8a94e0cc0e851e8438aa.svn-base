package com.rate.system.rate_system.service;

import com.rate.system.rate_system.entity.*;
import com.rate.system.rate_system.utils.Query;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserService {
	User get(Long id);
	
	User getUsername(String username);

	PageQuery<User> list(PageQuery<User> paras, Long deptId);

	Long save(User user);

	int update(User user);

	int remove(Long userId);

	int batchremove(Long[] userIds);

	boolean exit(Map<String, Object> params);

	Set<String> listRoles(Long userId);

	int resetPwd(UserVO userVO,User userDO) throws Exception;
	int adminResetPwd(UserVO userVO) throws Exception;
	Tree<Dept> getTree();

	/**
	 * 更新个人信息
	 * @param userDO
	 * @return
	 */
	int updatePersonal(User userDO);
	
	/**
	 * 更新个人图片
	 * @param file 图片
	 * @param avatar_data 裁剪信息
	 * @param userId 用户ID
	 * @throws Exception
	 */
    Map<String, Object> updatePersonalImg(MultipartFile file, String avatar_data, Long userId) throws Exception;

    long count(Map<String, Object> map,Long deptId);

	Long getUserId(User user);

	User findUserByDeptId(Long deptId);

	List<User> getUserList(Long deptId, Long parentId);
	/**
	 * 翻页查询部门相关的人员包括自己
	 * @param query
	 * @param deptId
	 * @return
	 */
	List<User> getUsersByDeptIdPage(Query query, Long deptId);

	long countByDeptId(Query query, Long deptId);
	/**
	 * 查询部门相关的人员包括自己
	 * @param deptId
	 * @return
	 */
	List<User> getUsersByDeptId(Long deptId);
	
	List<User> getListByDeptId(Long deptId);

	List<PollutantFactoryNew>  getPollutantFactoryList();

   /**
    * @Param [deptId]
    * @Return java.util.List<com.rate.system.rate_system.entity.User>
    * @author xiaoshi       
    * @Description  
    * @Date 2019/6/3
    * @Time 18:32
    **/
	List<User> findAll(Long deptId);

   /**
    * @Param [deptId]
    * @Return java.util.List<com.rate.system.rate_system.entity.User>
    * @author xiaoshi
    * @Description
    * @Date 2019/6/3
    * @Time 20:27
    **/
	List<User> findUsersByDeptId(Long deptId);

	/**
	 *  查询所有用户
	 * @author LiuYong
	 * @return
	 */
	List<User> queryAll();

	/**
	 * 根据id查询用户
	 * @param userId
	 * @return
	 */
	User queryById(String userId);

    void saveSitePower(List<String> siteIdList, Long userId);

    /**
     * 根据用户id查询所有可见的站点编码
     * @param userId
     * @Return  java.util.List<java.lang.String>
     * @Date    2019/7/25 16:36
     */
	List<String> getSitePowerList(Long  userId);

	void updateSitePower(List<String> siteIdList, Long userId);

	List<String> getAllSiteId();

    List<User> listAll(Map<String, Object> params, Long deptId);

    List<User> getuserByname(String username2);
}
