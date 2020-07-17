package com.rate.system.rate_system.service.impl;

import java.util.*;

import com.rate.system.rate_system.dao.*;
import com.rate.system.rate_system.entity.SitePower;
import com.rate.system.rate_system.utils.IdGen;
import org.beetl.sql.core.db.KeyHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rate.system.rate_system.entity.Role;
import com.rate.system.rate_system.entity.RoleMenu;
import com.rate.system.rate_system.service.RoleService;


@Service
public class RoleServiceImpl implements RoleService {

    public static final String ROLE_ALL_KEY = "\"role_all\"";

    public static final String DEMO_CACHE_NAME = "role";

    @Autowired
    RoleDao roleMapper;
    @Autowired
    RoleMenuDao roleMenuMapper;
    @Autowired
    UserDao userMapper;
    @Autowired
    UserRoleDao userRoleMapper;
    @Autowired
    SitePowerDao sitePowerDao;

    @Override
    public List<Role> list() {
        return roleMapper.all();
    }


    @Override
    public List<Role> list(Long userId) {
        List<Long> rolesIds = userRoleMapper.listRoleId(userId);
        List<Role> roles = roleMapper.all();
        for (Role roleDO : roles) {
            roleDO.setRoleSign("false");
            for (Long roleId : rolesIds) {
                if (Objects.equals(roleDO.getRoleId(), roleId)) {
                    roleDO.setRoleSign("true");
                    break;
                }
            }
        }
        return roles;
    }

    @Override
    public Long getRoleIdByUserId(Long userId) {
        return userRoleMapper.findRoleIdByUserId(userId);
    }

    @Transactional
    @Override
    public void save(Role role) {
    	KeyHolder keyHolder = roleMapper.insertReturnKey(role);
        Long roleId = keyHolder.getLong();
        List<Long> menuIds = role.getMenuIds();
        List<RoleMenu> rms = new ArrayList<>();
        for (Long menuId : menuIds) {
            RoleMenu rmDo = new RoleMenu();
            rmDo.setRoleId(roleId);
            rmDo.setMenuId(menuId);
            rms.add(rmDo);
        }
        roleMenuMapper.deleteByRoleId(roleId);
        if (rms.size() > 0) {
            roleMenuMapper.insertBatch(rms);
        }
    }

    @Transactional
    @Override
    public int remove(Long id) {
//         Map<String,Object> param = new HashMap<>();
//         param.put("roleId", id);
         int count = roleMapper.deleteById(id);
        roleMenuMapper.deleteById(id);
        return count;
    }

    @Override
    public Role get(Long id) {
    	Role role = new Role();
    	role.setRoleId(id);
        return roleMapper.templateOne(role);
    }

    @Override
    public int update(Role role) {
        int r = roleMapper.updateTemplateById(role);
        List<Long> menuIds = role.getMenuIds();
        Long roleId = role.getRoleId();
//        Map<String,Object> param = new HashMap<>();
//        param.put("roleId", roleId);
//        roleMenuMapper.deleteById(param);
//        roleMenuMapper.deleteById(roleId);
        roleMenuMapper.deleteByRoleId(roleId);
        List<RoleMenu> rms = new ArrayList<>();
        for (Long menuId : menuIds) {
            RoleMenu rmDo = new RoleMenu();
            rmDo.setRoleId(roleId);
            rmDo.setMenuId(menuId);
            rms.add(rmDo);
        }
        if (rms.size() > 0) {
            roleMenuMapper.insertBatch(rms);
        }
        return r;
    }

    @Override
    public int batchremove(Long[] ids) {
    	int r = 0;
		try {
			//        int r = roleMapper.batchRemove(ids);
			//        int r = roleMapper.deleteById(ids);
			for (Long id : ids) {
				r = roleMapper.deleteById(id);
			} 
			return r;
		} catch (Exception e) {
			r = -1;
			return r;
		}
    }

    @Override
    public int updateSitePower(SitePower sitePower) {
        //获得所有站点编码
        List<String> siteCodes = sitePower.getSiteCodes();
        //角色id
        Long roleId = sitePower.getRoleId();
        // 排除所有父节点的值 LS420104007
//        String[] parent = { "-1", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
//        List<String> parentIds = new ArrayList<String>();
//        Collections.addAll(parentIds, parent);
        //先删除 再重新加
        sitePowerDao.deleteByRoleId(roleId);
        List<SitePower> sitePowers = new ArrayList<>();
        for (String siteCode : siteCodes) {
//            if (parentIds.contains(siteCode)) {
//                continue;
//            }
            SitePower sitePowerDO = new SitePower();
            sitePowerDO.setId(IdGen.uuid());
            sitePowerDO.setRoleId(roleId);
            sitePowerDO.setSiteCode(siteCode);
            sitePowers.add(sitePowerDO);

        }
        try {
            if (sitePowers.size() > 0) {
                sitePowerDao.insertBatch(sitePowers);
            }
            return sitePowers.size();
        } catch (Exception e) {
            return -1;
        }
    }


    @Override
    public List<String> getSitePowerList(Long  roleId) {
        return sitePowerDao.getSitePowerList(roleId);
    }

    @Override
    public List<Long> getRoleByname(String roleName) {
        return roleMapper.getRoleByname(  roleName);
    }
}