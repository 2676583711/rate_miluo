package com.rate.web.site.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.beetl.sql.core.annotatoin.AutoID;
import org.beetl.sql.core.annotatoin.Table;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author shuzhangyao
 * @date 2019/5/27 10:36
 **/
@Table(name = "miluo_site")
public class Site implements Serializable {

    @AutoID
    private String id;
    /**
     * 站点编码
     */
    private String siteCode;
    /**
     * 站点名称
     */
    private String name;
    /**
     * 经度
     */
    private String longitude;
    /**
     * 纬度
     */
    private String latitude;
    /**
     * 类型
     * 1 监测水  2 空气 3 涉气污染源
     */
    private String siteType;
    /**
     * 子类型
     */
    private String siteCategory;
    /**
     * 地址
     */
    private String address;
    /**
     * 备注说明
     */
    private String remark;
    /**
     * 站点数据状态
     * 数据状态(1 正常 0删除)
     */
    private String status;
    /**
     * 维护人
     */
    private String personId;
    /**
     * 现场设备图片
     */
    private String picMachine;
    /**
     * 维护方式
     * 1 定期（1个月）2 定期（3个月）
     */
    private String maintenanceWay;
    private String tsp;
    private String noise;

    public String getTsp() {
        return tsp;
    }

    public void setTsp(String tsp) {
        this.tsp = tsp;
    }

    public String getNoise() {
        return noise;
    }

    public void setNoise(String noise) {
        this.noise = noise;
    }

    /**
     * 区域类型（1.城区2.乡镇）
     */
    private String areaType;

    //监测因子列表
    private String params;
    /**
     * 负责人
     */
    private  String fzr;
    /**
     * 联系电话
     */
    private  String phone;

    private String equmentId;


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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPicMachine() {
        return picMachine;
    }

    public void setPicMachine(String picMachine) {
        this.picMachine = picMachine;
    }

    public String getMaintenanceWay() {
        return maintenanceWay;
    }

    public void setMaintenanceWay(String maintenanceWay) {
        this.maintenanceWay = maintenanceWay;
    }
    private BigDecimal o3;

    public String getSiteCategory() {
        return siteCategory;
    }

    public void setSiteCategory(String siteCategory) {
        this.siteCategory = siteCategory;
    }

    //aqi
    private Integer aqi ;
    //no
    private Integer no ;
    //nox
    private Integer nox ;
    //aqi等级
    private String aqType ;
    //co(一小时)
    private String co ;
    //co的aqi(一小时)
    private BigDecimal coIaqi ;
    //湿度
    private String humi ;
    //no2(一小时)
    private String no2 ;
    //no2的aqi(一小时)
    private BigDecimal no2Iaqi ;
    //o3(一小时)
    private String o3oneHour ;
    //o3的aqi(一小时)
    private BigDecimal o3oneHourIaqi ;
    //pm10(一小时)
    private String pm10 ;
    //pm10的aqi(一小时)
    private BigDecimal pm10Iaqi ;
    //pm25(一小时)
    private String pm25 ;
    //pm25的aqi(一小时)
    private BigDecimal pm25Iaqi ;
    //气压
    private String press ;
    //首要污染物
    private String primaryEp ;
    //雨量
    private String rain ;

    //so2(一小时)
    private String  so2 ;
    //so2的aqi(一小时)
    private BigDecimal so2Iaqi ;
    //气温
    private String temp ;
    //风向
    private String wd ;
    //风速
    private String ws ;
    //创建时间
    private Date createDate ;
    //数据时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date reportTime ;

    private String siteName;


    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
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

    /**aqi等级
     *@return
     */
    public String getAqType(){
        return  aqType;
    }
    /**aqi等级
     *@param  aqType
     */
    public void setAqType(String aqType ){
        this.aqType = aqType;
    }



    /**co的aqi(一小时)
     *@return
     */
    public BigDecimal getCoIaqi(){
        return  coIaqi;
    }
    /**co的aqi(一小时)
     *@param  coIaqi
     */
    public void setCoIaqi(BigDecimal coIaqi ){
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


    /**no2的aqi(一小时)
     *@return
     */
    public BigDecimal getNo2Iaqi(){
        return  no2Iaqi;
    }
    /**no2的aqi(一小时)
     *@param  no2Iaqi
     */
    public void setNo2Iaqi(BigDecimal no2Iaqi ){
        this.no2Iaqi = no2Iaqi;
    }

    /**o3(一小时)
     *@return
     */

    /**o3(一小时)
     *@param  o3oneHour
     */

    /**o3的aqi(一小时)
     *@return
     */
    public BigDecimal getO3oneHourIaqi(){
        return  o3oneHourIaqi;
    }
    /**o3的aqi(一小时)
     *@param  o3oneHourIaqi
     */
    public void setO3oneHourIaqi(BigDecimal o3oneHourIaqi ){
        this.o3oneHourIaqi = o3oneHourIaqi;
    }

    /**pm10(一小时)
     *@return
     */

    /**pm10(一小时)
     *@param  pm10
     */

    /**pm10的aqi(一小时)
     *@return
     */
    public BigDecimal getPm10Iaqi(){
        return  pm10Iaqi;
    }
    /**pm10的aqi(一小时)
     *@param  pm10Iaqi
     */
    public void setPm10Iaqi(BigDecimal pm10Iaqi ){
        this.pm10Iaqi = pm10Iaqi;
    }

    /**pm25(一小时)
     *@return
     */
    /**pm25(一小时)
     *@param
     */
    public String getCo() {
        return co;
    }

    public void setCo(String co) {
        this.co = co;
    }

    public String getNo2() {
        return no2;
    }

    public void setNo2(String no2) {
        this.no2 = no2;
    }

    public String getO3oneHour() {
        return o3oneHour;
    }

    public void setO3oneHour(String o3oneHour) {
        this.o3oneHour = o3oneHour;
    }

    public String getPm10() {
        return pm10;
    }

    public void setPm10(String pm10) {
        this.pm10 = pm10;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    /**pm25的aqi(一小时)
     *@return
     */
    public BigDecimal getPm25Iaqi(){
        return  pm25Iaqi;
    }
    /**pm25的aqi(一小时)
     *@param  pm25Iaqi
     */
    public void setPm25Iaqi(BigDecimal pm25Iaqi ){
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


    /**so2(一小时)
     *@return
     */
    public String getSo2(){
        return  so2;
    }
    /**so2(一小时)
     *@param  so2
     */
    public void setSo2(String so2 ){
        this.so2 = so2;
    }

    /**so2的aqi(一小时)
     *@return
     */
    public BigDecimal getSo2Iaqi(){
        return  so2Iaqi;
    }
    /**so2的aqi(一小时)
     *@param  so2Iaqi
     */
    public void setSo2Iaqi(BigDecimal so2Iaqi ){
        this.so2Iaqi = so2Iaqi;
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

    public BigDecimal getO3() {
        return o3;
    }

    public void setO3(BigDecimal o3) {
        this.o3 = o3;
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
    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    private String videoName;

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    //总铬
    private String chromium ;
    //codmn
    private String codmn ;
    //综合毒性
    private String comprehensiveToxicity ;
    //电导率
    private String ddl ;
    private String k03 ;
    private String k04 ;
    //氨氮
    private String nh3N ;
    //总铅
    private String pb ;
    //ph
    private String ph ;
    //plc
    private String plc ;
    //溶解氧
    private String rjy ;
    //总砷
    private String shen ;
    //设备编号
    private String stationCode ;
    //水温
    private String sw ;
    //总磷
    private String tp ;
    //水质等级
    private String waterSort ;
    //浊度
    private String zd ;
    //监测时间
    private Date dateTime ;

//    private String tp ;
    private String tn ;
    private String cod ;
    private String nh3 ;

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

    /**总铬
     *@return
     */
    public String getChromium(){
        return  chromium;
    }
    /**总铬
     *@param  chromium
     */
    public void setChromium(String chromium ){
        this.chromium = chromium;
    }

    /**codmn
     *@return
     */
    public String getCodmn(){
        return  codmn;
    }
    /**codmn
     *@param  codmn
     */
    public void setCodmn(String codmn ){
        this.codmn = codmn;
    }

    /**综合毒性
     *@return
     */
    public String getComprehensiveToxicity(){
        return  comprehensiveToxicity;
    }
    /**综合毒性
     *@param  comprehensiveToxicity
     */
    public void setComprehensiveToxicity(String comprehensiveToxicity ){
        this.comprehensiveToxicity = comprehensiveToxicity;
    }

    /**电导率
     *@return
     */
    public String getDdl(){
        return  ddl;
    }
    /**电导率
     *@param  ddl
     */
    public void setDdl(String ddl ){
        this.ddl = ddl;
    }

    public String getK03(){
        return  k03;
    }
    public void setK03(String k03 ){
        this.k03 = k03;
    }

    public String getK04(){
        return  k04;
    }
    public void setK04(String k04 ){
        this.k04 = k04;
    }

    /**氨氮
     *@return
     */
    public String getNh3N(){
        return  nh3N;
    }
    /**氨氮
     *@param  nh3N
     */
    public void setNh3N(String nh3N ){
        this.nh3N = nh3N;
    }

    /**总铅
     *@return
     */
    public String getPb(){
        return  pb;
    }
    /**总铅
     *@param  pb
     */
    public void setPb(String pb ){
        this.pb = pb;
    }

    /**ph
     *@return
     */
    public String getPh(){
        return  ph;
    }
    /**ph
     *@param  ph
     */
    public void setPh(String ph ){
        this.ph = ph;
    }

    /**plc
     *@return
     */
    public String getPlc(){
        return  plc;
    }
    /**plc
     *@param  plc
     */
    public void setPlc(String plc ){
        this.plc = plc;
    }

    /**溶解氧
     *@return
     */
    public String getRjy(){
        return  rjy;
    }
    /**溶解氧
     *@param  rjy
     */
    public void setRjy(String rjy ){
        this.rjy = rjy;
    }

    /**总砷
     *@return
     */
    public String getShen(){
        return  shen;
    }
    /**总砷
     *@param  shen
     */
    public void setShen(String shen ){
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
    public String getSw(){
        return  sw;
    }
    /**水温
     *@param  sw
     */
    public void setSw(String sw ){
        this.sw = sw;
    }

    /**总磷
     *@return
     */
    public String getTp(){
        return  tp;
    }
    /**总磷
     *@param  tp
     */
    public void setTp(String tp ){
        this.tp = tp;
    }
   private String tps;

    public String getTps() {
        return tps;
    }

    public void setTps(String tps) {
        this.tps = tps;
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
    public String getZd(){
        return  zd;
    }
    /**浊度
     *@param  zd
     */
    public void setZd(String zd ){
        this.zd = zd;
    }

    /**监测时间
     *@return
     */
    public Date getDateTime(){
        return  dateTime;
    }
    /**监测时间
     *@param  dateTime
     */
    public void setDateTime(Date dateTime ){
        this.dateTime = dateTime;
    }



    private String bo1 ;
    //总镉值
    private String cd ;
    //总铜值
    private String cu ;
    //设备编码


    //总锌值
    private String zn ;
    //检测时间
    private Date recordingTime ;

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

}
