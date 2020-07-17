package com.rate.system.rate_system.controller;

import com.alibaba.fastjson.JSONObject;
import com.rate.system.rate_system.annotation.Log;
import com.rate.system.rate_system.config.Constant;
import com.rate.system.rate_system.entity.Menu;
import com.rate.system.rate_system.entity.Tree;
import com.rate.system.rate_system.service.MenuService;
import com.rate.system.rate_system.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author bootdo 1992lcg@163.com
 */
@RequestMapping("/sys/menu")
@Controller
public class MenuController extends BaseController {
	String prefix = "system/menu";
	@Autowired
	MenuService menuService;

	@RequiresPermissions("sys:menu:menu")
	@GetMapping()
	String menu(Model model) {
		return prefix + "/menu";
	}
	@GetMapping("/fontIcoList")
	String fontIcoList(Model model) {
		return prefix + "/FontIcoList";
	}

	@RequiresPermissions("sys:menu:menu")
	@RequestMapping("/list")
	@ResponseBody
	List<Menu> list(@RequestParam Map<String, Object> params) {
		List<Menu> menus = menuService.list(params);
		return menus;
	}
	
	/**
	 * Description:为污染源建的方法，获取菜单列表
	 * 通过用户id查找菜单
	 * @return
	 */
	@RequestMapping("/listMenusByUserId")
	@ResponseBody
	public JSONObject listMenusByUserId() {
		List<Menu> menus = menuService.listById(getUserId(),getDeptId());
		JSONObject obj = new JSONObject();
		obj.put("Data", menus);
		obj.put("Result", true);
		return obj;
	}

	@Log("添加菜单")
	@RequiresPermissions("sys:menu:add")
	@GetMapping("/add/{pId}")
	String add(Model model, @PathVariable("pId") Long pId) {
		model.addAttribute("pId", pId);
		if (pId == 0) {
			model.addAttribute("pName", "根目录");
		} else {
			model.addAttribute("pName", menuService.get(pId).getName());
		}
		return prefix + "/add";
	}

	@Log("编辑菜单")
	@RequiresPermissions("sys:menu:edit")
	@GetMapping("/edit/{id}")
	String edit(Model model, @PathVariable("id") Long id) {
		Menu mdo = menuService.get(id);
		Long pId = mdo.getParentId();
		model.addAttribute("pId", pId);
		if (pId == 0) {
			model.addAttribute("pName", "根目录");
		} else {
			model.addAttribute("pName", menuService.get(pId).getName());
		}
		model.addAttribute("menu", mdo);
		return prefix + "/edit";
	}

	@Log("保存菜单")
	@RequiresPermissions("sys:menu:add")
	@PostMapping("/save")
	@ResponseBody
	R save(Menu menu) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		try {
			// if (menuService.save(menu) > 0) {
			// return R.ok();
			// } else {
			// return R.error(1, "保存失败");
			// }
			menuService.save(menu);
			return R.ok();
		} catch (Exception e) {
			return R.error(1, "保存失败");
		}

	}

	@Log("更新菜单")
	@RequiresPermissions("sys:menu:edit")
	@PostMapping("/update")
	@ResponseBody
	R update(Menu menu) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (menuService.update(menu) > 0) {
			return R.ok();
		} else {
			return R.error(1, "更新失败");
		}
	}

	@Log("删除菜单")
	@RequiresPermissions("sys:menu:remove")
	@PostMapping("/remove")
	@ResponseBody
	R remove(Long id) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (menuService.remove(id) > 0) {
			return R.ok();
		} else {
			return R.error(1, "删除失败");
		}
	}

	@GetMapping("/tree")
	@ResponseBody
	Tree<Menu> tree() {
		Tree<Menu> tree = new Tree<Menu>();
		tree = menuService.getTree();
		return tree;
	}

	@GetMapping("/tree/{roleId}")
	@ResponseBody
	Tree<Menu> tree(@PathVariable("roleId") Long roleId) {
		Tree<Menu> tree = new Tree<Menu>();
		tree = menuService.getTree(roleId);
		return tree;
	}

	@GetMapping("/menuJsonByRoleId")
	@ResponseBody
	List<Menu> menuJSON(){
		List<Menu> menus = menuService.getMenuJson(getRoleId());
		return menus;
	}
}