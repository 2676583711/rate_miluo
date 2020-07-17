package com.rate.web.site.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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

import com.rate.system.rate_system.annotation.Log;
import com.rate.system.rate_system.utils.PageUtils;
import com.rate.system.rate_system.utils.R;
import com.rate.web.site.entity.MiluoPollutantFactor;
import com.rate.web.site.service.MiluoPollutantFactorService;
import com.rate.web.task.entity.MiluoTaskInfo;

@Controller
@RequestMapping("/pollutantfactor")
public class MiluoPollutantFactorController {

	   @Autowired
	    private MiluoPollutantFactorService miluoPollutantFactorService;
	   
	   private String prefix = "/module/pollutantfactor"; 
	   /**
	     * 定义时间格式
	     */
	    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	   
		@GetMapping("/index")
		public String checkConcentration(){
			return prefix + "/index";
		}
	   
		/**
		 * <p>Title: list</p>  
		
		 * <p>Description: </p>  
		
		 * @param params
		 * @param pageSize
		 * @param pageNumber
		 * @return
		 */
		@RequestMapping("/list")
		@ResponseBody
		public PageUtils list(@RequestParam Map<String, Object> params, Long pageSize, Long pageNumber){
	        PageQuery<MiluoPollutantFactor> pageQuery = new PageQuery<>(pageNumber, pageSize, params);
	        PageQuery<MiluoPollutantFactor> page = miluoPollutantFactorService.list(pageQuery);
	        return new PageUtils(page.getList(), page.getTotalRow());
		}
		
		@Log("删除待办任务")
		@PostMapping("/remove")
		@ResponseBody
		R remove(String id) {
			if (miluoPollutantFactorService.remove(id) > 0) {
				return R.ok();
			}
			return R.error();
		}
	   
		@GetMapping("/edit/{id}")
		String edit(Model model,@PathVariable("id") String id) {
			MiluoPollutantFactor miluoPollutantFactor=miluoPollutantFactorService.findMiluoFactorInfoById(id);
			model.addAttribute("miluoPollutantFactor", miluoPollutantFactor);
			return prefix + "/edit";
		}
		

		@GetMapping("/detail/{id}")
		String detail(Model model,@PathVariable("id") String id) {
			MiluoPollutantFactor miluoPollutantFactor=miluoPollutantFactorService.findMiluoFactorInfoById(id);
			model.addAttribute("miluoPollutantFactor", miluoPollutantFactor);
			return prefix + "/view";
		}
		
		
		@PostMapping("/batchRemove")
		@ResponseBody
		R batchRemove(@RequestParam("ids[]") String[] ids) {
			int r = miluoPollutantFactorService.batchRemove(ids);
			if (r > 0) {
				return R.ok();
			}
			return R.error();
		}
		/**
		 * <p>Title: update</p>  
		 * <p>Description: </p>  
		 * @param miluoTaskInfo
		 * @return
		 */
		@ResponseBody
		@RequestMapping("/update")
		public R update(MiluoPollutantFactor miluoPollutantFactor) {
			try {
				miluoPollutantFactor.setCreateTime(SDF.format(new Date()));
				miluoPollutantFactorService.updateTemplateById(miluoPollutantFactor);
				return R.ok();
			} catch (Exception e) {
				e.printStackTrace();
				return R.error();
			}
		}
		
		@GetMapping("/add")
		public String add(){
			return prefix + "/add";
		}
		/**
		
		 * <p>Title: save</p>  
		
		 * <p>Description: </p>  
		
		 * @param miluoPollutantFactor
		 * @return
		 */
		@ResponseBody
		@RequestMapping("/save")
		public R save(MiluoPollutantFactor miluoPollutantFactor) {
			try {
				miluoPollutantFactor.setCreateTime(SDF.format(new Date()));
				miluoPollutantFactorService.save(miluoPollutantFactor);
				return R.ok();
			} catch (Exception e) {
				e.printStackTrace();
				return R.error();
			}
		}
}
