package com.rate.system.rate_system.api.controller;

import com.rate.system.rate_system.annotation.Log;
import com.rate.system.rate_system.api.entity.Verification;
import com.rate.system.rate_system.api.service.ApiLoginService;
import com.rate.system.rate_system.controller.BaseController;
import com.rate.system.rate_system.entity.Dept;
import com.rate.system.rate_system.entity.User;
import com.rate.system.rate_system.service.DeptService;
import com.rate.system.rate_system.service.UserService;
import com.rate.system.rate_system.utils.CodeMsg;
import com.rate.system.rate_system.utils.IdGen;
import com.rate.system.rate_system.utils.MD5Utils;
import com.rate.system.rate_system.utils.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 * 手机app登陆
 * @author	zhangbiao
 * @date	2018年9月07日 
 */
@RestController
@RequestMapping("api/sys")
public class ApiLoginController extends BaseController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ApiLoginService apiLoginService;
	@Autowired
	UserService userService;
	@Autowired
	DeptService deptService;
	
	

	
	/**
	 * APP端登陆
	 * @ApiOperation:接口功能描述
	 * @ApiImplicitParam：接口传入参数描述
	 * @return
	 */
	@Log("app端登录")
	@PostMapping("/applogin")
	@ApiOperation(value="APP端登陆", notes="APP端登陆验证")
	@ApiImplicitParams({ 
	        @ApiImplicitParam(name = "username",value = "用户名", paramType="query",dataType = "String"),
				@ApiImplicitParam(name = "password",value = "密码", paramType="query" ,dataType = "String"),
	})
	Result applogin(String username,String password,HttpServletRequest request) {
		password = MD5Utils.encrypt(username, password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
        	Subject subject = SecurityUtils.getSecurityManager().login(SecurityUtils.getSubject(), token);
        	//如果用户未正常退出(如在另外一台设备登陆，直接删除APP重新下载登陆)，又登陆了,让原来的登陆状态失效
        	User user = userService.getUsername(username);
        	Verification vf = apiLoginService.selectByUserId(user.getUserId());
        	if(vf!=null&&vf.getType()==0){
        		vf.setType(1L);
        		vf.setUpdateTime(new Date());
        		apiLoginService.updateType(vf);
        	}
        	//根据得到的userId 获取对于的roleId
			User userInfo = userService.get(user.getUserId());
			//存放登陆信息
        	Verification verification = new Verification();
        	String code=IdGen.uuid();
        	verification.setCode(code);
        	verification.setStartTime(new Date());
        	verification.setUserId(user.getUserId());
        	verification.setType(0L);
        	apiLoginService.insert(verification);
        	//返回登陆数据
        	Map<String, Object> dataMap = new HashMap<String, Object>();  
			dataMap.put("code", code);  
			dataMap.put("userId", userInfo.getUserId());
			dataMap.put("role",userInfo.getRoleIds());
			return Result.success(dataMap);
		} catch (AuthenticationException e) {
			e.printStackTrace();
			return Result.error(CodeMsg.LONIN_ERROR);
		}
	}
	
	@Log("自动验证是否存在该用户")
	@GetMapping("/appAutoCode")
	@ApiOperation(value="自动登陆验证", notes="APP端自动验证")
	@ApiImplicitParams({ 
        @ApiImplicitParam(name = "code",value = "用户令牌", paramType="query",dataType = "String"),
        @ApiImplicitParam(name = "userId",value = "用户ID", paramType="query" ,dataType = "Long"),
    })
	Result AutoVerification(String code,Long userId,HttpServletRequest request) {
        try {
        	if(!apiLoginService.appAutoCode(code,userId)){
        		return Result.error(CodeMsg.VALIDATA_ERROR);
        	}
			return Result.success();
		} catch (AuthenticationException e) {
			return Result.error(CodeMsg.LONIN_ERROR);
		}
	}

	@Log("app端退出")
	@GetMapping("/logout")
	@ApiOperation(value="APP端退出登录", notes="APP端退出登录")
	@ApiImplicitParams({ 
        @ApiImplicitParam(name = "userId",value = "用户ID", paramType="query" ,dataType = "Long")
    })
	Result logout(Long userId) {
		 try {
			 Verification vf = apiLoginService.selectByUserId(userId);
	        	if(vf!=null&&vf.getType()==0){
	        		vf.setType(1L);
	        		vf.setUpdateTime(new Date());
	        		apiLoginService.updateType(vf);
	        	}
				return Result.success();
			} catch (AuthenticationException e) {
				return Result.error(CodeMsg.SERVER_EXCEPTION);
			}
	}
	
	@Log("获取个人信息")
	@PostMapping("/personalInformation")
	@ApiOperation(value="系统设置-获取个人信息", notes="系统设置-获取个人信息")
	@ApiImplicitParams({ 
        @ApiImplicitParam(name = "code",value = "用户令牌", paramType="query",dataType = "String"),
        @ApiImplicitParam(name = "userId",value = "用户ID", paramType="query" ,dataType = "Long"),
    })
	Result personalInformation(String code,Long userId) {
		 try {
			//验证是否处于登陆状态
			 if(!apiLoginService.appAutoCode(code,userId)){
	        		return Result.error(CodeMsg.VALIDATA_ERROR);
	        	}
				apiLoginService.appAutoCode(code,userId);
				User user = userService.get(userId);
				Dept dept1 = deptService.get(user.getDeptId());
				Dept dept = deptService.get(dept1.getParentId());
				Map<String, Object> dataMap = new HashMap<String, Object>();
				dataMap.put("username", user.getUsername());
				dataMap.put("name", user.getName());
				dataMap.put("mobile", user.getMobile());
				dataMap.put("deptName",dept.getName());
				return Result.success(dataMap);
			} catch (AuthenticationException e) {
				return Result.error(CodeMsg.SERVER_EXCEPTION);
			}
	}

}
