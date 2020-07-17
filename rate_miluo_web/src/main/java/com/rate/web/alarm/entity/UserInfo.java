package com.rate.web.alarm.entity;

/**
 * @Author chenshixue
 * @Date 2020/1/3
 */

public class UserInfo {

    private String siteName;
    private Long mgrId;
    private String name;
    private String mobile;
    private Long fzr;

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public Long getMgrId() {
        return mgrId;
    }

    public void setMgrId(Long mgrId) {
        this.mgrId = mgrId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getFzr() {
        return fzr;
    }

    public void setFzr(Long fzr) {
        this.fzr = fzr;
    }
}
