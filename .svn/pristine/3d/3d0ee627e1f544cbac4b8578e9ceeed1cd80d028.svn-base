package com.rate.system.rate_system.shiro;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.rate.system.rate_system.dao.UserDao;
import com.rate.system.rate_system.entity.User;
import com.rate.system.rate_system.service.MenuService;
import com.rate.system.rate_system.utils.ShiroUtils;

public class UserRealm extends AuthorizingRealm{

	/**
	 * 点击某个菜单时会调用这个方法
	 * 执行顺序：拦截器-->获取方法-->找到方法的注解权限串-->找到当前的用户subject-->找用户在缓存中有无权限串，无则-->
	 * 调用本方法获取权限串-->放到缓存中-->判断菜单权限串是否包含在用户权限串集合中-->回归整个线路再次返回到拦截器...
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		Long userId = ShiroUtils.getUserId();
		MenuService menuService = ApplicationContextRegister.getBean(MenuService.class);
		//perms类似于这样的字符串：[common:dict:remove, sys:role:remove, common:dict:batchRemove…]
		Set<String> perms = menuService.listPerms(userId);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(perms);
		//返回了用户的权限串后，就会返回到另一个母方法中去判断是否有权限，由于有缓存，所以每个用户第一次进入时会读取一次权限串，后面将会读取缓存
		return info;
	}

	/**
	 * 身份验 证,登录时会调用这个方法
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		Map<String, Object> map = new HashMap<>(16);
		map.put("username", username);
		String password = new String((char[]) token.getCredentials());
		User u = new User();
		u.setUsername(username);
		UserDao userMapper = ApplicationContextRegister.getBean(UserDao.class);
		// 查询用户信息
		User user = userMapper.templateOne(u);

		// 账号不存在
		if (user == null) {
			throw new UnknownAccountException("账号或密码不正确");
		}

		// 密码错误
		if (!password.equals(user.getPassword())) {
			throw new IncorrectCredentialsException("账号或密码不正确");
		}

		// 账号锁定
		if (user.getStatus() == 0) {
			throw new LockedAccountException("账号已被锁定,请联系管理员");
		}
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
		return info;
	}

}
