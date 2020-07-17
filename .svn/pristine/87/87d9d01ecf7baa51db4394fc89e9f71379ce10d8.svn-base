package com.rate.web.api.controller;

import com.rate.system.rate_system.api.service.ApiLoginService;
import com.rate.system.rate_system.service.UserService;
import com.rate.system.rate_system.utils.CodeMsg;
import com.rate.system.rate_system.utils.Result;
import com.rate.web.site.service.SiteService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 公共访问
 *
 * @ClassName CommentController
 * @Author liuyong
 * @Date 2019/6/5 9:25
 * @Version 1.0
 **/
@Controller
@RequestMapping("api/comment")
public class CommentController {

    @Autowired
    private SiteService siteService;

    @Autowired
    private ApiLoginService apiLoginService;

    @Autowired
    private UserService userService;

    /**
     * 获取站点树
     *
     * @param userId
     * @param code
     * @return
     */
    @ResponseBody
    @RequestMapping("getSiteTree")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "code", value = "令牌", paramType = "query", dataType = "String")
    })
    public Result getSiteTree(String userId, String code) {
        try {
            boolean checkLogin = apiLoginService.checkLogin(userId, code);
            if (!checkLogin) {
                //用户未登录
                return Result.error(CodeMsg.VALIDATA_ERROR);
            }
            Map<String, Object> params = new HashMap<>(2);
            params.put("userId", Long.parseLong(userId));
            //站点树
            return Result.success(siteService.findSiteTreeByType(params));
//            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(CodeMsg.SERVER_BUSY);
        }
    }

    /**
     * 获取所有用户
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("getUserList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "code", value = "令牌", paramType = "query", dataType = "String")
    })
    public Result getUserByUser(@RequestParam Map<String, Object> params) {
        try {
            if (params == null) {
                return Result.error(CodeMsg.PARAMETER_ISNULL);
            }
            boolean checkLogin = apiLoginService.checkLogin((String) params.get("userId"), (String) params.get("code"));
            if (!checkLogin) {
                //用户未登录
                return Result.error(CodeMsg.VALIDATA_ERROR);
            }
            return Result.success(userService.queryAll());
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(CodeMsg.SERVER_BUSY);
        }
    }

    @ResponseBody
    @RequestMapping("getSiteList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "code", value = "令牌", paramType = "query", dataType = "String")
    })
    public Result getSiteList(@RequestParam("userId") String userId, @RequestParam("code") String code) {
        try {
            boolean checkLogin = apiLoginService.checkLogin(userId, code);
            if (!checkLogin) {
                //用户未登录
                return Result.error(CodeMsg.VALIDATA_ERROR);
            }
            //获取该角色下的所有站点
            Map<String, Object> resultMap = siteService.querySiteList(userId);
            return Result.success(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(CodeMsg.SERVER_BUSY);
        }
    }

    /**
     * 跳转二维码页面
     *
     * @return
     */
    @RequestMapping("/goPage")
    public String goPage() {
        return "/app/ewm";
    }

    /**
     * 跳转下载页面
     *
     * @return
     */
    @RequestMapping("/download")
    public String download() {
        return "/app/download";
    }

}
