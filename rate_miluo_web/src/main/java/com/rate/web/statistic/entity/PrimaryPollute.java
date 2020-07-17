package com.rate.web.statistic.entity;

import java.io.Serializable;

/**
 * @ClassName PrimaryPollute
 * @Author LiuYong
 * @Date 2019/6/27 9:45
 * @Version 1.0
 **/
public class PrimaryPollute implements Serializable {


    /**
     * 区域
     */
    private String areaName;

    /**
     * 站点名称
     */
    private String siteName;
    /**
     * 首要污染物
     */
    private String primaryEp;
    /**
     * 时间段
     */
    private String timeQuantum;
    /**
     * SO2首要污染物天数
     */
    private Integer so2PrimaryDays;
    /**
     * NO2首要污染物天数
     */
    private Integer no2PrimaryDays;
    /**
     * PM10首要污染物天数
     */
    private Integer pm10PrimaryDays;
    /**
     * PM2.5首要污染物天数
     */
    private Integer pm25PrimaryDays;
    /**
     * O3_8h首要污染物天数
     */
    private Integer o3EightPrimaryDays;
    /**
     * CO首要污染物天数
     */
    private Integer coPrimaryDays;
    /**
     * 备注
     */
    private String remark;
    /**
     * 日期
     */
    private String sDateTime;
    /**
     * 站点类型
     */
    private String stationType;

    public String getPrimaryEp() {
        return primaryEp;
    }

    public void setPrimaryEp(String primaryEp) {
        this.primaryEp = primaryEp;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getTimeQuantum() {
        return timeQuantum;
    }

    public void setTimeQuantum(String timeQuantum) {
        this.timeQuantum = timeQuantum;
    }

    public Integer getSo2PrimaryDays() {
        return so2PrimaryDays;
    }

    public void setSo2PrimaryDays(Integer so2PrimaryDays) {
        this.so2PrimaryDays = so2PrimaryDays;
    }

    public Integer getNo2PrimaryDays() {
        return no2PrimaryDays;
    }

    public void setNo2PrimaryDays(Integer no2PrimaryDays) {
        this.no2PrimaryDays = no2PrimaryDays;
    }

    public Integer getPm10PrimaryDays() {
        return pm10PrimaryDays;
    }

    public void setPm10PrimaryDays(Integer pm10PrimaryDays) {
        this.pm10PrimaryDays = pm10PrimaryDays;
    }

    public Integer getPm25PrimaryDays() {
        return pm25PrimaryDays;
    }

    public void setPm25PrimaryDays(Integer pm25PrimaryDays) {
        this.pm25PrimaryDays = pm25PrimaryDays;
    }

    public Integer getO3EightPrimaryDays() {
        return o3EightPrimaryDays;
    }

    public void setO3EightPrimaryDays(Integer o3EightPrimaryDays) {
        this.o3EightPrimaryDays = o3EightPrimaryDays;
    }

    public Integer getCoPrimaryDays() {
        return coPrimaryDays;
    }

    public void setCoPrimaryDays(Integer coPrimaryDays) {
        this.coPrimaryDays = coPrimaryDays;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getsDateTime() {
        return sDateTime;
    }

    public void setsDateTime(String sDateTime) {
        this.sDateTime = sDateTime;
    }

    public String getStationType() {
        return stationType;
    }

    public void setStationType(String stationType) {
        this.stationType = stationType;
    }
}
