package com.rate.system.rate_system.controller;

import java.util.Map;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.rate.system.rate_system.entity.LogInfo;
import com.rate.system.rate_system.entity.Page;
import com.rate.system.rate_system.service.LogService;
import com.rate.system.rate_system.utils.Query;
import com.rate.system.rate_system.utils.R;

@RequestMapping("/common/log")
@Controller
public class LogController extends BaseController{
	@Autowired
	LogService logService;
	String prefix = "system/log";

	@GetMapping()
	String log() {
		return prefix + "/log";
	}

	@ResponseBody
	@GetMapping("/list")
	Page<LogInfo> list(@RequestParam Map<String, Object> params) {
		Page<LogInfo> page = null;
		Query query = null;
		if(getParentId()==20){
			query=new Query(params);
			page = logService.queryList(query);
		}else {
			params.put("parentId", getParentId());
			query=new Query(params);
			page = logService.queryList(query);
		}
		return page;
	}
	
	@ResponseBody
	@PostMapping("/remove")
	R remove(String id) {
		if (logService.remove(id)>0) {
			return R.ok();
		}
		return R.error();
	}

	@ResponseBody
	@PostMapping("/batchRemove")
	R batchRemove(@RequestParam("ids[]") String[] ids) {
		int r = logService.batchRemove(ids);
		if (r > 0) {
			return R.ok();
		}
		return R.error();
	}
}
