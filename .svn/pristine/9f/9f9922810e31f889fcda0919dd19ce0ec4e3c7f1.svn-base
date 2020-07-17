package com.rate.web.vidicon.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import org.beetl.ext.fn.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rate.system.rate_system.entity.FileDO;
import com.rate.system.rate_system.service.FileService;
import com.rate.system.rate_system.utils.IdGen;
import com.rate.system.rate_system.utils.R;
import com.rate.web.vidicon.entity.Vidicon;
import com.rate.web.vidicon.entity.VidiconFile;
import com.rate.web.vidicon.entity.VidiconOwner;
import com.rate.web.vidicon.service.VidiconFileService;
import com.rate.web.vidicon.service.VidiconOwnerService;
import com.rate.web.vidicon.service.VidiconService;

/**
 * @ClassName: VidiconController
 * @Description: 摄像机
 * @author jiangya
 * @date 2019年6月1日 下午1:26:04
 *
 */
@Controller
@RequestMapping("/vidicon")
public class VidiconController {
	private String prefix = "/vidicon";
	@Autowired
	VidiconService vidiconService;
	@Autowired
	FileService fileService;
	@Value(value = "${rate.uploadPath}") // 读取配置文件中的文件上传路径
	private String path;
	@Autowired
	VidiconOwnerService vidiconOwnerService;
	@Autowired
	VidiconFileService vidiconFileService;

	/**
	 * vidiconShow: 站点摄像机列表<br/>
	 * 
	 * @author jiangya
	 * @param model
	 * @param id
	 * @param curPage
	 * @return
	 */
	@GetMapping("/showVidicon/{siteId}/{curPage}")
	public String showVidicon(Model model, @PathVariable("siteId") Integer siteId,
			@PathVariable("curPage") String curPage) {
		// 根据站点ID查询摄像机列表
		List<Vidicon> siteVidicons = vidiconService.getVidiconsBySiteId(siteId);
		model.addAttribute("curPage", curPage);
		model.addAttribute("siteId", siteId);
		model.addAttribute("siteVidicons", siteVidicons);
		return prefix + "/showVidicon";
	}

	/**
	 * showVidicon:根据站点和摄像头名称搜索 <br/>
	 * 
	 * @author jiangya
	 * @param model
	 * @param siteId
	 * @param vidiconName
	 * @param curPage
	 * @return
	 */
	@GetMapping("/showVidicon/{siteId}")
	public String showVidicon(Model model, @PathVariable("siteId") Integer siteId,
			@RequestParam("vidiconName") String vidiconName, @RequestParam("curPage") String curPage) {
		// 根据站点ID查询摄像机列表
		List<Vidicon> siteVidicons = vidiconService.getVidiconsBySiteIdAndName(siteId, vidiconName);
		model.addAttribute("curPage", curPage);
		model.addAttribute("siteId", siteId);
		model.addAttribute("siteVidicons", siteVidicons);
		return prefix + "/showVidicon";
	}

	/**
	 * addVidicon: 添加摄像头<br/>
	 *
	 * @author jiangya
	 * @param model
	 * @param siteId
	 * @return
	 */
	@GetMapping("/addVidicon")
	public String addVidicon(Model model, String siteId) {
		// 全部摄像头所有人
		List<VidiconOwner> vidiconOwners = vidiconOwnerService.getAllVidiconOwner();
		model.addAttribute("siteId", siteId);
		model.addAttribute("vidiconOwners", vidiconOwners);
		return prefix + "/addVidicon";
	}

	/**
	 * 编辑摄像头
	 * @param model
	 * @param siteId
	 * @Return  java.lang.String
	 * @Author  chenshixue
	 * @Date    2020/3/6 14:02
	 */
	@GetMapping("/editVidicon")
	public String editVidicon(Model model, String vidiconId) {
		// 全部摄像头所有人
		List<VidiconOwner> vidiconOwners = vidiconOwnerService.getAllVidiconOwner();
		Vidicon vidicon = vidiconService.getVidiconByVidiconId(Long.parseLong(vidiconId));

		model.addAttribute("vidiconOwners", vidiconOwners);
		model.addAttribute("vidicon", vidicon);
		return prefix + "/editVidicon";
	}

	/**
	 * saveDevice: 保存摄像头<br/>
	 * 
	 * @author jiangya
	 * @param siteEquipment
	 * @return
	 */
	@PostMapping("/saveVidicon")
	@ResponseBody
	public R saveVidicon(Vidicon vidicon) {
		try {
			Long i = vidiconService.saveVidicon(vidicon);
			if (i > 0) {
				return R.ok();
			}
			return R.error();
		} catch (Exception e) {
			return R.error();
		}
	}

	@PostMapping("/updateVidicon")
	@ResponseBody
	public R updateVidicon(Vidicon vidicon) {
		try {
			int i = vidiconService.updateVidicon(vidicon);
			if (i > 0) {
				return R.ok();
			}
			return R.error();
		} catch (Exception e) {
			return R.error();
		}
	}

	/**
	 * removeVidicon: 删除<br/>
	 * 
	 * @author jiangya
	 * @param id
	 * @return
	 */
	@PostMapping("/removeVidicon")
	@ResponseBody
	public R removeVidicon(@RequestParam("vidiconId") Long id) {
		try {
			int i = vidiconService.remove(id);
			if (i > 0) {
				return R.ok();
			}
			return R.error();
		} catch (Exception e) {
			return R.error();
		}
	}

	/**
	 * vidiconVideo:查看实时录像 <br/>
	 * 
	 * @author jiangya
	 * @param model
	 * @param siteId
	 * @return
	 */
	@GetMapping("/vidiconVideo")
	public String vidiconVideo(Model model, Long vidiconId) {
		model.addAttribute("vidiconId", vidiconId);
		return prefix + "/vidiconVideo";
	}

	@GetMapping("/vidiconRecVideo")
	public String vidiconRecVideo(Model model, Long vidiconId) {
		Vidicon vidicon = vidiconService.getVidiconByVidiconId(vidiconId);
		model.addAttribute("vidicon", JSON.toJSONString(vidicon));
		return prefix + "/vidiconRecVideo";
	}

	/**
	 * vidiconVideo: 实时录像数据<br/>
	 * 
	 * @author jiangya
	 * @param vidiconId
	 * @return
	 */
	@GetMapping("/vidiconVideoData")
	@ResponseBody
	public Vidicon vidiconVideoData(Long vidiconId) {
		Vidicon vidicon = vidiconService.getVidiconByVidiconId(vidiconId);
		return vidicon;
	}

	/**
	 * uploadPic: 摄像头截图上传<br/>
	 * 
	 * @author jiangya
	 * @param picUrl
	 * @return
	 */
	@PostMapping("/uploadPic")
	@ResponseBody
	@Transactional
	public R uploadPic(String picUrl, Long vidiconId) {
		try {
			// 构造URL
			URL url = new URL(picUrl);
			URLConnection con = url.openConnection();
			// 设置请求超时为5s
			con.setConnectTimeout(5 * 1000);
			// 输入流
			InputStream is = con.getInputStream();
			// 1K的数据缓冲
			byte[] bs = new byte[1024];
			// 读取到的数据长度
			int len;
			int size = 0;
			// 输出的文件流
			Date date = new Date();
			SimpleDateFormat formatter1 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyMMdd");
			File sf = new File(path + File.separator + formatter2.format(date));// 防止单个文件夹图片过多
			if (!sf.exists()) {
				sf.mkdirs();
			}
			OutputStream os = new FileOutputStream(sf.getPath() + File.separator + formatter1.format(date) + ".jpg");
			// 开始读取
			while ((len = is.read(bs)) != -1) {
				os.write(bs, 0, len);
				size += len;
			}
			// 完毕，关闭所有链接
			os.close();
			is.close();
			// 保存文件信息
			FileDO fileDO = new FileDO();
			String fileId = IdGen.uuid();
			fileDO.setId(fileId);
			fileDO.setCreateDate(date);
			fileDO.setFileext(".jpg");
			fileDO.setUrl("/files/" + formatter2.format(date) + File.separator + formatter1.format(date) + ".jpg");
			fileDO.setFilename("摄像头截图" + formatter1.format(date) + ".jpg");
			fileDO.setType(11);
			fileDO.setFilesize(new BigDecimal(size));
			fileService.save(fileDO);
			// 保存摄像头和文件对应关系
			VidiconFile vidiconFile = new VidiconFile();
			vidiconFile.setId(IdGen.uuid());
			vidiconFile.setVidiconId(vidiconId);
			vidiconFile.setFileId(fileId);
			vidiconFile.setPrintscreenDate(date);
			vidiconFileService.save(vidiconFile);
			return R.ok();
		} catch (IOException e) {
			e.printStackTrace();
			return R.error();
		}
	}

	/**
	 * vidiconNewPrintscreen:获取摄像头最新一张截图 <br/>
	 * 
	 * @author jiangya
	 * @param vidiconId
	 * @return
	 */
	@GetMapping("/vidiconNewPrintscreen")
	@ResponseBody
	public FileDO vidiconNewPrintscreen(Long vidiconId) {
		// 最新一张截图
		VidiconFile vidiconFile = vidiconFileService.getNewPrintscreenByVidiconId(vidiconId);
		if (vidiconFile != null) {
			return fileService.get(vidiconFile.getFileId());
		}
		return null;
	}

}
