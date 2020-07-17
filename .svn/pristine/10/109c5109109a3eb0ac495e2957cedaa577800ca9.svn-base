package com.rate.web.vidicon.controller;

import com.alibaba.fastjson.JSON;
import com.rate.system.rate_system.controller.BaseController;
import com.rate.system.rate_system.utils.R;
import com.rate.system.rate_system.utils.StringUtils;
import com.rate.web.site.entity.Site;
import com.rate.web.vidicon.entity.Vidicon;
import com.rate.web.vidicon.entity.VidiconOwner;
import com.rate.web.vidicon.service.VidiconOwnerService;
import com.rate.web.vidicon.service.VidiconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Author chenshixue
 * @Date 2020/3/24
 */
@Controller
@RequestMapping("/monitor/video")
public class VideoController extends BaseController {

    private String prefix = "/module/video";

    @Autowired
    private VidiconService vidiconService;

    @Autowired
    private VidiconOwnerService vidiconOwnerService;

    @GetMapping
    public String index() {
        return prefix + "/list";
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<Vidicon> findVidiconList(@RequestParam Map<String, Object> params) {
        String ids = (String) params.get("ids");
        if (StringUtils.isNotBlank(ids)) {
            params.put("ids", Arrays.asList(ids.split(",")));
        }
        List<Vidicon> list = vidiconService.findVidiconList(params);
        return list;
    }

    @RequestMapping("/add")
    public String add(Model model) {
        List<Site> siteList = vidiconService.findSiteListByUserId(getUserId());
        List<VidiconOwner> ownerList = vidiconOwnerService.getAllVidiconOwner();
        model.addAttribute("siteList", siteList);
        model.addAttribute("ownerList", ownerList);
        return prefix + "/add";
    }

    @RequestMapping("/save")
    @ResponseBody
    public R save(Vidicon vidicon) {
        Long id = vidiconService.saveVidicon(vidicon);
        if (id > 0) {
            return R.ok();
        }
        return R.error("操作失败");
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id) {
        List<Site> siteList = vidiconService.findSiteListByUserId(getUserId());
        List<VidiconOwner> ownerList = vidiconOwnerService.getAllVidiconOwner();
        Vidicon vidicon = vidiconService.getVidiconByVidiconId(id);
        model.addAttribute("siteList", siteList);
        model.addAttribute("ownerList", ownerList);
        model.addAttribute("vidicon", vidicon);
        return prefix + "/edit";
    }

    @RequestMapping("/update")
    @ResponseBody
    public R update(Vidicon vidicon) {
        int row = vidiconService.updateVidicon(vidicon);
        if (row > 0) {
            return R.ok();
        }
        return R.error("操作失败");
    }

    @RequestMapping("/remove")
    @ResponseBody
    public R remove(Long id) {
        int row =  vidiconService.remove(id);
        if (row > 0) {
            return R.ok();
        }
        return R.error("操作失败");
    }

    @RequestMapping("/openVideoPage/{type}")
    public String openVideoPage(Model model, @PathVariable("type") int type, Long id) {
        Vidicon vidicon = vidiconService.getVidiconByVidiconId(id);
        model.addAttribute("vidicon", JSON.toJSON(vidicon));
        if (type == 1) {  // 直播地址
            return prefix + "/playPage";
        } else {  // 监控地址
            return prefix + "/monitorPage";
        }
    }
}
