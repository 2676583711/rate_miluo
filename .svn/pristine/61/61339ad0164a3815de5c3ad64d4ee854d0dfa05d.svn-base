package com.rate.system.rate_system.controller;

import java.util.List;

import com.rate.system.rate_system.entity.SitePower;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rate.system.rate_system.annotation.Log;
import com.rate.system.rate_system.config.Constant;
import com.rate.system.rate_system.entity.Role;
import com.rate.system.rate_system.service.RoleService;
import com.rate.system.rate_system.utils.R;

@RequestMapping("/sys/role")
@Controller
public class RoleController extends BaseController {
	String prefix = "system/role";
	@Autowired
	RoleService roleService;

	@RequiresPermissions("sys:role:role")
	@GetMapping()
	String role() {
		return prefix + "/role";
	}

	@RequiresPermissions("sys:role:role")
	@GetMapping("/list")
	@ResponseBody()
	List<Role> list() {
		List<Role> roles = roleService.list();
		return roles;
	}

	@Log("添加角色")
	@RequiresPermissions("sys:role:add")
	@GetMapping("/add")
	String add() {
		return prefix + "/add";
	}

	@Log("编辑角色")
	@RequiresPermissions("sys:role:edit")
	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Long id, Model model) {
		Role roleDO = roleService.get(id);
		model.addAttribute("role", roleDO);
		return prefix + "/edit";
	}

	@Log("保存角色")
	@RequiresPermissions("sys:role:add")
	@PostMapping("/save")
	@ResponseBody()
	R save(Role role) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		try {
			roleService.save(role);
			return R.ok();
		} catch (Exception e) {
			return R.error(1, "保存失败");
		}
		
	}

	@Log("更新角色")
	@RequiresPermissions("sys:role:edit")
	@PostMapping("/update")
	@ResponseBody()
	R update(Role role) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (roleService.update(role) > 0) {
			return R.ok();
		} else {
			return R.error(1, "保存失败");
		}
	}

	@Log("删除角色")
	@RequiresPermissions("sys:role:remove")
	@PostMapping("/remove")
	@ResponseBody()
	R save(Long id) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (roleService.remove(id) > 0) {
			return R.ok();
		} else {
			return R.error(1, "删除失败");
		}
	}
	
	@RequiresPermissions("sys:role:batchRemove")
	@Log("批量删除角色")
	@PostMapping("/batchRemove")
	@ResponseBody
	R batchRemove(@RequestParam("ids[]") Long[] ids) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		int r = roleService.batchremove(ids);
		if (r > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * @Title: sitePower
	 * @Description: 配置站点权限
	 * @param @param id
	 * @param @param model
	 * @param @return
	 * @return String
	 * @author jiangya
	 *
	 * @throws
	 */
	@Log("配置站点权限")
	@RequiresPermissions("sys:role:site")
	@GetMapping("/sitePower/{id}")
	String sitePower(@PathVariable("id") Long id, Model model) {
		Role roleDO = roleService.get(id);
		List<String> siteCodeS=roleService.getSitePowerList(id);
		model.addAttribute("role", roleDO);
		model.addAttribute("siteCodeList", siteCodeS);
		return prefix + "/sitePower";
	}

	/**
	 * @Title: updateSitePower
	 * @Description: 更新角色站点权限
	 * @param @param sitePower
	 * @param @return
	 * @return R
	 * @author jiangya
	 *
	 * @throws
	 */
	@Log("更新角色站点权限")
	@RequiresPermissions("sys:role:site")
	@PostMapping("/updateSitePower")
	@ResponseBody()
	R updateSitePower(SitePower sitePower) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (roleService.updateSitePower(sitePower) > 0) {
			return R.ok();
		} else {
			return R.error(1, "保存失败");
		}
	}

}