package com.rate.web.airsite.controller;

import com.alibaba.fastjson.JSONArray;
import com.rate.system.rate_system.controller.BaseController;
import com.rate.system.rate_system.dao.UserDao;
import com.rate.system.rate_system.entity.User;
import com.rate.system.rate_system.service.UserService;
import com.rate.system.rate_system.utils.PageUtils;
import com.rate.system.rate_system.utils.R;
import com.rate.web.airsite.entity.*;
import com.rate.web.airsite.service.AirSiteService;
import com.rate.web.airsite.service.SiteEquipmentService;
import com.rate.web.airsite.service.SiteParamService;
import com.rate.web.airsite.service.SiteVidiconService;
import com.rate.web.site.entity.FactorAndParam;
import com.rate.web.site.entity.FactorParam;
import com.rate.web.site.entity.FactorParamsVideo;
import com.rate.web.site.entity.Site;
import com.rate.web.site.service.ParamService;
import com.rate.web.site.service.SiteService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author xxx
 * @date 2019/5/27 15:31
 **/
@Controller
@RequestMapping("/airSite")
public class AirController extends BaseController {
    @Autowired
    AirSiteService airSiteService;
    @Autowired
    SiteEquipmentService siteEquipmentService;
    @Autowired
    SiteParamService siteParamService;
    @Autowired
    SiteService siteService;
    @Autowired
    UserService userService;
    @Autowired
    SiteVidiconService siteVidiconService;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ParamService paramService;

    // 首页
    @RequestMapping("/list")
    public String list(HttpServletRequest request, Model model) {
        return "/module/airSite/list";
    }

    // 首页列表
    @RequestMapping("/index")
    @ResponseBody
    public PageUtils index(@RequestParam Map<String, Object> params, @RequestParam(name = "siteIds[]", required = false) List<String> siteIds, Long pageSize, Long pageNumber) {
        if (siteIds == null || siteIds.size() == 0) {
            siteIds = siteService.querySiteByUser(String.valueOf(getUserId()));
        }
        params.put("siteIds", siteIds);
        params.put("userId", getUserId());
        PageQuery<Site> param = new PageQuery<>(pageNumber, pageSize, params);
        PageQuery<Site> page = siteService.getSiteListByPage(param);
        PageUtils pageUtils = new PageUtils(page.getList(), page.getTotalRow());
        return pageUtils;
    }

    // 站点新增页面
    @RequiresPermissions("system:site:operate")
    @RequestMapping("/addSite")
    public String addSite(Model model) {
        model.addAttribute("userList", userDao.getAllList());
        return "/module/airSite/addSite";
    }

    @RequestMapping("/getUserList")
    @ResponseBody
    public List<User> getUserList(){

        List<User> allList = userDao.getAllList();


        return allList;
    }


    // 保存站点操作
    @PostMapping("/saveSite")
    @ResponseBody
    public R saveSite(AirSite airSite) {
        if (airSite.getSiteCategory().equals("0")) {
            airSite.setSiteCategory(null);
        } else {
            airSite.setSiteCategory(airSite.getSiteType() + airSite.getSiteCategory());
        }
        airSiteService.saveSite(airSite);
        return R.ok();
    }

    // 删除站点
    @PostMapping("/remove")
    @ResponseBody
    public R remove(Integer id) {
        try {
            airSiteService.remove(id);
            return R.ok();
        } catch (Exception e) {
            return R.error();
        }
    }

    /**
     * 批量删除站点
     *
     * @return
     */
    @RequiresPermissions("system:site:operate")
    @PostMapping("/batchRemove")
    @ResponseBody
    public R batchRemove(@RequestParam("ids[]") Integer[] stationIds) {
        try {
            airSiteService.batchRemove(stationIds);
            return R.ok();
        } catch (Exception e) {
            return R.error();
        }
    }

    /**
     * 获得监测因子列表
     *
     * @return
     */
    @GetMapping("/getParamList")
    @ResponseBody
    public List<JianCeParam> getParamList() {
        return siteParamService.getJianCeParamList();
    }

    /**
     * 添加监测因子
     *
     * @return
     */
    @PostMapping("/addFactor")
    @ResponseBody
    public R addFactor(SiteParam siteParam) {
        try {
            siteParamService.insert(siteParam);
            return R.ok();
        } catch (Exception e) {
            return R.error();
        }
    }

    /**
     * 修改报警值页面
     *
     * @return
     */
    @GetMapping("/factorValueSet")
    public String factorValueSet(Model model, @RequestParam("type") String type, @RequestParam("equmentId") String equmentId) {
        // 会先该类型设备的所有参数
        List<FactorAndParam> paramList = paramService.findParamAndLimit(type, equmentId);
        model.addAttribute("paramList", paramList);
        model.addAttribute("equId", equmentId);
        return "/module/airSite/factorValueSet";
    }

    /**
     * 设备因子报警信息查看
     *
     * @return
     */
    @GetMapping("/factorValueShow")
    public String factorValueShow(Model model, @RequestParam("type") String type, @RequestParam("equmentId") String equmentId) {
        // 会先该类型设备的所有参数
        List<FactorAndParam> paramList = paramService.findParamAndLimit(type, equmentId);
        model.addAttribute("paramList", paramList);
        model.addAttribute("equId", equmentId);
        return "/module/airSite/factorValueShow";
    }

    /**
     * 保存报警值操作
     *
     * @return
     */
    @PostMapping("/save/factorValueSet")
    @ResponseBody
    public R SetfactorValue(@RequestParam Map<String, Object> params) {
        try {
            String jsonArray = (String) params.get("paramList");
            String videoId = (String) params.get("equId");
            List<FactorParamsVideo> paramList = JSONArray.parseArray(jsonArray, FactorParamsVideo.class);
            paramService.listUpdateObject(paramList, videoId);
            return R.ok();
        } catch (Exception e) {
            return R.error();
        }
    }

    /**
     * 保存站点设备
     *
     * @return
     */
    @PostMapping("/saveDevice")
    @ResponseBody
    public R saveDevice(SiteEquipment siteEquipment) {
        try {
            Date date = siteEquipment.getAzDate();
//            java.sql.Date d = new java.sql.Date(s.getTime());
            siteEquipment.setAzDate(date);
            siteEquipmentService.insert(siteEquipment);
            return R.ok();
        } catch (Exception e) {
            return R.error();
        }
    }

    /**
     * 删除站点设备
     *
     * @return
     */
    @PostMapping("/removeDevice")
    @ResponseBody
    public R removeDevice(@RequestParam("equId") Integer id) {
        try {
            SiteEquipment siteEquipment = siteEquipmentService.getById(id);
            if (siteEquipment != null) {
                siteEquipmentService.deleteAll(siteEquipment);
            }
            return R.ok();
        } catch (Exception e) {
            return R.error();
        }
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") String id) {
        AirSite airSite = airSiteService.findById(id);

        String fzr = airSite.getFzr();
        airSite.setFzr2(Integer.valueOf(fzr));
        User user = userDao.userById(fzr);
        model.addAttribute("userFzr", user == null ? "" : user.getName());
        model.addAttribute("userId", getUserId());
     //  model.addAttribute("users", userService.findAll(getDeptId()));
        model.addAttribute("userList", userDao.getAllList());
        model.addAttribute("airSite", airSite);

        return "/module/airSite/editSite";
    }

    @PostMapping("/updateSite")
    @ResponseBody
    public R updateSite(AirSite airSite) {
        try {
            airSiteService.updateSite(airSite);
            return R.ok();
        } catch (Exception e) {
            return R.error();
        }
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") String id) {
        AirSite airSite = airSiteService.findById(id);
        String fzr = airSite.getFzr();
        User user = userDao.userById(fzr);
        model.addAttribute("user",user);
        model.addAttribute("airSite", airSite);

        return "/module/airSite/detailSite";
    }

    @GetMapping("/addFactor/{equId}")
    public String addFactor(Model model, @PathVariable("equId") Integer equId) {

        model.addAttribute("equId", equId);

        return "/module/airSite/addFactor";
    }

    /**
     * <p>Title: addFactor</p>
     * <p>Description: 跳转页面</p>
     *
     * @param model
     * @param equId
     * @return
     */
    @GetMapping("/addParam/{equId}")
    public String addParam(Model model, @PathVariable("equId") Integer equId) {
        model.addAttribute("equId", equId);
        return "/module/airSite/addParam";
    }

    // 添加设备页面
    @GetMapping("/addDevice/{id}")
    public String addDevice(Model model, @PathVariable("id") String id) {
        model.addAttribute("siteId", id);
        List<SiteVidicon> vidiconList = siteVidiconService.getListBySiteId(Integer.parseInt(id));
        if (vidiconList.size() > 0) {
            model.addAttribute("vidiconList", vidiconList);
        }
        return "/module/airSite/addDevice";
    }

    /**
     * 验证设备编码唯一性
     * @param equmentId
     * @Return  java.lang.Boolean
     * @Author  chenshixue
     * @Date    2019/12/13 10:54
     */
    @RequestMapping("/validateEquId")
    @ResponseBody
    public Boolean validateEquId(@RequestParam String equmentId) {
        int count = siteEquipmentService.countEquipments(equmentId);
        if (count >= 1) {
            return false;
        } else {
            return true;
        }
    }


    /**
     * 显示站点设备
     *
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/showEqu/{id}")
    public String showEqu(Model model, @PathVariable("id") Integer id) {
        List<SiteEquipment> equList = new ArrayList();
        //根据站点ID查询设备列表
        List<SiteEquipment> list = siteEquipmentService.getEquBySiteId(id);
        if (null != list) {
            for (SiteEquipment equipment : list) {
                List<FactorParam> factorParams = paramService.findParamListByType(equipment.getType());
                equipment.setFactorParams(factorParams);
                //根据设备表里的 视频ID   查询   视屏对象
                SiteVidicon siteVidicon = siteVidiconService.getByEquId(equipment.getVidiconId());
                //设置设备里的   摄像机名称
                if (null != siteVidicon) {
                    equipment.setVidiconName(siteVidicon.getVidiconName());
                }
                equList.add(equipment);
            }
        }
        model.addAttribute("siteId", id);
        model.addAttribute("equList", equList);
        return "module/airSite/showEqu";
    }
}
