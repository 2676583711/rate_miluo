package com.rate.web.graphic.controller;

import com.alibaba.fastjson.JSONObject;
import com.rate.system.rate_system.controller.BaseController;
import com.rate.system.rate_system.utils.DateUtils;
import com.rate.system.rate_system.utils.StringUtils;
import com.rate.web.graphic.service.GraphicPolluteService;
import com.rate.web.statement.util.TableSelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @author shuzhangyao
 * @date 2019/5/31 16:51
 **/
@Controller
@RequestMapping("/graphic/pollute")
public class GraphicPolluteController extends BaseController {

    private String prefix = "graphic/pollute/";

    @Autowired
    private GraphicPolluteService graphicPolluteService;




    @RequestMapping("/index")
    public String index(){
        return "graphic/pollute/index";
    }
    @GetMapping("/minute")
    public String minutePage(Model model){
        return prefix + "minute/minutePage";
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
     * 分钟数据
     * @param params
     * @Return  com.alibaba.fastjson.JSONObject
     * @Author  chenshixue
     * @Date    2019/9/26 17:44
     */
    @RequestMapping("/minute/list")
    @ResponseBody
    public JSONObject minuteList(@RequestParam Map<String, Object> params) {
        List<String> tableList = graphicPolluteService.findTableList();
        String dateRange = (String) params.get("dateRange");
        String siteCodes = (String) params.get("siteCodes");
        String[] date = dateRange.split(" ~ ");
        params.put("startTime", date[0]);
        params.put("endTime", date[1]);
        List<String> signList = TableSelUtil.getTableSignListOfMonth(date[0], date[1], DateUtils.DATE_TIME_PATTERN);
        List<String> list = new ArrayList<>();
        for (String sign : signList) {
            String tableName = "miluo_pollutant_realtime_" + sign;
            for (String table : tableList) {
                if (tableName.equals(table)) {
                    list.add(tableName);
                }
            }
        }
        params.put("tableNames", list);
        params.put("siteIds", Arrays.asList(siteCodes.split(",")));
        return graphicPolluteService.findMinuteData(params);
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
    public JSONObject hourList(@RequestParam Map<String, Object> params) {
        String dateRange = (String) params.get("dateRange");
        String siteCodes = (String) params.get("siteCodes");
        Map<String, Object> sqlParam = new HashMap<>();
        //时间范围
        if (StringUtils.isNotBlank(dateRange)){
            String[] date = dateRange.split(" ~ ");
            sqlParam.put("startDate", DateUtils.format(date[0], DateUtils.DATE_TIME_PATTERN3));
            sqlParam.put("endDate", DateUtils.format(date[1], DateUtils.DATE_TIME_PATTERN3));
        }
        //选中站点编号
        sqlParam.put("siteIds", Arrays.asList(siteCodes.split(",")));
        return graphicPolluteService.findHourData(sqlParam);
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
        return graphicPolluteService.findDayData(params);
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
            res.put("data", graphicPolluteService.findChangeData(params));
        }
        return res;
    }

}
