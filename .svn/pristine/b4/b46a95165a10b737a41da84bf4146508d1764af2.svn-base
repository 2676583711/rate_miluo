package com.rate.system.rate_system.dao;

import com.rate.system.rate_system.entity.Dept;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * 部门管理
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 15:35:39
 */
@SqlResource("system.sysDept")
public interface DeptDao extends BaseMapper<Dept> {

	int getDeptUserNumber(@Param("deptId")Long deptId);

	int count(@Param("parentId")Long parentId);

	Long[] listParentDept();

	List<Dept> queryByCondition(Map<String,Object> params,@Param("parentId") Object parentId);

	List<Dept> getByDeptId(@Param("deptId") Long deptId);
	
	Dept getDeptByIdOne(@Param("deptId") Long deptId);

	List<Dept> findByName(Map<String, Object> params);
	
	Long findPwId(@Param("parentId") Long parentId);
/**
 * @Param [params]
 * @Return java.util.List<com.rate.system.rate_system.entity.Dept>
 * @author xiaoshi       
 * @Description  
 * @Date 2019/6/3
 * @Time 18:32
 **/
	List<Dept> findAllByCondition(Map<String,Object> params);

	void updateDelFlagByParentId(@Param("delFlag") Integer delFlag, @Param("deptId") Long deptId);

	List<Dept> getByDeptId(@Param("deptId") Long deptId, @Param("delFlag") Integer delFlag);

    List<Dept> getdeptByname(@Param("deptName") String deptName);
}
