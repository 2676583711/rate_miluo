package com.rate.web.statement.entity;

import java.util.Date;

import org.beetl.sql.core.annotatoin.Table;
/**
 * 微型站接收数据
 * @author chenshixue
 * @date 2019年5月27日
 */
@Table(name="miluo_air_minute_small_statement")
public class AirMinuteStatement {
	//编号
	private String id ;
	//(0:标况，1:实况)
	private Integer type ;
	//CO
	private Double co ;
	//CO标识
	private String coTag ;
	//湿度
	private Double humi ;
	//湿度标识
	private String humiTag ;
	//NO
	private Double no ;
	//N02
	private Double no2 ;
	//N02标识
	private String no2Tag ;
	//NO标识
	private String noTag ;
	//噪声
	private Double noise ;
	//噪声标识
	private String noiseTag ;
	//NOx
	private Double nox ;
	//NOx标识
	private String noxTag ;
	//O3
	private Double o3 ;
	//O3标识
	private String o3Tag ;
	//PM10
	private Double pm10 ;
	//PM10标识
	private String pm10Tag ;
	//PM2.5
	private Double pm25 ;
	//PM2.5标识
	private String pm25Tag ;
	//气压
	private Double press ;
	//气压标识
	private String pressTag ;
	//雨量
	private Double rain ;
	//雨量标识
	private String rainTag ;
	//站点
	private String siteCode ;
	//SO2
	private Double so2 ;
	//S02标识
	private String so2Tag ;
	//气温
	private Double temp ;
	//气温标识
	private String tempTag ;
	//总悬浮颗粒物
	private Double tsp ;
	//总悬浮颗粒物标识
	private String tspTag ;
	//风向
	private Double wd ;
	//风向标识
	private String wdTag ;
	//风速
	private Double ws ;
	//风速标识
	private String wsTag ;
	//断开连接的时间
	private Date breakTime ;
	//上报时间
	private Date reportTime ;
	//站点名称
	private String siteName;
	
	public AirMinuteStatement() {
	}
	
	/**编号
	*@return 
	*/
	public String getId(){
		return  id;
	}
	/**编号
	*@param  id
	*/
	public void setId(String id ){
		this.id = id;
	}
	
	/**(0:标况，1:实况)
	*@return 
	*/
	public Integer getType(){
		return  type;
	}
	/**(0:标况，1:实况)
	*@param  type
	*/
	public void setType(Integer type ){
		this.type = type;
	}
	
	/**CO
	*@return 
	*/
	public Double getCo(){
		return  co;
	}
	/**CO
	*@param  co
	*/
	public void setCo(Double co ){
		this.co = co;
	}
	
	/**CO标识
	*@return 
	*/
	public String getCoTag(){
		return  coTag;
	}
	/**CO标识
	*@param  coTag
	*/
	public void setCoTag(String coTag ){
		this.coTag = coTag;
	}
	
	/**湿度
	*@return 
	*/
	public Double getHumi(){
		return  humi;
	}
	/**湿度
	*@param  humi
	*/
	public void setHumi(Double humi ){
		this.humi = humi;
	}
	
	/**湿度标识
	*@return 
	*/
	public String getHumiTag(){
		return  humiTag;
	}
	/**湿度标识
	*@param  humiTag
	*/
	public void setHumiTag(String humiTag ){
		this.humiTag = humiTag;
	}
	
	/**NO
	*@return 
	*/
	public Double getNo(){
		return  no;
	}
	/**NO
	*@param  no
	*/
	public void setNo(Double no ){
		this.no = no;
	}
	
	/**N02
	*@return 
	*/
	public Double getNo2(){
		return  no2;
	}
	/**N02
	*@param  no2
	*/
	public void setNo2(Double no2 ){
		this.no2 = no2;
	}
	
	/**N02标识
	*@return 
	*/
	public String getNo2Tag(){
		return  no2Tag;
	}
	/**N02标识
	*@param  no2Tag
	*/
	public void setNo2Tag(String no2Tag ){
		this.no2Tag = no2Tag;
	}
	
	/**NO标识
	*@return 
	*/
	public String getNoTag(){
		return  noTag;
	}
	/**NO标识
	*@param  noTag
	*/
	public void setNoTag(String noTag ){
		this.noTag = noTag;
	}
	
	/**噪声
	*@return 
	*/
	public Double getNoise(){
		return  noise;
	}
	/**噪声
	*@param  noise
	*/
	public void setNoise(Double noise ){
		this.noise = noise;
	}
	
	/**噪声标识
	*@return 
	*/
	public String getNoiseTag(){
		return  noiseTag;
	}
	/**噪声标识
	*@param  noiseTag
	*/
	public void setNoiseTag(String noiseTag ){
		this.noiseTag = noiseTag;
	}
	
	/**NOx
	*@return 
	*/
	public Double getNox(){
		return  nox;
	}
	/**NOx
	*@param  nox
	*/
	public void setNox(Double nox ){
		this.nox = nox;
	}
	
	/**NOx标识
	*@return 
	*/
	public String getNoxTag(){
		return  noxTag;
	}
	/**NOx标识
	*@param  noxTag
	*/
	public void setNoxTag(String noxTag ){
		this.noxTag = noxTag;
	}
	
	/**O3
	*@return 
	*/
	public Double getO3(){
		return  o3;
	}
	/**O3
	*@param  o3
	*/
	public void setO3(Double o3 ){
		this.o3 = o3;
	}
	
	/**O3标识
	*@return 
	*/
	public String getO3Tag(){
		return  o3Tag;
	}
	/**O3标识
	*@param  o3Tag
	*/
	public void setO3Tag(String o3Tag ){
		this.o3Tag = o3Tag;
	}
	
	/**PM10
	*@return 
	*/
	public Double getPm10(){
		return  pm10;
	}
	/**PM10
	*@param  pm10
	*/
	public void setPm10(Double pm10 ){
		this.pm10 = pm10;
	}
	
	/**PM10标识
	*@return 
	*/
	public String getPm10Tag(){
		return  pm10Tag;
	}
	/**PM10标识
	*@param  pm10Tag
	*/
	public void setPm10Tag(String pm10Tag ){
		this.pm10Tag = pm10Tag;
	}
	
	/**PM2.5
	*@return 
	*/
	public Double getPm25(){
		return  pm25;
	}
	/**PM2.5
	*@param  pm25
	*/
	public void setPm25(Double pm25 ){
		this.pm25 = pm25;
	}
	
	/**PM2.5标识
	*@return 
	*/
	public String getPm25Tag(){
		return  pm25Tag;
	}
	/**PM2.5标识
	*@param  pm25Tag
	*/
	public void setPm25Tag(String pm25Tag ){
		this.pm25Tag = pm25Tag;
	}
	
	/**气压
	*@return 
	*/
	public Double getPress(){
		return  press;
	}
	/**气压
	*@param  press
	*/
	public void setPress(Double press ){
		this.press = press;
	}
	
	/**气压标识
	*@return 
	*/
	public String getPressTag(){
		return  pressTag;
	}
	/**气压标识
	*@param  pressTag
	*/
	public void setPressTag(String pressTag ){
		this.pressTag = pressTag;
	}
	
	/**雨量
	*@return 
	*/
	public Double getRain(){
		return  rain;
	}
	/**雨量
	*@param  rain
	*/
	public void setRain(Double rain ){
		this.rain = rain;
	}
	
	/**雨量标识
	*@return 
	*/
	public String getRainTag(){
		return  rainTag;
	}
	/**雨量标识
	*@param  rainTag
	*/
	public void setRainTag(String rainTag ){
		this.rainTag = rainTag;
	}
	
	/**站点
	*@return 
	*/
	public String getSiteCode(){
		return  siteCode;
	}
	/**站点
	*@param  siteCode
	*/
	public void setSiteCode(String siteCode ){
		this.siteCode = siteCode;
	}
	
	/**SO2
	*@return 
	*/
	public Double getSo2(){
		return  so2;
	}
	/**SO2
	*@param  so2
	*/
	public void setSo2(Double so2 ){
		this.so2 = so2;
	}
	
	/**S02标识
	*@return 
	*/
	public String getSo2Tag(){
		return  so2Tag;
	}
	/**S02标识
	*@param  so2Tag
	*/
	public void setSo2Tag(String so2Tag ){
		this.so2Tag = so2Tag;
	}
	
	/**气温
	*@return 
	*/
	public Double getTemp(){
		return  temp;
	}
	/**气温
	*@param  temp
	*/
	public void setTemp(Double temp ){
		this.temp = temp;
	}
	
	/**气温标识
	*@return 
	*/
	public String getTempTag(){
		return  tempTag;
	}
	/**气温标识
	*@param  tempTag
	*/
	public void setTempTag(String tempTag ){
		this.tempTag = tempTag;
	}
	
	/**总悬浮颗粒物
	*@return 
	*/
	public Double getTsp(){
		return  tsp;
	}
	/**总悬浮颗粒物
	*@param  tsp
	*/
	public void setTsp(Double tsp ){
		this.tsp = tsp;
	}
	
	/**总悬浮颗粒物标识
	*@return 
	*/
	public String getTspTag(){
		return  tspTag;
	}
	/**总悬浮颗粒物标识
	*@param  tspTag
	*/
	public void setTspTag(String tspTag ){
		this.tspTag = tspTag;
	}
	
	/**风向
	*@return 
	*/
	public Double getWd(){
		return  wd;
	}
	/**风向
	*@param  wd
	*/
	public void setWd(Double wd ){
		this.wd = wd;
	}
	
	/**风向标识
	*@return 
	*/
	public String getWdTag(){
		return  wdTag;
	}
	/**风向标识
	*@param  wdTag
	*/
	public void setWdTag(String wdTag ){
		this.wdTag = wdTag;
	}
	
	/**风速
	*@return 
	*/
	public Double getWs(){
		return  ws;
	}
	/**风速
	*@param  ws
	*/
	public void setWs(Double ws ){
		this.ws = ws;
	}
	
	/**风速标识
	*@return 
	*/
	public String getWsTag(){
		return  wsTag;
	}
	/**风速标识
	*@param  wsTag
	*/
	public void setWsTag(String wsTag ){
		this.wsTag = wsTag;
	}
	
	/**断开连接的时间
	*@return 
	*/
	public Date getBreakTime(){
		return  breakTime;
	}
	/**断开连接的时间
	*@param  breakTime
	*/
	public void setBreakTime(Date breakTime ){
		this.breakTime = breakTime;
	}
	
	/**上报时间
	*@return 
	*/
	public Date getReportTime(){
		return  reportTime;
	}
	/**上报时间
	*@param  reportTime
	*/
	public void setReportTime(Date reportTime ){
		this.reportTime = reportTime;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	
}
