package com.rate.web.statistic.controller;

import com.rate.system.rate_system.utils.StringUtils;
import com.rate.web.statistic.service.WaterWarnService;
import com.rate.web.statistic.vo.WaterWarn;
import com.rate.web.utils.GetLastYear;
import com.rate.web.watercalcute.service.WaterCalauteSortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: rate_miluo_parent
 * @ClassName: WaterWarn
 * @description: 水质预警
 * @author: chenh
 * @create: 2020-07-15 17:36
 **/
@Controller
@RequestMapping("/water/warn")
public class WaterWarnController {

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
    public List<WaterWarn> getMarker(@RequestParam("dateTime")String dateTime) {
        List<WaterWarn> waterLists=new ArrayList<WaterWarn>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
         Date date = new Date();
         Calendar cal = Calendar.getInstance();
         cal.setTime(date);
         cal.add(Calendar.MONTH, -1);
        if(StringUtils.isBlank(dateTime)){
            dateTime=sdf.format( cal.getTime());
        }
        List<WaterWarn> waterWarns=waterWarnService.findAllWaterWarn(dateTime);
        Map<String, List<WaterWarn>> collectS1 = waterWarns.stream().collect(Collectors.groupingBy(WaterWarn::getSiteName));
        //遍历分组
        for (Map.Entry<String, List<WaterWarn>> entryUser : collectS1.entrySet()) {
            String key = entryUser.getKey();
            List<WaterWarn> entryUserList = entryUser.getValue();
            if(entryUserList.size()==2){
                entryUserList.get(0).setWaterSort(entryUserList.get(0).getWaterSort()-entryUserList.get(1).getWaterSort());
                waterLists.add(entryUserList.get(0));
            }
        }
        return waterWarns;
    }
}
