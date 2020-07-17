package com.rate.web.alarm.monitor.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.rate.system.rate_system.utils.excel.ExcelField;
import org.beetl.sql.core.annotatoin.Table;

import java.io.Serializable;

import java.util.Date;

@Table(name = "miluo_exceeds_bid")
public class Exceed implements Serializable {
    //站点名称
    @ExcelField(title = "站  点  名  称",sort = 1)
    private String name;

    private String id;
    //指标值
    private Double indexValue;
    //0:未处理，1:已处理
    private int status;
    @ExcelField(title = "状态",sort = 7)
    private String statuss;
    //超标比率
    private Double exceedsBidRatio;

    //处理人
    private String handler;
    //超标因子
    @ExcelField(title = "报  警  信  息",sort = 2)
    private String pollutant;
    //备注
    private String remark;

    //站点编码
    private String siteCode;
    //检测值
    @ExcelField(title = "检测值",sort =3)
    private Double value;
    //超标时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ExcelField(title = "报警时间",sort =4)
    private String exceedsBidDate;
    //处理时间
    private Date handlingTime;

    private String exceedsType;

    private int siteId;

//    我用的极光推送向app端推送消息，第一次登录的时候能正常推送指定的用户，当用手机切换账户的时候，还是推送第一个账户的信息
    private String userId;
    //空气等级
    private String airDegree;
    @ExcelField(title = "空气等级",sort =5)
    private String airDegrees;
    //是否严重超标
    private String seriousExceed;
    @ExcelField(title = "是否严重超标",sort =6)
    private String seriousExceeds;

    public String getAirDegree() {
		return airDegree;
	}

	public void setAirDegree(String airDegree) {
		this.airDegree = airDegree;
	}

	public String getSeriousExceed() {
		return seriousExceed;
	}

	public void setSeriousExceed(String seriousExceed) {
		this.seriousExceed = seriousExceed;
	}

	public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatuss() {
        int status = getStatus();
        if (status == 0) {
            return "未处理";
        } else if (status == 1) {
            return "已处理";
        } else if (status == 2) {
            return "任务已添加";
        }else {
            return "-";
        }
    }

    public String getSeriousExceeds() {
        String seriousExceed = getSeriousExceed();
        if ("0".equals(seriousExceed)) {
            return "是";
        }else {
            return "否";
        }
    }

    public String getAirDegrees() {
        String data = getAirDegree();
        if("1".equals(data)){
            return "优";
        }else if("2".equals(data)){
            return "良";
        }else if("3".equals(data)){
            return "轻度污染";
        }else if("4".equals(data)){
            return "中度污染";
        }else if("5".equals(data)){
            return "重度污染";
        }else{
            return "严重污染";
        }
    }

    //负责人
    private String fzr;
    //联系方式
    private String phone;
    //设备编号
    private String equmentId;

    public String getEqumentId() {
        return equmentId;
    }

    public void setEqumentId(String equmentId) {
        this.equmentId = equmentId;
    }

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    public Exceed() {
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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public String getExceedsBidDate() {
        return exceedsBidDate;
    }

    public void setExceedsBidDate(String exceedsBidDate) {
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

    public String getFzr() {
        return fzr;
    }

    public void setFzr(String fzr) {
        this.fzr = fzr;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
