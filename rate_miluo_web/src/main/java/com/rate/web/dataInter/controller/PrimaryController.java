package com.rate.web.dataInter.controller;

import com.rate.system.rate_system.utils.CodeMsg;
import com.rate.system.rate_system.utils.Result;
import com.rate.system.rate_system.utils.StringUtils;
import com.rate.web.connected.newdata.service.AirHourStatementService;
import com.rate.web.connected.newdata.service.PolluteWaterHourStatementService;
import com.rate.web.connected.newdata.service.WaterAutoService;
import com.rate.web.dataInter.vo.AirVO;
import com.rate.web.dataInter.vo.PolluteVO;
import com.rate.web.dataInter.vo.PolluteWaterVO;
import com.rate.web.dataInter.vo.WaterAutoVO;
import com.rate.web.statement.service.PolluteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 对外接口
 * @Author  chenshixue
 * @Date    2019/11/21 16:21
 */
@Controller
@RequestMapping("/hourInter")
public class PrimaryController {

    @Autowired
    private AirHourStatementService airHourStatementService;

    @Autowired
    private PolluteWaterHourStatementService polluteWaterHourStatementService;

    @Autowired
    private WaterAutoService waterAutoService;

    @Autowired
    private PolluteService polluteService;

    /**
     * 空气站小时数据接口，可查7天内的数据
     * @param siteCode
     * @param startTime
     * @param endTime
     * @Return  com.rate.system.rate_system.utils.Result
     * @Author  chenshixue
     * @Date    2019/11/21 14:16
     */
    @RequestMapping("/airList")
    @ResponseBody
    public Result autoSiteList(String siteCode,String startTime,String endTime){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            if (StringUtils.isBlank(siteCode) || StringUtils.isBlank(startTime) || StringUtils.isBlank(endTime)) {
                return Result.error(CodeMsg.PARAMETER_ISNULL);
            }
            Calendar cal = Calendar.getInstance();
            Date start = sdf.parse(startTime);
            cal.add(Calendar.DAY_OF_MONTH, -7);;
            if (start.before(cal.getTime())) {
                return Result.error(CodeMsg.OUT_OF_DATE);
            }
            List<AirVO> voList = airHourStatementService.hourInter(siteCode, startTime, endTime);
            if (voList.size() == 0) {
                return Result.error(CodeMsg.NOT_FIND_DATA);
            }
            return Result.success(voList);
        } catch (ParseException e) {
            e.printStackTrace();
            return Result.error(CodeMsg.PARAMETER_ERROE);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(CodeMsg.SERVER_BUSY);
        }
    }

    /**
     * 涉水污染源小时数据接口，可查7天内的数据
     * @param siteCode
     * @param startTime
     * @param endTime
     * @Return  com.rate.system.rate_system.utils.Result
     * @Author  chenshixue
     * @Date    2019/11/21 15:31
     */
    @RequestMapping("/polluteWaterList")
    @ResponseBody
    public Result polluteWaterList(String siteCode,String startTime,String endTime){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            if (StringUtils.isBlank(siteCode) || StringUtils.isBlank(startTime) || StringUtils.isBlank(endTime)) {
                return Result.error(CodeMsg.PARAMETER_ISNULL);
            }
            Calendar cal = Calendar.getInstance();
            Date start = sdf.parse(startTime);
            cal.add(Calendar.DAY_OF_MONTH, -7);
            if (start.before(cal.getTime())) {
                return Result.error(CodeMsg.OUT_OF_DATE);
            }
            List<PolluteWaterVO> voList = polluteWaterHourStatementService.hourPolluteInter(siteCode, startTime, endTime);;
            if (voList.size() == 0) {
                return Result.error(CodeMsg.NOT_FIND_DATA);
            }
            return Result.success(voList);
        } catch (ParseException e) {
            e.printStackTrace();
            return Result.error(CodeMsg.PARAMETER_ERROE);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(CodeMsg.SERVER_BUSY);
        }
    }

    /**
     * 水质自动站分钟数据接口，可查7天内的数据
     * @param siteCode
     * @param startTime
     * @param endTime
     * @Return  com.rate.system.rate_system.utils.Result
     * @Author  chenshixue
     * @Date    2019/11/21 15:49
     */
    @RequestMapping("/waterAutoList")
    @ResponseBody
    public Result getWaterAutoList(String siteCode,String startTime,String endTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            if (StringUtils.isBlank(siteCode) || StringUtils.isBlank(startTime) || StringUtils.isBlank(endTime)) {
                return  Result.error(CodeMsg.PARAMETER_ISNULL);
            }
            Calendar cal = Calendar.getInstance();
            Date start = sdf.parse(startTime);
            Date end = sdf.parse(endTime);
            cal.add(Calendar.DAY_OF_MONTH, -7);
            if (start.before(cal.getTime())) {
                CodeMsg.SERVER_EXCEPTION = null;
                return Result.error(CodeMsg.OUT_OF_DATE);
            }
            long gap = end.getTime() - start.getTime();
            // 分钟数据时间间隔不能超过一天
            if (gap > 24 * 3600 * 1000) {
                return Result.error(CodeMsg.OUT_OF_INTERVAL);
            }
            List<WaterAutoVO> voList = waterAutoService.minuteWaterInter(siteCode, startTime, endTime);
            if (voList.size() == 0) {
                return  Result.error(CodeMsg.NOT_FIND_DATA);
            }
            return Result.success(voList);
        } catch (ParseException e) {
            e.printStackTrace();
            return Result.error(CodeMsg.PARAMETER_ERROE);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(CodeMsg.SERVER_BUSY);
        }
    }

    /**
     * 污染源分钟数据接口，可查7天内的数据
     * @param siteCode
     * @param startTime
     * @param endTime
     * @Return  com.rate.system.rate_system.utils.Result
     * @Author  chenshixue
     * @Date    2019/11/24 19:28
     */
    @RequestMapping("/polluteList")
    @ResponseBody
    public Result getPolluteList(String siteCode, String startTime,String endTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            if (StringUtils.isBlank(siteCode) || StringUtils.isBlank(startTime) || StringUtils.isBlank(endTime)) {
                return  Result.error(CodeMsg.PARAMETER_ISNULL);
            }
            Calendar cal = Calendar.getInstance();
            Date start = sdf.parse(startTime);
            Date end = sdf.parse(endTime);
            cal.add(Calendar.DAY_OF_MONTH, -7);
            if (start.before(cal.getTime())) {
                CodeMsg.SERVER_EXCEPTION = null;
                return Result.error(CodeMsg.OUT_OF_DATE);
            }
            long gap = end.getTime() - start.getTime();
            // 分钟数据时间间隔不能超过一天
            if (gap > 24 * 3600 * 1000) {
                return Result.error(CodeMsg.OUT_OF_INTERVAL);
            }
            List<PolluteVO> voList = polluteService.minutePolluteInter(siteCode, startTime, endTime);
            if (voList.size() == 0) {
                return  Result.error(CodeMsg.NOT_FIND_DATA);
            }
            return Result.success(voList);
        } catch (ParseException e) {
            e.printStackTrace();
            return Result.error(CodeMsg.PARAMETER_ERROE);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(CodeMsg.SERVER_BUSY);
        }
    }

}
