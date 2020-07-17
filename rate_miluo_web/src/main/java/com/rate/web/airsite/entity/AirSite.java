package com.rate.web.airsite.entity;

import org.beetl.sql.core.annotatoin.AutoID;
import org.beetl.sql.core.annotatoin.Table;

import java.io.Serializable;
import java.util.List;

/**
 * @author xxx
 * @date 2019/5/27 16:13
 **/
@Table(name = "miluo_site")
public class AirSite implements Serializable {
    /**
     * 主键
     */
    @AutoID
    private Integer id;
    //名称
    private String name;
    //原名
    private String oldName;
    //站点编码
    private String siteCode;
    //经度
    private String longitude;
    //纬度
    private String latitude;
    //站点类型（1 监测水  2 空气 3 污染源）
    private String siteType;
    //站点细分类别
    private String siteCategory;
    //地址
    private String address;
    //备注
    private String remark;
    //区域类型（1.城区2.乡镇）
    private String areaType;

    //监测因子列表
    private String params;

    private String fzr;
    private  Integer fzr2 ;
    private String phone;

    private Integer status;

    public Integer getFzr2() {
        return fzr2;
    }

    public void setFzr2(Integer fzr2) {
        this.fzr2 = fzr2;
    }

    private List<SiteEquipment> siteEquipments;

    public String getSiteCategory() {
        return siteCategory;
    }

    public void setSiteCategory(String siteCategory) {
        this.siteCategory = siteCategory;
    }

    public List<SiteEquipment> getSiteEquipments() {
        return siteEquipments;
    }

    public void setSiteEquipments(List<SiteEquipment> siteEquipments) {
        this.siteEquipments = siteEquipments;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFzr() {
        return fzr;
    }

    public void setFzr(String fzr) {
        this.fzr = fzr;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getSiteType() {
        return siteType;
    }

    public void setSiteType(String siteType) {
        this.siteType = siteType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}
