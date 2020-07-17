package com.rate.system.rate_system.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.rate.system.rate_system.config.BootdoConfig;
import com.rate.system.rate_system.entity.FileDO;
import com.rate.system.rate_system.service.FileService;
import com.rate.system.rate_system.utils.FileType;
import com.rate.system.rate_system.utils.FileUtil;
import com.rate.system.rate_system.utils.IdGen;
import com.rate.system.rate_system.utils.PageUtils;
import com.rate.system.rate_system.utils.Query;
import com.rate.system.rate_system.utils.R;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.swing.filechooser.FileView;

/**
 * 文件上传
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-19 16:02:20
 */
@Controller
@RequestMapping("/common/sysFile")
public class FileController extends BaseController {

	@Autowired
	private FileService sysFileService;

	@Autowired
	private BootdoConfig bootdoConfig;

	/**
	 * 跳转文件页面
	 * @param model 分页信息
	 */
	@GetMapping()
	@RequiresPermissions("common:sysFile:sysFile")
	String sysFile(Model model) {
		Map<String, Object> params = new HashMap<>(16);
		return "module/zy/file/file";
	}

	/**
	 * 文件列表
	 * @param params 分页参数等
	 * @return 文件列表及条数
	 */
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("common:sysFile:sysFile")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<FileDO> sysFileList = sysFileService.list(query);
	/*	model.addAttribute("fileList", sysFileList);*/
		int total = sysFileService.count(query);
		PageUtils pageUtils = new PageUtils(sysFileList, total);
		return pageUtils;
	}
	@GetMapping("/target/{id}")
	public String getFileTarget(Model model, @PathVariable(value="id") String id) {
		FileDO file=sysFileService.get(id);
		return file.getUrl();
	}

	@GetMapping("/add")
	// @RequiresPermissions("common:bComments")
	String add() {
		return "common/sysFile/add";
	}

	@GetMapping("/edit")
	// @RequiresPermissions("common:bComments")
	String edit(String id, Model model) {
		FileDO sysFile = sysFileService.get(id);
		model.addAttribute("sysFile", sysFile);
		return "common/sysFile/edit";
	}

	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("common:info")
	public R info(@PathVariable("id") String id) {
		FileDO sysFile = sysFileService.get(id);
		return R.ok().put("sysFile", sysFile);
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("common:save")
	public R save(FileDO sysFile) {
		try {
			sysFileService.save(sysFile);
			return R.ok();
		} catch (Exception e) {
			return R.error();
		}
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("common:update")
	public R update(@RequestBody FileDO sysFile) {
		sysFileService.update(sysFile);

		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	// @RequiresPermissions("common:remove")
	public R remove(String id, HttpServletRequest request) {
		if ("test".equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		String fileName = bootdoConfig.getUploadPath() + sysFileService.get(id).getUrl().replace("/files/", "");
		if (sysFileService.remove(id) > 0) {
			boolean b = FileUtil.deleteFile(fileName);
			if (!b) {
				return R.error("数据库记录删除成功，文件删除失败");
			}
			return R.ok();
		} else {
			return R.error();
		}
	}

	/**
	 * 批量删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("common:remove")
	public R remove(@RequestParam("ids[]") String[] ids) {
		if ("test".equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		sysFileService.batchRemove(ids);
		return R.ok();
	}

	@ResponseBody
	@PostMapping("/upload")
	R upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		if ("test".equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		String fileTitl = file.getOriginalFilename();
		String fileName = FileUtil.renameToUUID(fileTitl);
		BigDecimal fileSize = new BigDecimal(file.getSize());
		String fileExt =  "." +fileTitl.substring(fileTitl.lastIndexOf(".") + 1);
		FileDO sysFile = new FileDO(IdGen.uuid(),FileType.fileType(fileName),fileExt,"",fileTitl, fileSize,fileName,"/files/"+fileName, new Date());
		try {
			FileUtil.uploadFile(file.getBytes(), bootdoConfig.getUploadPath(), fileName);
		} catch (Exception e) {
			return R.error();
		}
		try {
			sysFileService.save(sysFile);
			return R.ok().put("fileName",sysFile.getUrl()).put("id", sysFile.getId());
		} catch (Exception e) {
			return R.error();
		}
	}

	/**
	 * 下载
	 */
	@GetMapping("/down")
	@ResponseBody
//	@RequiresPermissions("common:down")
	public R down(@RequestParam("id") String id,HttpServletResponse response) {
		if ("test".equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		//获取文件名
		String fileName = sysFileService.get(id).getPathName();
		sysFileService.down(response,fileName,bootdoConfig.getUploadPath());
		return R.ok();
	}

}
