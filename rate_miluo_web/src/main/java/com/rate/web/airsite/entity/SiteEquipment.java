package com.rate.web.airsite.entity;

import com.rate.web.site.entity.FactorParam;
import org.beetl.sql.core.annotatoin.AutoID;
import org.beetl.sql.core.annotatoin.Table;

import java.util.Date;
import java.util.List;

/**
 * @author xxx
 * @date 2019/5/30 9:11
 **/
@Table(name = "miluo_video")
public class SiteEquipment {
    /**
     * 设备id
     */
    @AutoID
    private Integer id;
    /**
     * 站点ID
     */
    private Integer siteId;
    /**
     *摄像机
     */
    private String sxj;
    /**
     *设备名称
     */
    private String name;
    /**
     *视频流量卡
     */
    private String spllk;
    /**
     *数采流量卡
     */
    private String scllk;
    /**
     *平台地址
     */
    private String ptdz;
    /**
     *APP地址/权限
     */
    private String appdzqx;
    /**
     *安装时间
     */

    private Date azDate;
    /**
     *备注
     */
    private String remark;

    private String mn;

    /**
     * 设备ID
     */
    private String equmentId;
    /**
     * 设备地址
     */
    private String address;
    /**
     * 设备下面的摄像机ID
     */
    private Long vidiconId;
    /**
     * 设备下面的摄像机名称
     */
    private String vidiconName;

    // 设备类型
    private String type;

    private List<FactorParam> factorParams;

    public List<FactorParam> getFactorParams() {
        return factorParams;
    }

    public void setFactorParams(List<FactorParam> factorParams) {
        this.factorParams = factorParams;
    }

    public String getVidiconName() {
        return vidiconName;
    }

    public void setVidiconName(String vidiconName) {
        this.vidiconName = vidiconName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getVidiconId() {
        return vidiconId;
    }

    public void setVidiconId(Long vidiconId) {
        this.vidiconId = vidiconId;
    }

    public String getEqumentId() {
        return equmentId;
    }

    public void setEqumentId(String equmentId) {
        this.equmentId = equmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMn() {
        return mn;
    }

    public void setMn(String mn) {
        this.mn = mn;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getSxj() {
        return sxj;
    }

    public void setSxj(String sxj) {
        this.sxj = sxj;
    }

    public String getSpllk() {
        return spllk;
    }

    public void setSpllk(String spllk) {
        this.spllk = spllk;
    }

    public String getScllk() {
        return scllk;
    }

    public void setScllk(String scllk) {
        this.scllk = scllk;
    }

    public String getPtdz() {
        return ptdz;
    }

    public void setPtdz(String ptdz) {
        this.ptdz = ptdz;
    }

    public String getAppdzqx() {
        return appdzqx;
    }

    public void setAppdzqx(String appdzqx) {
        this.appdzqx = appdzqx;
    }

    public Date getAzDate() {
        return azDate;
    }

    public void setAzDate(Date azDate) {
        this.azDate = azDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
