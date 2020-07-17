package com.rate.web.statement.controller;

import com.rate.system.rate_system.controller.BaseController;
import com.rate.system.rate_system.service.UserService;
import com.rate.system.rate_system.utils.excel.ExportExcel;
import com.rate.web.statement.entity.RealtimeTreatmentPlant;
import com.rate.web.statement.service.WaterPlantService;
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
 * 涉水污染源浓度查询
 * @author chenshixue
 * @date 2019年6月4日
 */
@Controller
@RequestMapping("/statement/waterPlant")
public class WaterPlantController extends BaseController {
	
	@Autowired
	private WaterPlantService waterPlantService;

	@Autowired
	private UserService userService;

	private static List<RealtimeTreatmentPlant> listExp;
	/**
	 * 实时浓度查询
	 * @param params
	 * @return
	 */
	@RequestMapping("/minuteList")
	@ResponseBody
	public List<RealtimeTreatmentPlant> minuteList(@RequestParam Map<String, Object> params){
		String siteCode = (String)params.get("siteCodes");
        if(siteCode != null && !"".equals(siteCode)){
			params.put("siteIds", siteCode.split(","));
        } else {
			params.put("siteIds", userService.getSitePowerList(getUserId()));
		}
		List<RealtimeTreatmentPlant> realtimeTreatmentPlants = waterPlantService.minuteList(params);
        listExp = realtimeTreatmentPlants;
		return realtimeTreatmentPlants;
	}
	
	/**
	 * 小时浓度查询
	 * @param params
	 * @return
	 */
	@RequestMapping("/hourList")
	@ResponseBody
	public List<RealtimeTreatmentPlant> hourList(@RequestParam Map<String, Object> params){
		String siteCode = (String)params.get("siteCodes");
		if(siteCode != null && !"".equals(siteCode)){
			params.put("siteIds", siteCode.split(","));
		} else {
			params.put("siteIds", userService.getSitePowerList(getUserId()));
		}
        List<RealtimeTreatmentPlant> list = waterPlantService.hourList(params);
		listExp = list;
		return list;
	}
	
	/**
	 * 日浓度查询
	 * @param params
	 * @return
	 */
	@RequestMapping("/dailyList")
	@ResponseBody
	public List<RealtimeTreatmentPlant> dayilList(@RequestParam Map<String, Object> params){
		String siteCode = (String)params.get("siteCodes");
		if(siteCode != null && !"".equals(siteCode)){
			params.put("siteIds", siteCode.split(","));
		} else {
			params.put("siteIds", userService.getSitePowerList(getUserId()));
		}
		List<RealtimeTreatmentPlant> realtimeTreatmentPlants = waterPlantService.dailyList(params);
		listExp = realtimeTreatmentPlants;
		return waterPlantService.dailyList(params);
	}

	@RequestMapping("/exportExcel")
	@ResponseBody
	public void getExp(@RequestParam Map<String, Object> params,HttpServletResponse response) {
		String sort = (String) params.get("sort");
		RealtimeTreatmentPlant.setSort(sort);
		String beginTime = (String) params.get("beginTime");
		String endTime = (String) params.get("endTime");
		String date = beginTime + "至" + endTime;
		for (RealtimeTreatmentPlant realtimeTreatmentPlant : listExp) {
			if (realtimeTreatmentPlant.getPh() != null) {
				realtimeTreatmentPlant.setPh(new BigDecimal(realtimeTreatmentPlant.getPh()).setScale(1,BigDecimal.ROUND_HALF_UP).toString());
			}
		}
		try {
			new ExportExcel(date+"涉水污染源数据", RealtimeTreatmentPlant.class, 2).setDataList(listExp).write(response, "涉水污染源数据.xlsx")
					.dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
