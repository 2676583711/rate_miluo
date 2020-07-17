package com.rate.web.api.controller;

import com.rate.system.rate_system.api.service.ApiLoginService;
import com.rate.system.rate_system.utils.CodeMsg;
import com.rate.system.rate_system.utils.Result;
import com.rate.web.alarm.entity.AlarmEntity;
import com.rate.web.alarm.monitor.entity.Exceed;
import com.rate.web.alarm.monitor.service.ExceedService;
import com.rate.web.site.service.SiteService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 警报页面查询
 *
 * @ClassName AlertController
 * @Author liuyong
 * @Date 2019/6/5 11:24
 * @Version 1.0
 **/
@RestController
@RequestMapping("api/alert")
public class AlertController {

    @Autowired
    private ApiLoginService apiLoginService;

    @Autowired
    private ExceedService exceedService;

    @Autowired
    private SiteService siteService;
    /**
     * 获取警报数据
     *
     * @param params
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("getListAlert")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "code", value = "令牌", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "startTime", value = "起始时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "siteId", value = "站点编码", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pageNum", value = "起始值", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示的数量", paramType = "query", dataType = "Integer"),
            @ApiImplicitParam(name = "siteType", value = "站点类型", paramType = "query", dataType = "String")
    })
    public Result getListAlert(@RequestParam Map<String, Object> params, Integer pageNum, Integer pageSize) {
        try {
            if (params == null) {
                return Result.error(CodeMsg.PARAMETER_ISNULL);
            }
            boolean checkLogin = apiLoginService.checkLogin((String) params.get("userId"), (String) params.get("code"));
            if (!checkLogin) {
                //用户未登录
                return Result.error(CodeMsg.VALIDATA_ERROR);
            }
            String siteCode = (String) params.get("siteId");
            if(siteCode ==null || siteCode.equals("")){
                List<String> siteIds = siteService.querySiteByUser((String) params.get("userId"));
                if(siteIds == null || siteIds.isEmpty()){
                    return Result.success();
                }
                params.put("siteIds", siteIds);
            }else {
                params.put("siteIds", siteCode.split(","));
            }
            List<AlarmEntity> alertListByParams = exceedService.getAlertListByParams2(params, pageNum, pageSize);
            return Result.success(alertListByParams);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(CodeMsg.SERVER_BUSY);
        }
    }
}
