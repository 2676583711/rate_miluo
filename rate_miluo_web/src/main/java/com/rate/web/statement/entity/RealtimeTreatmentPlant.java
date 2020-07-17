package com.rate.web.statement.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rate.system.rate_system.utils.DateUtils;
import com.rate.system.rate_system.utils.excel.ExcelField;

import java.util.Date;

/**
 * 污水厂实时数据
 * @author chenshixue
 * @date 2019年5月29日
 */
public class RealtimeTreatmentPlant {
	public static String sort;
	//id
	private String id ;
	//污水值
	@ExcelField(title = "排放量(L/s)",sort = 4)
	private String bo1 ;
	//总镉值
	@ExcelField(title = "Cd(ug/L)",sort = 7)
	private String cd ;
	//总铜值
	@ExcelField(title = "Cu(mg/L)",sort = 10)
	private String cu ;
	//设备编码
	private String equmentId ;
	//总铅值
	@ExcelField(title = "Pb(mg/L)",sort = 6)
	private String pb ;
	//ph值
	@ExcelField(title = "PH",sort = 5)
	private String ph ;
	//总砷值
	@ExcelField(title = "As(mg/L)",sort = 8)
	private String shen ;
	//总锌值
	@ExcelField(title = "Zn(mg/L)",sort = 9)
	private String zn ;
	//总磷101
	@ExcelField(title = "TP(mg/L)",sort = 11)
	private String tp;
	//总氮065
	@ExcelField(title = "TN(mg/L)",sort = 12)
	private String tn;
	//cod011
	@ExcelField(title = "COD(mg/L)",sort = 13)
	private String cod;
	//氨氮nh3060
	@ExcelField(title = "NH3(mg/L)",sort = 14)
	private String nh3;
	//检测时间
	private Date recordingTime ;
	@ExcelField(title = "  时    间  ",sort = 3)
	private String time ;
	//格式化后的天时间
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date queryTime;
	//格式化的小时时间
	@JsonFormat(pattern="yyyy-MM-dd HH")
	private Date hourTime;
	// 站点id
	private Integer siteId;
	//站点名称
	@ExcelField(title = " 站  点  名  称 ",sort = 1)
	private String siteName;
	//站点编码
	private String siteCode;
	//设备名称
	@ExcelField(title = " 设   备 ",sort = 2)
	private String equipmentName;

	public RealtimeTreatmentPlant() {
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public static String getSort() {
		return sort;
	}

	public static void setSort(String sort) {
		RealtimeTreatmentPlant.sort = sort;
	}

	public String getTime() {
		String sort = getSort();
		if ("1".equals(sort)){
			return DateUtils.format(recordingTime, DateUtils.DATE_TIME_PATTERN);
		} else if ("2".equals(sort)) {
			return DateUtils.format(hourTime, DateUtils.DATE_TIME_PATTERN3);
		}else {
			return DateUtils.format(queryTime, DateUtils.DATE_PATTERN);
		}
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	/**id
	*@return 
	*/
	public String getId(){
		return  id;
	}
	/**id
	*@param  id
	*/
	public void setId(String id ){
		this.id = id;
	}
	
	/**污水值
	*@return 
	*/
	public String getBo1(){
		return  bo1;
	}
	/**污水值
	*@param  bo1
	*/
	public void setBo1(String bo1 ){
		this.bo1 = bo1;
	}
	
	/**总镉值
	*@return 
	*/
	public String getCd(){
		return  cd;
	}
	/**总镉值
	*@param  cd
	*/
	public void setCd(String cd ){
		this.cd = cd;
	}
	
	/**总铜值
	*@return 
	*/
	public String getCu(){
		return  cu;
	}
	/**总铜值
	*@param  cu
	*/
	public void setCu(String cu ){
		this.cu = cu;
	}
	
	/**设备编码
	*@return 
	*/
	public String getEqumentId(){
		return  equmentId;
	}
	/**设备编码
	*@param  equmentId
	*/
	public void setEqumentId(String equmentId ){
		this.equmentId = equmentId;
	}
	
	/**总铅值
	*@return 
	*/
	public String getPb(){
		return  pb;
	}
	/**总铅值
	*@param  pb
	*/
	public void setPb(String pb ){
		this.pb = pb;
	}
	
	/**ph值
	*@return 
	*/
	public String getPh(){
		return  ph;
	}
	/**ph值
	*@param  ph
	*/
	public void setPh(String ph ){
		this.ph = ph;
	}
	
	/**总砷值
	*@return 
	*/
	public String getShen(){
		return  shen;
	}
	/**总砷值
	*@param  shen
	*/
	public void setShen(String shen ){
		this.shen = shen;
	}
	
	/**总锌值
	*@return 
	*/
	public String getZn(){
		return  zn;
	}
	/**总锌值
	*@param  zn
	*/
	public void setZn(String zn ){
		this.zn = zn;
	}
	
	public String getTp() {
		return tp;
	}

	public void setTp(String tp) {
		this.tp = tp;
	}

	public String getTn() {
		return tn;
	}

	public void setTn(String tn) {
		this.tn = tn;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getNh3() {
		return nh3;
	}

	public void setNh3(String nh3) {
		this.nh3 = nh3;
	}

	/**检测时间
	*@return 
	*/
	public Date getRecordingTime(){
		return  recordingTime;
	}
	/**检测时间
	*@param  recordingTime
	*/
	public void setRecordingTime(Date recordingTime ){
		this.recordingTime = recordingTime;
	}

	public Date getQueryTime() {
		return queryTime;
	}

	public void setQueryTime(Date queryTime) {
		this.queryTime = queryTime;
	}

	public Date getHourTime() {
		return hourTime;
	}

	public void setHourTime(Date hourTime) {
		this.hourTime = hourTime;
	}
	
	
}
