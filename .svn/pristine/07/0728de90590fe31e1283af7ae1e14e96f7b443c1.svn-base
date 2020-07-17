package com.rate.system.rate_system.controller;

import com.rate.system.rate_system.annotation.Log;
import com.rate.system.rate_system.entity.FileDO;
import com.rate.system.rate_system.entity.Menu;
import com.rate.system.rate_system.entity.Tree;
import com.rate.system.rate_system.service.FileService;
import com.rate.system.rate_system.service.MenuService;
import com.rate.system.rate_system.utils.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController extends BaseController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MenuService menuService;
	@Autowired
	FileService fileService;
	@GetMapping({ "/", "" })
	String welcome(Model model) {
		
		return "redirect:/loginNew";
	}

	@Log("请求访问主页")
	@GetMapping({ "/index" })
	String index(Model model) {
		List<Tree<Menu>> menus = menuService.listMenuTree(getUserId());
		model.addAttribute("menus", menus);
		String name = getUser().getName();
		String picId = getUser().getPicId();
		model.addAttribute("name", name);
		FileDO fileDO = null;
		if(picId != null) {
			fileDO = fileService.get(getUser().getPicId());
		}
		if(fileDO!=null&&fileDO.getUrl()!=null){
			if(fileService.isExist(fileDO.getUrl())){
				model.addAttribute("picUrl",fileDO.getUrl());
			}else {
				model.addAttribute("picUrl","/img/a1.jpg");
			}
		}else {
			model.addAttribute("picUrl","/img/a1.jpg");
		}
		model.addAttribute("username", getUser().getUsername());
		model.addAttribute("userId", getUser().getUserId());
		model.addAttribute("roleId", getRoleId());
		return "index";
	}

	@GetMapping("/login")
	String login() {
		return "loginNew";
	}

	@Log("获取登录验证码")
	@GetMapping({ "/randomCode" })
	public void getVerify(HttpServletRequest request, HttpServletResponse response) {
		try {
			//设置相应类型,告诉浏览器输出的内容为图片
			response.setContentType("image/jpeg");
			//设置响应头信息，告诉浏览器不要缓存此内容
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expire", 0);
			RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
			//输出验证码图片方法
			randomValidateCode.getRandcode(request, response);
		} catch (Exception e) {
			logger.error("获取验证码失败>>>> ", e);
		}
	}
	
	@Log("登录")
	@PostMapping("/login")
	@ResponseBody
	R ajaxLogin(String username, String password, String yanzm, HttpSession session) {
		String randomCode = (String) session.getAttribute(RandomValidateCodeUtil.RANDOM_CODE_KEY);
		if(!randomCode.equalsIgnoreCase(yanzm)){
			return R.error("验证码错误");
		}
		password = MD5Utils.encrypt(username, password);
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			return R.ok();
		} catch (AuthenticationException e) {
			return R.error("用户或密码错误");
		}
	}
	@Log("退出")
	@GetMapping("/logout")
	String logout() {
		ShiroUtils.logout();
		return "redirect:/loginNew";
	}

	@GetMapping("/main")
	String main() {
		return "main";
	}
	@GetMapping("/desktop")
	String desktop(Model model, String mapType) {
	    model.addAttribute("mapType", mapType);
	    model.addAttribute("desktop", "666");
	    Long roleId = getRoleId();
		model.addAttribute("showLegend",false);
	    if(roleId != 78 && roleId != 79 && roleId != 81 && roleId != 82){
			model.addAttribute("showLegend",true);
		}
		return "desktop";
	}
	/**
	 * 切换主题
	 */
	@RequestMapping(value = "switchSkin/{skinName}")
	public String switchSkin(@PathVariable String skinName, HttpServletRequest request, HttpServletResponse response) {
		if (StringUtils.isNotBlank(skinName) && !"select".equals(skinName)){
			Cookie cookie = new Cookie("skinName_" + getUser().getUserId(), skinName);
			response.addCookie(cookie);
			return "redirect:/index";
		}
		return "/switchSkin";
	}

}
