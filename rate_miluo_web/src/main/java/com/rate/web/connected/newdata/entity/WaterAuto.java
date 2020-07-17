package com.rate.web.connected.newdata.entity;

import org.beetl.sql.core.annotatoin.Table;

import java.util.Date;

/**
 * @ProjectName: rate_miluo_parent
 * @Package: com.rate.web.connected.newdata.entity
 * @ClassName: WaterAuto
 * @author: xiaoshi
 * @Description: 这是水自动站的最新数据
 * @Date: 2019/6/1 9:37
 * @Version: 1.0
 */
@Table(name="miluo_water_site")
public class WaterAuto {

    //站点名称
    private String name;
    //主键
    private String id ;
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
    private String cd;
    private String kmn;
    private String temp;
    private String humi;
    private String yls;
    private String llz;
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
    // 经度
    private String longitude;
    // 纬度
    private String latitude;
    //地址
    private String address;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String videoName;

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getYls() {
        return yls;
    }

    public void setYls(String yls) {
        this.yls = yls;
    }

    public String getLlz() {
        return llz;
    }

    public void setLlz(String llz) {
        this.llz = llz;
    }

    public WaterAuto() {
    }

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public String getKmn() {
        return kmn;
    }

    public void setKmn(String kmn) {
        this.kmn = kmn;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getHumi() {
        return humi;
    }

    public void setHumi(String humi) {
        this.humi = humi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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


}
