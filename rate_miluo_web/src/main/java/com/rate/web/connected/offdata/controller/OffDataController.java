package com.rate.web.connected.offdata.controller;


import com.rate.system.rate_system.constant.RoleConstant;
import com.rate.system.rate_system.controller.BaseController;
import com.rate.system.rate_system.service.UserService;
import com.rate.system.rate_system.utils.PageUtils;
import com.rate.web.connected.offdata.dao.MiluoStatementTypeDao;
import com.rate.web.connected.offdata.entity.MiluoStatementType;
import com.rate.web.connected.offdata.service.MiluoStatementTypeService;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/internet")
public class OffDataController extends BaseController {

   private String prefix="module/connected";

    @Autowired
    private MiluoStatementTypeService miluoStatementTypeService;

    @Autowired
    private MiluoStatementTypeDao miluoStatementTypeDao;

    @Autowired
    private UserService userService;

    @RequestMapping("/offData")
    public String getAirHourSmallStatement(Model model) {
        Long roleId = getRoleId();
        model.addAttribute("roleId", roleId);
        if(roleId == 1 || roleId == 73 || roleId == 74 || roleId == 75 || roleId == 77){
            return prefix+"/offDate/show";
        }else{
            return prefix+"/offDate/showPlus";
        }
    }

    @RequestMapping("/list")
    @ResponseBody
    public PageUtils getUtils(@RequestParam Map<String, Object> params, Long pageSize, Long pageNumber) {
//        and time >=(NOW() - interval 240 hour)
//        获取后台传回来的两个时间的数据分别是startDate和endDate
        String beginTime = (String) params.get("beginTime");
        String endTime = (String) params.get("endTime");
        // 按指定站点查询
        String siteCodeList = (String) params.get("siteIds");
        if ( siteCodeList==null||siteCodeList.equals("undefined")|| siteCodeList=="" ) {
            params.remove("siteIds");
        } else {
            params.put("siteIds", siteCodeList.split(","));
        }
        if (!RoleConstant.ROLE_IDS_INNER.contains(getRoleId())) {
            params.put("siteIds2", userService.getSitePowerList(getUserId()));
        } else {
            params.put("siteIds2", userService.getAllSiteId());
        }
        PageQuery<MiluoStatementType> paras = new PageQuery<>(pageNumber, pageSize, params);
        PageQuery<MiluoStatementType> page = miluoStatementTypeService.lx(paras);
        List<MiluoStatementType> pageList = page.getList();
//        根据idList的方法返回一个String的数组
        List<String> strings1 = miluoStatementTypeDao.idList(beginTime, endTime);
//        创建一个二维数组
        List<List<String>> allIds = new ArrayList<>();
        List<String> ids = null;
        for (int i = 0; i < strings1.size(); i++) {
            ids = new ArrayList<>();
            String[] idArr = strings1.get(i).split(",");
            for (int j = 0; j < idArr.length; j++) {
                ids.add(idArr[j]);
            }
            allIds.add(ids);
        }
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < allIds.size(); i++) {
            List<MiluoStatementType> miluoStatementTypes1 = miluoStatementTypeService.oSList(allIds.get(i), beginTime, endTime);
            List<Long> list = new ArrayList<>();
            for (int y = 0; y < miluoStatementTypes1.size(); y++) {
                if (y + 1 != miluoStatementTypes1.size()) {
                    String y1 = miluoStatementTypes1.get(y).getSiteStatement();
                    String y2 = miluoStatementTypes1.get(y + 1).getSiteStatement();
                    if (y1.equals("1") && y2 != null) {
                        long time = miluoStatementTypes1.get(y).getTime().getTime();
                        long time1 = miluoStatementTypes1.get(y + 1).getTime().getTime();
                        Long index1 = time1 - time;
                        list.add(index1);
                    }
                }
            }
            if (list.size() > 0) {
                Long max = Collections.max(list);
                Long sum = 0L;
                for (Long aLong : list) {
                    sum += aLong;
                }
                String max1 = null;
                if (max >= 86400000) {
                    max1 = max / 1000 / 60 + "分" + max / 1000 % 60 + "秒";
                } else if (max >= 3600000) {
                    max1 = max / 1000 / 60 + "分" + max / 1000 % 60 + "秒";
                } else if (max >= 60000) {
                    max1 = max / 1000 / 60 + "分" + max / 1000 % 60 + "秒";
                } else if (max > 0) {
                    max1 = max / 1000 % 60 + "秒";
                }
                String sum1 = null;
                if (sum >= 86400000) {
                    sum1 = sum / 1000 / 60 + "分" + sum / 1000 % 60 + "秒";
                } else if (sum >= 3600000) {
                    sum1 = sum / 1000 / 60 + "分" + sum / 1000 % 60 + "秒";
                } else if (sum >= 60000) {
                    sum1 = sum / 1000 / 60 + "分" + sum / 1000 % 60 + "秒";
                } else if (sum > 0) {
                    sum1 = sum / 1000 % 60 + "秒";
                }
                String s = max1 + "," + sum1;
                map.put(miluoStatementTypes1.get(0).getEquipmentId(), s);
            }
        }
        for (MiluoStatementType miluoStatementType : pageList) {
            String equipmentId = miluoStatementType.getEquipmentId();
            String s = map.get(equipmentId);
            if (s != null) {
                String[] split = s.split(",");
                miluoStatementType.setMaxOnLine(split[0]);
                miluoStatementType.setOffCount(split[1]);
            }
        }
        PageUtils pageUtils = new PageUtils(pageList, page.getTotalRow());
        return pageUtils;
    }

    @RequestMapping("/polluteWater")
    @ResponseBody
    public PageUtils getPolluteWaterUtils(@RequestParam Map<String, Object> params, Long pageSize, Long pageNumber) {
//        and time >=(NOW() - interval 240 hour)
//        获取后台传回来的两个时间的数据分别是startDate和endDate
        String beginTime = (String) params.get("beginTime");
        String endTime = (String) params.get("endTime");
        // 按指定站点查询
        String siteCodeList = (String) params.get("siteIds");
        if (siteCodeList==null||siteCodeList.equals("undefined")|| siteCodeList=="" ) {
            params.remove("siteIds");
        } else {
            params.put("siteIds", siteCodeList.split(","));
        }
        params.put("siteIds2", userService.getSitePowerList(getUserId()));
        PageQuery<MiluoStatementType> paras = new PageQuery<>(pageNumber, pageSize, params);
        PageQuery<MiluoStatementType> page = miluoStatementTypeService.lxPolluteWater(paras);
        List<MiluoStatementType> pageList = page.getList();
//        根据idList的方法返回一个String的数组
        List<String> strings1 = miluoStatementTypeDao.idPolluteWaterList(beginTime, endTime);
//        创建一个二维数组
        List<List<String>> allIds = new ArrayList<>();
        List<String> ids = null;
        for (int i = 0; i < strings1.size(); i++) {
            ids = new ArrayList<>();
            String[] idArr = strings1.get(i).split(",");
            for (int j = 0; j < idArr.length; j++) {
                ids.add(idArr[j]);
            }
            allIds.add(ids);
        }
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < allIds.size(); i++) {
            List<MiluoStatementType> miluoStatementTypes1 = miluoStatementTypeService.oSList(allIds.get(i), beginTime, endTime);
            List<Long> list = new ArrayList<>();
            for (int y = 0; y < miluoStatementTypes1.size(); y++) {
                if (y + 1 != miluoStatementTypes1.size()) {
                    String y1 = miluoStatementTypes1.get(y).getSiteStatement();
                    String y2 = miluoStatementTypes1.get(y + 1).getSiteStatement();
                    if (y1.equals("1") && y2 != null) {
                        long time = miluoStatementTypes1.get(y).getTime().getTime();
                        long time1 = miluoStatementTypes1.get(y + 1).getTime().getTime();
                        Long index1 = time1 - time;
                        list.add(index1);
                    }
                }
            }
            if (list.size() > 0) {
                Long max = Collections.max(list);
                Long sum = 0L;
                for (Long aLong : list) {
                    sum += aLong;
                }
                String max1 = null;
                if (max >= 86400000) {
                    max1 = max / 1000 / 60 + "分" + max / 1000 % 60 + "秒";
                } else if (max >= 3600000) {
                    max1 = max / 1000 / 60 + "分" + max / 1000 % 60 + "秒";
                } else if (max >= 60000) {
                    max1 = max / 1000 / 60 + "分" + max / 1000 % 60 + "秒";
                } else if (max > 0) {
                    max1 = max / 1000 % 60 + "秒";
                }
                String sum1 = null;
                if (sum >= 86400000) {
                    sum1 = sum / 1000 / 60 + "分" + sum / 1000 % 60 + "秒";
                } else if (sum >= 3600000) {
                    sum1 = sum / 1000 / 60 + "分" + sum / 1000 % 60 + "秒";
                } else if (sum >= 60000) {
                    sum1 = sum / 1000 / 60 + "分" + sum / 1000 % 60 + "秒";
                } else if (sum > 0) {
                    sum1 = sum / 1000 % 60 + "秒";
                }
                String s = max1 + "," + sum1;
                map.put(miluoStatementTypes1.get(0).getEquipmentId(), s);
            }
        }
        for (MiluoStatementType miluoStatementType : pageList) {
            String equipmentId = miluoStatementType.getEquipmentId();
            String s = map.get(equipmentId);
            if (s != null) {
                String[] split = s.split(",");
                miluoStatementType.setMaxOnLine(split[0]);
                miluoStatementType.setOffCount(split[1]);
            }
        }
        PageUtils pageUtils = new PageUtils(pageList, page.getTotalRow());
        return pageUtils;
    }


    @RequestMapping("/waterAuto")
    @ResponseBody
    public PageUtils getWaterAutoUtils(@RequestParam Map<String, Object> params, Long pageSize, Long pageNumber) {
//        and time >=(NOW() - interval 240 hour)
//        获取后台传回来的两个时间的数据分别是startDate和endDate
        String beginTime = (String) params.get("beginTime");
        String endTime = (String) params.get("endTime");
        // 按指定站点查询
        String siteCodeList = (String) params.get("siteIds");
        if (siteCodeList==null || siteCodeList.equals("undefined")|| siteCodeList=="" ) {
            params.remove("siteIds");
        } else {
            params.put("siteIds", siteCodeList.split(","));
        }
        if (!RoleConstant.ROLE_IDS_INNER.contains(getRoleId())) {
            params.put("siteIds2", userService.getSitePowerList(getUserId()));
        } else {
            params.put("siteIds2", userService.getAllSiteId());
        }
        PageQuery<MiluoStatementType> paras = new PageQuery<>(pageNumber, pageSize, params);
        PageQuery<MiluoStatementType> page = miluoStatementTypeService.lxWaterAuto(paras);
        List<MiluoStatementType> pageList = page.getList();
//        根据idList的方法返回一个String的数组
        List<String> strings1 = miluoStatementTypeDao.idWaterAutoList(beginTime, endTime);
//        创建一个二维数组
        List<List<String>> allIds = new ArrayList<>();
        List<String> ids = null;
        for (int i = 0; i < strings1.size(); i++) {
            ids = new ArrayList<>();
            String[] idArr = strings1.get(i).split(",");
            for (int j = 0; j < idArr.length; j++) {
                ids.add(idArr[j]);
            }
            allIds.add(ids);
        }
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < allIds.size(); i++) {
            List<MiluoStatementType> miluoStatementTypes1 = miluoStatementTypeService.oSList(allIds.get(i), beginTime, endTime);
            List<Long> list = new ArrayList<>();
            for (int y = 0; y < miluoStatementTypes1.size(); y++) {
                if (y + 1 != miluoStatementTypes1.size()) {
                    String y1 = miluoStatementTypes1.get(y).getSiteStatement();
                    String y2 = miluoStatementTypes1.get(y + 1).getSiteStatement();
                    if (y1.equals("1") && y2 != null) {
                        long time = miluoStatementTypes1.get(y).getTime().getTime();
                        long time1 = miluoStatementTypes1.get(y + 1).getTime().getTime();
                        Long index1 = time1 - time;
                        list.add(index1);
                    }
                }
            }
            if (list.size() > 0) {
                Long max = Collections.max(list);
                Long sum = 0L;
                for (Long aLong : list) {
                    sum += aLong;
                }
                String max1 = null;
                if (max >= 86400000) {
                    max1 = max / 1000 / 60 + "分" + max / 1000 % 60 + "秒";
                } else if (max >= 3600000) {
                    max1 = max / 1000 / 60 + "分" + max / 1000 % 60 + "秒";
                } else if (max >= 60000) {
                    max1 = max / 1000 / 60 + "分" + max / 1000 % 60 + "秒";
                } else if (max > 0) {
                    max1 = max / 1000 % 60 + "秒";
                }
                String sum1 = null;
                if (sum >= 86400000) {
                    sum1 = sum / 1000 / 60 + "分" + sum / 1000 % 60 + "秒";
                } else if (sum >= 3600000) {
                    sum1 = sum / 1000 / 60 + "分" + sum / 1000 % 60 + "秒";
                } else if (sum >= 60000) {
                    sum1 = sum / 1000 / 60 + "分" + sum / 1000 % 60 + "秒";
                } else if (sum > 0) {
                    sum1 = sum / 1000 % 60 + "秒";
                }
                String s = max1 + "," + sum1;
                map.put(miluoStatementTypes1.get(0).getEquipmentId(), s);
            }
        }
        for (MiluoStatementType miluoStatementType : pageList) {
            String equipmentId = miluoStatementType.getEquipmentId();
            String s = map.get(equipmentId);
            if (s != null) {
                String[] split = s.split(",");
                miluoStatementType.setMaxOnLine(split[0]);
                miluoStatementType.setOffCount(split[1]);
            }
        }
        PageUtils pageUtils = new PageUtils(pageList, page.getTotalRow());
        return pageUtils;
    }



}
