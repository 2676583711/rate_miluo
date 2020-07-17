package com.rate.system.rate_system.service;

import com.rate.system.rate_system.entity.Dept;
import com.rate.system.rate_system.entity.Tree;

import java.util.List;
import java.util.Map;

/**
 * 部门管理
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-27 14:28:36
 */
public interface DeptService {
	
	Dept get(Long deptId);
	
	List<Dept> list(Map<String,Object> params,Long deptId);
	
	int count(Long parentId);
	
	void save(Dept sysDept);
	
	int update(Dept sysDept);
	
	int remove(Long deptId);
	
	int batchRemove(Long[] deptIds);

	Tree<Dept> getTree(Long deptId);
	
	boolean checkDeptHasUser(Long deptId);

	List<Dept> findByParentId();
	
	Dept findUp(Long parentId, Integer upId);

	Dept findByDelParentId(Long parentId, int i);
	/**
	 * @Param [params, deptId]
	 * @Return java.util.List<com.rate.system.rate_system.entity.Dept>
	 * @author xiaoshi       
	 * @Description  
	 * @Date 2019/6/3
	 * @Time 18:32
	 **/

	List<Dept>  findAllByCondition(Map<String, Object> params, Long deptId);

    void updateDelFlagByParentId(Integer delFlag, Long deptId);

	List<Dept> getdeptByname(String deptName);
}
