package com.rate.system.rate_system.controller;

import com.rate.system.rate_system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.rate.system.rate_system.entity.User;
import com.rate.system.rate_system.service.DeptService;
import com.rate.system.rate_system.utils.ShiroUtils;

@Controller
public class BaseController {
	
	@Autowired
    private DeptService deptService;
	@Autowired
	private RoleService roleService;
	
	public User getUser() {
		User user=ShiroUtils.getUser();
		return user;
	}

	public Long getUserId() {
		return getUser().getUserId();
	}

	public String getUsername() {
		return getUser().getUsername();
	}
	
	public Long getDeptId() {
		return getUser().getDeptId();
	}
	
	public Long getParentId() {
		return deptService.get(getDeptId()).getParentId();
	}
	
	public Long getParentId(Long userId) {
		return deptService.get(getDeptId()).getParentId();
	}
	
	public Integer getPwId() {
		return getUser().getPwId();
	}

	public Long getRoleId(){return roleService.getRoleIdByUserId(getUserId());}
}