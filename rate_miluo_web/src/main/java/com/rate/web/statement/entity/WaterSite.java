package com.rate.web.statement.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rate.system.rate_system.utils.DateUtils;
import com.rate.system.rate_system.utils.excel.ExcelField;

public class WaterSite {

	//主键
	private String id ;
	//总铬
	private Double chromium ;
	//codmn
	private Double codmn ;
	//综合毒性
	@ExcelField(title = "综合毒性",sort = 12)
	private Double comprehensiveToxicity ;
	//电导率
	@ExcelField(title = "电导率(uS/cm)",sort = 7)
	private Double ddl ;
	private Double k03 ;
	private Double k04 ;
	//氨氮
	@ExcelField(title = "nh3N",sort = 11)
	private Double nh3N ;
	//总铅
	@ExcelField(title = "总铅(ug/L)",sort = 8)
	private Double pb ;
	//ph
	@ExcelField(title = "PH",sort = 5)
	private Double ph ;
	//plc
	private Double plc ;
	//溶解氧
	@ExcelField(title = "溶解氧(mg/L)",sort = 9)
	private Double rjy ;
	//总砷
	@ExcelField(title = "总砷(ug/L)",sort = 10)
	private Double shen ;
	//设备编号
	private String stationCode ;
	//水温
	@ExcelField(title = "水温(℃)",sort = 4)
	private Double sw ;
	//总磷
	@ExcelField(title = "总磷(mg/L)",sort = 13)
	private Double tp ;
	//水质等级
	@ExcelField(title = "水质等级",sort = 18)
	private String waterSort ;
	//浊度
	@ExcelField(title = "浊度(NTU)",sort = 6)
	private Double zd ;
	@ExcelField(title = "镉(mg/L)",sort = 14)
	private Double cd;
	@ExcelField(title = "高锰酸盐指数(mg/L)",sort = 15)
	private Double kmn;
	@ExcelField(title = "室温(℃)",sort = 16)
	private Double temp;
	@ExcelField(title = "湿度(%)",sort = 17)
	private Double humi;
	@ExcelField(title = "叶绿素(ug/L)",sort = 18)
	private Double yls;
	@ExcelField(title = "蓝绿藻(Kcells/mL)",sort = 19)
	private Double llz;
	//监测时间
	private Date queryTime ;
	private Date dateTime;
	@ExcelField(title = "时  间",sort = 3)
	private String dateTimes;
	//站点名称
	@ExcelField(title = "站 点 名 称",sort = 1)
	private String siteName;
	//站点编码
	private String siteCode;
	//设备名称
	@ExcelField(title = " 设  备 ",sort = 2)
	private String equipmentName;
	
	public WaterSite() {
	}

	public String getDateTimes() {
		return DateUtils.format(dateTime,DateUtils.DATE_TIME_PATTERN);
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
	
	/**总铬
	*@return 
	*/
	public Double getChromium(){
		return  chromium;
	}
	/**总铬
	*@param  chromium
	*/
	public void setChromium(Double chromium ){
		this.chromium = chromium;
	}
	
	/**codmn
	*@return 
	*/
	public Double getCodmn(){
		return  codmn;
	}
	/**codmn
	*@param  codmn
	*/
	public void setCodmn(Double codmn ){
		this.codmn = codmn;
	}
	
	/**综合毒性
	*@return 
	*/
	public Double getComprehensiveToxicity(){
		return  comprehensiveToxicity;
	}
	/**综合毒性
	*@param  comprehensiveToxicity
	*/
	public void setComprehensiveToxicity(Double comprehensiveToxicity ){
		this.comprehensiveToxicity = comprehensiveToxicity;
	}

	public Double getCd() {
		return cd;
	}

	public void setCd(Double cd) {
		this.cd = cd;
	}

	public Double getKmn() {
		return kmn;
	}

	public void setKmn(Double kmn) {
		this.kmn = kmn;
	}

	public Double getTemp() {
		return temp;
	}

	public void setTemp(Double temp) {
		this.temp = temp;
	}

	public Double getHumi() {
		return humi;
	}

	public void setHumi(Double humi) {
		this.humi = humi;
	}

	/**电导率
	*@return 
	*/
	public Double getDdl(){
		return  ddl;
	}
	/**电导率
	*@param  ddl
	*/
	public void setDdl(Double ddl ){
		this.ddl = ddl;
	}
	
	public Double getK03(){
		return  k03;
	}
	public void setK03(Double k03 ){
		this.k03 = k03;
	}
	
	public Double getK04(){
		return  k04;
	}
	public void setK04(Double k04 ){
		this.k04 = k04;
	}
	
	/**氨氮
	*@return 
	*/
	public Double getNh3N(){
		return  nh3N;
	}
	/**氨氮
	*@param  nh3N
	*/
	public void setNh3N(Double nh3N ){
		this.nh3N = nh3N;
	}
	
	/**总铅
	*@return 
	*/
	public Double getPb(){
		return  pb;
	}
	/**总铅
	*@param  pb
	*/
	public void setPb(Double pb ){
		this.pb = pb;
	}
	
	/**ph
	*@return 
	*/
	public Double getPh(){
		return  ph;
	}
	/**ph
	*@param  ph
	*/
	public void setPh(Double ph ){
		this.ph = ph;
	}
	
	/**plc
	*@return 
	*/
	public Double getPlc(){
		return  plc;
	}
	/**plc
	*@param  plc
	*/
	public void setPlc(Double plc ){
		this.plc = plc;
	}
	
	/**溶解氧
	*@return 
	*/
	public Double getRjy(){
		return  rjy;
	}
	/**溶解氧
	*@param  rjy
	*/
	public void setRjy(Double rjy ){
		this.rjy = rjy;
	}
	
	/**总砷
	*@return 
	*/
	public Double getShen(){
		return  shen;
	}
	/**总砷
	*@param  shen
	*/
	public void setShen(Double shen ){
		this.shen = shen;
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
	
	/**水温
	*@return 
	*/
	public Double getSw(){
		return  sw;
	}
	/**水温
	*@param  sw
	*/
	public void setSw(Double sw ){
		this.sw = sw;
	}
	
	/**总磷
	*@return 
	*/
	public Double getTp(){
		return  tp;
	}
	/**总磷
	*@param  tp
	*/
	public void setTp(Double tp ){
		this.tp = tp;
	}
	
	/**水质等级
	*@return 
	*/
	public String getWaterSort(){
		return  waterSort;
	}
	/**水质等级
	*@param  waterSort
	*/
	public void setWaterSort(String waterSort ){
		this.waterSort = waterSort;
	}
	
	/**浊度
	*@return 
	*/
	public Double getZd(){
		return  zd;
	}
	/**浊度
	*@param  zd
	*/
	public void setZd(Double zd ){
		this.zd = zd;
	}
	
	/**监测时间
	*@return 
	*/
	public Date getQueryTime(){
		return  queryTime;
	}
	/**监测时间
	*@param  dateTime
	*/
	public void setQueryTime(Date queryTime ){
		this.queryTime = queryTime;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public Double getYls() {
		return yls;
	}

	public void setYls(Double yls) {
		this.yls = yls;
	}

	public Double getLlz() {
		return llz;
	}

	public void setLlz(Double llz) {
		this.llz = llz;
	}
}
