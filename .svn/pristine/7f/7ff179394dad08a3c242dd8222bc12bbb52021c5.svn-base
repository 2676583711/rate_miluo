package com.rate.web.statement.entity;

import com.rate.system.rate_system.utils.DateUtils;
import com.rate.system.rate_system.utils.excel.ExcelField;

import java.util.Date;

/**
 * @Author chenshixue
 * @Date 2019/8/30
 */
public class PolluteRealtime {

    private String id;
    // 站点名称
    @ExcelField(title = "站  点  名  称",sort = 1)
    private String siteName;
    // 设备名称
    @ExcelField(title = "设  备  名  称",sort = 1)
    private String equipmentName;
    // 设备id
    private String equmentId;
    // 排放量
    @ExcelField(title = "排放量(m³/s)",sort = 3)
    private Double exhaust;
    // 烟尘
    @ExcelField(title = "烟尘(mg/m³)",sort = 4)
    private Double dust;
    // 氧气含量
    @ExcelField(title = "氧含量(%)",sort = 5)
    private Double o2;
    // 二氧化硫
    @ExcelField(title = "二氧化硫(mg/m³)",sort = 6)
    private Double so2;
    // 烟气温度
    @ExcelField(title = "温度(℃)",sort = 9)
    private Double temp;
    // 氮氧化物
    @ExcelField(title = "氮氧化物(mg/m³)",sort = 7)
    private Double nox;


    private Double nox2;
    private Double so22;

    // 烟气流速
    @ExcelField(title = "烟气流速(m/s)",sort = 8)
    private Double flowVelocity;
    // 烟气压力
    @ExcelField(title = "压力(kPa)",sort = 10)
    private Double press;
    // 一氧化碳
    private Double co;
    // 二氧化碳
    private Double co2;
    // 氯化氢
    private Double hcl;
    // 一氧化氮
    private Double no;
    // 二氧化氮
    private Double no2;
    // 烟气湿度
    private Double humidity;
    // 垃圾焚烧炉膛内焚烧平均温度
    private Double avgTempInChamber;
    // 垃圾焚烧炉膛内DCS温度
    private Double dcsTempInChamber;


    // 数据时间
    private Date recordingTime;
    @ExcelField(title = " 时    间 ",sort = 2)
    private String recordingTimes;
    // 数据类型（1实时 0折算）
    private Integer dataType;

    private Integer siteId;

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getId() {
        return id;
    }

    public String getRecordingTimes() {
        return DateUtils.format(recordingTime,DateUtils.DATE_TIME_PATTERN);
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

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEqumentId() {
        return equmentId;
    }

    public void setEqumentId(String equmentId) {
        this.equmentId = equmentId;
    }

    public Double getExhaust() {
        return exhaust;
    }

    public void setExhaust(Double exhaust) {
        this.exhaust = exhaust;
    }

    public Double getDust() {
        return dust;
    }

    public void setDust(Double dust) {
        this.dust = dust;
    }

    public Double getO2() {
        return o2;
    }

    public void setO2(Double o2) {
        this.o2 = o2;
    }

    public Double getSo2() {
        return so2;
    }

    public void setSo2(Double so2) {
        this.so2 = so2;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getNox() {
        return nox;
    }

    public void setNox(Double nox) {
        this.nox = nox;
    }

    public Double getFlowVelocity() {
        return flowVelocity;
    }

    public void setFlowVelocity(Double flowVelocity) {
        this.flowVelocity = flowVelocity;
    }

    public Double getPress() {
        return press;
    }

    public void setPress(Double press) {
        this.press = press;
    }

    public Double getCo() {
        return co;
    }

    public void setCo(Double co) {
        this.co = co;
    }

    public Double getCo2() {
        return co2;
    }

    public void setCo2(Double co2) {
        this.co2 = co2;
    }

    public Double getHcl() {
        return hcl;
    }

    public void setHcl(Double hcl) {
        this.hcl = hcl;
    }

    public Double getNo() {
        return no;
    }

    public void setNo(Double no) {
        this.no = no;
    }

    public Double getNo2() {
        return no2;
    }

    public void setNo2(Double no2) {
        this.no2 = no2;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getAvgTempInChamber() {
        return avgTempInChamber;
    }

    public void setAvgTempInChamber(Double avgTempInChamber) {
        this.avgTempInChamber = avgTempInChamber;
    }

    public Double getDcsTempInChamber() {
        return dcsTempInChamber;
    }

    public void setDcsTempInChamber(Double dcsTempInChamber) {
        this.dcsTempInChamber = dcsTempInChamber;
    }

    public Date getRecordingTime() {
        return recordingTime;
    }

    public void setRecordingTime(Date recordingTime) {
        this.recordingTime = recordingTime;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public Double getNox2() {
        return nox2;
    }

    public void setNox2(Double nox2) {
        this.nox2 = nox2;
    }

    public Double getSo22() {
        return so22;
    }

    public void setSo22(Double so22) {
        this.so22 = so22;
    }
}
