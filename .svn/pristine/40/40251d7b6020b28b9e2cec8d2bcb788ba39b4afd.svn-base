package com.rate.web.airparam.controller;

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
import com.rate.system.rate_system.controller.BaseController;
import com.rate.system.rate_system.utils.PageUtils;
import com.rate.system.rate_system.utils.R;
import com.rate.web.airparam.entity.AirParam;
import com.rate.web.airparam.entity.MiluoSiteDevicedParam;
import com.rate.web.airparam.service.AirParamService;
import com.rate.web.airsite.entity.SiteParam;

@Controller
@RequestMapping("/module/airparam")
public class AirParamController extends BaseController{
	private String prefix = "/module/airparam";

	@Autowired
	private AirParamService airParamService;
	
	 private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@GetMapping()
    String pram(){
		return prefix + "/list";
	}
	
//	@RequiresPermissions("sys:con:con")
//	@GetMapping("/list")
//	@ResponseBody()
//	List<AirParam> list() {
//		List<AirParam> airParams = airParamService.list();
//		return  airParams;
//	}
	
	@ResponseBody
    @GetMapping("/list")
    PageUtils list(@RequestParam Map<String,Object> params, Long pageSize, Long pageNumber, String pluName){
        PageQuery<AirParam> paras = new PageQuery<>(pageNumber,pageSize,params);
        PageQuery<AirParam> page = airParamService.list(paras, pluName);
        PageUtils pageUtils = new PageUtils(page.getList(),page.getTotalRow());
        return pageUtils;
    }
	
	@Log("添加操作")
	@RequiresPermissions("sys:con:add")
	@GetMapping("/add")
	String add() {
		return prefix + "/add";
	}
	
	@Log("保存参数")
	@RequiresPermissions("sys:con:add")
	@PostMapping("/save")
	@ResponseBody()
	R save(AirParam airParam) {
		airParam.setCeateTime(SDF.format(new Date()));
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		try {
			airParamService.save(airParam);
			return R.ok();
		} catch (Exception e) {
			return R.error(1, "保存失败");
		}
	}
	
	@Log("编辑操作")
	@RequiresPermissions("sys:con:edit")
	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") String id, Model model) {
		AirParam airParamDO = airParamService.get(id);
		model.addAttribute("airParam", airParamDO);
		return prefix + "/edit";
	}
	
	@Log("更新参数")
	@RequiresPermissions("sys:con:edit")
	@PostMapping("/update")
	@ResponseBody()
	R update(AirParam airParam) {
		airParam.setCeateTime(SDF.format(new Date()));
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (airParamService.update(airParam) > 0) {
			return R.ok();
		} else {
			return R.error(1, "更新失败");
		}
	}
	
	@Log("删除参数")
	@RequiresPermissions("sys:con:remove")
	@PostMapping("/remove")
	@ResponseBody()
	R remove(String id) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (airParamService.remove(id) > 0) {
			return R.ok();
		} else {
			return R.error(1, "删除失败");
		}
	}
	
	@Log("批量删除")
	@RequiresPermissions("sys:con:batchRemove")
	@PostMapping("/batchRemove")
	@ResponseBody
	R batchRemove(@RequestParam("ids[]") String[] ids) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		int r = airParamService.batchremove(ids);
		if (r > 0) {
			return R.ok();
		}
		return R.error();
	}
	@ResponseBody
    @GetMapping("/search")
    List<AirParam> search(String searchText){
		return airParamService.searchObject(searchText);
	}
	@ResponseBody
    @GetMapping("/findAllList")
    List<AirParam> findAllList(){
		return airParamService.findAllList();
	}
	   
    /**
     * <p>Title: addFactor</p>  
    
     * <p>Description: </p>  
    
     * @param siteParam
     * @return
     */
    @PostMapping("/addParam")
    @ResponseBody
    public R addParam(MiluoSiteDevicedParam miluoSiteDevicedParam) {
        try {
        	airParamService.saveSiteParam(miluoSiteDevicedParam);
            return R.ok();
        }catch (Exception e) {
        return R.error();
        }
    }
	
}
