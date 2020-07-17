package com.rate.system.rate_system.service.impl;

import com.rate.system.rate_system.dao.DeptDao;
import com.rate.system.rate_system.entity.Dept;
import com.rate.system.rate_system.entity.Tree;
import com.rate.system.rate_system.service.DeptService;
import com.rate.system.rate_system.utils.BuildTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Service
public class DeptServiceImpl implements DeptService {
	@Autowired
	private DeptDao sysDeptMapper;
	@Autowired
	private DeptService sysDeptService;

	@Override
	public Dept get(Long deptId){
		return sysDeptMapper.single(deptId);
	}

	@Override
	public List<Dept> list(Map<String,Object> params,Long deptId){
		Integer delFlag = sysDeptMapper.single(deptId).getDelFlag();
		Long parentId = sysDeptService.get(deptId).getParentId();
		//如果是开发公司，查看所有部门
		if (delFlag == 1) {
			return sysDeptMapper.findByName(params);
			// 如果是环保局系统管理员，查看自己环保局和运维公司的部门，如果是运维公司管理员，只能查看自己公司部门
		} else if (delFlag == 2 || delFlag == 3) {
			params.put("delFlag", delFlag);
			params.put("deptId", deptId);
			return sysDeptMapper.queryByCondition(params, parentId);
		}
		return null;
	}

	@Override
	public int count(Long parentId){
		return sysDeptMapper.count(parentId);
	}

	@Override
	public void save(Dept sysDept){
		sysDeptMapper.insertTemplate(sysDept);
	}

	@Override
	public int update(Dept sysDept){
		return sysDeptMapper.updateById(sysDept);
	}

	@Override
	public int remove(Long deptId){
		return sysDeptMapper.deleteById(deptId);
	}

	@Override
	public int batchRemove(Long[] deptIds){
		int num = 0;
		for(int i=0;i<deptIds.length;i++) {
			sysDeptMapper.deleteById(deptIds[i]);
			num++;
		}
		return num;
	}

	@Override
	public Tree<Dept> getTree(Long deptId) {
		List<Tree<Dept>> trees = new ArrayList<Tree<Dept>>();
		List<Dept> sysDepts = null;
		Integer delFlag = sysDeptMapper.single(deptId).getDelFlag();
		// 如果为运维公司人员
		if (delFlag == 3) {
			sysDepts = sysDeptMapper.getByDeptId(deptId);
			//如果是环保局
		}else if (delFlag==2){
			sysDepts = sysDeptMapper.getByDeptId(deptId,delFlag);
		} else {
			// 否则技术支持公司系统管理员查询所有
			sysDepts = sysDeptMapper.all();
		}
		for (Dept sysDept : sysDepts) {
			Tree<Dept> tree = new Tree<Dept>();
			tree.setId(sysDept.getDeptId().toString());
			tree.setParentId(sysDept.getParentId().toString());
			tree.setText(sysDept.getName());
			Map<String, Object> state = new HashMap<>(16);
			state.put("opened", true);
			tree.setState(state);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<Dept> t = BuildTree.build(trees);
		return t;
	}

	@Override
	public boolean checkDeptHasUser(Long deptId) {
		// TODO Auto-generated method stub
		//查询部门以及此部门的下级部门
		int result = sysDeptMapper.getDeptUserNumber(deptId);
		return result==0?true:false;
	}
	
	/**
	 * 根据父id查询运维公司
	 */
	@Override
	public List<Dept> findByParentId() {
		return sysDeptMapper.createQuery().andEq("parent_id", 0).select();
	}
	
	/**
	 * 根据父id和级别id查询部门员工
	 */
	@Override
	public Dept findUp(Long parentId, Integer levelId) {
		return sysDeptMapper.createQuery().andEq("parent_id", parentId).andEq("del_flag", levelId).single();
	}

	@Override
	public Dept findByDelParentId(Long parentId, int i) {
		return sysDeptMapper.createQuery().andEq("parent_id",parentId).andEq("del_flag", i).single();
	}



	@Override
	public List<Dept> findAllByCondition(Map<String, Object> params, Long deptId) {
		Long parentId = get(deptId).getParentId();//获取当前部门所属公司的公司id
		if (parentId == 20) {
			//如果是雷特公司的人  有权查看所有的部门
			return sysDeptMapper.findAllByCondition(params);
		} else {
			//如果不是雷特的人  只能查看自己公司的部门
			return sysDeptMapper.queryByCondition(params, parentId);
		}
	}

	@Override
	public void updateDelFlagByParentId(Integer delFlag, Long deptId) {
		sysDeptMapper.updateDelFlagByParentId(delFlag,deptId);
	}

	@Override
	public List<Dept> getdeptByname(String deptName) {
		return sysDeptMapper.getdeptByname(deptName);
	}


}
