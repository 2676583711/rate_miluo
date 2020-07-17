package com.rate.web.task.entity;

import com.rate.system.rate_system.utils.excel.ExcelField;
import org.beetl.sql.core.annotatoin.AssignID;

import java.util.Date;

/* 
* 
* gen by beetlsql 2019-06-03
*/
public class MiluoTaskInfoExp {

	//主键
	@AssignID
	private String id ;
	//状态(1:已处理0未处理)
	private Integer status ;
	@ExcelField(title = "处理状态",sort = 7)
	private String statusText ;
	//负责人id
	private String chargePersonId ;
	//紧急程度（普通不紧急	0，重要紧急	1，最高级别	2）
	@ExcelField(title = "紧急程度",sort = 4)
	private String degreeEmergency ;
	//部门id
	private String deptId ;
	//任务名称
	@ExcelField(title = "任  务  名  称",sort = 1)
	private String name ;
	//发布人id
	private String pubPersonId ;
	//备注
	private String remark ;
	//任务编号
	private String taskNo;
	//设备编号
	@ExcelField(title = "设备编号",sort = 9)
	private String stationCode ;
	//任务类型（报警任务0, 巡检1,维护2,易耗品更换3,仪器校准4,维修5,试剂更换6,废液回收7,其他8）
	@ExcelField(title = "任务类型",sort = 8)
	private String taskType ;
	//结束时间
	@ExcelField(title = "结束时间",sort = 6)
	private Date endTime ;
	//开始时间
	@ExcelField(title = "负责人",sort = 3)
	private String fzr ;
	@ExcelField(title = "开始时间",sort = 5)
	private Date startTime ;
	private String dpname ;
	private String mobile;
    private  String photo;

    private String seriousExceed;


	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	public String getSeriousExceed() {
		return seriousExceed;
	}

	public void setSeriousExceed(String seriousExceed) {
		this.seriousExceed = seriousExceed;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	//站点名称
	@ExcelField(title = "站  点  名  称",sort = 2)
	private String siteName;

	/**
	 * 发布人
	 */
	private String pubPersonName;

	/**
	 *  负责人
	 */
	private String chargePersonName;

	/**
	 *  站点类型
	 */
	private String siteType;

	/**
	 * 经度
	 */
	private String longitude;

	/**
	 * 纬度
	 */
	private String latitude;

	/**
	 *  站点类型
	 */
	private String siteCategory;

	/**
	 * 站点编号
	 */
	private String siteCode;

	/**
	 *  站点id
	 */
	private String siteId;

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getSiteCategory() {
		return siteCategory;
	}

	public void setSiteCategory(String siteCategory) {
		this.siteCategory = siteCategory;
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

	public String getPubPersonName() {
		return pubPersonName;
	}

	public void setPubPersonName(String pubPersonName) {
		this.pubPersonName = pubPersonName;
	}

	public String getChargePersonName() {
		return chargePersonName;
	}

	public void setChargePersonName(String chargePersonName) {
		this.chargePersonName = chargePersonName;
	}

	public String getFzr() {
		return fzr;
	}

	public void setFzr(String fzr) {
		this.fzr = fzr;
	}

	public String getDpname() {
		return dpname;
	}

	public void setDpname(String dpname) {
		this.dpname = dpname;
	}


	public MiluoTaskInfoExp() {
	}
	
	/**主键
	*@return 
	*/
	public String getId(){
		return  id;
	}
	/**主键
	*@param  id
	*/
	public void setId(String id ){
		this.id = id;
	}
	
	/**状态(1:已处理0未处理)
	*@return 
	*/
	public Integer getStatus(){
		return  status;
	}
	/**状态(1:已处理0未处理)
	*@param  status
	*/
	public void setStatus(Integer status ){
		this.status = status;
	}
	
	/**负责人id
	*@return 
	*/
	public String getChargePersonId(){
		return  chargePersonId;
	}
	/**负责人id
	*@param  chargePersonId
	*/
	public void setChargePersonId(String chargePersonId ){
		this.chargePersonId = chargePersonId;
	}
	
	/**紧急程度（普通不紧急	0，重要紧急	1，最高级别	2）
	*@return 
	*/
	public String getDegreeEmergency(){
		return  degreeEmergency;
	}
	/**紧急程度（普通不紧急	0，重要紧急	1，最高级别	2）
	*@param  degreeEmergency
	*/
	public void setDegreeEmergency(String degreeEmergency ){
		this.degreeEmergency = degreeEmergency;
	}
	
	/**部门id
	*@return 
	*/
	public String getDeptId(){
		return  deptId;
	}
	/**部门id
	*@param  deptId
	*/
	public void setDeptId(String deptId ){
		this.deptId = deptId;
	}
	
	/**任务名称
	*@return 
	*/
	public String getName(){
		return  name;
	}
	/**任务名称
	*@param  name
	*/
	public void setName(String name ){
		this.name = name;
	}
	
	/**发布人
	*@return 
	*/
	public String getPubPersonId(){
		return  pubPersonId;
	}
	/**发布人
	*@param  pubPersonId
	*/
	public void setPubPersonId(String pubPersonId ){
		this.pubPersonId = pubPersonId;
	}
	
	/**备注
	*@return 
	*/
	public String getRemark(){
		return  remark;
	}
	/**备注
	*@param  remark
	*/
	public void setRemark(String remark ){
		this.remark = remark;
	}
	
	/**设备编号
	*@return 
	*/
	public String getStationCode(){
		return  stationCode;
	}
	/**设备编号
	*@param  stationCode
	*/
	public void setStationCode(String stationCode ){
		this.stationCode = stationCode;
	}
	
	/**任务类型（报警任务0, 巡检1,维护2,易耗品更换3,仪器校准4,维修5,试剂更换6,废液回收7,其他8）
	*@return 
	*/
	public String getTaskType(){
		return  taskType;
	}
	/**任务类型（报警任务0, 巡检1,维护2,易耗品更换3,仪器校准4,维修5,试剂更换6,废液回收7,其他8）
	*@param  taskType
	*/
	public void setTaskType(String taskType ){
		this.taskType = taskType;
	}
	
	/**结束时间
	*@return 
	*/
	public Date getEndTime(){
		return  endTime;
	}
	/**结束时间
	*@param  endTime
	*/
	public void setEndTime(Date endTime ){
		this.endTime = endTime;
	}
	
	/**开始时间
	*@return 
	*/
	public Date getStartTime(){
		return  startTime;
	}
	/**开始时间
	*@param  startTime
	*/
	public void setStartTime(Date startTime ){
		this.startTime = startTime;
	}

	public String getTaskNo() {
		return taskNo;
	}

	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	
}