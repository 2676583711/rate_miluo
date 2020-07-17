package com.rate.web.statement.controller;

import com.rate.system.rate_system.controller.BaseController;
import com.rate.system.rate_system.service.UserService;
import com.rate.system.rate_system.utils.excel.ExportExcel;
import com.rate.web.statement.entity.WaterSite;
import com.rate.web.statement.entity.WaterSiteExp;
import com.rate.web.statement.service.AutoSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 水质自动站浓度查询
 * @author chenshixue
 * @date 2019年6月4日
 */
@Controller
@RequestMapping("/statement/autoSite")
public class AutoSiteController extends BaseController {

	@Autowired
	private AutoSiteService autoSiteService;

	@Autowired
	private UserService userService;

	private static List<WaterSite> listExp;
	/**
	 * 分钟浓度查询
	 * @param params
	 * @return
	 */
	@RequestMapping("/minuteList")
	@ResponseBody
	public List<WaterSite> minuteList(@RequestParam Map<String, Object> params){
		String siteCode = (String)params.get("siteCodes");
		String[] siteCodes = null;
		if(siteCode != null && !siteCode.equals("")){
			siteCodes = siteCode.split(",");
		}
		params.put("siteCodes", siteCodes);
		params.put("siteIds", userService.getSitePowerList(getUserId()));
        List<WaterSite> waterSites = autoSiteService.minuteList(params);
        listExp = waterSites;
        return waterSites;
	}
	
	/**
	 * 小时浓度查询
	 * @param params
	 * @return
	 */
	@RequestMapping("/hourList")
	@ResponseBody
	public List<WaterSite> hourList(@RequestParam Map<String, Object> params){
		String siteCode = (String)params.get("siteCodes");
		String[] siteCodes = null;
        if(siteCode != null && !siteCode.equals("")){
        	siteCodes = siteCode.split(",");
        }
        params.put("siteCodes", siteCodes);
        params.put("siteIds", userService.getSitePowerList(getUserId()));
        List<WaterSite> waterSites = autoSiteService.hourList(params);
        listExp = waterSites;
        return waterSites;
	}
	
	/**
	 * 日均浓度查询
	 * @param params
	 * @return
	 */
	@RequestMapping("/dailyList")
	@ResponseBody
	public List<WaterSite> dailyList(@RequestParam Map<String, Object> params){
		String siteCode = (String)params.get("siteCodes");
		String[] siteCodes = null;
        if(siteCode != null && !siteCode.equals("")){
        	siteCodes = siteCode.split(",");
        }
        params.put("siteCodes", siteCodes);
		params.put("siteIds", userService.getSitePowerList(getUserId()));
        List<WaterSite> waterSites = autoSiteService.dailyList(params);
        listExp = waterSites;
        return waterSites;
	}

    @RequestMapping("/exportExcel")
    @ResponseBody
    public void getExp(@RequestParam Map<String, Object> params,HttpServletResponse response) {
        String sort = (String) params.get("sort");
        String beginTime = (String) params.get("beginTime");
        String endTime = (String) params.get("endTime");
        String date = beginTime + "至" + endTime;
        for (WaterSite waterSite : listExp) {
            if (waterSite.getSw() != null) {
                waterSite.setSw(new BigDecimal(waterSite.getSw()).setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue());
            }
            if (waterSite.getPh() != null) {
                waterSite.setPh(new BigDecimal(waterSite.getPh()).setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue());
            }
            if (waterSite.getDdl() != null) {
                waterSite.setDdl(new BigDecimal(waterSite.getDdl()).setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue());
            }
            if (waterSite.getZd() != null) {
                waterSite.setZd(new BigDecimal(waterSite.getZd()).setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue());
            }
        }
        try {
            if (sort.equals("3")){
                new ExportExcel(date+"水质自动站数据", WaterSiteExp.class, 2).setDataList(listExp).write(response, "水质自动站数据.xlsx")
                        .dispose();
            }else {
                new ExportExcel(date+"水质自动站数据", WaterSite.class, 2).setDataList(listExp).write(response, "水质自动站数据.xlsx")
                        .dispose();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
