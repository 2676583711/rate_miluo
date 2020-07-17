package com.rate.system.rate_system.controller;

import com.rate.system.rate_system.config.Constant;
import com.rate.system.rate_system.entity.Dept;
import com.rate.system.rate_system.entity.Tree;
import com.rate.system.rate_system.service.DeptService;
import com.rate.system.rate_system.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 部门管理
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-27 14:40:36
 */

@Controller
@RequestMapping("/system/sysDept")
public class DeptController extends BaseController {
	private String prefix = "system/dept";
	@Autowired
	private DeptService sysDeptService;

	@GetMapping()
	@RequiresPermissions("system:sysDept:sysDept")
	String dept() {
		return prefix + "/dept";
	}

	//@ApiOperation(value="获取部门列表", notes="")
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("system:sysDept:sysDept")
	public List<Dept> list(@RequestParam Map<String,Object> params) {
		List<Dept> list = sysDeptService.list(params,getDeptId());
		return list;
	}
	
	/**
	 * 添加公司
	 * @param model
	 * @return
	 */
	@GetMapping("/add")
	@RequiresPermissions("system:sysDept:add")
	String add(Model model) {
		Integer delFlag = sysDeptService.get(getDeptId()).getDelFlag();
		//技术支持公司的管理员可以添加所有权限部门,环保局可添加环保局和运维公司
		model.addAttribute("delFlag", delFlag);
		//添加公司
		model.addAttribute("saveCompany", true);
		model.addAttribute("pName", "无");
		model.addAttribute("pId", 0);
		return  prefix + "/add";
	}
	
	/**
	 * 添加部门
	 * @param pId
	 * @param model
	 * @return
	 */
	@GetMapping("/addDept/{pId}")
	@RequiresPermissions("system:sysDept:addDept")
	String addDept(@PathVariable("pId") Long pId, Model model) {
		Dept dept = sysDeptService.get(pId);
		Integer delFlag = dept.getDelFlag();
		//什么类型的公司只能添加同类型的部门
		model.addAttribute("delFlag", delFlag);
		//添加部门
		model.addAttribute("saveCompany", false);
		model.addAttribute("pName", sysDeptService.get(pId).getName());
		return  prefix + "/add";
	}

	@GetMapping("/edit/{deptId}")
	@RequiresPermissions("system:sysDept:edit")
	String edit(@PathVariable("deptId") Long deptId, Model model) {
		// 技术支持公司的管理员可以修改成所有权限部门,环保局可添加环保局和运维公司
		Integer delFlag = sysDeptService.get(getDeptId()).getDelFlag();
		model.addAttribute("delFlag", delFlag);
		Dept sysDept = sysDeptService.get(deptId);
		model.addAttribute("sysDept", sysDept);
		//如果修改的是公司
		if(Constant.DEPT_ROOT_ID.equals(sysDept.getParentId())) {
			model.addAttribute("parentDeptName", "无");
			model.addAttribute("updateCompany", true);
		}else {
			Dept parDept = sysDeptService.get(sysDept.getParentId());
			model.addAttribute("parentDeptName", parDept.getName());
			model.addAttribute("updateCompany", false);
		}

		return  prefix + "/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	//@RequiresPermissions("system:sysDept:add")
	public R save(Dept sysDept) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		try {
			sysDeptService.save(sysDept);
			return R.ok();
		}catch (Exception e) {
			return R.error();
		}
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("system:sysDept:edit")
	public R update(Dept sysDept) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		// 如果修改的是公司,先修改公司下所有部门的权限级别统一
		Long parentId = sysDept.getParentId();
		if (parentId==0) {
			sysDeptService.updateDelFlagByParentId(sysDept.getDelFlag(),sysDept.getDeptId());
		}
		if (sysDeptService.update(sysDept) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("system:sysDept:remove")
	public R remove(Long deptId) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if(sysDeptService.count(deptId)>0) {
			return R.error(1, "包含下级部门,不允许修改");
		}
		if(sysDeptService.checkDeptHasUser(deptId)) {
			if (sysDeptService.remove(deptId) > 0) {
				return R.ok();
			}
		}else {
			return R.error(1, "部门包含用户,不允许修改");
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("system:sysDept:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] deptIds) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		sysDeptService.batchRemove(deptIds);
		return R.ok();
	}

	@GetMapping("/tree")
	@ResponseBody
	public Tree<Dept> tree() {
		Tree<Dept> tree = new Tree<Dept>();
		tree = sysDeptService.getTree(getDeptId());
		return tree;
	}

	@GetMapping("/treeView")
	String treeView() {
		return  prefix + "/deptTree";
	}

}
