package com.rate.web.statistic.vo;

/**
 * @ClassName RateVO
 * @Author LiuYong
 * @Date 2019/6/25 15:54
 * @Version 1.0
 **/
public class RateVO {

    /**
     * 站点名称
     */
    private String siteName;

    /**
     *  优良天数
     */
    private Integer choicenessDays;

    /**
     * 有效天数
     */
    private Integer validDays;

    /**
     * 前年优良天数
     */
    private Integer preYearChoicenessDays;

    /**
     * 前年有效天数
     */
    private Integer preYearValidDays;

    /**
     * 上月优良天数
     */
    private Integer preMonthChoicenessDays;

    /**
     * 上月有效天数
     */
    private Integer preMonthValidDays;

    /**
     * 优良天数比率
     */
    private Double excellentAndGoodRatio;

    /**
     * 上月优良天数比率
     */
    private Double ratioPoor;

    private Double mexcellentAndGoodRatio;

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public Integer getChoicenessDays() {
        return choicenessDays;
    }

    public void setChoicenessDays(Integer choicenessDays) {
        this.choicenessDays = choicenessDays;
    }

    public Integer getValidDays() {
        return validDays;
    }

    public void setValidDays(Integer validDays) {
        this.validDays = validDays;
    }

    public Integer getPreYearChoicenessDays() {
        return preYearChoicenessDays;
    }

    public void setPreYearChoicenessDays(Integer preYearChoicenessDays) {
        this.preYearChoicenessDays = preYearChoicenessDays;
    }

    public Integer getPreYearValidDays() {
        return preYearValidDays;
    }

    public void setPreYearValidDays(Integer preYearValidDays) {
        this.preYearValidDays = preYearValidDays;
    }

    public Integer getPreMonthChoicenessDays() {
        return preMonthChoicenessDays;
    }

    public void setPreMonthChoicenessDays(Integer preMonthChoicenessDays) {
        this.preMonthChoicenessDays = preMonthChoicenessDays;
    }

    public Integer getPreMonthValidDays() {
        return preMonthValidDays;
    }

    public void setPreMonthValidDays(Integer preMonthValidDays) {
        this.preMonthValidDays = preMonthValidDays;
    }

    public Double getExcellentAndGoodRatio() {
        return excellentAndGoodRatio;
    }

    public void setExcellentAndGoodRatio(Double excellentAndGoodRatio) {
        this.excellentAndGoodRatio = excellentAndGoodRatio;
    }

    public Double getRatioPoor() {
        return ratioPoor;
    }

    public void setRatioPoor(Double ratioPoor) {
        this.ratioPoor = ratioPoor;
    }

    public Double getMexcellentAndGoodRatio() {
        return mexcellentAndGoodRatio;
    }

    public void setMexcellentAndGoodRatio(Double mexcellentAndGoodRatio) {
        this.mexcellentAndGoodRatio = mexcellentAndGoodRatio;
    }
}
