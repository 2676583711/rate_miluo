package com.rate.web.site.entity;

import java.util.Date;

/**
 * @Author chenshixue
 * @Date 2019/12/11
 */

public class FactorAndParam {

    // 站点名称
    private String siteName;
    // 设备id
    private String videoId;
    // 设备名称
    private String videoName;
    // 参数id
    private Integer paramId;
    // 参数名称
    private String name;
    // 参数标识
    private String signName;
    // 设备类型
    private String videoType;
    // 参数单位
    private String unit;
    // 设备因子id
    private Integer id;
    // 一级上限
    private Double limitTop1;
    // 二级上限
    private Double limitTop2;
    // 三级上限
    private Double limitTop3;
    // 一级下限
    private Double limitBottom1;
    // 二级下限
    private Double limitBottom2;
    // 三级下限
    private Double limitBottom3;
    // 是否需要报警
    private Integer needAlarm;
    // 上限报警等级，推送给负责人
    private Integer alarmLevelTopToFzr;
    // 上限报警等级，推送给环保局
    private Integer alarmLevelTopToHbj;
    // 下限报警等级，推送给负责人
    private Integer alarmLevelBottomToFzr;
    // 下限报警等级，推送给环保局
    private Integer alarmLevelBottomToHbj;
    // 更新时间
    private Date updatedTime;

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public Integer getParamId() {
        return paramId;
    }

    public void setParamId(Integer paramId) {
        this.paramId = paramId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getLimitTop1() {
        return limitTop1;
    }

    public void setLimitTop1(Double limitTop1) {
        this.limitTop1 = limitTop1;
    }

    public Double getLimitTop2() {
        return limitTop2;
    }

    public void setLimitTop2(Double limitTop2) {
        this.limitTop2 = limitTop2;
    }

    public Double getLimitTop3() {
        return limitTop3;
    }

    public void setLimitTop3(Double limitTop3) {
        this.limitTop3 = limitTop3;
    }

    public Double getLimitBottom1() {
        return limitBottom1;
    }

    public void setLimitBottom1(Double limitBottom1) {
        this.limitBottom1 = limitBottom1;
    }

    public Double getLimitBottom2() {
        return limitBottom2;
    }

    public void setLimitBottom2(Double limitBottom2) {
        this.limitBottom2 = limitBottom2;
    }

    public Double getLimitBottom3() {
        return limitBottom3;
    }

    public void setLimitBottom3(Double limitBottom3) {
        this.limitBottom3 = limitBottom3;
    }

    public Integer getNeedAlarm() {
        return needAlarm;
    }

    public void setNeedAlarm(Integer needAlarm) {
        this.needAlarm = needAlarm;
    }

    public Integer getAlarmLevelTopToFzr() {
        return alarmLevelTopToFzr;
    }

    public void setAlarmLevelTopToFzr(Integer alarmLevelTopToFzr) {
        this.alarmLevelTopToFzr = alarmLevelTopToFzr;
    }

    public Integer getAlarmLevelTopToHbj() {
        return alarmLevelTopToHbj;
    }

    public void setAlarmLevelTopToHbj(Integer alarmLevelTopToHbj) {
        this.alarmLevelTopToHbj = alarmLevelTopToHbj;
    }

    public Integer getAlarmLevelBottomToFzr() {
        return alarmLevelBottomToFzr;
    }

    public void setAlarmLevelBottomToFzr(Integer alarmLevelBottomToFzr) {
        this.alarmLevelBottomToFzr = alarmLevelBottomToFzr;
    }

    public Integer getAlarmLevelBottomToHbj() {
        return alarmLevelBottomToHbj;
    }

    public void setAlarmLevelBottomToHbj(Integer alarmLevelBottomToHbj) {
        this.alarmLevelBottomToHbj = alarmLevelBottomToHbj;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Override
    public String toString() {
        return "FactorAndParam{" +
                "siteName='" + siteName + '\'' +
                ", videoId='" + videoId + '\'' +
                ", videoName='" + videoName + '\'' +
                ", paramId=" + paramId +
                ", name='" + name + '\'' +
                ", signName='" + signName + '\'' +
                ", videoType='" + videoType + '\'' +
                ", unit='" + unit + '\'' +
                ", id=" + id +
                ", limitTop1=" + limitTop1 +
                ", limitTop2=" + limitTop2 +
                ", limitTop3=" + limitTop3 +
                ", limitBottom1=" + limitBottom1 +
                ", limitBottom2=" + limitBottom2 +
                ", limitBottom3=" + limitBottom3 +
                ", needAlarm=" + needAlarm +
                ", alarmLevelTopToFzr='" + alarmLevelTopToFzr + '\'' +
                ", alarmLevelTopToHbj='" + alarmLevelTopToHbj + '\'' +
                ", alarmLevelBottomToFzr='" + alarmLevelBottomToFzr + '\'' +
                ", alarmLevelBottomToHbj='" + alarmLevelBottomToHbj + '\'' +
                ", updatedTime=" + updatedTime +
                '}';
    }
}
