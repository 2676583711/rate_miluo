package com.rate.web.alarm.pollutantdata.controller;


import com.alibaba.fastjson.JSONObject;
import com.rate.system.rate_system.constant.RoleConstant;
import com.rate.system.rate_system.controller.BaseController;
import com.rate.system.rate_system.dao.UserDao;
import com.rate.system.rate_system.entity.User;
import com.rate.system.rate_system.service.DictService;
import com.rate.system.rate_system.service.UserService;
import com.rate.system.rate_system.utils.DateUtils;
import com.rate.system.rate_system.utils.PageUtils;
import com.rate.system.rate_system.utils.R;
import com.rate.system.rate_system.utils.excel.ExportExcel;
import com.rate.web.alarm.entity.AlarmEntity;
import com.rate.web.alarm.entity.AlarmEntityEpx;
import com.rate.web.alarm.monitor.dao.ExceedDao;
import com.rate.web.alarm.monitor.service.ExceedService;
import com.rate.web.alarm.pollutantdata.entity.PolluteFactory;
import com.rate.web.alarm.pollutantdata.service.PolluteFactoryService;
import com.rate.web.statement.entity.PolluteRealtime;
import com.rate.web.statement.entity.PolluteRealtimeExp;
import com.rate.web.task.service.MiluoTaskInfoService;
import com.rate.web.vidicon.entity.Vidicon;
import com.rate.web.vidicon.service.VidiconService;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/index")
public class PollutantDataController extends BaseController {

    @Autowired
    private ExceedService airExceedService;

    @Autowired
    private PolluteFactoryService polluteFactoryService;

    @Autowired
    private ExceedDao exceedDao;

    @Autowired
    private UserService userService;

    @Autowired
    private MiluoTaskInfoService miluoTaskInfoService;

    @Autowired
    private VidiconService vidiconService;

    @Autowired
    private DictService dictService;

    @Autowired
    private UserDao userDao;

    private String prefix = "module/alarm";

    @RequestMapping("/pollute")
    public String getAirHourSmallStatement(Model model, String siteId) {
        model.addAttribute("siteId", siteId);
        model.addAttribute("roleId", getRoleId());
        return "module/alarm/pollutantdata/show";
    }

    @RequestMapping("/polluteSituation")
    public String getPolluteSituation(Model model, String siteId) {
        model.addAttribute("siteId", siteId);
        model.addAttribute("roleId", getRoleId());
        return "module/alarm/pollutantSituation/show";
    }

    @RequestMapping("/polluteFactory")
    @ResponseBody
    public PageUtils getUtils(@RequestParam Map<String, Object> params, Long pageSize, Long pageNumber) {
        String siteCodeList = (String) params.get("siteIds");
        if (params.get("page") != null) {
            params.put("startDate", DateUtils.format(new Date(), "yyyy-MM-dd 00:00:00"));
        }
        if ( siteCodeList == null || siteCodeList.equals("undefined") || siteCodeList == "" ) {
            if (!RoleConstant.ROLE_IDS_INNER.contains(getRoleId())) {
                params.put("siteIds", userService.getAllSiteId());
            } else {
                params.put("siteIds", userService.getSitePowerList(getUserId()));
            }
        } else {
            params.put("siteIds", siteCodeList.split(","));
        }
        PageQuery<AlarmEntity> paras = new PageQuery<>(pageNumber, pageSize, params);
        PageQuery<AlarmEntity> page = polluteFactoryService.polluteFactoryAlarmQuery(paras);
        PageUtils pageUtils = new PageUtils(page.getList(), page.getTotalRow());

        return pageUtils;
    }


    @RequestMapping("/polluteFactorySituation")
    @ResponseBody
    public PageUtils getUtilsSituation(@RequestParam Map<String, Object> params, Long pageSize, Long pageNumber) throws ParseException {
        String endDate = (String) params.get("endDate");
        String startDate = (String) params.get("startDate");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long parse = sdf.parse(endDate).getTime();
        long parse1 = sdf.parse(startDate).getTime();
        long time = System.currentTimeMillis()-24*60*60*1000;
        if (parse>time){
            params.put("endDate",sdf.format(time));
        }
        if (parse1>time){
          return null;
        }

        String siteCodeList = (String) params.get("siteIds");

        if ( siteCodeList == null || siteCodeList.equals("undefined") || siteCodeList == "" ) {
            if (!RoleConstant.ROLE_IDS_INNER.contains(getRoleId())) {
                params.put("siteIds", userService.getSitePowerList(getUserId()));
            } else {
                params.put("siteIds", userService.getAllSiteId());
            }
        } else {
            params.put("siteIds", siteCodeList.split(","));
        }//exportSituationExcel
        PageQuery<AlarmEntityEpx> paras = new PageQuery<>(pageNumber, pageSize, params);
        PageQuery<AlarmEntityEpx> page = polluteFactoryService.polluteFactoryAlarmQueryEpx(paras);
        Calendar c = Calendar.getInstance();
        List<AlarmEntityEpx> list = page.getList();
        for (AlarmEntityEpx alarmEntityEpx : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("siteId",alarmEntityEpx.getId());
            Date dataTime = alarmEntityEpx.getDataTime();
            c.setTime(dataTime);
            c.add(Calendar.DATE, 1);
            Date d = c.getTime();
            map.put("startTime" ,dataTime);
            map.put("endTime", d);
            String pollutant = alarmEntityEpx.getPollutant();
            if (pollutant.contains("二氧化硫")){
                map.put("pollutant","so2");
            }
            if (pollutant.contains("氮氧化物")){
                map.put("pollutant","nox");
            }
            if (pollutant.contains("氧气")){
                map.put("pollutant","o2");
            }

            PolluteRealtime polluteRealtime=polluteFactoryService.selHourPollutant(map);
            //getSiteName是借用字段 实际上是平均值
            alarmEntityEpx.setValue24H(polluteRealtime.getSiteName());
            if(polluteRealtime.getSiteName()!=null){
                Double siteName = Double.valueOf(polluteRealtime.getSiteName());
                Double indexValue = Double.valueOf(alarmEntityEpx.getIndexValue());
                if (siteName>indexValue){
                    alarmEntityEpx.setText("仍超标");
                }else {
                    alarmEntityEpx.setText("已整改");
                }
            }else {
                alarmEntityEpx.setText("仍超标");
            }

        }
        PageUtils pageUtils = new PageUtils(page.getList(), page.getTotalRow());

        return pageUtils;
    }


    // 事件页面
    @RequestMapping("/edetail/{id}")
    public String eventDetail(Model model, @PathVariable("id") String id)throws ParseException {
        String[] split = id.split(",");
        model.addAttribute("id",split[0] );
        model.addAttribute("time",split[1] );
        model.addAttribute("pollutant",split[2] );
        return  "module/alarm/pollutantSituation/eventDetail";
    }

    @RequestMapping("/eventList")
    @ResponseBody
    public PageUtils eventList(@RequestParam Map<String,Object> params) throws ParseException {
        String id = String.valueOf(params.get("id"));
        String time = String.valueOf(params.get("time"));
        String pollutant = String.valueOf(params.get("pollutant"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        Map<String, Object> map = new HashMap<>();
        params.put("siteId",id);
        Date dataTime = sdf.parse(time);
        c.setTime(dataTime);
        c.add(Calendar.DATE, 1);
        Date d = c.getTime();
        params.put("startTime" ,dataTime);
        params.put("endTime", d);

        if (pollutant.contains("二氧化硫")){
            params.put("pollutant","so2");
        }
        if (pollutant.contains("氮氧化物")){
            params.put("pollutant","nox");
        }
        if (pollutant.contains("氧气")){
            params.put("pollutant","o2");
        }
        List<PolluteRealtimeExp> polluteRealtime=polluteFactoryService.selHourPollutantList(params);
        for (PolluteRealtimeExp polluteRealtimeExp : polluteRealtime) {
            polluteRealtimeExp.setPollutant(pollutant);
        }
        PageUtils pageUtils = new PageUtils(polluteRealtime,polluteRealtime.size());
        return pageUtils;
    }


    @RequestMapping("/polluteFactoryExp")
    @ResponseBody
    public void getExp(@RequestParam Map<String, Object> params,HttpServletResponse response) throws IOException {
        String beginTime = (String) params.get("startDate");
        String endTime = (String) params.get("endDate");
        String date = beginTime + "至" + endTime;
        String siteCodeList = (String) params.get("siteIds");
        if (siteCodeList == null || siteCodeList.equals("undefined") || siteCodeList == "") {
            params.remove("siteIds");
        } else {
            params.put("siteIds", siteCodeList.split(","));
        }
        if (!RoleConstant.ROLE_IDS_INNER.contains(getRoleId())) {
            params.put("siteIds2", userService.getSitePowerList(getUserId()));
        } else {
            params.put("siteIds2", userService.getAllSiteId());
        }
        List<PolluteFactory> exceeds = polluteFactoryService.factoryAlarmExp(params);
        for (PolluteFactory exceed : exceeds) {
            if (exceed.getPollutant() != null) {
                exceed.setPollutant(exceed.getPollutant()+"污染物浓度超标");
            }
        }
        try {
            new ExportExcel(date + "涉气污染源报警查询", PolluteFactory.class, 2).setDataList(exceeds).write(response, "涉气污染源报警查询.xlsx")
                    .dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/polluteDataVideo/{id}")
    public String getVideo(Model model, @PathVariable(value = "id", required = false) String id) {
        model.addAttribute("id", id);
        return prefix + "/pollutantdata/video";
    }

    @GetMapping("/polluteDataVideo")
    @ResponseBody
    public JSONObject getVideoList(@RequestParam Map<String, Object> params) {
        JSONObject result = new JSONObject();
        String id = (String) params.get("id");
        List<Vidicon> siteVidicons = vidiconService.getVidiconsById(id);
        result.put("videos", siteVidicons);
        return result;
    }

    @RequestMapping("/polluteDataUpdateIgnore")
    @ResponseBody
    public R polluteDataUpdateIgnore(@RequestParam(value = "id") String id) {
        AlarmEntity entity = exceedDao.single(Long.parseLong(id));
        entity.setStatus(1);
        entity.setHandler(getUserId());
        entity.setHandleTime(new Date());
        exceedDao.updateById(entity);
        return new R().ok("操作成功！");
    }

    @RequestMapping("/polluteDataUpdateReturn")
    @ResponseBody
    public R polluteDataUpdateReturn(@RequestParam(value = "id") String id) {
        AlarmEntity entity = exceedDao.single(Long.parseLong(id));
        entity.setStatus(0);
        entity.setHandler(null);
        entity.setHandleTime(null);
        exceedDao.updateById(entity);
        return new R().ok("操作成功！");
    }

    @GetMapping("/polluteDataAdd")
    public String add(Model model, Long id) {
        AlarmEntity entity = airExceedService.findInfoById(id);
        if (entity.getSeriousExceed() != null && entity.getSeriousExceed() == 1) {
            entity.setText("严重超标");
        }
        entity.setPollutant(entity.getPollutant() + entity.getText());
        model.addAttribute("info", entity);
        return prefix + "/pollutantdata/add";
    }

    //getUser
    @PostMapping("/getPolluteDataUser")
    @ResponseBody
    public List<User> getUser(@RequestParam Long deptId) {
        List<User> users = userService.findUsersByDeptId(deptId);
        return users;
    }
}
