package com.rate.web.connected.offdata.controller;

import com.rate.system.rate_system.controller.BaseController;
import com.rate.system.rate_system.service.UserService;
import com.rate.system.rate_system.utils.excel.ExportExcel;
import com.rate.web.connected.offdata.entity.OffDataVO;
import com.rate.web.connected.offdata.entity.OffDataVOExp;
import com.rate.web.connected.offdata.service.OffDataService;
import com.rate.web.site.enums.RoleConstant;
import com.rate.web.statement.service.PolluteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Author chenshixue
 * @Date 2019/12/6
 */
@Controller
@RequestMapping("/offline/data")
public class OffDataController2 extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private OffDataService offDataService;
    @Autowired
    private PolluteService polluteService;

    /**
     * 空气离线数据统计
     * @param params
     * @Return  java.util.List<com.rate.web.connected.offdata.entity.OffDataVO>
     * @Author  chenshixue
     * @Date    2019/12/6 21:47
     */
    @RequestMapping("/airList")
    @ResponseBody
    public List<OffDataVO> getAirOffList(@RequestParam Map<String, Object> params) {
        // 按指定站点查询
        String siteCodeList = (String) params.get("siteCodes");
        if (siteCodeList==null || siteCodeList.equals("undefined") || siteCodeList=="") {
            List<String> siteIds;
            if (Arrays.asList(RoleConstant.ROLEIDS).contains(getRoleId())) {
                siteIds = userService.getSitePowerList(getUserId());
            } else {
                siteIds = userService.getAllSiteId();
            }
            params.put("siteIds", siteIds);
        } else {
            params.put("siteIds", Arrays.asList(siteCodeList.split(",")));
        }
        List<String> equmentIds = polluteService.findEqumentIds((List<String>) params.get("siteIds"));
        params.put("equmentIds", equmentIds);
        List<OffDataVO> list = offDataService.getAirOffList(params);
        return list;
    }

    /*
     * 污染源离线数据统计
     * @param params
     * @Return  java.util.List<com.rate.web.connected.offdata.entity.OffDataVO>
     * @Author  chenshixue
     * @Date    2019/12/6 21:53
     */
    @RequestMapping("/polluteList")
    @ResponseBody
    public List<OffDataVO> getPolluteOffList(@RequestParam Map<String, Object> params) {
        // 按指定站点查询
        String siteCodeList = (String) params.get("siteCodes");
        if (siteCodeList==null || siteCodeList.equals("undefined") || siteCodeList=="") {
            List<String> siteIds;
            if (Arrays.asList(RoleConstant.ROLEIDS).contains(getRoleId())) {
                siteIds = userService.getSitePowerList(getUserId());
            } else {
                siteIds = userService.getAllSiteId();
            }
            params.put("siteIds", siteIds);
        } else {
            params.put("siteIds", Arrays.asList(siteCodeList.split(",")));
        }
        List<String> equmentIds = polluteService.findEqumentIds((List<String>) params.get("siteIds"));
        params.put("equmentIds", String.join(",", equmentIds));
        List<OffDataVO> list = offDataService.getPolluteOffList(params);
        return list;
    }

    /**
     * 水质站离线数据统计
     * @param params
     * @Return  java.util.List<com.rate.web.connected.offdata.entity.OffDataVO>
     * @Author  chenshixue
     * @Date    2019/12/6 23:38
     */
    @RequestMapping("/waterPlantList")
    @ResponseBody
    public List<OffDataVO> getWaterPlantOffList(@RequestParam Map<String, Object> params) {
        // 按指定站点查询
        String siteCodeList = (String) params.get("siteCodes");
        if (siteCodeList==null || siteCodeList.equals("undefined") || siteCodeList=="") {
            List<String> siteIds;
            if (Arrays.asList(RoleConstant.ROLEIDS).contains(getRoleId())) {
                siteIds = userService.getSitePowerList(getUserId());
            } else {
                siteIds = userService.getAllSiteId();
            }
            params.put("siteIds", siteIds);
        } else {
            params.put("siteIds", Arrays.asList(siteCodeList.split(",")));
        }
        List<String> equmentIds = polluteService.findEqumentIds((List<String>) params.get("siteIds"));
        params.put("equmentIds", equmentIds);
        List<OffDataVO> list = offDataService.getWaterPlantOffList(params);
        return list;
    }

    @RequestMapping("/waterAuto")
    @ResponseBody
    public List<OffDataVO> getWaterAutoOffList(@RequestParam Map<String, Object> params) {
        // 按指定站点查询
        String siteCodeList = (String) params.get("siteCodes");
        if (siteCodeList==null || siteCodeList.equals("undefined") || siteCodeList=="") {
            List<String> siteIds;
            if (Arrays.asList(RoleConstant.ROLEIDS).contains(getRoleId())) {
                siteIds = userService.getSitePowerList(getUserId());
            } else {
                siteIds = userService.getAllSiteId();
            }
            params.put("siteIds", siteIds);
        } else {
            params.put("siteIds", Arrays.asList(siteCodeList.split(",")));
        }
        List<String> equmentIds = polluteService.findEqumentIds((List<String>) params.get("siteIds"));
        params.put("equmentIds", equmentIds);
        List<OffDataVO> list = offDataService.getWaterAutoOffList(params);
        return list;
    }

    @RequestMapping("/offExp")
    @ResponseBody
    public void getExp(@RequestParam Map<String, Object> params, HttpServletResponse response) {
        String beginTime = (String) params.get("beginTime");
        String endTime = (String) params.get("endTime");
        String date = beginTime + "至" + endTime;
        String str = "";
        String index = (String) params.get("index");
        List<OffDataVO> list;
        if ("0".equals(index)){
            list = getAirOffList(params);
            str = "空气质量";
        } else if ("2".equals(index)) {
            list = getPolluteOffList(params);
            str = "涉气污染源";
        } else if ("3".equals(index)) {
            list = getWaterPlantOffList(params);
            str = "涉水污染源";
        }else {
            list = getWaterAutoOffList(params);
            str = "水质自动站";
        }
        for (OffDataVO offDataVO : list) {
            if (offDataVO.getStatus() != null && offDataVO.getStatus() == 0) {
                offDataVO.setStatuss("在线");
            }else {
                offDataVO.setStatuss("离线");
            }
            if (offDataVO.getOffMaxTime() != null) {
                String date1 = getDate(offDataVO.getOffMaxTime());
                offDataVO.setOffMaxTimes(date1);
            }
            if (offDataVO.getOffTotalTime() != null) {
                String date1 = getDate(offDataVO.getOffTotalTime());
                offDataVO.setOffTotalTimes(date1);
            }
        }
        try {
            if ("3".equals(index)) {
                new ExportExcel(date + str + "离线数据统计", OffDataVOExp.class, 2).setDataList(list).write(response, "离线数据统计.xlsx").dispose();
            } else {
                new ExportExcel(date+str+"离线数据统计", OffDataVO.class, 2).setDataList(list).write(response, "离线数据统计.xlsx").dispose();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getDate(Long data) {
        String str = null;
        if (data != null) {
            if (data == 0) {
                str = "0";
            } else if (data < 60000) {
                str = data / 1000 + "秒";
            } else if (data <= 3600000) {
                Long minute = data / 60000;
                str = minute + "分钟";
            } else {
                Long hour = data / 3600000;
                Long minute = (data - hour * 3600000) / 60000;
                str = hour + "小时" + (minute==0?"":(" "+minute) + "分钟");
            }
        }
        return str;
    }
}
