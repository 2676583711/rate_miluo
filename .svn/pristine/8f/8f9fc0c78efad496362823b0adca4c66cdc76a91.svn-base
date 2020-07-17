package com.rate.system.rate_system.api.controller;

import com.rate.system.rate_system.annotation.Log;
import com.rate.system.rate_system.api.entity.ApiSteup;
import com.rate.system.rate_system.api.service.ApiSteupService;
import com.rate.system.rate_system.controller.BaseController;
import com.rate.system.rate_system.utils.CodeMsg;
import com.rate.system.rate_system.utils.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authc.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 设置开关接口
 * @author	zhangbiao
 * @date	2018年9月12日 
 */
@RestController
@RequestMapping("api/steup")
public class ApiSteupController extends BaseController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ApiSteupService apiSteupService;
	
	@Log("获取用户设置信息")
	@PostMapping("/getByUserId")
	@ApiOperation(value="获取用户设置信息", notes="获取用户设置信息")
	@ApiImplicitParams({ 
        @ApiImplicitParam(name = "code",value = "用户令牌", paramType="query",dataType = "String"),
        @ApiImplicitParam(name = "userId",value = "用户ID", paramType="query" ,dataType = "Long"),
    })
	Result getByUserId(Long userId,String code) {
		 try {
			 ApiSteup apiSteup = apiSteupService.getByUserId(userId);
				return Result.success(apiSteup);
			} catch (AuthenticationException e) {
				return Result.error(CodeMsg.SERVER_EXCEPTION);
			}
	}
	
	@Log("超限报警开关")
	@GetMapping("/updateOverrun")
	@ApiOperation(value="设置-超限开关", notes="设置-超限开关")
	@ApiImplicitParams({ 
        @ApiImplicitParam(name = "code",value = "用户令牌", paramType="query",dataType = "String"),
        @ApiImplicitParam(name = "userId",value = "用户ID", paramType="query" ,dataType = "Long"),
        @ApiImplicitParam(name = "overrun",value = "超限开关，0/null:开启，1：关闭", paramType="query" ,dataType = "Integer")
    })
	Result updateOverrun(Integer overrun,Long userId,String code) {
		 try {
			 apiSteupService.updateOverrunOnOff(overrun, userId);
				return Result.success();
			} catch (AuthenticationException e) {
				return Result.error(CodeMsg.SERVER_EXCEPTION);
			}
	}
	
	@Log("超限报警开关")
	@GetMapping("/updateOperation")
	@ApiOperation(value="设置-运维开关", notes="设置-运维开关")
	@ApiImplicitParams({ 
        @ApiImplicitParam(name = "code",value = "用户令牌", paramType="query",dataType = "String"),
        @ApiImplicitParam(name = "userId",value = "用户ID", paramType="query" ,dataType = "Long"),
        @ApiImplicitParam(name = "operation",value = "运维开关，0/null:开启，1：关闭", paramType="query" ,dataType = "Integer")
    })
	Result updateOperation(Integer operation,Long userId,String code) {
		 try {
			 apiSteupService.updateOperationOnOff(operation, userId);
				return Result.success();
			} catch (AuthenticationException e) {
				return Result.error(CodeMsg.SERVER_EXCEPTION);
			}
	}
	
	@Log("超限报警开关")
	@GetMapping("/updateSos")
	@ApiOperation(value="设置-报警开关", notes="设置-报警开关")
	@ApiImplicitParams({ 
        @ApiImplicitParam(name = "code",value = "用户令牌", paramType="query",dataType = "String"),
        @ApiImplicitParam(name = "userId",value = "用户ID", paramType="query" ,dataType = "Long"),
        @ApiImplicitParam(name = "sos",value = "报警开关，0/null:开启，1：关闭", paramType="query" ,dataType = "Integer")
    })
	Result updateSos(Integer sos,Long userId,String code) {
		 try {
			 apiSteupService.updateSosOnOff(sos, userId);
				return Result.success();
			} catch (AuthenticationException e) {
				return Result.error(CodeMsg.SERVER_EXCEPTION);
			}
	}

}
