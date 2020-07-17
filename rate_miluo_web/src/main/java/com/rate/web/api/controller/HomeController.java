package com.rate.web.api.controller;

import com.rate.system.rate_system.api.service.ApiLoginService;
import com.rate.system.rate_system.utils.CodeMsg;
import com.rate.system.rate_system.utils.Result;
import com.rate.web.api.service.HourStatementsService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * APP首页接口
 *
 * @ClassName HomeController
 * @Author LiuYong
 * @Date 2019/6/4 9:34
 * @Version 1.0
 **/
@RestController
@RequestMapping("api/home")
public class HomeController {

    @Autowired
    private ApiLoginService apiLoginService;

    @Autowired
    private HourStatementsService hourStatementsService;

    /**
     * 首页查询所有数据(空气站)
     *
     * @param userId
     * @param code
     * @param category
     * @return
     * @author LiuYong
     */
    @RequestMapping("getDataByStandard")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "code", value = "令牌", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "category", value = "站点类型", paramType = "query", dataType = "String")
    })
    public Result getHourDataByStandard(String userId, String code, String category) {
        try {
            boolean checkLogin = apiLoginService.checkLogin(userId, code);
            if (!checkLogin) {
                //用户未登录
                return Result.error(CodeMsg.VALIDATA_ERROR);
            }
            //首页所有数据的集合
            return Result.success(hourStatementsService.getDataBySiteCategory(category, 1));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(CodeMsg.SERVER_BUSY);
        }
    }
}
