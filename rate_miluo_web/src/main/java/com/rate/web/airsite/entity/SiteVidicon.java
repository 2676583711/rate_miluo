package com.rate.web.airsite.entity;

import org.beetl.sql.core.annotatoin.AutoID;
import org.beetl.sql.core.annotatoin.Table;

import java.util.Date;
import java.util.List;

/**
 * @author xxx
 * @date 2019/5/30 9:11
 **/
@Table(name = "miluo_vidicon")
public class SiteVidicon {
    /**
     * 摄像机id
     */
    @AutoID
    private Long id;
    /**
     * 站点ID
     */
    private Integer siteId;
    /**
     *摄像机
     */
    private String vidiconName;
    /**
     *设备序列号
     */
    private String seriesNumber;
    /**
     *摄像头所有人
     */
    private Long ownerId;
    /**
     *摄像机供应商品牌
     */
    private String supplier;
    /**
     *HLS高清播放地址
     */
    private String hlshdUrl;
    /**
     *HLS普通播放地址
     */
    private String hlsUrl;
    /**
     *rtmp高清播放地址
     */
    private String rtmphdurl;
    /**
     *rtmp普通播放地址
     */
    private String rtmpUrl;
    /**
     *ezopen高清播放地址
     */
    private String ezopenhdUrl;
    /**
     * ezopen普通播放地址
     */
    private String ezopenUrl;
    /**
     * ezopen回放地址
     */
    private String ezopenPlaybackUrl;
    /**
     * 摄像机位置(1.监测点位置,2.水排放口,3.进水口,4.气排放口,5.站房内,6.站房外,7.生产车间)
     */
    private Integer vidiconLocation;
    /**
     * 安装时间
     */
    private Date installationDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getVidiconName() {
        return vidiconName;
    }

    public void setVidiconName(String vidiconName) {
        this.vidiconName = vidiconName;
    }

    public String getSeriesNumber() {
        return seriesNumber;
    }

    public void setSeriesNumber(String seriesNumber) {
        this.seriesNumber = seriesNumber;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getHlshdUrl() {
        return hlshdUrl;
    }

    public void setHlshdUrl(String hlshdUrl) {
        this.hlshdUrl = hlshdUrl;
    }

    public String getHlsUrl() {
        return hlsUrl;
    }

    public void setHlsUrl(String hlsUrl) {
        this.hlsUrl = hlsUrl;
    }

    public String getRtmphdurl() {
        return rtmphdurl;
    }

    public void setRtmphdurl(String rtmphdurl) {
        this.rtmphdurl = rtmphdurl;
    }

    public String getRtmpUrl() {
        return rtmpUrl;
    }

    public void setRtmpUrl(String rtmpUrl) {
        this.rtmpUrl = rtmpUrl;
    }

    public String getEzopenhdUrl() {
        return ezopenhdUrl;
    }

    public void setEzopenhdUrl(String ezopenhdUrl) {
        this.ezopenhdUrl = ezopenhdUrl;
    }

    public String getEzopenUrl() {
        return ezopenUrl;
    }

    public void setEzopenUrl(String ezopenUrl) {
        this.ezopenUrl = ezopenUrl;
    }

    public String getEzopenPlaybackUrl() {
        return ezopenPlaybackUrl;
    }

    public void setEzopenPlaybackUrl(String ezopenPlaybackUrl) {
        this.ezopenPlaybackUrl = ezopenPlaybackUrl;
    }

    public Integer getVidiconLocation() {
        return vidiconLocation;
    }

    public void setVidiconLocation(Integer vidiconLocation) {
        this.vidiconLocation = vidiconLocation;
    }

    public Date getInstallationDate() {
        return installationDate;
    }

    public void setInstallationDate(Date installationDate) {
        this.installationDate = installationDate;
    }
}
