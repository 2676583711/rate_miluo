package com.rate.web.vidicon.entity;

import org.beetl.sql.core.annotatoin.Table;

import java.util.Date;

/**
* @ClassName: MiluoVidicon
* @Description: 摄像机
* @author jiangya
* @date 2019年6月1日 上午11:26:24
*
*/
@Table(name="miluo_vidicon")
public class Vidicon  {
	
	//主键
	private Long id ;
	//所属站点id
	private Integer siteId ;
	//站点名称
	private String siteName;
	//站点类型
	private String siteType;
	//访问令牌
	private String accessToken ;
	//ezopen回放地址
	private String ezopenPlaybackUrl ;
	//ezopen普通播放地址
	private String ezopenUrl ;
	//ezopen高清播放地址
	private String ezopenhdUrl ;
	//HLS普通播放地址
	private String hlsUrl ;
	//HLS高清播放地址
	private String hlshdUrl ;
	//rtmp普通播放地址
	private String rtmpUrl ;
	//rtmp高清播放地址
	private String rtmphdUrl ;
	//摄像头供应商品牌
	private String supplier ;
	//摄像头位置(1.监测点位置,2.水排放口,3.进水口,4.气排放口,5.站房内,6.站房外,7.生产车间)
	private String vidiconLocation ;
	//摄像头名称
	private String vidiconName ;
	//安装时间
	private Date installationDate ;
	//摄像头设备序列号
	private String seriesNumber ;
	//设备通道
	private String channelNo;
	//摄像头所有人
	private Long ownerId ;
	private String ownerName;
	//默认播放地址
	private String defaultUrl;
	public Vidicon() {
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getSiteType() {
		return siteType;
	}

	public void setSiteType(String siteType) {
		this.siteType = siteType;
	}

	public String getChannelNo() {
		return channelNo;
	}

	public void setChannelNo(String channelNo) {
		this.channelNo = channelNo;
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

	public Date getInstallationDate() {
		return installationDate;
	}

	public void setInstallationDate(Date installationDate) {
		this.installationDate = installationDate;
	}

	/**主键
	*@return 
	*/
	public Long getId(){
		return  id;
	}
	/**主键
	*@param  id
	*/
	public void setId(Long id ){
		this.id = id;
	}
	
	/**所属站点id
	*@return 
	*/
	public Integer getSiteId(){
		return  siteId;
	}
	/**所属站点id
	*@param  siteId
	*/
	public void setSiteId(Integer siteId ){
		this.siteId = siteId;
	}
	
	/**访问令牌
	*@return 
	*/
	public String getAccessToken(){
		return  accessToken;
	}
	/**访问令牌
	*@param  accessToken
	*/
	public void setAccessToken(String accessToken ){
		this.accessToken = accessToken;
	}
	
	/**ezopen回放地址
	*@return 
	*/
	public String getEzopenPlaybackUrl(){
		return  ezopenPlaybackUrl;
	}
	/**ezopen回放地址
	*@param  ezopenPlaybackUrl
	*/
	public void setEzopenPlaybackUrl(String ezopenPlaybackUrl ){
		this.ezopenPlaybackUrl = ezopenPlaybackUrl;
	}
	
	/**ezopen普通播放地址
	*@return 
	*/
	public String getEzopenUrl(){
		return  ezopenUrl;
	}
	/**ezopen普通播放地址
	*@param  ezopenUrl
	*/
	public void setEzopenUrl(String ezopenUrl ){
		this.ezopenUrl = ezopenUrl;
	}
	
	/**ezopen高清播放地址
	*@return 
	*/
	public String getEzopenhdUrl(){
		return  ezopenhdUrl;
	}
	/**ezopen高清播放地址
	*@param  ezopenhdUrl
	*/
	public void setEzopenhdUrl(String ezopenhdUrl ){
		this.ezopenhdUrl = ezopenhdUrl;
	}
	
	/**HLS普通播放地址
	*@return 
	*/
	public String getHlsUrl(){
		return  hlsUrl;
	}
	/**HLS普通播放地址
	*@param  hlsUrl
	*/
	public void setHlsUrl(String hlsUrl ){
		this.hlsUrl = hlsUrl;
	}
	
	/**HLS高清播放地址
	*@return 
	*/
	public String getHlshdUrl(){
		return  hlshdUrl;
	}
	/**HLS高清播放地址
	*@param  hlshdUrl
	*/
	public void setHlshdUrl(String hlshdUrl ){
		this.hlshdUrl = hlshdUrl;
	}
	
	/**rtmp普通播放地址
	*@return 
	*/
	public String getRtmpUrl(){
		return  rtmpUrl;
	}
	/**rtmp普通播放地址
	*@param  rtmpUrl
	*/
	public void setRtmpUrl(String rtmpUrl ){
		this.rtmpUrl = rtmpUrl;
	}
	
	/**rtmp高清播放地址
	*@return 
	*/
	public String getRtmphdUrl(){
		return  rtmphdUrl;
	}
	/**rtmp高清播放地址
	*@param  rtmphdUrl
	*/
	public void setRtmphdUrl(String rtmphdUrl ){
		this.rtmphdUrl = rtmphdUrl;
	}
	
	/**摄像头供应商品牌
	*@return 
	*/
	public String getSupplier(){
		return  supplier;
	}
	/**摄像头供应商品牌
	*@param  supplier
	*/
	public void setSupplier(String supplier ){
		this.supplier = supplier;
	}
	
	/**摄像头位置(1.监测点位置,2.水排放口,3.进水口,4.气排放口,5.站房内,6.站房外,7.生产车间)
	*@return 
	*/
	public String getVidiconLocation(){
		return  vidiconLocation;
	}
	/**摄像头位置(1.监测点位置,2.水排放口,3.进水口,4.气排放口,5.站房内,6.站房外,7.生产车间)
	*@param  vidiconLocation
	*/
	public void setVidiconLocation(String vidiconLocation ){
		this.vidiconLocation = vidiconLocation;
	}
	
	/**摄像机名称
	*@return 
	*/
	public String getVidiconName(){
		return  vidiconName;
	}
	/**摄像机名称
	*@param  vidiconName
	*/
	public void setVidiconName(String vidiconName ){
		this.vidiconName = vidiconName;
	}

	public String getDefaultUrl() {
		return defaultUrl;
	}

	public void setDefaultUrl(String defaultUrl) {
		this.defaultUrl = defaultUrl;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
}