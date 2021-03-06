package com.rate.web.alarm.waterauto.controller;


import com.alibaba.fastjson.JSONObject;
import com.mascloud.sdkclient.Client;
import com.rate.system.rate_system.constant.RoleConstant;
import com.rate.system.rate_system.controller.BaseController;
import com.rate.system.rate_system.dao.UserDao;
import com.rate.system.rate_system.entity.User;
import com.rate.system.rate_system.service.DictService;
import com.rate.system.rate_system.service.UserService;
import com.rate.system.rate_system.utils.*;
import com.rate.system.rate_system.utils.excel.ExportExcel;
import com.rate.web.alarm.entity.AlarmEntity;
import com.rate.web.alarm.monitor.dao.ExceedDao;
import com.rate.web.alarm.monitor.service.ExceedService;
import com.rate.web.alarm.pollutantdata.entity.PolluteFactory;
import com.rate.web.alarm.waterauto.dao.WaterDao;
import com.rate.web.alarm.waterauto.entity.Water;
import com.rate.web.alarm.waterauto.service.WaterService;
import com.rate.web.task.entity.MiluoTaskInfo;
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
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/index")
public class WaterAutoController extends BaseController {

    private String prefix = "module/alarm";

    @Autowired
    private ExceedService airExceedService;

    @Autowired
    private WaterService waterService;

    @Autowired
    private WaterDao waterDao;

    @Autowired
    private UserService userService;

    @Autowired
    private MiluoTaskInfoService miluoTaskInfoService;

    @Autowired
    private VidiconService vidiconService;

    @Autowired
    private ExceedDao exceedDao;

    @Autowired
    private DictService dictService;

    @Autowired
    private UserDao userDao;

    @RequestMapping("/water")
    public String getAirHourSmallStatement(Model model, String siteId) {
        model.addAttribute("siteId", siteId);
        model.addAttribute("roleId", getRoleId());
        return prefix + "/waterauto/show";
    }

    @RequestMapping("/getWater")
    @ResponseBody
    public PageUtils getUtils(@RequestParam Map<String, Object> params, Long pageSize, Long pageNumber) {
        String siteCodeList = (String) params.get("siteIds");
        if (params.get("page") != null) {
            params.put("startDate", DateUtils.format(new Date(), "yyyy-MM-dd 00:00:00"));
        }
        if ( siteCodeList == null || siteCodeList.equals("undefined") || siteCodeList == "" ) {
            if (!RoleConstant.ROLE_IDS_INNER.contains(getRoleId())) {
                params.put("siteIds", userService.getSitePowerList(getUserId()));
            } else {
                params.put("siteIds", userService.getAllSiteId());
            }
        } else {
            params.put("siteIds", siteCodeList.split(","));
        }
        PageQuery<AlarmEntity> paras = new PageQuery<>(pageNumber, pageSize, params);
        PageQuery<AlarmEntity> page = waterService.waterAlarm(paras);
        PageUtils pageUtils = new PageUtils(page.getList(), page.getTotalRow());
        return pageUtils;
    }
    @RequestMapping("/getWaterExp")
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

        List<Water> exceeds = waterDao.waterAlarmExp(params);
        for (Water exceed : exceeds) {
            if (exceed.getPollutant() != null) {
                exceed.setPollutant(exceed.getPollutant()+"污染物浓度超标");
            }
        }
        try {
            new ExportExcel(date + "水质自动站数据报警查询", PolluteFactory.class, 2).setDataList(exceeds).write(response, "水质自动站数据报警查询.xlsx")
                    .dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("/buttonWaterAlarm")
    @ResponseBody
    public PageUtils getWaterAlarmUtils(@RequestParam Map<String, Object> params, Long pageSize, Long pageNumber) {
        params.put("siteIds2", userService.getSitePowerList(getUserId()));
        PageQuery<Water> paras = new PageQuery<>(pageNumber, pageSize, params);
        PageQuery<Water> page = waterService.buttonWaterAlarm(paras);
        PageUtils pageUtils = new PageUtils(page.getList(), page.getTotalRow());
        return pageUtils;
    }

    @RequestMapping("/waterAutoVideo/{id}")
    public String getVideo(Model model, @PathVariable(value = "id", required = false) String id) {
        model.addAttribute("id", id);
        return prefix + "/waterauto/video";
    }

    @GetMapping("/waterAutoVideo")
    @ResponseBody
    public com.alibaba.fastjson.JSONObject getVideoList(@RequestParam Map<String, Object> params) {
        com.alibaba.fastjson.JSONObject result = new JSONObject();
        String id = (String) params.get("id");
        List<Vidicon> siteVidicons = vidiconService.getVidiconsById(id);
        result.put("videos", siteVidicons);
        return result;
    }

    @RequestMapping("/waterUpdateIgnore")
    @ResponseBody
    public R waterUpdateIgnore(@RequestParam(value = "id") String id) {
        AlarmEntity entity = exceedDao.single(Long.parseLong(id));
        entity.setStatus(1);
        entity.setHandler(getUserId());
        entity.setHandleTime(new Date());
        exceedDao.updateById(entity);
        return new R().ok("操作成功！");
    }

    @RequestMapping("/waterUpdateReturn")
    @ResponseBody
    public R waterUpdateReturn(@RequestParam(value = "id") String id) {
        AlarmEntity entity = exceedDao.single(Long.parseLong(id));
        entity.setStatus(0);
        entity.setHandler(null);
        entity.setHandleTime(null);
        exceedDao.updateById(entity);
        return new R().ok("操作成功！");
    }

    @GetMapping("/waterAutoAdd")
    public String add(Model model, long id) {
        AlarmEntity entity = airExceedService.findInfoById(id);
        if (entity.getSeriousExceed() != null && entity.getSeriousExceed() == 1) {
            entity.setText("严重超标");
        }
        entity.setPollutant(entity.getPollutant() + entity.getText());
        model.addAttribute("info", entity);
        return prefix + "/waterauto/add";
    }

    @PostMapping("/waterAutoSave")
    @ResponseBody
    public R save(MiluoTaskInfo miluoTaskInfo) {
        R res = new R();
        String Uid = dictService.listByType("sms_username").get(0).getValue();
        String Key = dictService.listByType("sms_key").get(0).getValue();
        miluoTaskInfo.setId(IdGen.uuid());
        miluoTaskInfo.setPubPersonId(getUserId() + "");  //任务发布人
        try {
            if(StringUtils.isBlank(miluoTaskInfo.getMobile())
                    && StringUtils.isNotBlank(miluoTaskInfo.getChargePersonId())){  //如果负责人没有电话
                miluoTaskInfoService.save(miluoTaskInfo);
                return res.ok("操作成功！短信未推送");
            }else{  // 有电话，则发送信息
                String degree = null;
                if (miluoTaskInfo.getDegreeEmergency().equals("2")) {
                    degree = "最高级别";
                } else if (miluoTaskInfo.getDegreeEmergency().equals("0")) {
                    degree = "普通不紧急";
                } else if (miluoTaskInfo.getDegreeEmergency().equals("1")) {
                    degree = "重要紧急";
                }
                String taskType = "";
                if (miluoTaskInfo.getTaskType().equals("1")) {
                    taskType = "巡检";
                } else if (miluoTaskInfo.getTaskType().equals("2")) {
                    taskType = "维护";
                } else if (miluoTaskInfo.getTaskType().equals("5")) {
                    taskType = "设备维修";
                } else if (miluoTaskInfo.getTaskType().equals("8")) {
                    taskType = "其他任务";
                } else if (miluoTaskInfo.getTaskType().equals("9")) {
                    taskType = "因子超标";
                } else {
                    taskType = "未知";
                }
                String seriousExceed = miluoTaskInfo.getSeriousExceed();  // 是否严重超标

                User user = userDao.userById(Integer.parseInt(miluoTaskInfo.getChargePersonId()));
                String fzrName = user.getName();  // 负责人
                String mobile = user.getMobile(); // 负责人电话
                String remark = miluoTaskInfo.getRemark();
                String smsText = "负责人：" + fzrName + "； 报警级别：" + degree + "； 报警类型：" + taskType + "； 报警内容：" + remark + "。请尽快处理";

                // 严重超标会发送给负责人及他的上级
                String[] mobiles = null;
                if ("1".equals(seriousExceed)) {
                    Integer mgrId = user.getMgrId();
                    User marUser= userDao.userById(mgrId);  // 上级
                    if (marUser != null && StringUtils.isNotBlank(marUser.getMobile())) {
                        String mobile1 = marUser.getMobile();  // 上级电话
                        if (mobile1.equals(mobile)) {
                            mobiles = new String[]{mobile1};
                        } else {
                            mobiles = new String[]{mobile, mobile1};
                        }
                    }
                } else {
                    mobiles = new String[]{mobile};
                }

                // 登录账户
                Client client = Client.getInstance();
                // 登录地址需另外提供
                boolean isLoggedIn = client.login( "http://112.35.4.197:15000",Uid, Key, "汨罗市环境保护保护局" );
                int result = 0;
                if (isLoggedIn) {  // 登录成功，普通短信
                    result = client.sendDSMS(mobiles, smsText, "", 1, "yKIvhGE1V", null, true );
                }
                if (result == 1) {
                    // TODO  存入消息记录表
                    res = R.ok("任务添加成功，短信已发送");
                } else {
                    res = R.ok("任务添加成功，短信推送失败");
                }
                miluoTaskInfoService.save(miluoTaskInfo);
                return res;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    //getUser
    @PostMapping("/getWaterAutoUser")
    @ResponseBody
    public List<User> getUser(@RequestParam Long deptId) {
        List<User> users = userService.findUsersByDeptId(deptId);
        return users;
    }

}
