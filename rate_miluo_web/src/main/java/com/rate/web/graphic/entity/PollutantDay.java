package com.rate.web.graphic.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.beetl.sql.core.annotatoin.Table;

import java.io.Serializable;
import java.util.Date;

/**
 * @author shuzhangyao
 * @date 2019/6/1 19:23
 **/
@Table(name = "miluo_pollutant_hour")
public class PollutantDay implements Serializable {
    private String id ;
    private String siteName;
    //设备id
    private String equmentId ;
    private String gyqll ;
    //颗粒物(干)
    private String klwG ;
    //颗粒物(排放率)
    private String klwPfl ;
    //颗粒物（湿）
    private String klwS ;
    //颗粒物(折算值)
    private String klwZsz ;
    private String nox ;
    //NOx(排放率)
    private String noxPfl ;
    //NOx(折算值)
    private String noxZsz ;
    //O2(干)
    private String o2 ;
    private String so2 ;
    //SO2(排放率)
    private String so2Pfl ;
    //SO2(折算值)
    private String so2Zsz ;
    //湿烟气流量
    private String syqll ;
    //烟气流速
    private String yqls ;
    //烟气湿度
    private String yqsd ;
    //烟气温度
    private String yqwd ;
    //烟气压力
    private String yqyl ;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createDate ;
    //数据时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date queryTime ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getEqumentId() {
        return equmentId;
    }

    public void setEqumentId(String equmentId) {
        this.equmentId = equmentId;
    }

    public String getGyqll() {
        return gyqll;
    }

    public void setGyqll(String gyqll) {
        this.gyqll = gyqll;
    }

    public String getKlwG() {
        return klwG;
    }

    public void setKlwG(String klwG) {
        this.klwG = klwG;
    }

    public String getKlwPfl() {
        return klwPfl;
    }

    public void setKlwPfl(String klwPfl) {
        this.klwPfl = klwPfl;
    }

    public String getKlwS() {
        return klwS;
    }

    public void setKlwS(String klwS) {
        this.klwS = klwS;
    }

    public String getKlwZsz() {
        return klwZsz;
    }

    public void setKlwZsz(String klwZsz) {
        this.klwZsz = klwZsz;
    }

    public String getNox() {
        return nox;
    }

    public void setNox(String nox) {
        this.nox = nox;
    }

    public String getNoxPfl() {
        return noxPfl;
    }

    public void setNoxPfl(String noxPfl) {
        this.noxPfl = noxPfl;
    }

    public String getNoxZsz() {
        return noxZsz;
    }

    public void setNoxZsz(String noxZsz) {
        this.noxZsz = noxZsz;
    }

    public String getO2() {
        return o2;
    }

    public void setO2(String o2) {
        this.o2 = o2;
    }

    public String getSo2() {
        return so2;
    }

    public void setSo2(String so2) {
        this.so2 = so2;
    }

    public String getSo2Pfl() {
        return so2Pfl;
    }

    public void setSo2Pfl(String so2Pfl) {
        this.so2Pfl = so2Pfl;
    }

    public String getSo2Zsz() {
        return so2Zsz;
    }

    public void setSo2Zsz(String so2Zsz) {
        this.so2Zsz = so2Zsz;
    }

    public String getSyqll() {
        return syqll;
    }

    public void setSyqll(String syqll) {
        this.syqll = syqll;
    }

    public String getYqls() {
        return yqls;
    }

    public void setYqls(String yqls) {
        this.yqls = yqls;
    }

    public String getYqsd() {
        return yqsd;
    }

    public void setYqsd(String yqsd) {
        this.yqsd = yqsd;
    }

    public String getYqwd() {
        return yqwd;
    }

    public void setYqwd(String yqwd) {
        this.yqwd = yqwd;
    }

    public String getYqyl() {
        return yqyl;
    }

    public void setYqyl(String yqyl) {
        this.yqyl = yqyl;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getQueryTime() {
        return queryTime;
    }

    public void setQueryTime(Date queryTime) {
        this.queryTime = queryTime;
    }
}
