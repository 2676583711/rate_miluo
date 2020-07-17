package com.rate.web.task.controller;

import com.alibaba.fastjson.JSON;
import com.rate.system.rate_system.annotation.Log;
import com.rate.system.rate_system.controller.BaseController;
import com.rate.system.rate_system.entity.FileDO;
import com.rate.system.rate_system.service.FileService;
import com.rate.system.rate_system.utils.*;
import com.rate.system.rate_system.utils.excel.ExportExcel;
import com.rate.web.task.dao.MiluoTaskInfoDao;
import com.rate.web.task.entity.MiluoTaskInfo;
import com.rate.web.task.entity.MiluoTaskInfoExp;
import com.rate.web.task.service.MiluoTaskInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
* <p>Title: MiluoTaskInfoController</p>  

* <p>Description: </p>  

* @author chenh  

* @date 2019年6月3日
 */
@Controller
@RequestMapping("/task/agents")
public class MiluoTaskInfoController extends BaseController{

	@Autowired
	private MiluoTaskInfoService miluoTaskInfoService;

	@Autowired
	private MiluoTaskInfoDao miluoTaskInfoDao;
	
	private String prefix = "/task/miluotaskinfo";
	
	@GetMapping("index")
	public String checkConcentration(){
		return prefix + "/index";
	}
	
	
	@GetMapping("historyIndex")
	public String historyList(){
		return  "/task/historytaskinfo/index";
	}

	@RequestMapping("/count")
	@ResponseBody
	public PageUtils taskCount(@RequestParam Map<String, Object> params, Long pageSize, Long pageNumber){
		params.put("userId", getUserId());
		params.put("roleId", getRoleId());
		PageQuery<MiluoTaskInfo> pageQuery = new PageQuery<>(pageNumber, pageSize, params);
		PageQuery<MiluoTaskInfo> page = miluoTaskInfoService.taskInfoCount(pageQuery);
		return new PageUtils(page.getList(), page.getTotalRow());
	}

	/**
	 * 
	
	 * <p>Title: minuteList</p>  
	
	 * <p>Description:分页 </p>  
	
	 * @param params
	 * @param pageSize
	 * @param pageNumber
	 * @return
	 */
	@RequestMapping("/minuteList")
	@ResponseBody
	public PageUtils list(@RequestParam Map<String, Object> params, Long pageSize, Long pageNumber){
		params.put("userId", getUserId());
		params.put("roleId", getRoleId());
        PageQuery<MiluoTaskInfo> pageQuery = new PageQuery<>(pageNumber, pageSize, params);
        PageQuery<MiluoTaskInfo> page = miluoTaskInfoService.list(pageQuery);
        return new PageUtils(page.getList(), page.getTotalRow());
	}
	@RequestMapping("/minExp")
	@ResponseBody
	public void getExp(@RequestParam Map<String, Object> params,HttpServletResponse response) throws IOException {
		String date = sf.format(new Date());
		params.put("userId", getUserId());
		params.put("roleId", getRoleId());
		List<MiluoTaskInfo> exceeds = miluoTaskInfoDao.listExp(params);
		exceeds = formatExceedsList(exceeds);
		try {
			new ExportExcel(date + "待办任务管理", MiluoTaskInfo.class, 2).setDataList(exceeds).write(response, "待办任务管理.xlsx")
					.dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private List<MiluoTaskInfo> formatExceedsList(List<MiluoTaskInfo> exceeds) {
		for (MiluoTaskInfo info : exceeds) {

			// 处理状态
			Integer status = info.getStatus();
			if (status != null && status == 1) {
				info.setStatusText("未处理");
			} else {
				info.setStatusText("未处理");
			}

			// 紧急程度
			String degreeEmergency = info.getDegreeEmergency();
			if ("2".equals(degreeEmergency)) {
				info.setDegreeEmergency("最高级别");
			} else if ("1".equals(degreeEmergency)) {
				info.setDegreeEmergency("重要紧急");
			} else if ("0".equals(degreeEmergency)) {
				info.setDegreeEmergency("普通不紧急");
			} else {
				info.setDegreeEmergency("其他");
			}

			// 任务类型
			String taskType = info.getTaskType();
			if ("0".equals(taskType)) {
				info.setTaskType("报警任务");
			} else if ("1".equals(taskType)) {
				info.setTaskType("巡检");
			} else if ("2".equals(taskType)) {
				info.setTaskType("维护");
			} else if ("3".equals(taskType)) {
				info.setTaskType("易耗品更换");
			} else if ("4".equals(taskType)) {
				info.setTaskType("仪器校准");
			} else if ("5".equals(taskType)) {
				info.setTaskType("维修");
			} else if ("6".equals(taskType)) {
				info.setTaskType("试剂更换");
			} else if ("7".equals(taskType)) {
				info.setTaskType("废液回收");
			} else if ("8".equals(taskType)) {
				info.setTaskType("其他");
			} else if ("9".equals(taskType)) {
				info.setTaskType("因子超标");
			} else if ("10".equals(taskType)) {
				info.setTaskType("因子过低");
			}
		}

		return exceeds;
	}


	@RequestMapping("/historyLists")
	@ResponseBody
	public PageUtils historyList(@RequestParam Map<String, Object> params, Long pageSize, Long pageNumber){
		params.put("userId", getUserId());
		params.put("roleId", getRoleId());
        PageQuery<MiluoTaskInfo> pageQuery = new PageQuery<>(pageNumber, pageSize, params);
        PageQuery<MiluoTaskInfo> page = miluoTaskInfoService.historyList(pageQuery);
        return new PageUtils(page.getList(), page.getTotalRow());
	}

	private static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@RequestMapping("/hisExp")
	@ResponseBody
	public void getHisExp(@RequestParam Map<String, Object> params,HttpServletResponse response) throws IOException {
		String date = sf.format(new Date());
		params.put("userId", getUserId());
		params.put("roleId", getRoleId());
		List<MiluoTaskInfo> exceeds = miluoTaskInfoDao.historyListExp(params);
		exceeds = formatExceedsList(exceeds);
		try {
			new ExportExcel(date + "历史任务管理", MiluoTaskInfoExp.class, 2).setDataList(exceeds).write(response, "历史任务管理.xlsx")
					.dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
/**
 * <p>Title: remove</p>  
 * <p>Description: 删除代办把任务</p>  
 * @param id
 * @return
 */
	@Log("删除待办任务")
	@PostMapping("/remove")
	@ResponseBody
	R remove(String id) {
		if (miluoTaskInfoService.remove(id) > 0) {
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 
	
	 * <p>Title: edit</p>  
	
	 * <p>Description: 查看待办任务</p>  
	
	 * @param model
	 * @param id
	 * @return
	 */
	@Log("查看待办任务")
	@GetMapping("/edit/{id}/{status}")
	String edit(Model model,@PathVariable("id") String id,@PathVariable("status") String status) {
		MiluoTaskInfo miluoTaskInfo=miluoTaskInfoService.findMiluoTaskInfoById(id,status);
		//任务类型（报警任务0, 巡检1,维护2,易耗品更换3,仪器校准4,维修5,试剂更换6,废液回收7,其他8）
		if(miluoTaskInfo.getTaskType().equals("0")) {
			miluoTaskInfo.setTaskType("报警任务");
		}else if(miluoTaskInfo.getTaskType().equals("1")) {
			miluoTaskInfo.setTaskType("巡检");
		}else if(miluoTaskInfo.getTaskType().equals("2")) {
			miluoTaskInfo.setTaskType("维护");
		}else if(miluoTaskInfo.getTaskType().equals("3")) {
			miluoTaskInfo.setTaskType("易耗品更换");
		}else if(miluoTaskInfo.getTaskType().equals("4")) {
			miluoTaskInfo.setTaskType("仪器校准");
		}else if(miluoTaskInfo.getTaskType().equals("5")) {
			miluoTaskInfo.setTaskType("维修");
		}else if(miluoTaskInfo.getTaskType().equals("6")) {
			miluoTaskInfo.setTaskType("试剂更换");
		}else if(miluoTaskInfo.getTaskType().equals("7")) {
			miluoTaskInfo.setTaskType("废液回收");
		}else if(miluoTaskInfo.getTaskType().equals("9")){
			miluoTaskInfo.setTaskType("因子超标");
		}else {
			miluoTaskInfo.setTaskType("其他");
		}
		if(miluoTaskInfo.getDegreeEmergency().equals("0")) {
			miluoTaskInfo.setDegreeEmergency("普通不紧急");
		}else if(miluoTaskInfo.getDegreeEmergency().equals("1")) {
			miluoTaskInfo.setDegreeEmergency("重要紧急");
		}else if(miluoTaskInfo.getDegreeEmergency().equals("2")){
			miluoTaskInfo.setDegreeEmergency("最高级别");
		}else {
			miluoTaskInfo.setDegreeEmergency("其他");
		}

		String photo = miluoTaskInfo.getPhoto();
		if (StringUtils.isNotBlank(photo)) {
			model.addAttribute("photo", splitPicPath(photo));
		}
		model.addAttribute("miluoTaskInfo", miluoTaskInfo);
		return prefix + "/view";
	}
	
	/**
	 * <p>Title: batchRemove</p>  
	
	 * <p>Description: 批量删除待办任务</p>  
	 * @param ids
	 * @return
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	R batchRemove(@RequestParam("ids[]") String[] ids) {
		int r = miluoTaskInfoService.batchRemove(ids);
		if (r > 0) {
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * <p>Title: edit</p>  
	 * <p>Description: 编辑待办任务</p>  
	 * @param model
	 * @param id
	 * @return
	 */
	@RequiresPermissions("edit:task:task")
	@GetMapping("/editTask/{id}/{status}")
	String editTask(Model model,@PathVariable("id") String id,@PathVariable("status") String status) {
		MiluoTaskInfo miluoTaskInfo=miluoTaskInfoService.findMiluoTaskInfoById(id,status);
		String photo = miluoTaskInfo.getPhoto();
		if (StringUtils.isNotBlank(photo)) {
			model.addAttribute("photo", splitPicPath(photo));
		}
		//任务类型（报警任务0, 巡检1,维护2,易耗品更换3,仪器校准4,维修5,试剂更换6,废液回收7,其他8）
		model.addAttribute("miluoTaskInfo", miluoTaskInfo);
		return prefix + "/edit";
	}
	
	
	/**
	 * <p>Title: editTask</p>  
	
	 * <p>Description:修改 </p>  
	
	 * @param
	 * @param
	 * @param
	 * @return
	 */

	@PostMapping("/update")
	@ResponseBody
	public R update(MiluoTaskInfo miluoTaskInfo,String imgId3) {
		try {

			if (imgId3 != null && !"".equals(imgId3)) {
				miluoTaskInfo.setPhoto(imgId3);
			}
			miluoTaskInfoService.updateTemplateById(miluoTaskInfo);
			return R.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}

	@Autowired
	private FileSaveUtil fileSaveUtil;

   /**
    * @Param [file3]
    * @Return java.lang.String
    * @author xiaoshi       
    * @Description  图片的上传功能
    * @Date 2019/7/6
    * @Time 13:15
    **/
	@PostMapping("/upload")
	@ResponseBody
	public String upload(
		@RequestParam(value = "input-b3[]", required = false) MultipartFile[] file3) {

		String image3 = "";

		Map<String, Object> imagesMap = new HashMap<String, Object>();
		try {
				if (file3.length != 0) {
					byte[] bytes3;
					FileDO fileDo3;
					for (int j = 0; j < file3.length; j++) {
						bytes3 = file3[j].getBytes();
						bytes3 = PicUtils.compressPicForScale(bytes3, PicUtils.MAX_SIZE, "FemaleContract" + (j + 1));
						fileDo3 = fileSaveUtil.savebyte(bytes3, file3[j]);
						image3 += fileDo3.getId() + ",";
					}
					imagesMap.put("image3", image3);
				}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return JSON.toJSONString(imagesMap);
	}



	/**
	 * 分割照片id字符串 拿到对应url 返回url集合
	 *
	 * @param contractPhoto 照片id  ,分隔
	 * @return java.util.List<java.lang.String>
	 * @author wuweijie
	 * @date 2019/2/14 10:00
	 **/

	@Autowired
	private FileService sysFileService;

	private List<FileDO> splitPicPath(String contractPhoto) {
		String[] pics = contractPhoto.split(",");
		List<FileDO> picPaths = new ArrayList<>();
		for (String picId : pics) {
			if (StringUtils.isNotBlank(picId)) {
				FileDO fileDO = sysFileService.get(picId);
				if (picPaths != null) {
					picPaths.add(fileDO);
				} else {
					continue;
				}
			}
		}
		return picPaths;
	}

	/**
	 * 删除图片
	 *
	 * @param imgId        图片id
	 * @param workerId     职工id
	 * @param enterpriseId 企业id
	 * @return java.lang.String
	 * @date 2019/2/13 16:18
	 **/

	@Value(value = "${rate.uploadPath}")

	private String path;


	@GetMapping("/deleteImg")
	@ResponseBody
	public String deleteImg(String imgId, String miluoTaskInfoId, Integer type) {
		//全部照片id
		String pic = null;
		//被删除照片id
		String url = sysFileService.get(imgId).getUrl();
		if (miluoTaskInfoId != null && StringUtils.isNotBlank(miluoTaskInfoId)) {
		MiluoTaskInfo miluoTaskInfo	=miluoTaskInfoService.TaskInfoById(miluoTaskInfoId);
			if (type == 3) {
				pic = miluoTaskInfo.getPhoto();
				pic = pic.replace(imgId + ",", "");
				miluoTaskInfo.setPhoto(pic);
			}
		 miluoTaskInfoService.updateTemplateById(miluoTaskInfo);
		}
		url = path + url.replace("/files/", "");
		url.replaceAll("//", "/");
		sysFileService.remove(imgId);
		File file = new File(url);
		file.delete();
		return "ok";
	}
}
