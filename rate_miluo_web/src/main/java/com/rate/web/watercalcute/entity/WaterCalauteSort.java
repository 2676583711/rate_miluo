package com.rate.web.watercalcute.entity;

import org.beetl.sql.core.annotatoin.Table;

import java.math.BigDecimal;

/**
 * @program: rate_hbsg_water
 * @ClassName: DataReportVo
 * @description:
 * @author: chenh
 * @create: 2020-04-02 14:49
 **/
@Table(name="miluo_water_param_new")
public class WaterCalauteSort {
        //主键
        private String id ;
        //是否参与水质计算（1：参与；2：不参与）
        private Integer isCalculate ;
        //排序
        private Integer sortorder ;
        //类型1
        private Double type1 ;
        //类型2
        private Double type2 ;
        //类型3
        private Double type3 ;
        //类型4
        private Double type4 ;
        //类型5
        private Double type5 ;
        //描述
        private String description ;
        //lh编码
        private String lhCode ;
        //类型名称
        private String name ;
        //符号（计算水质类别对比）
        private String symbol ;
        //单位
        private String unit ;
        //wp编码
        private String wpCode ;

        public WaterCalauteSort() {
        }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getIsCalculate() {
        return isCalculate;
    }

    public void setIsCalculate(Integer isCalculate) {
        this.isCalculate = isCalculate;
    }

    public Integer getSortorder() {
        return sortorder;
    }

    public void setSortorder(Integer sortorder) {
        this.sortorder = sortorder;
    }

    public Double getType1() {
        return type1;
    }

    public void setType1(Double type1) {
        this.type1 = type1;
    }

    public Double getType2() {
        return type2;
    }

    public void setType2(Double type2) {
        this.type2 = type2;
    }

    public Double getType3() {
        return type3;
    }

    public void setType3(Double type3) {
        this.type3 = type3;
    }

    public Double getType4() {
        return type4;
    }

    public void setType4(Double type4) {
        this.type4 = type4;
    }

    public Double getType5() {
        return type5;
    }

    public void setType5(Double type5) {
        this.type5 = type5;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLhCode() {
        return lhCode;
    }

    public void setLhCode(String lhCode) {
        this.lhCode = lhCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getWpCode() {
        return wpCode;
    }

    public void setWpCode(String wpCode) {
        this.wpCode = wpCode;
    }
}
