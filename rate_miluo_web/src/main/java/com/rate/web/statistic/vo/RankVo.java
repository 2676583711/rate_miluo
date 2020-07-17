package com.rate.web.statistic.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rate.system.rate_system.utils.excel.ExcelField;

import java.io.Serializable;
import java.util.Date;

/**
 * @Param 
 * @Return 
 * @author xiaoshi       
 * @Description  
 * @Date 2019/6/27
 * @Time 14:48
 **/
public class RankVo implements Serializable {

    //区域
    private String areaName;
    //站点名称
    private String siteName;
    private String siteType;
    //站点编码
    private String siteCode ;
    // 时间
    @ExcelField(title = "时间" , groups = 1 ,sort = 3)
    private String time;
    //AQI
    private Long aqi;

    //排名
    private Integer ranking;

    private String pm25;
    private String pm10;
    private String so2;
    private String no2;
    private String co;
    private String o3;
    private String o3oneHour;
    private String o3eightHour;
    @ExcelField(title = "监测值(μg/m³)", align = 2, sort = 3)
    private Integer rankValue;


    // 排名
    @ExcelField(title = "排名" , groups = 1 ,sort = 1)
    private Integer sort;
    // 综合指数
    @ExcelField(title = "综合指数" , groups = 1 ,sort = 5)
    private Double zhzs;
    // 最大单项指数
    @ExcelField(title = "最大单项指数" , groups = 1 ,sort = 6)
    private Double bigOne;
    // 最大污染物
    @ExcelField(title = "最大污染物" , groups = 1 ,sort = 7)
    private String bigPre;
    // pm25单项指数
    @ExcelField(title = "PM2.5单项指数" , groups = 1 ,sort = 9)
    private Double pm25Zh;
    // pm10单项指数
    @ExcelField(title = "PM10单项指数" , groups = 1 ,sort = 11)
    private Double pm10Zh;
    // so2单项指数
    @ExcelField(title = "SO2单项指数" , groups = 1 ,sort = 17)
    private Double so2Zh;
    // no2单项指数
    @ExcelField(title = "NO2单项指数" , groups = 1 ,sort =15)
    private Double no2Zh;
    // o3单项指数
    @ExcelField(title = "O3 90PRE单项指数" , groups = 1 ,sort = 13)
    private Double o3Zh;
    // co单项指数
    @ExcelField(title = "CO 95PRE单项指数" , groups = 1 ,sort = 19)
    private Double coZh;

    //去年综合指数
    private Double odlzhzs;

    //同比增幅
    private Double newodlzhzs;


    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Double getZhzs() {
        return zhzs;
    }

    public void setZhzs(Double zhzs) {
        this.zhzs = zhzs;
    }

    public Double getBigOne() {
        return bigOne;
    }

    public void setBigOne(Double bigOne) {
        this.bigOne = bigOne;
    }

    public String getBigPre() {
        return bigPre;
    }

    public void setBigPre(String bigPre) {
        this.bigPre = bigPre;
    }

    public Double getPm25Zh() {
        return pm25Zh;
    }

    public void setPm25Zh(Double pm25Zh) {
        this.pm25Zh = pm25Zh;
    }

    public Double getPm10Zh() {
        return pm10Zh;
    }

    public void setPm10Zh(Double pm10Zh) {
        this.pm10Zh = pm10Zh;
    }

    public Double getSo2Zh() {
        return so2Zh;
    }

    public void setSo2Zh(Double so2Zh) {
        this.so2Zh = so2Zh;
    }

    public Double getNo2Zh() {
        return no2Zh;
    }

    public String getSiteCode() {
        return siteCode;
    }

    public Double getOdlzhzs() {
        return odlzhzs;
    }

    public void setOdlzhzs(Double odlzhzs) {
        this.odlzhzs = odlzhzs;
    }

    public Double getNewodlzhzs() {
        return newodlzhzs;
    }

    public void setNewodlzhzs(Double newodlzhzs) {
        this.newodlzhzs = newodlzhzs;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    public void setNo2Zh(Double no2Zh) {
        this.no2Zh = no2Zh;
    }

    public Double getO3Zh() {
        return o3Zh;
    }

    public void setO3Zh(Double o3Zh) {
        this.o3Zh = o3Zh;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getCoZh() {
        return coZh;
    }

    public void setCoZh(Double coZh) {
        this.coZh = coZh;
    }

    public String getSiteType() {
        return siteType;
    }

    public void setSiteType(String siteType) {
        this.siteType = siteType;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public String getPm10() {
        return pm10;
    }

    public void setPm10(String pm10) {
        this.pm10 = pm10;
    }

    public String getSo2() {
        return so2;
    }

    public void setSo2(String so2) {
        this.so2 = so2;
    }

    public String getNo2() {
        return no2;
    }

    public void setNo2(String no2) {
        this.no2 = no2;
    }

    public String getCo() {
        return co;
    }

    public void setCo(String co) {
        this.co = co;
    }

    public String getO3() {
        return o3;
    }

    public void setO3(String o3) {
        this.o3 = o3;
    }

    public String getO3oneHour() {
        return o3oneHour;
    }

    public void setO3oneHour(String o3oneHour) {
        this.o3oneHour = o3oneHour;
    }

    public String getO3eightHour() {
        return o3eightHour;
    }

    public void setO3eightHour(String o3eightHour) {
        this.o3eightHour = o3eightHour;
    }

    public Integer getRankValue() {
        return rankValue;
    }

    public void setRankValue(Integer rankValue) {
        this.rankValue = rankValue;
    }

    //污染等级
    private String type;

    private Double value;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date queryTime;

    private Date sDatetime;

    private String cityName;

    @ExcelField(title = "地区", align = 2, sort = 2)
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Date getsDatetime() {
        return sDatetime;
    }

    public void setsDatetime(Date sDatetime) {
        this.sDatetime = sDatetime;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    //颜色
    private String color;

    @ExcelField(title = "监测值(μg/m³)", align = 2, sort = 3)
    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    //站点类型
    private String stationType;

    public String getStationType() {
        return stationType;
    }

    public void setStationType(String stationType) {
        this.stationType = stationType;
    }

    @ExcelField(title = "区域", align = 2, sort = 1)
    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    @ExcelField(title = "站点名称", align = 2, sort = 1)
    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public Long getAqi() {
        return aqi;
    }

    public void setAqi(Long aqi) {
        this.aqi = aqi;
    }

    @ExcelField(title = "排名", align = 2, sort = 4)
    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getQueryTime() {
        return queryTime;
    }

    public void setQueryTime(Date queryTime) {
        this.queryTime = queryTime;
    }

    @Override
    public String toString() {
        return "RankVo{" +
                "siteName='" + siteName + '\'' +
                ", aqi=" + aqi +
                ", ranking=" + ranking +
                ", type='" + type + '\'' +
                ", value=" + value +
                ", queryTime=" + queryTime +
                ", sDatetime=" + sDatetime +
                ", cityName='" + cityName + '\'' +
                ", color='" + color + '\'' +
                ", stationType='" + stationType + '\'' +
                '}';
    }
}
