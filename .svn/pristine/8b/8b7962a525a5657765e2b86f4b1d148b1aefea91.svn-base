package com.rate.web.site.controller;

import com.alibaba.fastjson.JSONObject;
import com.rate.web.site.entity.FactorAndParam;
import com.rate.web.site.service.ParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author chenshixue
 * @Date 2019/12/9
 */
@Controller
@RequestMapping("/site/param/configuration")
public class ParamController {

    private String prefix = "/module/param";

    private final ParamService paramService;

    @Autowired
    public ParamController(ParamService paramService) {
        this.paramService = paramService;
    }

    @GetMapping()
    public String goPage() {
        return prefix + "/index";
    }

    @GetMapping("/addParam/{siteId}")
    public String addPage(@PathVariable String siteId, Model model) {
        // 根据站点id查询该站点下所有设备
        List<FactorAndParam> videoList = paramService.findVideoInfo(Integer.parseInt(siteId));
        if (videoList.size() > 0) {
            model.addAttribute("videoList", videoList);
        }
        return prefix + "/add";
    }

    @RequestMapping("/getListBySiteId")
    @ResponseBody
    public List<FactorAndParam> findParamList(@RequestParam Map<String, Object> params) {
        return paramService.findParamList(params);
    }

    @GetMapping("/inputFactorList")
    @ResponseBody
    public JSONObject findInputFactorList(@RequestParam Map<String, Object> params) {
        JSONObject obj = new JSONObject();
        obj.put("data", paramService.findInputFactorList(params));
        return obj;
    }

}
