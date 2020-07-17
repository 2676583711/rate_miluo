package com.rate.web.statement.controller;

import com.rate.system.rate_system.controller.BaseController;
import com.rate.system.rate_system.service.UserService;
import com.rate.system.rate_system.utils.excel.ExportExcel;
import com.rate.web.statement.entity.PolluteRealtime;
import com.rate.web.statement.service.PolluteService;
import com.rate.web.statement.util.TableSelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Author chenshixue
 * @Date 2019/8/30
 */
@Controller
@RequestMapping("/statement/pollute")
public class PolluteController extends BaseController {

    @Autowired
    private PolluteService polluteService;

    @Autowired
    private UserService userService;

    private static List<PolluteRealtime> listExp;

    @RequestMapping("/minuteList")
    @ResponseBody
    public List<PolluteRealtime> minuteList(@RequestParam Map<String, Object> params, Long pageSize, Long pageNumber){

        String beginTimeStr = (String) params.get("beginTime");
        String endTimeStr = (String) params.get("endTime");
        String dataType = (String) params.get("dataType");
        // 获取所有表标识
        List<String> signList = TableSelUtil.getTableSignListOfMonth(beginTimeStr, endTimeStr, "yyyy-MM-dd HH:mm");
        String siteCode = (String)params.get("siteCodes");
        String[] siteCodes = null;
        if(siteCode != null && !siteCode.equals("")){
            siteCodes = siteCode.split(",");
        } else {
            List<String> slist = userService.getSitePowerList(getUserId());
            siteCodes = slist.toArray(new String[slist.size()]);
        }
        // 根据站点id查询设备id，做这一步只是为了加快sql查询速度
        List<String> equmentIds = polluteService.findEqumentIds(Arrays.asList(siteCodes));
        siteCode = String.join(",", equmentIds);  // 这是设备id
        List<String> sqlList = new ArrayList<>();
        for (int i=0; i<signList.size(); i++) {
            String sql = "SELECT t.*, s.`name` siteName, v.`name` equipmentName FROM " +
                    " (SELECT * FROM miluo_pollutant_realtime_" + signList.get(i) +
                    " WHERE recording_time>='"+beginTimeStr+"' AND recording_time<='"+endTimeStr+"'" +
                    " AND equment_id IN ("+siteCode+")) t" +
                    " LEFT JOIN miluo_video v ON v.equment_id = t.equment_id" +
                    " LEFT JOIN miluo_site s ON s.`id` = v.`site_id`" +
                    " WHERE t.data_type = "+dataType;
            sqlList.add(sql);
        }
        // 用 UNION 拼接多张表查询
        String sqlAll = String.join(" UNION ", sqlList);
        params.put("sqlAll", sqlAll);

        List<PolluteRealtime> polluteRealtimes = polluteService.minuteList2(params);
        listExp = polluteRealtimes;
        return polluteRealtimes;
    }

    @RequestMapping("/hourList")
    @ResponseBody
    public List<PolluteRealtime> hourList(@RequestParam Map<String, Object> params, Long pageSize, Long pageNumber){
        String siteCode = (String)params.get("siteCodes");
        String[] siteCodes = null;
        if(siteCode != null && !siteCode.equals("")){
            siteCodes = siteCode.split(",");
        } else {
            List<String> slist = userService.getSitePowerList(getUserId());
            siteCodes = slist.toArray(new String[slist.size()]);
        }
        // 根据站点id查询设备id，做这一步只是为了加快sql查询速度
        List<String> equmentIds = polluteService.findEqumentIds(Arrays.asList(siteCodes));
        params.put("equmentIds", equmentIds);

        List<PolluteRealtime> polluteRealtimes = polluteService.hourList2(params);
        listExp = polluteRealtimes;
        return polluteRealtimes;
    }

    @RequestMapping("/dayList")
    @ResponseBody
    public List<PolluteRealtime> dayList(@RequestParam Map<String, Object> params, Long pageSize, Long pageNumber){
        String siteCode = (String)params.get("siteCodes");
        String[] siteCodes = null;
        if(siteCode != null && !siteCode.equals("")){
            siteCodes = siteCode.split(",");
        } else {
            List<String> slist = userService.getSitePowerList(getUserId());
            siteCodes = slist.toArray(new String[slist.size()]);
        }
        // 根据站点id查询设备id，做这一步只是为了加快sql查询速度
        List<String> equmentIds = polluteService.findEqumentIds(Arrays.asList(siteCodes));
        params.put("equmentIds", equmentIds);

        List<PolluteRealtime> polluteRealtimes = polluteService.dayList2(params);
        listExp = polluteRealtimes;
        return polluteRealtimes;
    }

    @RequestMapping("/exportExcel")
    @ResponseBody
    public void getExp(@RequestParam Map<String, Object> params,HttpServletResponse response) {
        String beginTime = (String) params.get("beginTime");
        String endTime = (String) params.get("endTime");
        String date = beginTime + "至" + endTime;
        try {
            new ExportExcel(date+"涉气污染源", PolluteRealtime.class, 2).setDataList(listExp).write(response, "涉气污染源.xlsx")
                    .dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
