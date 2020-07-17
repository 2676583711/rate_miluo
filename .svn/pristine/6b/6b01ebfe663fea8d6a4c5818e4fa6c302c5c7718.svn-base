package com.rate.system.rate_system.entity;

import com.rate.system.rate_system.utils.excel.ExcelField;
import org.beetl.sql.core.annotatoin.AutoID;
import org.beetl.sql.core.annotatoin.SeqID;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
@org.beetl.sql.core.annotatoin.Table(name="sys_user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    //
    @AutoID
    private Long userId;
    // 用户名
    @ExcelField(title = "用户名", groups = 1, sort = 2)
    private String username;
    @ExcelField(title = "上级领导", groups = 1, sort = 5)
    private String username2;
    // 用户真实姓名
    @ExcelField(title = "姓名", groups = 1, sort = 1)
    private String name;
    // 所属企业ID
    private Integer pwId;
    // 所属污染源企业ID（6/03号新建）
    private Integer factoryId;
    // 上级领导ID
    private Integer mgrId;
    // 上级领导名字
    private String mgrName;
    // 密码
    private String password;
    // 部门
    private Long deptId;
    private String deptName;
    // 邮箱
    private String email;
    // 手机号
    @ExcelField(title = "联系方式", groups = 1, sort = 3)
    private String mobile;
    // 状态 0:禁用，1:正常
    private Integer status;
    @ExcelField(title = "状态", groups = 1, sort = 6)
    private String statusExp;
    // 创建用户id
    private Long userIdCreate;
    public Integer getPwId() {
		return pwId;
	}

	public void setPwId(Integer pwId) {
		this.pwId = pwId;
	}

    public Integer getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Integer factoryId) {
        this.factoryId = factoryId;
    }

    // 创建时间
    private Date gmtCreate;
    // 修改时间
    private Date gmtModified;
    //角色
    private List<Long> roleIds;
    //性别
    private Long sex;
    //出身日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;
    //图片ID
    private String picId;

    public Integer getMgrId() {
        return mgrId;
    }

    public void setMgrId(Integer mgrId) {
        this.mgrId = mgrId;
    }

    public String getMgrName() {
        return mgrName;
    }

    public void setMgrName(String mgrName) {
        this.mgrName = mgrName;
    }

    //现居住地
    private String liveAddress;
    //爱好
    private String hobby;
    //省份
    private String province;
    //所在城市
    private String city;
    //所在地区
    private String district;
    //职位
    @ExcelField(title = "职位", groups = 1, sort = 4)
    private String workName;
    //角色名
    @ExcelField(title = "角色", groups = 1, sort = 7)
    private String roleName;
    //企业名称
    private String companyName;

    public String getUsername2() {
        return username2;
    }

    public void setUsername2(String username2) {
        this.username2 = username2;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getStatusExp() {
        return statusExp;
    }

    public void setStatusExp(String statusExp) {
        this.statusExp = statusExp;
    }

    public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@AutoID
    @SeqID(name="SYS_USER_ID_SEQ")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getUserIdCreate() {
        return userIdCreate;
    }

    public void setUserIdCreate(Long userIdCreate) {
        this.userIdCreate = userIdCreate;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }

    public Long getSex() {
        return sex;
    }

    public void setSex(Long sex) {
        this.sex = sex;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getPicId() {
        return picId;
    }

    public void setPicId(String picId) {
        this.picId = picId;
    }

    public String getLiveAddress() {
        return liveAddress;
    }

    public void setLiveAddress(String liveAddress) {
        this.liveAddress = liveAddress;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", name=" + name + ", password=" + password
				+ ", deptId=" + deptId + ", deptName=" + deptName + ", email=" + email + ", mobile=" + mobile
				+ ", status=" + status + ", userIdCreate=" + userIdCreate + ", gmtCreate=" + gmtCreate
				+ ", gmtModified=" + gmtModified + ", roleIds=" + roleIds + ", sex=" + sex + ", birth=" + birth
				+ ", picId=" + picId + ", liveAddress=" + liveAddress + ", hobby=" + hobby + ", province=" + province
				+ ", city=" + city + ", district=" + district + "]";
	}

}
