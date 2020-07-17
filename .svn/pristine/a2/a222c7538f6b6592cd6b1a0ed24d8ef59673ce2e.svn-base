package com.rate.system.rate_system.api.entity;

import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.Table;

import java.util.Date;
/**
 * app端登陆令牌验证
 * @author	zhangbiao
 * @date	2018-09-11
 */

@Table(name="api_verification")
public class Verification {
	@AssignID
    private Long id;

	/**
	 * 用户令牌
	 */
    private String code;

    private Date startTime;

    private Date updateTime;

    private Long userId;

    /**
     * 0：生效  1：失效（退出）
     */
    private Long type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }
}