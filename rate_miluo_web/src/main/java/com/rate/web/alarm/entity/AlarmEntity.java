package com.rate.web.alarm.entity;

import com.rate.system.rate_system.utils.excel.ExcelField;
import org.beetl.sql.core.annotatoin.AutoID;
import org.beetl.sql.core.annotatoin.Table;

import java.util.Date;

/**
 * @Author chenshixue
 * @Date 2019/12/19
 */
@Table(name = "miluo_site_alarm")
public class AlarmEntity {

    // 站点id
    private Long siteId;
    @AutoID
    private Long id;


    // 站点名称
    @ExcelField(title = "站点", sort = 1)
    private String siteName;
    // 设备编码
    private String equipmentId;
    // 设备名称
    private String equipmentName;
    // 污染物名称
    private String name;
    // 污染物标识
    @ExcelField(title = "报警因子", sort = 2)
    private String pollutant;
    // 报警数据时间
    @ExcelField(title = "报警时间", sort = 3)
    private Date dataTime;
    // 值
    @ExcelField(title = "监测值", sort = 4)
    private String value;
    // 标准值
    @ExcelField(title = "指标值", sort = 5)
    private String indexValue;
    // 超标/不足 级别
    @ExcelField(title = "报警等级", sort = 7)
    private String degreeText;
    // 超标率
    private Double exceedsBidRatio;
    // 超标/不足
    @ExcelField(title = "报警类型", sort = 6)
    private String text;
    // 状态：处理，未处理
    private Integer status;
    @ExcelField(title = "状态", sort = 8)
    private String statusText;
    // 是否严重超标
    private Integer seriousExceed;
    // 处理人id
    private Long handler;
    // 处理时间
    private Date handleTime;
    // 报警类型（空气，水质，污水厂，涉气污染源）
    private String alarmType;
    // 是否推送信息
    private Integer pushMessage;
    // 负责人id
    private String fzr;
    // 负责人名字
    private String fzrName;
    // 负责人电话
    private String mobile;



    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public Long getSiteId() {
        return siteId;
    }

    public String getFzr() {
        return fzr;
    }

    public void setFzr(String fzr) {
        this.fzr = fzr;
    }

    public String getFzrName() {
        return fzrName;
    }

    public void setFzrName(String fzrName) {
        this.fzrName = fzrName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getPollutant() {
        return pollutant;
    }

    public void setPollutant(String pollutant) {
        this.pollutant = pollutant;
    }

    public Date getDataTime() {
        return dataTime;
    }

    public void setDataTime(Date dataTime) {
        this.dataTime = dataTime;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getIndexValue() {
        return indexValue;
    }

    public void setIndexValue(String indexValue) {
        this.indexValue = indexValue;
    }

    public String getDegreeText() {
        return degreeText;
    }

    public void setDegreeText(String degreeText) {
        this.degreeText = degreeText;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSeriousExceed() {
        return seriousExceed;
    }

    public void setSeriousExceed(Integer seriousExceed) {
        this.seriousExceed = seriousExceed;
    }

    public Long getHandler() {
        return handler;
    }

    public void setHandler(Long handler) {
        this.handler = handler;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getExceedsBidRatio() {
        return exceedsBidRatio;
    }

    public void setExceedsBidRatio(Double exceedsBidRatio) {
        this.exceedsBidRatio = exceedsBidRatio;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public Integer getPushMessage() {
        return pushMessage;
    }

    public void setPushMessage(Integer pushMessage) {
        this.pushMessage = pushMessage;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }
}
