package com.rate.web.connected.offdata.entity;

import java.util.Date;

/**
 * @Author chenshixue
 * @Date 2019/12/6
 */
public class TempEntity {

    private String siteName;
    private String videoName;
    // 空气站分类
    private String siteCategory;
    // 设备id
    private String siteCode;
    private Date queryTime;

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getSiteCategory() {
        return siteCategory;
    }

    public void setSiteCategory(String siteCategory) {
        this.siteCategory = siteCategory;
    }

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    public Date getQueryTime() {
        return queryTime;
    }

    public void setQueryTime(Date queryTime) {
        this.queryTime = queryTime;
    }
}
