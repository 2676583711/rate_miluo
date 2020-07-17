package com.rate.web.dataInter.vo;

import java.util.Date;

/**
 * @Author chenshixue
 * @Date 2019/11/21
 */
public class WaterAutoVO {

    private String name;
    private String stationCode;
    private Date dateTime;
    private Double sw;
    private Double ph;
    private Double kmn;
    private Double ddl;
    private Double zd;
    private Double rjy;
    private Double tp;
    private Double nh3N;
    private Double shen;
    private Double pb;
    private Double cd;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dataTime) {
        this.dateTime = dataTime;
    }

    public Double getSw() {
        return sw;
    }

    public void setSw(Double sw) {
        this.sw = sw;
    }

    public Double getPh() {
        return ph;
    }

    public void setPh(Double ph) {
        this.ph = ph;
    }

    public Double getKmn() {
        return kmn;
    }

    public void setKmn(Double kmn) {
        this.kmn = kmn;
    }

    public Double getDdl() {
        return ddl;
    }

    public void setDdl(Double ddl) {
        this.ddl = ddl;
    }

    public Double getZd() {
        return zd;
    }

    public void setZd(Double zd) {
        this.zd = zd;
    }

    public Double getRjy() {
        return rjy;
    }

    public void setRjy(Double rjy) {
        this.rjy = rjy;
    }

    public Double getTp() {
        return tp;
    }

    public void setTp(Double tp) {
        this.tp = tp;
    }

    public Double getNh3N() {
        return nh3N;
    }

    public void setNh3N(Double nh3N) {
        this.nh3N = nh3N;
    }

    public Double getShen() {
        return shen;
    }

    public void setShen(Double shen) {
        this.shen = shen;
    }

    public Double getPb() {
        return pb;
    }

    public void setPb(Double pb) {
        this.pb = pb;
    }

    public Double getCd() {
        return cd;
    }

    public void setCd(Double cd) {
        this.cd = cd;
    }
}
