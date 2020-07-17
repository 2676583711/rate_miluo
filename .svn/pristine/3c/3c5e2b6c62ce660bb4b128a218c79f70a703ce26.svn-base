package com.rate.web.alarm.pollutantdata.entity;

import com.rate.system.rate_system.utils.DateUtils;
import com.rate.system.rate_system.utils.excel.ExcelField;
import org.beetl.sql.core.annotatoin.Table;

import java.util.Date;

@Table(name="miluo_exceeds_bid")
public class PolluteFactory {
    //站点名称
    @ExcelField(title = "站  点  名  称",sort = 1)
    private String name;

    private String id ;
    //指标值
    private Double indexValue ;
    //0:未处理，1:已处理
    private int status ;
    @ExcelField(title = "状态",sort = 5)
    private String statuss ;
    //超标比率
    private Double exceedsBidRatio ;
    //处理人
    private String handler ;
    //超标因子
    @ExcelField(title = "报警信息",sort = 2)
    private String pollutant ;
    //备注
    private String remark ;
    //站点编码
    private String siteCode ;
    //检测值
    @ExcelField(title = "检测值",sort = 3)
    private Double value ;
    //超标时间
    private Date exceedsBidDate ;
    @ExcelField(title = "报警时间",sort = 4)
    private String exceedsBidDates ;
    //处理时间
    private Date handlingTime ;

    private String exceedsType;

    private int siteId;
    private String seriousExceed;

    public String getSeriousExceed() {
        return seriousExceed;
    }

    public void setSeriousExceed(String seriousExceed) {
        this.seriousExceed = seriousExceed;
    }

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    public PolluteFactory() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getIndexValue() {
        return indexValue;
    }

    public void setIndexValue(Double indexValue) {
        this.indexValue = indexValue;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Double getExceedsBidRatio() {
        return exceedsBidRatio;
    }

    public void setExceedsBidRatio(Double exceedsBidRatio) {
        this.exceedsBidRatio = exceedsBidRatio;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getPollutant() {
        return pollutant;
    }

    public void setPollutant(String pollutant) {
        this.pollutant = pollutant;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Date getExceedsBidDate() {
        return exceedsBidDate;
    }

    public void setExceedsBidDate(Date exceedsBidDate) {
        this.exceedsBidDate = exceedsBidDate;
    }

    public Date getHandlingTime() {
        return handlingTime;
    }

    public void setHandlingTime(Date handlingTime) {
        this.handlingTime = handlingTime;
    }

    public String getExceedsType() {
        return exceedsType;
    }

    public void setExceedsType(String exceedsType) {
        this.exceedsType = exceedsType;
    }

    public String getStatuss() {
        int status = getStatus();
        if (status == 0) {
            return "未处理";
        } else if (status == 1) {
            return "已处理";
        }else if(status == 2) {
            return "任务已添加";
        }else {
            return "";
        }
    }

    public String getExceedsBidDates() {
        return DateUtils.format(getExceedsBidDate(),DateUtils.DATE_TIME_PATTERN);
    }

    @Override
    public String toString() {
        return "AirExceed{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", indexValue=" + indexValue +
                ", status=" + status +
                ", exceedsBidRatio=" + exceedsBidRatio +
                ", handler='" + handler + '\'' +
                ", pollutant='" + pollutant + '\'' +
                ", remark='" + remark + '\'' +
                ", siteCode='" + siteCode + '\'' +
                ", value=" + value +
                ", exceedsBidDate=" + exceedsBidDate +
                ", handlingTime=" + handlingTime +
                ", exceedsType='" + exceedsType + '\'' +
                '}';
    }
}
