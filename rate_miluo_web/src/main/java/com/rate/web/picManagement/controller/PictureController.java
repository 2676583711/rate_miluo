package com.rate.web.picManagement.controller;

import com.rate.system.rate_system.entity.FileDO;
import com.rate.system.rate_system.service.FileService;
import com.rate.system.rate_system.utils.FileUtil;
import com.rate.system.rate_system.utils.PageUtils;
import com.rate.system.rate_system.utils.R;
import com.rate.web.picManagement.controller.vo.VidiconFileVO;
import com.rate.web.vidicon.service.VidiconFileService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenshixue
 * @date 2019/7/8 23
 */
@Controller
@RequestMapping("/module/pic")
public class PictureController {

    private String prefix = "pic";
    @Autowired
    private FileService fileService;
    @Autowired
    private VidiconFileService vidiconFileService;
    @Value("${rate.uploadPath}")
    private String uploadPath;

    @GetMapping
    public String show(Model model){
        Map<String, Object> map = new HashMap<>();
        map.put("type", 11);
        List<FileDO> pics = fileService.list(map);
        model.addAttribute("total", pics.size());
        model.addAttribute("pics", pics);
        return prefix + "/show";
    }

    @RequiresPermissions("module:pic:list")
    @GetMapping("/list")
    @ResponseBody
    public PageUtils list(@RequestParam Map<String, Object> params, Integer pageSize, Integer pageNumber){
        if(params.get("dateCondition") != null && params.get("dateCondition") != ""){
            String[] dateArr = params.get("dateCondition").toString().split(" - ");
            params.put("startDate", dateArr[0]);
            params.put("endDate", dateArr[1]);
        }
        if(params.get("siteCodes") != null && params.get("siteCodes") != ""){
            String[] siteCodes = params.get("siteCodes").toString().split(",");
            params.put("siteCodes", Arrays.asList(siteCodes));
        }
        params.put("type", 11);
        params.remove("dateCondition");
        PageQuery<VidiconFileVO> paras = new PageQuery<>(pageNumber, pageSize, params);
        PageQuery<VidiconFileVO> pages = vidiconFileService.getScreenPicsByCondition(paras);
        PageUtils pageUtils = new PageUtils(pages.getList(), pages.getTotalRow());
        return pageUtils;
    }

    /**
     * 批量删除截图
     * @param ids 文件表ID
     * @return
     */
    @RequiresPermissions("module:pic:remove")
    @PostMapping("/remove")
    @ResponseBody
    public R remove(@RequestParam(value = "ids[]") String[] ids){
        try {
            //删除服务器上的图片
            List<FileDO> fileDOS =  fileService.getListByIds(ids);
            for(FileDO fileDO : fileDOS){
                // 从文件表中获取url，使用工具类删除
                String path = uploadPath + fileDO.getUrl().replace("/files", "");
                FileUtil.deleteFile(path);
            }
            // 删除文件表中的记录
            fileService.deleteByIds(ids);
            // 删除视频截图表中记录
            vidiconFileService.removeByFileIds(ids);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    }
}
