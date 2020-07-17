package com.rate.web.airparam.controller;

import java.util.Map;

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

import com.rate.system.rate_system.utils.PageUtils;
import com.rate.system.rate_system.utils.R;
import com.rate.web.airparam.entity.AirParam;
import com.rate.web.airparam.entity.MiluoSiteDevicedParam;
import com.rate.web.airparam.service.MiluoSiteDevicedParamService;

@Controller
@RequestMapping("/airSite")
public class MiluoSiteDevicedParamController {

	@Autowired
	private MiluoSiteDevicedParamService miluoSiteDevicedParamService;
	
	/**
     * <p>Title: factorValueSet</p>  
     * <p>Description: 空气因子列表</p>  
    
     * @param model
     * @param equId
     * @return
     */
    @GetMapping("/factorAirValueSet/{equId}")
    public String factorAirValueSet(Model model, @PathVariable("equId") Integer equId) {
    	model.addAttribute("equId", equId);
        return "/module/airSite/factorAirValueSet";
    }
    
    
    @ResponseBody
    @GetMapping("/airList")
    PageUtils list(@RequestParam Map<String,Object> params, Long pageSize, Long pageNumber, String pluName,String videoId){
        PageQuery<MiluoSiteDevicedParam> paras = new PageQuery<>(pageNumber,pageSize,params);
        PageQuery<MiluoSiteDevicedParam> page = miluoSiteDevicedParamService.airList(paras, pluName,videoId);
        PageUtils pageUtils = new PageUtils(page.getList(),page.getTotalRow());
        return pageUtils;
    }
    
    @PostMapping("/removeVideo")
    @ResponseBody
    public R remove(Integer id) {
        try {
        	miluoSiteDevicedParamService.removeVideo(id);
            return R.ok();
        } catch (Exception e) {
            return R.error();
        }
    }
    
}
