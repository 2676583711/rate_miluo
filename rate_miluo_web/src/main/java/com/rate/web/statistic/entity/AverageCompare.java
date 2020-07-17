package com.rate.web.statistic.entity;


/**
 * @Description: 大气综合分析同比
 */
public class AverageCompare {
    // 区域
    private String areaName;
    // 站点名称
    private String siteName;

    // so2上一年度指定时间段平均浓度
    private Double so2PreYearAvg;
    // so2本年度指定时间段平均浓度
    private Double so2Avg;
    // no2上一年度指定时间段平均浓度
    private Double no2PreYearAvg;
    // no2本年度指定时间段平均浓度
    private Double no2Avg;
    // pm10上一年度指定时间段平均浓度
    private Double pm10PreYearAvg;
    // pm10本年度指定时间段平均浓度
    private Double pm10Avg;
    // pm25上一年度指定时间段平均浓度
    private Double pm25PreYearAvg;
    // pm25本年度指定时间段平均浓度
    private Double pm25Avg;
    // co上一年度指定时间段平均浓度
    private Double coPreYearAvg;
    // co本年度指定时间段平均浓度
    private Double coAvg;
    // o3Eight上一年度指定时间段平均浓度
    private Double o3EightPreYearAvg;
    // o3Eight本年度指定时间段平均浓度
    private Double o3EightAvg;
    // pm25上月度指定时间段平均浓度
    private Double pm25MonthAvg;

    // pm10月度指定时间段平均浓度
    private Double pm10MonthAvg;
    // o3Eight上月度指定时间段平均浓度
    private Double o3EightMonthAvg;
    // so2上月度指定时间段平均浓度
    private Double so2MonthAvg;
    // no2上月度指定时间段平均浓度
    private Double no2MonthAvg;
    // co上月度指定时间段平均浓度
    private Double coMonthAvg;
    /**
     * 优良天数
     */
    private Integer excellentAndGood;
    /**
    /**
     * 优良天数比率
     */
    private Double excellentAndGoodRatio;

    /**
     * 上年优良天数
     */
    private Integer yearExcellentDays;

    /**
     * 上年优良天数比率
     */
    private Double yearRatio;

    /**
     * 上月优良天数
     */
    private Integer monthExcellentDays;

    /**
     * 上月优良天数比率
     */
    private Double monthRatio;

    private Double pm25Rate;
    private Double pm10Rate;
    private Double so2Rate;
    private Double no2Rate;
    private Double coRate;
    private Double o3Rate;

    private Double pm25MonthRate;
    private Double pm10MonthRate;
    private Double so2MonthRate;
    private Double no2MonthRate;
    private Double coRMonthate;
    private Double o3RMonthate;


    private Integer pm25Days;
    private Integer o3EightDays;
    private Integer pm10Days;

    private String dateRange;




    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }


    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public Double getSo2PreYearAvg() {
        return so2PreYearAvg;
    }

    public void setSo2PreYearAvg(Double so2PreYearAvg) {
        this.so2PreYearAvg = so2PreYearAvg;
    }

    public Double getSo2Avg() {
        return so2Avg;
    }

    public void setSo2Avg(Double so2Avg) {
        this.so2Avg = so2Avg;
    }

    public Double getNo2PreYearAvg() {
        return no2PreYearAvg;
    }

    public void setNo2PreYearAvg(Double no2PreYearAvg) {
        this.no2PreYearAvg = no2PreYearAvg;
    }

    public Double getNo2Avg() {
        return no2Avg;
    }

    public void setNo2Avg(Double no2Avg) {
        this.no2Avg = no2Avg;
    }

    public Double getPm10PreYearAvg() {
        return pm10PreYearAvg;
    }

    public void setPm10PreYearAvg(Double pm10PreYearAvg) {
        this.pm10PreYearAvg = pm10PreYearAvg;
    }

    public Double getPm10Avg() {
        return pm10Avg;
    }

    public void setPm10Avg(Double pm10Avg) {
        this.pm10Avg = pm10Avg;
    }

    public Double getPm25PreYearAvg() {
        return pm25PreYearAvg;
    }

    public void setPm25PreYearAvg(Double pm25PreYearAvg) {
        this.pm25PreYearAvg = pm25PreYearAvg;
    }

    public Double getPm25Avg() {
        return pm25Avg;
    }

    public void setPm25Avg(Double pm25Avg) {
        this.pm25Avg = pm25Avg;
    }

    public Double getCoPreYearAvg() {
        return coPreYearAvg;
    }

    public void setCoPreYearAvg(Double coPreYearAvg) {
        this.coPreYearAvg = coPreYearAvg;
    }

    public Double getCoAvg() {
        return coAvg;
    }

    public void setCoAvg(Double coAvg) {
        this.coAvg = coAvg;
    }

    public Double getO3EightPreYearAvg() {
        return o3EightPreYearAvg;
    }

    public void setO3EightPreYearAvg(Double o3EightPreYearAvg) {
        this.o3EightPreYearAvg = o3EightPreYearAvg;
    }

    public Double getO3EightAvg() {
        return o3EightAvg;
    }

    public void setO3EightAvg(Double o3EightAvg) {
        this.o3EightAvg = o3EightAvg;
    }

    public Integer getExcellentAndGood() {
        return excellentAndGood;
    }

    public void setExcellentAndGood(Integer excellentAndGood) {
        this.excellentAndGood = excellentAndGood;
    }

    public Double getExcellentAndGoodRatio() {
        return excellentAndGoodRatio;
    }

    public void setExcellentAndGoodRatio(Double excellentAndGoodRatio) {
        this.excellentAndGoodRatio = excellentAndGoodRatio;
    }

    public Integer getYearExcellentDays() {
        return yearExcellentDays;
    }

    public void setYearExcellentDays(Integer yearExcellentDays) {
        this.yearExcellentDays = yearExcellentDays;
    }

    public Double getYearRatio() {
        return yearRatio;
    }

    public void setYearRatio(Double yearRatio) {
        this.yearRatio = yearRatio;
    }

    public Double getPm25MonthAvg() {
        return pm25MonthAvg;
    }

    public void setPm25MonthAvg(Double pm25MonthAvg) {
        this.pm25MonthAvg = pm25MonthAvg;
    }

    public Double getPm10MonthAvg() {
        return pm10MonthAvg;
    }

    public void setPm10MonthAvg(Double pm10MonthAvg) {
        this.pm10MonthAvg = pm10MonthAvg;
    }

    public Integer getMonthExcellentDays() {
        return monthExcellentDays;
    }

    public void setMonthExcellentDays(Integer monthExcellentDays) {
        this.monthExcellentDays = monthExcellentDays;
    }

    public Double getMonthRatio() {
        return monthRatio;
    }

    public void setMonthRatio(Double monthRatio) {
        this.monthRatio = monthRatio;
    }

    public String getDateRange() {
        return dateRange;
    }

    public void setDateRange(String dateRange) {
        this.dateRange = dateRange;
    }

    public Double getO3EightMonthAvg() {
        return o3EightMonthAvg;
    }

    public void setO3EightMonthAvg(Double o3EightMonthAvg) {
        this.o3EightMonthAvg = o3EightMonthAvg;
    }

    public Double getPm25Rate() {
        return pm25Rate;
    }

    public void setPm25Rate(Double pm25Rate) {
        this.pm25Rate = pm25Rate;
    }

    public Double getPm10Rate() {
        return pm10Rate;
    }

    public void setPm10Rate(Double pm10Rate) {
        this.pm10Rate = pm10Rate;
    }

    public Double getSo2Rate() {
        return so2Rate;
    }

    public void setSo2Rate(Double so2Rate) {
        this.so2Rate = so2Rate;
    }

    public Double getNo2Rate() {
        return no2Rate;
    }

    public void setNo2Rate(Double no2Rate) {
        this.no2Rate = no2Rate;
    }

    public Double getCoRate() {
        return coRate;
    }

    public void setCoRate(Double coRate) {
        this.coRate = coRate;
    }

    public Double getO3Rate() {
        return o3Rate;
    }

    public void setO3Rate(Double o3Rate) {
        this.o3Rate = o3Rate;
    }

    public Double getPm25MonthRate() {
        return pm25MonthRate;
    }

    public void setPm25MonthRate(Double pm25MonthRate) {
        this.pm25MonthRate = pm25MonthRate;
    }

    public Double getPm10MonthRate() {
        return pm10MonthRate;
    }

    public void setPm10MonthRate(Double pm10MonthRate) {
        this.pm10MonthRate = pm10MonthRate;
    }

    public Double getSo2MonthRate() {
        return so2MonthRate;
    }

    public void setSo2MonthRate(Double so2MonthRate) {
        this.so2MonthRate = so2MonthRate;
    }

    public Double getNo2MonthRate() {
        return no2MonthRate;
    }

    public void setNo2MonthRate(Double no2MonthRate) {
        this.no2MonthRate = no2MonthRate;
    }

    public Double getCoRMonthate() {
        return coRMonthate;
    }

    public void setCoRMonthate(Double coRMonthate) {
        this.coRMonthate = coRMonthate;
    }

    public Double getO3RMonthate() {
        return o3RMonthate;
    }

    public void setO3RMonthate(Double o3RMonthate) {
        this.o3RMonthate = o3RMonthate;
    }

    public Double getSo2MonthAvg() {
        return so2MonthAvg;
    }

    public void setSo2MonthAvg(Double so2MonthAvg) {
        this.so2MonthAvg = so2MonthAvg;
    }

    public Double getNo2MonthAvg() {
        return no2MonthAvg;
    }

    public void setNo2MonthAvg(Double no2MonthAvg) {
        this.no2MonthAvg = no2MonthAvg;
    }

    public Double getCoMonthAvg() {
        return coMonthAvg;
    }

    public void setCoMonthAvg(Double coMonthAvg) {
        this.coMonthAvg = coMonthAvg;
    }

    public Integer getPm25Days() {
        return pm25Days;
    }

    public void setPm25Days(Integer pm25Days) {
        this.pm25Days = pm25Days;
    }

    public Integer getO3EightDays() {
        return o3EightDays;
    }

    public void setO3EightDays(Integer o3EightDays) {
        this.o3EightDays = o3EightDays;
    }

    public Integer getPm10Days() {
        return pm10Days;
    }

    public void setPm10Days(Integer pm10Days) {
        this.pm10Days = pm10Days;
    }
}