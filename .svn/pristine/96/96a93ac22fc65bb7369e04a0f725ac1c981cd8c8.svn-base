package com.rate.web.statement.controller;

import com.rate.system.rate_system.controller.BaseController;
import com.rate.system.rate_system.service.UserService;
import com.rate.system.rate_system.utils.PageUtils;
import com.rate.system.rate_system.utils.excel.ExportExcel;
import com.rate.web.statement.entity.RealtimeTreatmentPlant;
import com.rate.web.statement.service.AirCheckConService;
import com.rate.web.statement.service.WaterPlantService;
import com.rate.web.statement.vo.AirDailyVO;
import com.rate.web.statement.vo.AirHourVO;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
/**
 * 空气监测浓度查询
 * @author chenshixue
 * @date 2019年5月27日
 */
@Controller
@RequestMapping("/statement/checkConcentration")
public class AirCheckConController extends BaseController{
	
	private String prefix = "/statement/checkConcentration";
	
	@Autowired
	private AirCheckConService airCheckConService;
	@Autowired
	private WaterPlantService waterPlantService;
	@Autowired
	private UserService userService;

	@GetMapping("")
	public String checkConcentration(Model model, String siteType, String siteId){
	    model.addAttribute("siteType", siteType);
	    model.addAttribute("siteId", siteId);
	    Long roleId = getRoleId();
	    model.addAttribute("roleId", roleId);
	    if(roleId == 1 || roleId == 73 || roleId == 74 || roleId == 75 || roleId == 77){
			return prefix + "/list";
		}else{
	    	return prefix + "/listPlus";
		}

	}
	
	/**
	 * 空气实时数据
	 * @param params
	 * @param pageSize
	 * @param pageNumber
	 * @return
	 */
	/*@RequestMapping("/minuteList")
	@ResponseBody
	public PageUtils minuteList(@RequestParam Map<String, Object> params, Long pageSize, Long pageNumber){
		String siteCode = (String)params.get("siteCodes");
		String[] siteCodes = null;
        if(siteCode != null && !siteCode.equals("")){
        	siteCodes = siteCode.split(",");
        }
        params.put("siteCodes", siteCodes);
        PageQuery<AirMinuteStatement> pageQuery = new PageQuery<>(pageNumber, pageSize, params);
        PageQuery<AirMinuteStatement> page = airCheckConService.minuteList(pageQuery);
        return new PageUtils(page.getList(), page.getTotalRow());
	}*/
	
	/**
	 * 空气小时数据
	 * @param params
	 * @return
	 */
	@RequestMapping("/hourList")
	@ResponseBody
	public List<AirHourVO> hourList(@RequestParam Map<String, Object> params){
		String siteCode = (String)params.get("siteCodes");
		String[] siteCodes = null;
        if(siteCode != null && !siteCode.equals("")){
        	siteCodes = siteCode.split(",");
        }
        params.put("siteCodes", siteCodes);
        params.put("siteIds", userService.getSitePowerList(getUserId()));
		return airCheckConService.hourList(params);
	}
	
	/**
	 * 空气日数据
	 * @param params
	 * @return
	 */
	@RequestMapping("/dailyList")
	@ResponseBody
	public List<AirDailyVO> dailyList(@RequestParam Map<String, Object> params){
		String siteCode = (String)params.get("siteCodes");
		String[] siteCodes = null;
        if(siteCode != null && !siteCode.equals("")){
        	siteCodes = siteCode.split(",");
        }
        params.put("siteCodes", siteCodes);
		params.put("siteIds", userService.getSitePowerList(getUserId()));
        List<AirDailyVO> airDailyVOS = airCheckConService.dailyList(params);
        return airDailyVOS;
	}
	
	/**
	 * 空气月数据
	 * @param params
	 * @param pageSize
	 * @param pageNumber
	 * @return
	 */
	/*@RequestMapping("/monthList")
	@ResponseBody
	public List<ConcentrationVO> monthList(@RequestParam Map<String, Object> params, Long pageSize, Long pageNumber){
		String siteCode = (String)params.get("siteCodes");
		String[] siteCodes = null;
        if(siteCode != null && !siteCode.equals("")){
        	siteCodes = siteCode.split(",");
        }
        params.put("siteCodes", siteCodes);
		return airCheckConService.monthList(params);
	}*/
	
	/**
	 * 水质自动站浓度查询
	 * @param params
	 * @param pageSize
	 * @param pageNumber
	 * @return
	 */
	@RequestMapping("/autoSiteList")
	@ResponseBody
	public PageUtils autoSiteList(@RequestParam Map<String, Object> params, Long pageSize, Long pageNumber){
		String siteCode = (String)params.get("siteCodes");
		String[] siteCodes = null;
        if(siteCode != null && !siteCode.equals("")){
        	siteCodes = siteCode.split(",");
        }
        params.put("siteCodes", siteCodes);
		PageQuery<RealtimeTreatmentPlant> pageQuery = new PageQuery<>(pageNumber, pageSize, params);
        PageQuery<RealtimeTreatmentPlant> page = waterPlantService.autoSiteList(pageQuery);
        return new PageUtils(page.getList(), page.getTotalRow());
	}
	
	/**
	 * 污染源浓度查询
	 * @param params
	 * @param pageSize
	 * @param pageNumber
	 * @return
	 */
	@RequestMapping("/pollutList")
	@ResponseBody
	public PageUtils pollutList(@RequestParam Map<String, Object> params, Long pageSize, Long pageNumber){
		String siteCode = (String)params.get("siteCodes");
		String[] siteCodes = null;
        if(siteCode != null && !siteCode.equals("")){
        	siteCodes = siteCode.split(",");
        }
        params.put("siteCodes", siteCodes);
		params.put("siteIds", userService.getSitePowerList(getUserId()));
		PageQuery<RealtimeTreatmentPlant> pageQuery = new PageQuery<>(pageNumber, pageSize, params);
        PageQuery<RealtimeTreatmentPlant> page = waterPlantService.pollutList(pageQuery);
        return new PageUtils(page.getList(), page.getTotalRow());
	}
	
	/**
	 * 导出空气小时浓度
	 * @param params
	 * @param response
	 * @param request
	 */
	@RequestMapping("/airHour/exportExcel")
    @ResponseBody
    public void airHourExportExcel(@RequestParam Map<String, Object> params, HttpServletResponse response,
                            HttpServletRequest request) {
		String siteCode = (String)params.get("siteCode");
		String beginTime = (String) params.get("beginTime");
		String endTime = (String) params.get("endTime");
		String date = beginTime + "至" + endTime;
		String[] siteCodes = null;
        if(siteCode != null && !siteCode.equals("")){
        	siteCodes = siteCode.split(",");
        }
        params.put("siteCodes", siteCodes);
		params.put("siteIds", userService.getSitePowerList(getUserId()));
        List<AirHourVO> list = airCheckConService.hourList(params);
		for (AirHourVO airHourVO : list) {
            String wd = airHourVO.getWd();
            if(wd != null && wd != ""){
                double value = Double.parseDouble(wd);
                if(value >= 333.5 || value <= 22.5){
                    airHourVO.setWd("北风");
                }else if(value > 22.5 && value <= 67.5){
                    airHourVO.setWd("东北风");
                }else if(value > 67.5 && value <= 112.5){
                    airHourVO.setWd("东风");
                }else if(value > 112.5 && value <= 157.5){
                    airHourVO.setWd("东南风");
                }else if(value > 157.5 && value <= 202.5){
                    airHourVO.setWd("南风");
                }else if(value > 202.5 && value <= 247.5){
                    airHourVO.setWd("西南风");
                }else if(value > 247.5 && value <= 292.5){
                    airHourVO.setWd("西风");
                }else if(value > 292.5 && value < 337.5){
                    airHourVO.setWd("西北风");
                }
            }else{
                airHourVO.setWd("-");
            }
        }
//        raplaceDataExport(list);
        try {
			new ExportExcel(date+"空气小时浓度列表", AirHourVO.class, 2).setDataList(list).write(response, "空气小时浓度列表.xlsx")
					.dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	/**
	 * 导出空气日浓度
	 * @param params
	 * @param response
	 * @param request
	 */
	@RequestMapping("/airDay/exportExcel")
    @ResponseBody
    public void airDayExportExcel(@RequestParam Map<String, Object> params, HttpServletResponse response,
                            HttpServletRequest request) {
		String siteCode = (String)params.get("siteCode");
        String beginTime = (String) params.get("beginTime");
        String endTime = (String) params.get("endTime");
        String date = beginTime + "至" + endTime;
		String[] siteCodes = null;
        if(siteCode != null && !siteCode.equals("")){
        	siteCodes = siteCode.split(",");
        }
        params.put("siteCodes", siteCodes);
		params.put("siteIds", userService.getSitePowerList(getUserId()));
        List<AirDailyVO> list = airCheckConService.dailyList(params);
        for (AirDailyVO airDailyVO : list) {
            String wd = airDailyVO.getWd();
            if(wd != null && wd != ""){
                double value = Double.parseDouble(wd);
                if(value >= 333.5 || value <= 22.5){
                    airDailyVO.setWd("北风");
                }else if(value > 22.5 && value <= 67.5){
                    airDailyVO.setWd("东北风");
                }else if(value > 67.5 && value <= 112.5){
                    airDailyVO.setWd("东风");
                }else if(value > 112.5 && value <= 157.5){
                    airDailyVO.setWd("东南风");
                }else if(value > 157.5 && value <= 202.5){
                    airDailyVO.setWd("南风");
                }else if(value > 202.5 && value <= 247.5){
                    airDailyVO.setWd("西南风");
                }else if(value > 247.5 && value <= 292.5){
                    airDailyVO.setWd("西风");
                }else if(value > 292.5 && value < 337.5){
                    airDailyVO.setWd("西北风");
                }
            }else{
                airDailyVO.setWd("-");
            }
        }
//        raplaceDataExport(list);
        try {
			new ExportExcel(date+"空气每日浓度列表", AirDailyVO.class, 2).setDataList(list).write(response, "空气每日浓度列表.xlsx")
					.dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
}
