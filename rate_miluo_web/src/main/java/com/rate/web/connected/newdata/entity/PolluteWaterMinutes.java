package com.rate.web.connected.newdata.entity;

import org.beetl.sql.core.annotatoin.Table;

import java.io.Serializable;
import java.util.Date;

@Table(name = "miluo_realtime_treatment_plant")
public class PolluteWaterMinutes implements Serializable {

    private String name;
   //id
    private String id ;
    //污水值
    private String bo1 ;
    //总镉值
    private String cd ;
    //总铜值
    private String cu ;
    //设备编码
    private String equmentId ;

    //总铅值
    private String pb ;
    //ph值
    private String ph ;
    //总砷值
    private String shen ;
    //总锌值
    private String zn ;
    private String tp ;
    private String tn ;
    private String cod ;
    private String nh3 ;

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

    //检测时间
    private Date recordingTime ;

    private String videoName;

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public PolluteWaterMinutes() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "WaterMinute{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", bo1='" + bo1 + '\'' +
                ", cd='" + cd + '\'' +
                ", cu='" + cu + '\'' +
                ", equmentId='" + equmentId + '\'' +
                ", pb='" + pb + '\'' +
                ", ph='" + ph + '\'' +
                ", shen='" + shen + '\'' +
                ", zn='" + zn + '\'' +
                ", recordingTime=" + recordingTime +
                '}';
    }
}
