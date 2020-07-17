package com.rate.system.rate_system.entity;

/**
 * @author gaoyuzhe
 * @date 2017/12/15.
 */
public class UserVO {
    /**
     * 更新的用户对象
     */
    private User user = new User();
    /**
     * 旧密码
     */
    private String pwdOld;
    /**
     * 新密码
     */
    private String pwdNew;

    public User getUser() {
        return user;
    }

    public void setUserDO(User user) {
        this.user = user;
    }

    public String getPwdOld() {
        return pwdOld;
    }

    public void setPwdOld(String pwdOld) {
        this.pwdOld = pwdOld;
    }

    public String getPwdNew() {
        return pwdNew;
    }

    public void setPwdNew(String pwdNew) {
        this.pwdNew = pwdNew;
    }
}
