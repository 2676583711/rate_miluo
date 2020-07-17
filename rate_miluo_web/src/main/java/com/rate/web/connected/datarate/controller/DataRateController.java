package com.rate.web.connected.datarate.controller;
import com.rate.system.rate_system.controller.BaseController;
import com.rate.system.rate_system.service.UserService;
import com.rate.system.rate_system.utils.PageUtils;
import com.rate.system.rate_system.utils.StringUtils;
import com.rate.web.airsite.entity.AirSite;
import com.rate.web.alarm.pollutantwater.entity.PolluteWater;
import com.rate.web.alarm.pollutantwater.service.PolluteWaterService;
import com.rate.web.connected.newdata.dao.AirHourStatementDao;
import com.rate.web.connected.newdata.dao.PolluteWaterHourStatementDao;
import com.rate.web.connected.newdata.dao.WaterAutoDao;
import com.rate.web.connected.newdata.entity.AirHourStatement;
import com.rate.web.connected.newdata.entity.PolluteWaterHourStatement;
import com.rate.web.connected.newdata.entity.WaterAuto;
import com.rate.web.connected.newdata.service.AirHourStatementService;
import com.rate.web.connected.newdata.service.PolluteWaterHourStatementService;
import com.rate.web.connected.newdata.service.WaterAutoService;
import com.rate.web.site.entity.Site;
import com.rate.web.site.enums.RoleConstant;
import com.rate.web.site.service.SiteService;
import org.beetl.sql.core.annotatoin.AssignID;
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
public class DataRateController extends BaseController {

    private String prefix="module/connected";
    @Autowired
    private AirHourStatementDao airHourStatementDao;

    @Autowired
    private SiteService siteService;

    @Autowired
    private AirHourStatementService airHourStatementService;

    @Autowired
    private WaterAutoDao waterAutoDao;
    @Autowired
    private WaterAutoService waterAutoService;
    @Autowired
    private UserService userService;


    @Autowired
    private PolluteWaterHourStatementDao polluteWaterHourStatementDao;

    @Autowired
    private PolluteWaterHourStatementService polluteWaterHourStatementService;


    @RequestMapping("/dataRate")
    public String getAirHourSmallStatement(Model model) {
        Long roleId = getRoleId();
        model.addAttribute("roleId", roleId);
        if(roleId == 1 || roleId == 73 || roleId == 74 || roleId == 75 || roleId == 77){
            return prefix+"/dataRate/show";
        }else{
            return prefix+"/dataRate/showPlus";
        }
    }
    @RequestMapping("/getdatarate")
    @ResponseBody
    public PageUtils getDataRateList(@RequestParam Map<String, Object> params, Long pageSize, Long pageNumber) {

        String beginTime = (String) params.get("beginTime");
        String endTime = (String) params.get("endTime");
        List<String> strings1 = airHourStatementDao.ids(beginTime,endTime);
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
        for (List<String> allId : allIds) {
            List<AirHourStatement> airHourSmallStatement = airHourStatementDao.dataRate(allId,beginTime,endTime);
            for (AirHourStatement hourSmallStatement : airHourSmallStatement) {
                String s = hourSmallStatement.getSo2() + "," + hourSmallStatement.getNo2() + "," + hourSmallStatement.getO3oneHour() + "," +
                        hourSmallStatement.getCo() + "," + hourSmallStatement.getPm10() + "," + hourSmallStatement.getPm25() + "," +
                        hourSmallStatement.getWs() + "," + hourSmallStatement.getWd() + "," + hourSmallStatement.getPress() + "," +
                        hourSmallStatement.getTemp() + "," + hourSmallStatement.getHumi() + "," + hourSmallStatement.getNoise()
                        + "," + hourSmallStatement.getTsp();
                map.put(hourSmallStatement.getSiteCode(), s);
            }
        }

        // 按指定站点查询
        String siteCodeList = (String) params.get("siteIds");
        if (siteCodeList==null || siteCodeList.equals("undefined")|| siteCodeList=="") {
            params.remove("siteIds");
        } else {
            params.put("siteIds", siteCodeList.split(","));
        }
        if (Arrays.asList(RoleConstant.ROLEIDS).contains(getRoleId())) {
            params.put("siteIds2", userService.getSitePowerList(getUserId()));
        } else {
            params.put("siteIds2", userService.getAllSiteId());
        }
        PageQuery<Site> paras = new PageQuery<>(pageNumber, pageSize, params);
        PageQuery<Site> page = siteService.getMicro(paras);
        List<Site> pageList = page.getList();

        for (Site site : pageList) {
            String siteCode = site.getEqumentId();
            String ss = map.get(siteCode);
            if (ss != null) {
                String[] split = ss.split(",");
                site.setSo2(split[0]);
                site.setNo2(split[1]);
                site.setO3oneHour(split[2]);
                site.setCo(split[3]);
                site.setPm10(split[4]);
                site.setPm25(split[5]);
                site.setWs(split[6]);
                site.setWd(split[7]);
                site.setPress(split[8]);
                site.setTemp(split[9]);
                site.setHumi(split[10]);
                site.setNoise(split[11]);
                site.setTsp(split[12]);
            }
        }
        PageUtils PageUtils = new PageUtils(pageList, page.getTotalRow());
        return PageUtils;
    }


    @RequestMapping("/details")
    public String getDetails(@RequestParam(value="siteCode",required=false) String siteCode, Model model) {

        model.addAttribute("siteCode",siteCode);

        return prefix+"/dataRate/findDetails";
    }

    @RequestMapping("/detailsList")
    @ResponseBody
    PageUtils getDetailsList(@RequestParam Map<String, Object> params, Long pageSize, Long pageNumber){


        PageQuery<AirHourStatement> paras=new PageQuery<>(pageNumber,pageSize,params);

        PageQuery<AirHourStatement> page = airHourStatementService.findDetails(paras);

        PageUtils pageUtils = new PageUtils(page.getList(), page.getTotalRow());

        return pageUtils;
    }

    @RequestMapping("/getWaterAutoRate")
    @ResponseBody
    public PageUtils getWaterAutoList(@RequestParam Map<String, Object> params, Long pageSize, Long pageNumber) {

        String beginTime = (String) params.get("beginTime");
        String endTime = (String) params.get("endTime");
        List<String> strings1 = waterAutoDao.waterAutoIds(beginTime,endTime);
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
        for (List<String> allId : allIds) {
            List<WaterAuto> waterAutos = waterAutoDao.waterAutoRate(allId, beginTime, endTime);
            for (WaterAuto waterAuto : waterAutos) {
                String s = waterAuto.getSw() + "," + waterAuto.getPlc() + "," + waterAuto.getZd() + "," +
                        waterAuto.getDdl() + "," + waterAuto.getCodmn() + "," + waterAuto.getPb() + "," +
                        waterAuto.getRjy() + "," + waterAuto.getPh() + "," + waterAuto.getShen() + "," +
                        waterAuto.getNh3N() + "," + waterAuto.getComprehensiveToxicity() + "," + waterAuto.getTp()
                        + "," + waterAuto.getChromium()+ "," + waterAuto.getWaterSort()+ "," + waterAuto.getK03()+ "," + waterAuto.getK04();
                map.put(waterAuto.getStationCode(), s);
            }
        }

        // 按指定站点查询
        String siteCodeList = (String) params.get("siteIds");
        if (siteCodeList.equals("undefined")|| siteCodeList=="") {
            params.remove("siteIds");
        } else {
            params.put("siteIds", siteCodeList.split(","));
        }
        params.put("siteIds2", userService.getSitePowerList(getUserId()));
        PageQuery<Site> paras = new PageQuery<>(pageNumber, pageSize, params);
        PageQuery<Site> page = siteService.getWaterAutoStation(paras);
        List<Site> pageList = page.getList();

        for (Site site : pageList) {
            String siteCode = site.getEqumentId();
            String ss = map.get(siteCode);
            if (ss != null) {
                String[] split = ss.split(",");
                site.setSw(split[0]);
                site.setPlc(split[1]);
                site.setZd(split[2]);
                site.setDdl(split[3]);
                site.setCodmn(split[4]);
                site.setPb(split[5]);
                site.setRjy(split[6]);
                site.setPh(split[7]);
                site.setShen(split[8]);
                site.setNh3N(split[9]);
                site.setComprehensiveToxicity(split[10]);
                site.setTp( split[11]);
                site.setChromium(split[12]);
                site.setWaterSort(split[13]);
                site.setK03(split[14]);
                site.setK04( split[15]);;
            }
        }
        PageUtils PageUtils = new PageUtils(pageList, page.getTotalRow());
        return PageUtils;
    }

    @RequestMapping("/waterAutoDetails")
    public String getWaterAutoDetails(@RequestParam(value="siteCode",required=false) String siteCode, Model model) {

        model.addAttribute("siteCode",siteCode);

        return prefix+"/dataRate/WaterAutoFindDetails";
    }

    @RequestMapping("/waterAutoList")
    @ResponseBody
    PageUtils getWaterAutoDetailsList(@RequestParam Map<String, Object> params, Long pageSize, Long pageNumber){


        PageQuery<WaterAuto> paras=new PageQuery<>(pageNumber,pageSize,params);

        PageQuery<WaterAuto> page =  waterAutoService.waterAutoDetails(paras);

        PageUtils pageUtils = new PageUtils(page.getList(), page.getTotalRow());

        return pageUtils;
    }


    @RequestMapping("/getPolluteWaterRate")
    @ResponseBody
    public PageUtils getPolluteWaterList(@RequestParam Map<String, Object> params, Long pageSize, Long pageNumber) {

        String beginTime = (String) params.get("beginTime");
        String endTime = (String) params.get("endTime");
        List<String> strings1 = polluteWaterHourStatementDao.polluteWaterIds(beginTime,endTime);
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
        for (List<String> allId : allIds) {
            List<PolluteWaterHourStatement> polluteWaterHourStatements = polluteWaterHourStatementDao.polluteWaterRate(allId, beginTime, endTime);

            for (PolluteWaterHourStatement polluteWaterHourStatement : polluteWaterHourStatements) {
                String s = polluteWaterHourStatement.getBo1() + "," + polluteWaterHourStatement.getPh() + "," + polluteWaterHourStatement.getPb() + "," +
                        polluteWaterHourStatement.getCd() + "," + polluteWaterHourStatement.getShen() + "," + polluteWaterHourStatement.getZn() + "," +
                        polluteWaterHourStatement.getCu() + "," + polluteWaterHourStatement.getPh()+ "," + polluteWaterHourStatement.getTp() + "," +
                        polluteWaterHourStatement.getTn() + "," + polluteWaterHourStatement.getCod() + "," + polluteWaterHourStatement.getNh3();

                map.put(polluteWaterHourStatement.getEqumentId(), s);
            }
        }

        // 按指定站点查询
        String siteCodeList = (String) params.get("siteIds");
        if (siteCodeList.equals("undefined")|| siteCodeList=="") {
            params.remove("siteIds");
        } else {
            params.put("siteIds", siteCodeList.split(","));
        }
        if (Arrays.asList(RoleConstant.ROLEIDS).contains(getRoleId())) {
            params.put("siteIds2", userService.getSitePowerList(getUserId()));
        } else {
            params.put("siteIds2", userService.getAllSiteId());
        }
        PageQuery<Site> paras = new PageQuery<>(pageNumber, pageSize, params);
        PageQuery<Site> page = siteService.getPolluteWaterStation(paras);
        List<Site> pageList = page.getList();

        for (Site site : pageList) {
            String siteCode = site.getEqumentId();
            String ss = map.get(siteCode);
            if (ss != null) {
                String[] split = ss.split(",");
                site.setBo1(split[0]);
                site.setPh(split[1]);
                site.setPb(split[2]);
                site.setCd(split[3]);
                site.setShen(split[4]);
                site.setZn(split[5]);
                site.setCu(split[6]);
                site.setPh(split[7]);
                site.setTp(split[8]);
                site.setTn(split[9]);
                site.setCod(split[10]);
                site.setNh3(split[11]);
            }
        }
        PageUtils PageUtils = new PageUtils(pageList, page.getTotalRow());
        return PageUtils;
    }


    @RequestMapping("/polluteWaterDetails")
    public String getpolluteWaterDetails(@RequestParam(value="siteCode",required=false) String siteCode, Model model) {

        model.addAttribute("siteCode",siteCode);

        return prefix+"/dataRate/polluteWaterFindDetails";
    }

    @RequestMapping("/polluteWaterList")
    @ResponseBody
    PageUtils getPolluteWaterDetailsList(@RequestParam Map<String, Object> params, Long pageSize, Long pageNumber){


        PageQuery<PolluteWaterHourStatement> paras=new PageQuery<>(pageNumber,pageSize,params);

        PageQuery<PolluteWaterHourStatement> page =  polluteWaterHourStatementService.polluteWaterDetails(paras);

        PageUtils pageUtils = new PageUtils(page.getList(), page.getTotalRow());

        return pageUtils;
    }


}
