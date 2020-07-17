package com.rate.system.rate_system.service.impl;

import com.rate.system.rate_system.config.BootdoConfig;
import com.rate.system.rate_system.dao.*;
import com.rate.system.rate_system.entity.*;
import com.rate.system.rate_system.service.FileService;
import com.rate.system.rate_system.service.UserService;
import com.rate.system.rate_system.utils.*;
import org.apache.commons.lang.ArrayUtils;
import org.beetl.sql.core.engine.PageQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.*;


@Transactional
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao userMapper;
	@Autowired
	UserRoleDao userRoleMapper;
	@Autowired
	DeptDao deptMapper;
	@Autowired
    SitePowerDao sitePowerDao;
	@Autowired
	private FileService sysFileService;
	@Autowired
	private BootdoConfig bootdoConfig;
	@Autowired
	private PollutantFactoryNewDao pollutantFactoryDao;
	@Autowired
	private DeptDao deptDao;

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Override
	public User get(Long userId) {
		List<Long> roleIds = userRoleMapper.listRoleId(userId);
		User user = userMapper.single(userId);
		user.setDeptName(deptMapper.single(user.getDeptId()).getName());
		user.setRoleIds(roleIds);
		return user;
	}

	@Override
	public PageQuery<User> list(PageQuery<User> paras, Long deptId) {
		Integer delFlag = deptMapper.single(deptId).getDelFlag();
		// 根据部门权限查询
		PageQuery<User> page = userMapper.queryByCondtion(paras, deptId,delFlag);
		return page;
	}

	@Override
	public List<User> listAll(Map<String, Object> params, Long deptId) {
		Integer delFlag = deptMapper.single(deptId).getDelFlag();
		List<User> page = userMapper.listAll(params, deptId,delFlag);
		return page;
	}

	@Override
	public List<User> getuserByname(String username2) {
		return userMapper.getuserByname(username2);
	}

	@Transactional
	@Override
	public Long save(User user) {
		Long id;
		try {
			id = userMapper.insertReturnKey(user).getLong();//返回新增的userId

			Long roleId = user.getRoleIds().get(0);
			UserRole ur = new UserRole();
			ur.setUserId(id);
			ur.setRoleId(roleId);
			userRoleMapper.insert(ur);
		}catch (Exception e) {
			return 0L;
		}
		return id;
	}

	@Override
	@Transactional
	public int update(User user) {
		int r = userMapper.updateUserById(user);
		Long roleId = user.getRoleIds().get(0);

		//更新
		UserRole ur = new UserRole();
		userRoleMapper.updateByUserId(user.getUserId(), roleId);
		return r;
	}

	@Transactional
	@Override
	public int remove(Long userId) {
		sitePowerDao.deleteByUserId(userId);
		userRoleMapper.removeByUserId(userId);
		return userMapper.deleteById(userId);
	}

	@Override
	public boolean exit(Map<String, Object> params) {
		boolean exit;
//		exit = userMapper.all().size() > 0;
		List<User> list = userMapper.exitUser(params);
		exit = list.size() > 0;
		return exit;
	}

	@Override
	public Set<String> listRoles(Long userId) {
		return null;
	}

	@Override
	public int resetPwd(UserVO userVO,User userDO) throws Exception {
		if(Objects.equals(userVO.getUser().getUserId(),userDO.getUserId())){
			if(Objects.equals(MD5Utils.encrypt(userDO.getUsername(),userVO.getPwdOld()),userDO.getPassword())){
				userDO.setPassword(MD5Utils.encrypt(userDO.getUsername(),userVO.getPwdNew()));
				return userMapper.updateById(userDO);
			}else{
				throw new Exception("输入的旧密码有误！");
			}
		}else{
			throw new Exception("你修改的不是你登录的账号！");
		}
	}
	@Override
	public int adminResetPwd(UserVO userVO) throws Exception {
		User userDO =get(userVO.getUser().getUserId());
		if("admin".equals(userDO.getUsername())){
			throw new Exception("超级管理员的账号不允许直接重置！");
		}
		userDO.setPassword(MD5Utils.encrypt(userDO.getUsername(), userVO.getPwdNew()));
		return userMapper.updateById(userDO);


	}

	@Transactional
	@Override
	public int batchremove(Long[] userIds) {
		int count = 0;
		for(int i=0;i<userIds.length;i++) {
			sitePowerDao.deleteByUserId(userIds[i]);
			userRoleMapper.removeByUserId(userIds[i]);
			userMapper.deleteById(userIds[i]);
			count++;
		}
		return count;
	}

	@Override
	public Tree<Dept> getTree() {
		List<Tree<Dept>> trees = new ArrayList<Tree<Dept>>();
		List<Dept> depts = deptMapper.all();
		Long[] pDepts = deptMapper.listParentDept();
		Long[] uDepts = userMapper.listAllDept();
		Long[] allDepts = (Long[]) ArrayUtils.addAll(pDepts, uDepts);
		for (Dept dept : depts) {
			if (!ArrayUtils.contains(allDepts, dept.getDeptId())) {
				continue;
			}
			Tree<Dept> tree = new Tree<Dept>();
			tree.setId(dept.getDeptId().toString());
			tree.setParentId(dept.getParentId().toString());
			tree.setText(dept.getName());
			Map<String, Object> state = new HashMap<>(16);
			state.put("opened", true);
			state.put("mType", "dept");
			tree.setState(state);
			trees.add(tree);
		}
		List<User> users = userMapper.all();
		for (User user : users) {
			Tree<Dept> tree = new Tree<Dept>();
			tree.setId(user.getUserId().toString());
			tree.setParentId(user.getDeptId().toString());
			tree.setText(user.getName());
			Map<String, Object> state = new HashMap<>(16);
			state.put("opened", true);
			state.put("mType", "user");
			tree.setState(state);
			trees.add(tree);
		}
		// 默认顶级菜单为０，根据数据库实际情况调整
		Tree<Dept> t = BuildTree.build(trees);
		return t;
	}

	@Override
	public int updatePersonal(User userDO) {
		return userMapper.updateById(userDO);
	}

    @Override
    public Map<String, Object> updatePersonalImg(MultipartFile file, String avatar_data, Long userId) throws Exception {
		String fileName = file.getOriginalFilename();
		fileName = FileUtil.renameToUUID(fileName);
		FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/" + fileName, new Date());
		//获取图片后缀
		String prefix = fileName.substring((fileName.lastIndexOf(".")+1));
		String[] str=avatar_data.split(",");
		//获取截取的x坐标
		int x = (int)Math.floor(Double.parseDouble(str[0].split(":")[1]));
		//获取截取的y坐标
		int y = (int)Math.floor(Double.parseDouble(str[1].split(":")[1]));
		//获取截取的高度
		int h = (int)Math.floor(Double.parseDouble(str[2].split(":")[1]));
		//获取截取的宽度
		int w = (int)Math.floor(Double.parseDouble(str[3].split(":")[1]));
		//获取旋转的角度
		int r = Integer.parseInt(str[4].split(":")[1].replaceAll("}", ""));
		try {
			BufferedImage cutImage = ImageUtils.cutImage(file,x,y,w,h,prefix);
			BufferedImage rotateImage = ImageUtils.rotateImage(cutImage, r);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			boolean flag = ImageIO.write(rotateImage, prefix, out);
			//转换后存入数据库
			byte[] b = out.toByteArray();
			FileUtil.uploadFile(b, bootdoConfig.getUploadPath(), fileName);
		} catch (Exception e) {
			throw  new Exception("图片裁剪错误！！");
		}
		Map<String, Object> result = new HashMap<>();
		sysFileService.save(sysFile);
		User userDO = new User();
		userDO.setUserId(userId);
		userDO.setPicId(sysFile.getId());
		if(userMapper.updateById(userDO)>0){
			result.put("url",sysFile.getUrl());
		}
		return result;
    }

	@Override
	public long count(Map<String, Object> map,Long deptId) {
		Integer delFlag = deptMapper.single(deptId).getDelFlag();
		if(delFlag == 7) {
			return userMapper.getCount(map,deptId);
		}else {
			return userMapper.getCount2(map);
		}
	}

	@Override
	public Long getUserId(User user) {
		return userMapper.createQuery().andEq("name", user.getName()).single().getUserId();
	}

	@Override
	public User findUserByDeptId(Long deptId) {
		return userMapper.createQuery().andEq("dept_id", deptId).single();
	}

	@Override
	public List<User> getUserList(Long deptId, Long parentId) {
		return userMapper.getUserList(deptId,parentId);
	}

	@Override
	public List<User> getUsersByDeptIdPage(Query query, Long deptId) {
		return userMapper.getUsersByDeptIdPage(query,deptId);
	}

	@Override
	public long countByDeptId(Query query, Long deptId) {
		return userMapper.countByDeptId(query,deptId);
	}

	@Override
	public List<User> getUsersByDeptId(Long deptId) {
		return userMapper.getUsersByDeptId(deptId);
	}
	
	@Override
	public User getUsername(String username){
		return userMapper.getUserName(username);
	}

	@Override
	public List<User> getListByDeptId(Long deptId) {
		return userMapper.getCompanyNameByDeptId(deptId);
	}

	@Override
	public List<PollutantFactoryNew> getPollutantFactoryList() {
		return pollutantFactoryDao.getPollutantFactoryList();
	}
/**
 * @Param [deptId]
 * @Return java.util.List<com.rate.system.rate_system.entity.User>
 * @author xiaoshi
 * @Description
 * @Date 2019/6/3
 * @Time 18:22
 **/
	@Override
	public List<User> findAll(Long deptId) {
		Long parentId = deptDao.single(deptId).getParentId();//获取当前部门所属公司的公司id
		if(parentId!=null && parentId==20){
			return userMapper.all();
		}
		return userMapper.findAllByCompany(parentId);
	}
   /**
    * @Param [deptId]
    * @Return java.util.List<com.rate.system.rate_system.entity.User>
    * @author xiaoshi
    * @Description
    * @Date 2019/6/3
    * @Time 20:27
    **/
	@Override
	public List<User> findUsersByDeptId(Long deptId) {
		if(deptId==null) return userMapper.all();
		return userMapper.createQuery().andEq("dept_id", deptId).select();
	}

	@Override
	public List<User> queryAll() {
		return userMapper.all();
	}

	// 新增站点权限
	@Override
	public void saveSitePower(List<String> siteIdList, Long userId) {
		List<SitePower> sitePowers = new ArrayList<>();
		for (int i=0; i<siteIdList.size(); i++){
			SitePower sitePower = new SitePower();
			sitePower.setId(IdGen.uuid());
			sitePower.setUserId(userId);
			sitePower.setSiteCode(siteIdList.get(i));
			sitePowers.add(sitePower);
			sitePowerDao.insert(sitePower);
		}
	}

	@Override
	public List<String> getSitePowerList(Long userId) {
		return sitePowerDao.getSiteCodesByUserId(userId);
	}

	// 更新站点权限表
	@Override
	public void updateSitePower(List<String> siteIdList, Long userId) {
		// 先判断是否存在userId
		int count = sitePowerDao.getCountByUserId(userId);
		if(count > 0){
			//先删除，再添加
			sitePowerDao.deleteByUserId(userId);
		}
		saveSitePower(siteIdList, userId);
	}

	@Override
	public List<String> getAllSiteId() {
		return sitePowerDao.getAllSiteId();
	}



	@Override
    public User queryById(String userId) {
        long id = 0;
        try {
            id = Long.parseLong(userId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
        return userMapper.single(id);
    }
}
