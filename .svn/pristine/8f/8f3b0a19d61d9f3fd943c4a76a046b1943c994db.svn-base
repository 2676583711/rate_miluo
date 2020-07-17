package com.rate.system.rate_system.controller;

import com.alibaba.fastjson.JSONArray;
import com.rate.system.rate_system.annotation.Log;
import com.rate.system.rate_system.config.Constant;
import com.rate.system.rate_system.dao.SitePowerDao;
import com.rate.system.rate_system.dao.UserDao;
import com.rate.system.rate_system.dao.UserRoleDao;
import com.rate.system.rate_system.entity.*;
import com.rate.system.rate_system.service.DeptService;
import com.rate.system.rate_system.service.RoleService;
import com.rate.system.rate_system.service.UserService;
import com.rate.system.rate_system.utils.MD5Utils;
import com.rate.system.rate_system.utils.PageUtils;
import com.rate.system.rate_system.utils.R;
import com.rate.system.rate_system.utils.StringUtils;
import com.rate.system.rate_system.utils.excel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RequestMapping("/sys/user")
@Controller
public class UserController extends BaseController {
	private String prefix="system/user"  ;
	@Autowired
	UserService userService;

	@Autowired
	private UserDao userDao;
	@Autowired
	private DeptService sysDeptService;
	@Autowired
	RoleService roleService;
	@Autowired
	DeptService deptService;
	@Autowired
	SitePowerDao sitePowerDao;
	@Autowired
	UserRoleDao userRoleDao;

/*		@Autowired
	DictService dictService;
	@Autowired
	FileService fileService;*/
	@RequiresPermissions("sys:user:user")
	@GetMapping("")
	String user(Model model) {
		return prefix + "/user";
	}

	@GetMapping("/list")
	@ResponseBody
	PageUtils list(@RequestParam Map<String, Object> params, Long pageSize, Long pageNumber) {
		//选择部门树节点条件
		String deptIds = (String) params.get("deptIds");
		if(deptIds!=null && !"".equals(deptIds)) {
			String[] dept=deptIds.split(",");
			List<String> list = new ArrayList<String>();
			for(String str:dept) {
				list.add(str);
			}
			params.put("deptIds", list);
		}
		//部门父id
		params.put("parentId", getParentId());
		// 查询列表数据
		PageQuery<User> paras = new PageQuery<User>(pageNumber, pageSize, params);
		PageQuery<User> page = userService.list(paras,getDeptId());
		PageUtils pageUtils = new PageUtils(page.getList(), page.getTotalRow());
		return pageUtils;
	}

	@RequiresPermissions("sys:user:add")
	@Log("添加用户")
	@GetMapping("/add")
	String add(Model model) {
		List<Role> roles = null;
		//获得部门
		Dept dept = deptService.get(getDeptId());
		//级别
		List<Long> roleIds = userRoleDao.listRoleId(getUserId());
		Integer delFlag = dept.getDelFlag();
		Long parentId = dept.getParentId();
		//如果是系统管理员
		if(roleIds != null && roleIds.size() > 0 ){
			for(Long roleId : roleIds){
				if(roleId == 1L){
					roles = roleService.list();
					break;
				}
			}
			//如果是运维公司
		}else if(delFlag==3 || delFlag==2) {
			roles = roleService.list();
			for (int i = 0; i < roles.size(); i++) {
				if(roles.get(i).getRoleName().equals("系统管理员")) {
					roles.remove(i);
				}
			}
		}
		model.addAttribute("roles", roles);
		return prefix + "/add";
	}
	
	@RequiresPermissions("sys:user:edit")
	@Log("编辑用户")
	@GetMapping("/edit/{id}")
	String edit(Model model, @PathVariable("id") Long id) { // userId
		User userDO = userService.get(id);
		User user1=userDao.userById(id);
		if(userDO.getMgrId() != null){
			User user2 = userService.get(userDO.getMgrId().longValue());
			userDO.setMgrName(user2.getName());
		}
		model.addAttribute("user1", user1);
		model.addAttribute("user", userDO);
		List<Long> roleIds= userDO.getRoleIds();
		Dept dept = deptService.get(getDeptId());
		Long parentId = dept.getParentId();
		Integer delFlag = dept.getDelFlag();
		List<Role> roles = roleService.list();
		List<String> siteList = userService.getSitePowerList(id);
		model.addAttribute("siteList", siteList);
//		List<User> companyList = userService.getListByDeptId(getParentId());
//		model.addAttribute("companyList", companyList);
		//如果是系统管理员，显示所有
		if(getRoleId() == 1){
			for(long roleId:roleIds) {
				for (int i = 0; i < roles.size(); i++) {
					if(roles.get(i).getRoleId()==roleId) {
						roles.get(i).setRoleSign("true");
					}
				}
			}
			// 如果是环保局或运维公司，不显示系统管理员项
		}else if(delFlag==3 || delFlag==2){
			for (int i = 0; i < roles.size(); i++) {
				if(roles.get(i).getRoleName().equals("系统管理员")) {
					roles.remove(i);
				}
			}
			for(long roleId:roleIds) {
				for (int i = 0; i < roles.size(); i++) {
					if(roles.get(i).getRoleId()==roleId) {
						roles.get(i).setRoleSign("true");
					}
				}
			}
		}
		model.addAttribute("roles", roles);
		return prefix+"/edit";
	}

	@RequiresPermissions("sys:user:add")
	@Log("保存用户")
	@PostMapping("/save/{siteIds}")
	@ResponseBody
	R save(User user, @PathVariable(required = false) String siteIds) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));
		Long userId = userService.save(user);
		if(userId > 0){
 			try{
				if(StringUtils.isNotBlank(siteIds) || siteIds == "undefined"){
					Long roleId = user.getRoleIds().get(0);
					// 如果是这4个角色，则需要给每个人配置站点
					if(roleId == 78 || roleId == 79 || roleId == 81 || roleId == 82 ){
						List<String> siteIdList = Arrays.asList(siteIds.split(","));
						userService.saveSitePower(siteIdList, userId);
					}else{
//						List<String> siteAll = userService.getAllSiteId();
//						userService.saveSitePower(siteAll, userId);
					}
				}
				return R.ok();
			}catch(Exception e){
				return R.error();
			}

		}
		return R.error();
	}

	@RequiresPermissions("sys:user:edit")
	@Log("更新用户")
	@PostMapping("/update/{siteIds}")
	@ResponseBody
	R update(User user, @PathVariable(required = false) String siteIds) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		try{
			userService.update(user);
			if(StringUtils.isNotBlank(siteIds) || siteIds == "undefined"){
				Long roleId = user.getRoleIds().get(0);
				if(roleId == 78 || roleId == 79 || roleId == 81 || roleId == 82 ){
					List<String> siteIdList = Arrays.asList(siteIds.split(","));
					userService.updateSitePower(siteIdList, user.getUserId());
				}else{
//					List<String> siteAll = userService.getAllSiteId();
//					userService.updateSitePower(siteAll, user.getUserId());
				}
			}
			return R.ok();
		}catch(Exception e){
			e.printStackTrace();
			return R.error();
		}
	}


	@RequiresPermissions("sys:user:edit")
	@Log("更新用户")
	@PostMapping("/updatePeronal")
	@ResponseBody
	R updatePeronal(User user) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (userService.updatePersonal(user) > 0) {
			return R.ok();
		}
		return R.error();
	}

	
	@RequiresPermissions("sys:user:remove")
	@Log("删除用户")
	@PostMapping("/remove")
	@ResponseBody
	R remove(Long id) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (userService.remove(id) > 0) {
			return R.ok();
		}
		return R.error();
	}

	@RequiresPermissions("sys:user:batchRemove")
	@Log("批量删除用户")
	@PostMapping("/batchRemove")
	@ResponseBody
	R batchRemove(@RequestParam("ids[]") Long[] userIds) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		int r = userService.batchremove(userIds);
		if (r > 0) {
			return R.ok();
		}
		return R.error();
	}
	
	@PostMapping("/exit")
	@ResponseBody
	boolean exit(@RequestParam Map<String, Object> params) {
		// 存在，不通过，false
		return !userService.exit(params); 
	}
	
	@RequiresPermissions("sys:user:resetPwd")
	@Log("请求更改用户密码")
	@GetMapping("/resetPwd/{id}")
	String resetPwd(@PathVariable("id") Long userId, Model model) {
		User userDO = new User();
		userDO.setUserId(userId);
		model.addAttribute("user", userDO);
		return prefix + "/reset_pwd";
	}
	@Log("index.html首页请求更改用户密码")
	@GetMapping("/ChangePassword/{id}")
	String resetPwd2(@PathVariable("id") Long userId, Model model) {
		User userDO = new User();
		userDO.setUserId(userId);
		model.addAttribute("user", userDO);
		return prefix + "/ChangePassword";
	}
	@Log("提交更改用户密码")
	@PostMapping("/resetPwd")
	@ResponseBody
	R resetPwd(UserVO userVO) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		try{
			userService.resetPwd(userVO,getUser());
			return R.ok();
		}catch (Exception e){
			return R.error(1,e.getMessage());
		}

	}
	@RequiresPermissions("sys:user:resetPwd")
	@Log("admin提交更改用户密码")
	@PostMapping("/adminResetPwd")
	@ResponseBody
	R adminResetPwd(UserVO userVO) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		try{
			userService.adminResetPwd(userVO);
			return R.ok();
		}catch (Exception e){
			return R.error(1,e.getMessage());
		}

	}

	@ResponseBody
	@GetMapping("/getMgr")
	public List<User> getMgr() {
		List<User> userList=null;
		try {
			userList = userService.queryAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userList;
	}
	
	@ResponseBody
	@GetMapping("/getCompany")
	public List<PollutantFactoryNew> getCompany() {
		List<PollutantFactoryNew> companyList=null;
		try {
			companyList = userService.getPollutantFactoryList();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return companyList;
	}



	/**
	 * 导出Excel
	 *
	 * @throws Exception
	 */
	@RequestMapping("/exportExcel")
	@ResponseBody
	public void exportExcel(@RequestParam Map<String, Object> params, Long unionId, String name, String idCode,
							HttpServletResponse response) {
		List<User> userList;
		//选择部门树节点条件
		String deptIds = (String) params.get("deptIds");
		if(deptIds!=null && !"".equals(deptIds)) {
			String[] dept=deptIds.split(",");
			List<String> list = new ArrayList<String>();
			for(String str:dept) {
				list.add(str);
			}
			params.put("deptIds", list);
		}
		//部门父id
		params.put("parentId", getParentId());

		userList = userService.listAll(params,getDeptId());


		for (User user : userList) {
			Integer status = user.getStatus();
			if (status!=null && status==1){
				user.setStatusExp("正常");
			}else {
				user.setStatusExp("禁用");
			}

		}


		String title = "用户信息";

		try {
			new ExportExcel(title, User.class, 2).setDataList(userList).write(response, title + ".xlsx").dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 导入界面
	 *
	 * @throws Exception
	 */
	@GetMapping("/import")
	public String importPage() {
		return prefix + "/import";
	}

	/**
	 * 下载输入数据的模板
	 *
	 * @param response
	 */
	@RequestMapping("import/template")
	public void importFileTemplate(HttpServletResponse response) {
		String fileName = "用户信息_模板.xlsx";
		ExportExcelByXSSF excel = new ExportExcelByXSSF("用户信息_模板", UserEPX.class, 1);
		Map<String,Object> params = new HashMap<>();
		List<Dept> list = sysDeptService.list(params,getDeptId());
		Set<String> name= list.stream().map(Dept::getName).collect(Collectors.toSet());
		String[] deftname =   name.toArray(new String[name.size()]);


		XSSFWorkbook wb = excel.getWb();
		XSSFSheet sheet = excel.getSheet();

		String[] status = new String[]{"正常", "禁用"};
		List<Role>	roles = roleService.list();
		Set<String> rolename2= roles.stream().map(Role::getRoleName).collect(Collectors.toSet());
		String[] rolename =  rolename2.toArray(new String[rolename2.size()]);
		try {

			//设置身份证列单元格格式
			//CatalogExcelUtil.textFormat(wb, sheet, 3);
			CatalogExcelUtil.dropDownList42007(wb, sheet, deftname, 1, 1000, 4, 4);// 政治面貌
			CatalogExcelUtil.dropDownList42007(wb, sheet, status, 1, 1000, 6, 6);// 政治面貌
			CatalogExcelUtil.dropDownList42007(wb, sheet, rolename, 1, 1000, 7, 7);// 婚姻状况
			//CatalogExcelUtil.dropDownList42007(wb, sheet, educationNature, 1, 1000, 17, 17);// 学历性质
			//CatalogExcelUtil.dropDownList42007(wb, sheet, studyLength, 1, 1000, 18, 18);// 学制
			response.reset();
			response.setContentType("application/octet-stream; charset=utf-8");
			response.setHeader("Content-Disposition", "attachment; filename=" + Encodes.urlEncode(fileName));
			wb.write(response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	/**
	 * 导入
	 *
	 * @throws Exception
	 */
	@PostMapping("/inputFile")
	@ResponseBody
	public List<UserEPX> importFile(@RequestParam(value = "file", required = false) MultipartFile multipartFile) {
		try {
			//创建表格导入对象
			ImportExcel ei = new ImportExcel(multipartFile, 1, 0);
			List<UserEPX> list = ei.getDataList(UserEPX.class);

			for (UserEPX user : list) {
				String username = user.getUsername();
				if (username.length()>0){
					user.setUsername(username.replaceAll(" ", ""));
				}
				String username2 = user.getName();
				if (username2.length()>0){

				}
				String password = user.getPassword();
				if (password.length()>0){
					user.setPassword(password.replaceAll(" ", ""));
				}
				String mobile = user.getMobile();
				if (mobile.length()>0){

				}
				String statusExp = user.getStatusExp();
				if (statusExp.length()>0){

				}
				String roleName = user.getRoleName();
				if (roleName.length()>0){

				}

			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;// 返回空表示导入失败或异常

	}

	/**
	 * 导入保存
	 *
	 * @throws Exception
	 */
	@PostMapping("/importData")
	@ResponseBody
	public R importData(@RequestParam(value = "tableData") String data) {
		JSONArray jsonArray = JSONArray.parseArray(data);
		List<UserEPX> list = jsonArray.toJavaList(UserEPX.class);

		Map<String, Object> result = new HashMap<>();
		String error = "";
		int successCount = 0;
		try {

			for (UserEPX userEPX : list) {
				User user = new User();
				List<User> userlist1 =userService.getuserByname(userEPX.getUsername());
				if (userlist1!=null && userlist1.size()>0){
					error=error+userEPX.getUsername()+"用户名已存在,";
					continue;
				}
				user.setUsername(userEPX.getUsername());
				user.setName(userEPX.getName());
				user.setPassword(MD5Utils.encrypt(userEPX.getUsername(), userEPX.getPassword()));
				user.setMobile(userEPX.getMobile());
				String deptName = userEPX.getWorkName();
				List<Dept> deptlist = deptService.getdeptByname(deptName);
				user.setDeptId(deptlist.get(0).getDeptId());
				String username2 = userEPX.getUsername2();//上级名
				if (username2!=null){
					List<User> userlist =userService.getuserByname(username2);
					if (userlist!=null && userlist.size()>0){
						user.setMgrId(userlist.get(0).getUserId().intValue());
					}else {
						user.setMgrId(null);
					}

				}else {
					user.setMgrId(null);
				}
				if (userEPX.getStatusExp().equals("正常")){
					user.setStatus(1);
				}else {
					user.setStatus(0);
				}
				String roleName = userEPX.getRoleName();

				List<Long> rolelist =roleService.getRoleByname(roleName);

				user.setRoleIds(rolelist);
				Long userId = userService.save(user);
				if (userId>0){
					successCount++;
				}else {
					error=error+user.getUsername()+",";
				}
			}
			if (StringUtils.isNotBlank(error)) {
				error = error.substring(0, error.length() - 1);
			}
			result.put("successCount", successCount + "个成功！");
			result.put("error", "".equals(error) ? "无" + "导入失败！" : error + "导入失败！");
			return R.ok(result);
		} catch (Exception e) {
		}
		return R.error();// 返回空表示导入失败或异常
	}

	/*
	@GetMapping("/tree")
	@ResponseBody
	public Tree<Dept> tree() {
		Tree<Dept> tree = new Tree<Dept>();
		tree = userService.getTree();
		return tree;
	}

	@GetMapping("/treeView")
	String treeView() {
		return  prefix + "/userTree";
	}

	@GetMapping("/personal")
	String personal(Model model) {
		User userDO  = userService.get(getUserId());
		model.addAttribute("user",userDO);
		Long picId = userDO.getPicId();
		FileDO fileDO = null;
		String url = null;
		if(picId != null) {
			fileDO = fileService.get(picId);
			url = fileDO.getUrl();
			if(url != null && !"".equals(url)) {
				String str = url.replaceAll("/files/", "");
				fileDO.setUrl(str);
			}
		}
//		model.addAttribute("file", fileDO);
		model.addAttribute("hobbyList",dictService.getHobbyList(userDO));
		model.addAttribute("sexList",dictService.getSexList());
		return prefix + "/personal";
	}
	@ResponseBody
	@PostMapping("/uploadImg")
	R uploadImg(@RequestParam("avatar_file") MultipartFile file, String avatar_data, HttpServletRequest request) {
		if ("test".equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		Map<String, Object> result = new HashMap<>();
		try {
			Long userId = getUserId();
			result = userService.updatePersonalImg(file, avatar_data, getUserId());
		} catch (Exception e) {
			return R.error("更新图像失败！");
		}
		if(result!=null && result.size()>0){
			return R.ok(result);
		}else {
			return R.error("更新图像失败！");
		}
	}
	
	
     * 在线预览图片
     
    @RequestMapping("/showImage")
    public @ResponseBody void showImage(String path,HttpServletResponse response) throws IOException{
    	response.setContentType("text/html; charset=UTF-8");
    	response.setContentType("image/jpeg");
//        String fullFileName = getRealPath("/upload/" + path);
        FileInputStream fis = new FileInputStream(path);
        OutputStream os = response.getOutputStream();
        try {
            int count = 0;
            byte[] buffer = new byte[1024 * 1024];
            while ((count = fis.read(buffer)) != -1)
                os.write(buffer, 0, count);
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null)
                os.close();
            if (fis != null)
                fis.close();
        }
    }*/
}
