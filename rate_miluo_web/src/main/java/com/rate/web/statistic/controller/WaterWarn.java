package com.rate.web.statistic.controller;

import com.rate.system.rate_system.utils.PageUtils;
import com.rate.web.desktop.entity.MapMarker;
import com.rate.web.statistic.service.WaterWarnService;
import com.rate.web.watercalcute.service.WaterCalauteSortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @program: rate_miluo_parent
 * @ClassName: WaterWarn
 * @description: 水质预警
 * @author: chenh
 * @create: 2020-07-15 17:36
 **/
@Controller
@RequestMapping("/water/warn")
public class WaterWarn {

    @Autowired
    private WaterWarnService waterWarnService;
    @Autowired
    private WaterCalauteSortService waterCalauteSortService;

    /** @author chenh
     * @Description 预警跳转首页
     * @date 2020/7/15 17:42
     * @param
     * @return java.lang.String
     */
    @RequestMapping("")
    public String index() {
        System.out.print(waterCalauteSortService.waterSort("7",0.2));
       return "/statistic/waterwarn";
    }

    /** @author chenh
     * @Description 得到所有得站点预警等级
     * @date 2020/7/16 11:11
     * @param
     * @return List<>
     */
    @ResponseBody
    @GetMapping("/getMarker")
    public List<MapMarker> getMarker(@RequestParam("dateTime")String dateTime) {
        return waterWarnService.findAllWaterWarn(dateTime);
    }
}
