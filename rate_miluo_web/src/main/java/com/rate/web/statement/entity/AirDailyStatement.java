package com.rate.web.statement.entity;

import java.util.Date;

import org.beetl.sql.core.annotatoin.Table;
/**
 * 空气日数据表
 * @author chenshixue
 * @date 2019年5月28日
 */
@Table(name="miluo_air_daily_statements")
public class AirDailyStatement {
	//主键
	private String id ;
	//aqi
	private Integer aqi ;
	//no
	private Integer no ;
	//nox
	private Integer nox ;
	//aq等级
	private String aqDegree ;
	//aqi类型
	private String aqType ;
	//co(一小时)
	private Double co ;
	//co的aqi(一小时)
	private Double coIaqi ;
	//湿度
	private String humi ;
	//no2(一小时)
	private Double no2 ;
	//no2的aqi(一小时)
	private Double no2Iaqi ;
	//噪声
	private String noise ;
	//o3(八小时)
	private Double o3eightHour ;
	//o3的aqi(一小时)
	private Double o3oneHourIaqi ;
	//pm10(一小时)
	private Double pm10 ;
	//pm10的aqi(一小时)
	private Double pm10Iaqi ;
	//pm25(一小时)
	private Double pm25 ;
	//pm25的aqi(一小时)
	private Double pm25Iaqi ;
	//气压
	private String press ;
	//首要污染物
	private String primaryEp ;
	//雨量
	private String rain ;
	//站点编码
	private String siteCode ;
	//so2(一小时)
	private Double so2 ;
	//so2的aqi(一小时)
	private Double so2Iaqi ;
	//站点名称
	private String stationName ;
	//站点类型
	private String stationType ;
	//气温
	private String temp ;
	//总悬浮颗粒物
	private String tsp ;
	//风向
	private String wd ;
	//风速
	private String ws ;
	//创建时间
	private Date createDate ;
	//数据时间
	private Date queryTime ;
	
	public AirDailyStatement() {
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
	
	/**aqi
	*@return 
	*/
	public Integer getAqi(){
		return  aqi;
	}
	/**aqi
	*@param  aqi
	*/
	public void setAqi(Integer aqi ){
		this.aqi = aqi;
	}
	
	/**no
	*@return 
	*/
	public Integer getNo(){
		return  no;
	}
	/**no
	*@param  no
	*/
	public void setNo(Integer no ){
		this.no = no;
	}
	
	/**nox
	*@return 
	*/
	public Integer getNox(){
		return  nox;
	}
	/**nox
	*@param  nox
	*/
	public void setNox(Integer nox ){
		this.nox = nox;
	}
	
	/**aq等级
	*@return 
	*/
	public String getAqDegree(){
		return  aqDegree;
	}
	/**aq等级
	*@param  aqDegree
	*/
	public void setAqDegree(String aqDegree ){
		this.aqDegree = aqDegree;
	}
	
	/**aqi类型
	*@return 
	*/
	public String getAqType(){
		return  aqType;
	}
	/**aqi类型
	*@param  aqType
	*/
	public void setAqType(String aqType ){
		this.aqType = aqType;
	}
	
	/**co(一小时)
	*@return 
	*/
	public Double getCo(){
		return  co;
	}
	/**co(一小时)
	*@param  co
	*/
	public void setCo(Double co ){
		this.co = co;
	}
	
	/**co的aqi(一小时)
	*@return 
	*/
	public Double getCoIaqi(){
		return  coIaqi;
	}
	/**co的aqi(一小时)
	*@param  coIaqi
	*/
	public void setCoIaqi(Double coIaqi ){
		this.coIaqi = coIaqi;
	}
	
	/**湿度
	*@return 
	*/
	public String getHumi(){
		return  humi;
	}
	/**湿度
	*@param  humi
	*/
	public void setHumi(String humi ){
		this.humi = humi;
	}
	
	/**no2(一小时)
	*@return 
	*/
	public Double getNo2(){
		return  no2;
	}
	/**no2(一小时)
	*@param  no2
	*/
	public void setNo2(Double no2 ){
		this.no2 = no2;
	}
	
	/**no2的aqi(一小时)
	*@return 
	*/
	public Double getNo2Iaqi(){
		return  no2Iaqi;
	}
	/**no2的aqi(一小时)
	*@param  no2Iaqi
	*/
	public void setNo2Iaqi(Double no2Iaqi ){
		this.no2Iaqi = no2Iaqi;
	}
	
	/**噪声
	*@return 
	*/
	public String getNoise(){
		return  noise;
	}
	/**噪声
	*@param  noise
	*/
	public void setNoise(String noise ){
		this.noise = noise;
	}
	
	/**o3(八小时)
	*@return 
	*/
	public Double getO3eightHour(){
		return  o3eightHour;
	}
	/**o3(八小时)
	*@param  o3eightHour
	*/
	public void setO3eightHour(Double o3eightHour ){
		this.o3eightHour = o3eightHour;
	}
	
	/**o3的aqi(一小时)
	*@return 
	*/
	public Double getO3oneHourIaqi(){
		return  o3oneHourIaqi;
	}
	/**o3的aqi(一小时)
	*@param  o3oneHourIaqi
	*/
	public void setO3oneHourIaqi(Double o3oneHourIaqi ){
		this.o3oneHourIaqi = o3oneHourIaqi;
	}
	
	/**pm10(一小时)
	*@return 
	*/
	public Double getPm10(){
		return  pm10;
	}
	/**pm10(一小时)
	*@param  pm10
	*/
	public void setPm10(Double pm10 ){
		this.pm10 = pm10;
	}
	
	/**pm10的aqi(一小时)
	*@return 
	*/
	public Double getPm10Iaqi(){
		return  pm10Iaqi;
	}
	/**pm10的aqi(一小时)
	*@param  pm10Iaqi
	*/
	public void setPm10Iaqi(Double pm10Iaqi ){
		this.pm10Iaqi = pm10Iaqi;
	}
	
	/**pm25(一小时)
	*@return 
	*/
	public Double getPm25(){
		return  pm25;
	}
	/**pm25(一小时)
	*@param  pm25
	*/
	public void setPm25(Double pm25 ){
		this.pm25 = pm25;
	}
	
	/**pm25的aqi(一小时)
	*@return 
	*/
	public Double getPm25Iaqi(){
		return  pm25Iaqi;
	}
	/**pm25的aqi(一小时)
	*@param  pm25Iaqi
	*/
	public void setPm25Iaqi(Double pm25Iaqi ){
		this.pm25Iaqi = pm25Iaqi;
	}
	
	/**气压
	*@return 
	*/
	public String getPress(){
		return  press;
	}
	/**气压
	*@param  press
	*/
	public void setPress(String press ){
		this.press = press;
	}
	
	/**首要污染物
	*@return 
	*/
	public String getPrimaryEp(){
		return  primaryEp;
	}
	/**首要污染物
	*@param  primaryEp
	*/
	public void setPrimaryEp(String primaryEp ){
		this.primaryEp = primaryEp;
	}
	
	/**雨量
	*@return 
	*/
	public String getRain(){
		return  rain;
	}
	/**雨量
	*@param  rain
	*/
	public void setRain(String rain ){
		this.rain = rain;
	}
	
	/**站点编码
	*@return 
	*/
	public String getSiteCode(){
		return  siteCode;
	}
	/**站点编码
	*@param  siteCode
	*/
	public void setSiteCode(String siteCode ){
		this.siteCode = siteCode;
	}
	
	/**so2(一小时)
	*@return 
	*/
	public Double getSo2(){
		return  so2;
	}
	/**so2(一小时)
	*@param  so2
	*/
	public void setSo2(Double so2 ){
		this.so2 = so2;
	}
	
	/**so2的aqi(一小时)
	*@return 
	*/
	public Double getSo2Iaqi(){
		return  so2Iaqi;
	}
	/**so2的aqi(一小时)
	*@param  so2Iaqi
	*/
	public void setSo2Iaqi(Double so2Iaqi ){
		this.so2Iaqi = so2Iaqi;
	}
	
	/**站点名称
	*@return 
	*/
	public String getStationName(){
		return  stationName;
	}
	/**站点名称
	*@param  stationName
	*/
	public void setStationName(String stationName ){
		this.stationName = stationName;
	}
	
	/**站点类型
	*@return 
	*/
	public String getStationType(){
		return  stationType;
	}
	/**站点类型
	*@param  stationType
	*/
	public void setStationType(String stationType ){
		this.stationType = stationType;
	}
	
	/**气温
	*@return 
	*/
	public String getTemp(){
		return  temp;
	}
	/**气温
	*@param  temp
	*/
	public void setTemp(String temp ){
		this.temp = temp;
	}
	
	/**总悬浮颗粒物
	*@return 
	*/
	public String getTsp(){
		return  tsp;
	}
	/**总悬浮颗粒物
	*@param  tsp
	*/
	public void setTsp(String tsp ){
		this.tsp = tsp;
	}
	
	/**风向
	*@return 
	*/
	public String getWd(){
		return  wd;
	}
	/**风向
	*@param  wd
	*/
	public void setWd(String wd ){
		this.wd = wd;
	}
	
	/**风速
	*@return 
	*/
	public String getWs(){
		return  ws;
	}
	/**风速
	*@param  ws
	*/
	public void setWs(String ws ){
		this.ws = ws;
	}
	
	/**创建时间
	*@return 
	*/
	public Date getCreateDate(){
		return  createDate;
	}
	/**创建时间
	*@param  createDate
	*/
	public void setCreateDate(Date createDate ){
		this.createDate = createDate;
	}
	
	/**数据时间
	*@return 
	*/
	public Date getQueryTime(){
		return  queryTime;
	}
	/**数据时间
	*@param  queryTime
	*/
	public void setQueryTime(Date queryTime ){
		this.queryTime = queryTime;
	}
}
