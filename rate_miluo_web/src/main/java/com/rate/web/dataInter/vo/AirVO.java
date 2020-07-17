package com.rate.web.dataInter.vo;

import java.util.Date;

/**
 * @Author chenshixue
 * @Date 2019/11/21
 */
public class AirVO {
    private String name;
    private String siteCode;
    private Date queryTime;
    private Integer aqi;
    private Double tsp;
    private Double pm10;
    private Double pm25;
    private Double no2;
    private Double so2;
    private Double o3oneHour;
    private Double co;
    private String primaryEp;
    private String aqType;
    private String aqDegree;
    private Double noise;
    private Double ws;
    private Double wd;
    private Double temp;
    private Double humi;
    private Double press;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    public Date getQueryTime() {
        return queryTime;
    }

    public void setQueryTime(Date queryTime) {
        this.queryTime = queryTime;
    }

    public Integer getAqi() {
        return aqi;
    }

    public void setAqi(Integer aqi) {
        this.aqi = aqi;
    }

    public Double getPm10() {
        return pm10;
    }

    public void setPm10(Double pm10) {
        this.pm10 = pm10;
    }

    public Double getPm25() {
        return pm25;
    }

    public void setPm25(Double pm25) {
        this.pm25 = pm25;
    }

    public Double getNo2() {
        return no2;
    }

    public void setNo2(Double no2) {
        this.no2 = no2;
    }

    public Double getSo2() {
        return so2;
    }

    public void setSo2(Double so2) {
        this.so2 = so2;
    }

    public Double getO3oneHour() {
        return o3oneHour;
    }

    public void setO3oneHour(Double o3oneHour) {
        this.o3oneHour = o3oneHour;
    }

    public Double getCo() {
        return co;
    }

    public void setCo(Double co) {
        this.co = co;
    }

    public Double getTsp() {
        return tsp;
    }

    public void setTsp(Double tsp) {
        this.tsp = tsp;
    }

    public String getPrimaryEp() {
        return primaryEp;
    }

    public void setPrimaryEp(String primaryEp) {
        this.primaryEp = primaryEp;
    }

    public String getAqType() {
        return aqType;
    }

    public void setAqType(String aqType) {
        this.aqType = aqType;
    }

    public String getAqDegree() {
        return aqDegree;
    }

    public void setAqDegree(String aqDegree) {
        this.aqDegree = aqDegree;
    }

    public Double getNoise() {
        return noise;
    }

    public void setNoise(Double noise) {
        this.noise = noise;
    }

    public Double getWs() {
        return ws;
    }

    public void setWs(Double ws) {
        this.ws = ws;
    }

    public Double getWd() {
        return wd;
    }

    public void setWd(Double wd) {
        this.wd = wd;
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

    public Double getPress() {
        return press;
    }

    public void setPress(Double press) {
        this.press = press;
    }
}
