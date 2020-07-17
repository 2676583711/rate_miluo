package com.rate.web.graphic.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.Table;

import java.io.Serializable;
import java.util.Date;

/**
 * @author shuzhangyao
 * @date 2019/5/31 16:48
 **/
@Table(name = "miluo_realtime_treatment_plant")
public class RealTimeTreatmentPlant implements Serializable {

    //id
    @AssignID
    private String id ;
    private String siteName;
    private String equName;
    //污水值
    private String bo1 ;
    //总镉值
    private String cd ;
    private String cd2 ;
    //总铜值
    private String cu ;
    //设备编码
    private String equmentId ;
    //总铅值
    private String pb ;
    //ph值
    private String ph ;
    private String ph2 ;
    //总砷值
    private String shen ;
    //总锌值
    private String zn ;
    //总磷
    private String tp ;
    private String tp2 ;
    //总氮
    private String tn ;
    private String tn2 ;
    //COD
    private String cod ;
    //氨氮
    private String nh3 ;
    //检测时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date recordingTime ;

    public String getCd2() {
        return cd2;
    }

    public void setCd2(String cd2) {
        this.cd2 = cd2;
    }

    public String getPh2() {
        return ph2;
    }

    public void setPh2(String ph2) {
        this.ph2 = ph2;
    }

    public String getTp2() {
        return tp2;
    }

    public void setTp2(String tp2) {
        this.tp2 = tp2;
    }

    public String getTn2() {
        return tn2;
    }

    public void setTn2(String tn2) {
        this.tn2 = tn2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBo1() {
        return bo1;
    }

    public void setBo1(String bo1) {
        this.bo1 = bo1;
    }

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public String getCu() {
        return cu;
    }

    public void setCu(String cu) {
        this.cu = cu;
    }

    public String getEqumentId() {
        return equmentId;
    }

    public void setEqumentId(String equmentId) {
        this.equmentId = equmentId;
    }

    public String getPb() {
        return pb;
    }

    public void setPb(String pb) {
        this.pb = pb;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public String getShen() {
        return shen;
    }

    public void setShen(String shen) {
        this.shen = shen;
    }

    public String getZn() {
        return zn;
    }

    public void setZn(String zn) {
        this.zn = zn;
    }

    public Date getRecordingTime() {
        return recordingTime;
    }

    public void setRecordingTime(Date recordingTime) {
        this.recordingTime = recordingTime;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getEquName() {
        return equName;
    }

    public void setEquName(String equName) {
        this.equName = equName;
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
}
