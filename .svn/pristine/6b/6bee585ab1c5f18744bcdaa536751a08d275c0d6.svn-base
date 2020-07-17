package com.rate.system.rate_system.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.beetl.sql.core.engine.PageQuery;
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
import com.rate.system.rate_system.entity.Dict;
import com.rate.system.rate_system.service.DictService;
import com.rate.system.rate_system.utils.PageUtils;
import com.rate.system.rate_system.utils.Query;
import com.rate.system.rate_system.utils.R;

@RequestMapping("/sys/dict")
@Controller
public class DictController extends BaseController{
	private String prefix="system/dict"; 
	
	@Autowired
	DictService dictService;
	
	@RequiresPermissions("sys:dict:dict")
	@GetMapping("")
	String dict() {
		return prefix + "/dict";
	}
	
	
	@GetMapping("/add")
	String add(Model model) {
		Long userId=getUserId();
		model.addAttribute("userId", userId);
		return prefix + "/add";
	}


	/**
	 * 获取列表
	 * */
	@GetMapping("/list")
	@ResponseBody
	PageUtils getList(@RequestParam Map<String,Object> params,Long pageSize,Long pageNumber) {
		PageQuery<Dict> paras=new PageQuery<>(pageNumber,pageSize,params);
		PageQuery<Dict> page=dictService.queryByCondition(paras);
		PageUtils pageUtils = new PageUtils(page.getList(), page.getTotalRow());
		return pageUtils;
	}
	
	/**
	 * 保存字典
	 * */
	@Log("保存字典")
	@PostMapping("/save")
	@ResponseBody
	R sava(Dict dict) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		dict.setCreateDate(new Date());
		if (dictService.save(dict) > 0) {
			return R.ok();
	}
		return R.error();
	}
	
	/**
	 * 删除字典
	 * */
	@Log("删除字典")
	@PostMapping("/remove")
	@ResponseBody
	R remove(Long id) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (dictService.remove(id) > 0) {
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 编辑字典
	 * */
	@Log("编辑字典")
	@GetMapping("/edit/{id}")
	String edit(Model model,@PathVariable("id") Long id) {
		Dict dict=dictService.get(id);
		Long userId=getUserId();
		model.addAttribute("dict", dict);
		model.addAttribute("userId", userId);
		return prefix + "/edit";
	}
	
	/**
	 * 更新字典
	 * */
	@Log("更新用户")
	@PostMapping("/update")
	@ResponseBody
	R update(Dict dict) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		dict.setUpdateDate(new Date());
		if (dictService.update(dict) > 0) {
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 批量删除用户
	 * */
	@Log("批量删除用户")
	@PostMapping("/batchRemove")
	@ResponseBody
	R batchRemove(@RequestParam("ids[]") Long[] ids) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		int r = dictService.batchRemove(ids);
		if (r > 0) {
			return R.ok();
		}
		return R.error();
	}
}
