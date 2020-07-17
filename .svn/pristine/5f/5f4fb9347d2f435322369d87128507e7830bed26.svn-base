package com.rate.web.graphic.controller;

import com.alibaba.fastjson.JSONObject;
import com.rate.system.rate_system.controller.BaseController;
import com.rate.system.rate_system.utils.DateUtils;
import com.rate.system.rate_system.utils.StringUtils;
import com.rate.web.graphic.service.GraphicPolluteWaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shuzhangyao
 * @date 2019/5/31 16:51
 **/
@Controller
@RequestMapping("/graphic/polluteWater")
public class GraphicPolluteWaterController extends BaseController {

    private String prefix = "graphic/polluteWater/";

    @Autowired
    private GraphicPolluteWaterService graphicPolluteWaterService;




    @RequestMapping("/index")
    public String index(){
        return "graphic/polluteWater/index";
    }

    @GetMapping("/hour")
    public String hourPage(Model model){
        return prefix + "hour/hourPage";
    }
    @GetMapping("/day")
    public String dayPage(Model model){
        return prefix + "day/dayPage";
    }
    @GetMapping("/change")
    public String changePage(Model model){
        return prefix + "change/changePage";
    }

    /**
     * 小时数据
     * @param params [dateRange,stationCode] 时间范围,站点编号 逗号分隔
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @author shuzhangyao
     * @date 2019/5/31 11:54
     **/
    @ResponseBody
    @GetMapping("/hour/list")
    public Map<String, Object> hourList(@RequestParam Map<String, Object> params) {
        String dateRange = (String) params.get("dateRange");
        String stationCode = (String) params.get("siteCode");
        Map<String, Object> sqlParam = new HashMap<>();
        //时间范围
        if (StringUtils.isNotBlank(dateRange)){
            String[] date = dateRange.split(" ~ ");
            sqlParam.put("startDate", DateUtils.format(date[0], DateUtils.DATE_TIME_PATTERN3));
            sqlParam.put("endDate", DateUtils.format(date[1], DateUtils.DATE_TIME_PATTERN3));
        }
        Map<String, Object> result = new HashMap<>();
        //选中站点编号
        sqlParam.put("siteIds", Arrays.asList(stationCode.split(",")));
        result.put("data", graphicPolluteWaterService.findHourData(sqlParam));
        return result;
    }
    /**
     * 获取日均数据
     * @param params 起始时间 站点编号
     * @return java.util.List<com.rate.grid.web.statement.entity.DailyStatement>
     * @author shuzhangyao
     * @date 2019/3/13 11:24
     **/
    @ResponseBody
    @GetMapping("/day/list")
    public JSONObject dayList(@RequestParam Map<String, Object> params) {
        String dateRange = (String) params.get("dateRange");
        if (StringUtils.isNotBlank(dateRange)){
            String[] date = dateRange.split(" ~ ");
            params.put("startDate", DateUtils.format(date[0], DateUtils.DATE_PATTERN));
            params.put("endDate", DateUtils.format(date[1], DateUtils.DATE_PATTERN));
        }
        //选中站点编号
        String siteCodes = (String) params.get("siteCodes");
        params.put("siteIds", Arrays.asList(siteCodes.split(",")));
        JSONObject res = new JSONObject();
        res.put("data", graphicPolluteWaterService.findDayData(params));
        return res;
    }
    @GetMapping("/change/list")
    @ResponseBody
    public JSONObject changeMicroDataList(@RequestParam Map<String, Object> params){
        JSONObject res = new JSONObject();
        String day = (String) params.get("day");
        //时间范围
        String startDate =  day+ " 00:00:00";
        String endDate = day
                + " 23:59:59";
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        //选中站点编号
        String siteCodes = (String) params.get("siteCodes");
        if(StringUtils.isNotBlank(siteCodes)){
            params.put("siteIds", Arrays.asList(siteCodes.split(",")));
            res.put("data", graphicPolluteWaterService.findChangeData(params));
        }
        return res;
    }

}
