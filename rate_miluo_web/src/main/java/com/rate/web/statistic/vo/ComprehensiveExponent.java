package com.rate.web.statistic.vo;

import java.math.BigDecimal;

/**
 * @ClassName ComprehensiveExponent
 * @Author LiuYong
 * @Date 2019/6/25 13:49
 * @Version 1.0
 **/
public class ComprehensiveExponent {
    /**
     * co95百分位浓度
     */
    private BigDecimal coAvg;
    /**
     * co95百分位浓度单项指数
     */
    private Double coExponent;
    /**
     * no2
     */
    private BigDecimal no2Avg;
    /**
     * no2单项指数
     */
    private Double no2Exponent;
    /**
     * 03,8小时滑动90百分位浓度
     */
    private BigDecimal o3EightHourAvg;
    /**
     * 03,8小时滑动90百分位浓度指数
     */
    private Double o3EightHourExponent;
    /**
     * o3一小时
     */
    private BigDecimal o3oneHourAvg;
    /**
     * o3单项指数
     */
    private Double o3Exponent;
    /**
     * pm10
     */
    private BigDecimal pm10Avg;
    /**
     * pm10单项指数
     */
    private Double pm10Exponent;
    /**
     * pm25
     */
    private BigDecimal pm25Avg;
    /**
     * pm25单项指数
     */
    private Double pm25Exponent;
    /**
     * so2
     */
    private BigDecimal so2Avg;
    /**
     * so2单项指数
     */
    private Double so2Exponent;
    /**
     * 站点编码
     */
    private String siteCode;
    /**
     * 站点名称
     */
    private String siteName;


    public BigDecimal getCoAvg() {
        return coAvg;
    }

    public void setCoAvg(BigDecimal coAvg) {
        this.coAvg = coAvg;
    }

    public Double getCoExponent() {
        return coExponent;
    }

    public void setCoExponent(Double coExponent) {
        this.coExponent = coExponent;
    }

    public BigDecimal getNo2Avg() {
        return no2Avg;
    }

    public void setNo2Avg(BigDecimal no2Avg) {
        this.no2Avg = no2Avg;
    }

    public Double getNo2Exponent() {
        return no2Exponent;
    }

    public void setNo2Exponent(Double no2Exponent) {
        this.no2Exponent = no2Exponent;
    }

    public BigDecimal getO3EightHourAvg() {
        return o3EightHourAvg;
    }

    public void setO3EightHourAvg(BigDecimal o3EightHourAvg) {
        this.o3EightHourAvg = o3EightHourAvg;
    }

    public Double getO3EightHourExponent() {
        return o3EightHourExponent;
    }

    public void setO3EightHourExponent(Double o3EightHourExponent) {
        this.o3EightHourExponent = o3EightHourExponent;
    }

    public BigDecimal getO3oneHourAvg() {
        return o3oneHourAvg;
    }

    public void setO3oneHourAvg(BigDecimal o3oneHourAvg) {
        this.o3oneHourAvg = o3oneHourAvg;
    }

    public Double getO3Exponent() {
        return o3Exponent;
    }

    public void setO3Exponent(Double o3Exponent) {
        this.o3Exponent = o3Exponent;
    }

    public BigDecimal getPm10Avg() {
        return pm10Avg;
    }

    public void setPm10Avg(BigDecimal pm10Avg) {
        this.pm10Avg = pm10Avg;
    }

    public Double getPm10Exponent() {
        return pm10Exponent;
    }

    public void setPm10Exponent(Double pm10Exponent) {
        this.pm10Exponent = pm10Exponent;
    }

    public BigDecimal getPm25Avg() {
        return pm25Avg;
    }

    public void setPm25Avg(BigDecimal pm25Avg) {
        this.pm25Avg = pm25Avg;
    }

    public Double getPm25Exponent() {
        return pm25Exponent;
    }

    public void setPm25Exponent(Double pm25Exponent) {
        this.pm25Exponent = pm25Exponent;
    }

    public BigDecimal getSo2Avg() {
        return so2Avg;
    }

    public void setSo2Avg(BigDecimal so2Avg) {
        this.so2Avg = so2Avg;
    }

    public Double getSo2Exponent() {
        return so2Exponent;
    }

    public void setSo2Exponent(Double so2Exponent) {
        this.so2Exponent = so2Exponent;
    }


    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    /**
     * 站点编码
     *
     * @return
     */
    public String getSiteCode() {
        return siteCode;
    }

    /**
     * 站点编码
     *
     * @param siteCode
     */
    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

}