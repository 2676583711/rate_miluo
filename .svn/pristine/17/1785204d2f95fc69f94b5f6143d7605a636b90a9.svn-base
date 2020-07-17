package com.rate.system.rate_system.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.rate.system.rate_system.annotation.Log;
import com.rate.system.rate_system.entity.Area;
import com.rate.system.rate_system.service.AreaService;

@Controller
@RequestMapping("/module/area")
public class AreaController {
	@Autowired
	private AreaService airservice;
	
	@Log("查询行政区划")
	@ResponseBody
	@GetMapping("/list")
	//@RequiresPermissions("module:waterarea:list")
	public Object list(@RequestParam Map<String, Object> params) {
		JSONObject obj = new JSONObject();
		Area p = new Area();
		p.setAreaCode(params.get("areaCode").toString());
		p.setAreaType(params.get("areaType").toString());
		List<Area> basinInfoList = airservice.queryByCondition(p);
		obj.put("Data", basinInfoList);
	    return obj;
	}
}
