package com.rate.web.statistic.entity;

import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.Table;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author shuzhangyao
 * @date 2019/7/15 9:27
 **/
@Table(name = "yc_daily_statement_examine")
public class DayExamine implements Serializable {

    //主键
    @AssignID
    private String id ;
    //aqi
    private Integer aqi ;
    //co的aqi
    private Integer coIaqi ;
    //数据源(1审核  2原始  3剔除沙尘  4混合)
    private Integer dataOrigin ;
    //数据类型: (0标况  1实况)
    private Integer dataType ;
    //no
    private Integer no ;
    //no2的aqi
    private Integer no2Iaqi ;
    //nox
    private Integer nox ;
    //03,8小时滑动平均浓度的aqi
    private Integer o3eightHourIaqi ;
    //o3一小时的aqi
    private Integer o3oneHourIaqi ;
    //pm10的aqi
    private Integer pm10Iaqi ;
    //pm25的aqi
    private Integer pm25Iaqi ;
    //so2的aqi
    private Integer so2Iaqi ;
    //污染程度类别
    private String aqType ;
    //co
    private BigDecimal co ;
    //湿度
    private BigDecimal humi ;
    private String humiMark ;
    //no2
    private BigDecimal no2 ;
    private String o3Mark ;
    //03,8小时滑动平均浓度
    private BigDecimal o3eightHour ;
    //o3一小时
    private BigDecimal o3oneHour ;
    //pm10
    private BigDecimal pm10 ;
    private String pm10Mark ;
    //pm25
    private BigDecimal pm25 ;
    private String pm25Mark ;
    //气压
    private BigDecimal press ;
    private String pressMark ;
    //首要污染物
    private String primaryEp ;
    //雨量
    private BigDecimal rain ;
    //站点编码
    private String siteCode ;
    //so2
    private BigDecimal so2 ;
    //气温
    private BigDecimal temp ;
    private String tempMark ;
    //风向
    private BigDecimal wd ;
    private String wdMark ;
    //风速
    private BigDecimal ws ;
    private String wsMark ;
    //创建时间
    private Date createDate ;
    //数据时间
    private Date sDatetime ;

    /**主键
     *@return
     */
    public String getId(){
        return  id;
    }
    /**主键
     *@param  id
     */
    public void setId(String id ){
        this.id = id;
    }

    /**aqi
     *@return
     */
    public Integer getAqi(){
        return  aqi;
    }
    /**aqi
     *@param  aqi
     */
    public void setAqi(Integer aqi ){
        this.aqi = aqi;
    }

    /**co的aqi
     *@return
     */
    public Integer getCoIaqi(){
        return  coIaqi;
    }
    /**co的aqi
     *@param  coIaqi
     */
    public void setCoIaqi(Integer coIaqi ){
        this.coIaqi = coIaqi;
    }

    /**数据源(1审核  2原始  3剔除沙尘  4混合)
     *@return
     */
    public Integer getDataOrigin(){
        return  dataOrigin;
    }
    /**数据源(1审核  2原始  3剔除沙尘  4混合)
     *@param  dataOrigin
     */
    public void setDataOrigin(Integer dataOrigin ){
        this.dataOrigin = dataOrigin;
    }

    /**数据类型: (0标况  1实况)
     *@return
     */
    public Integer getDataType(){
        return  dataType;
    }
    /**数据类型: (0标况  1实况)
     *@param  dataType
     */
    public void setDataType(Integer dataType ){
        this.dataType = dataType;
    }

    /**no
     *@return
     */
    public Integer getNo(){
        return  no;
    }
    /**no
     *@param  no
     */
    public void setNo(Integer no ){
        this.no = no;
    }

    /**no2的aqi
     *@return
     */
    public Integer getNo2Iaqi(){
        return  no2Iaqi;
    }
    /**no2的aqi
     *@param  no2Iaqi
     */
    public void setNo2Iaqi(Integer no2Iaqi ){
        this.no2Iaqi = no2Iaqi;
    }

    /**nox
     *@return
     */
    public Integer getNox(){
        return  nox;
    }
    /**nox
     *@param  nox
     */
    public void setNox(Integer nox ){
        this.nox = nox;
    }

    /**03,8小时滑动平均浓度的aqi
     *@return
     */
    public Integer getO3eightHourIaqi(){
        return  o3eightHourIaqi;
    }
    /**03,8小时滑动平均浓度的aqi
     *@param  o3eightHourIaqi
     */
    public void setO3eightHourIaqi(Integer o3eightHourIaqi ){
        this.o3eightHourIaqi = o3eightHourIaqi;
    }

    /**o3一小时的aqi
     *@return
     */
    public Integer getO3oneHourIaqi(){
        return  o3oneHourIaqi;
    }
    /**o3一小时的aqi
     *@param  o3oneHourIaqi
     */
    public void setO3oneHourIaqi(Integer o3oneHourIaqi ){
        this.o3oneHourIaqi = o3oneHourIaqi;
    }

    /**pm10的aqi
     *@return
     */
    public Integer getPm10Iaqi(){
        return  pm10Iaqi;
    }
    /**pm10的aqi
     *@param  pm10Iaqi
     */
    public void setPm10Iaqi(Integer pm10Iaqi ){
        this.pm10Iaqi = pm10Iaqi;
    }

    /**pm25的aqi
     *@return
     */
    public Integer getPm25Iaqi(){
        return  pm25Iaqi;
    }
    /**pm25的aqi
     *@param  pm25Iaqi
     */
    public void setPm25Iaqi(Integer pm25Iaqi ){
        this.pm25Iaqi = pm25Iaqi;
    }

    /**so2的aqi
     *@return
     */
    public Integer getSo2Iaqi(){
        return  so2Iaqi;
    }
    /**so2的aqi
     *@param  so2Iaqi
     */
    public void setSo2Iaqi(Integer so2Iaqi ){
        this.so2Iaqi = so2Iaqi;
    }

    /**污染程度类别
     *@return
     */
    public String getAqType(){
        return  aqType;
    }
    /**污染程度类别
     *@param  aqType
     */
    public void setAqType(String aqType ){
        this.aqType = aqType;
    }

    /**co
     *@return
     */
    public BigDecimal getCo(){
        return  co;
    }
    /**co
     *@param  co
     */
    public void setCo(BigDecimal co ){
        this.co = co;
    }

    /**湿度
     *@return
     */
    public BigDecimal getHumi(){
        return  humi;
    }
    /**湿度
     *@param  humi
     */
    public void setHumi(BigDecimal humi ){
        this.humi = humi;
    }

    public String getHumiMark(){
        return  humiMark;
    }
    public void setHumiMark(String humiMark ){
        this.humiMark = humiMark;
    }

    /**no2
     *@return
     */
    public BigDecimal getNo2(){
        return  no2;
    }
    /**no2
     *@param  no2
     */
    public void setNo2(BigDecimal no2 ){
        this.no2 = no2;
    }

    public String getO3Mark(){
        return  o3Mark;
    }
    public void setO3Mark(String o3Mark ){
        this.o3Mark = o3Mark;
    }

    /**03,8小时滑动平均浓度
     *@return
     */
    public BigDecimal getO3eightHour(){
        return  o3eightHour;
    }
    /**03,8小时滑动平均浓度
     *@param  o3eightHour
     */
    public void setO3eightHour(BigDecimal o3eightHour ){
        this.o3eightHour = o3eightHour;
    }

    /**o3一小时
     *@return
     */
    public BigDecimal getO3oneHour(){
        return  o3oneHour;
    }
    /**o3一小时
     *@param  o3oneHour
     */
    public void setO3oneHour(BigDecimal o3oneHour ){
        this.o3oneHour = o3oneHour;
    }

    /**pm10
     *@return
     */
    public BigDecimal getPm10(){
        return  pm10;
    }
    /**pm10
     *@param  pm10
     */
    public void setPm10(BigDecimal pm10 ){
        this.pm10 = pm10;
    }

    public String getPm10Mark(){
        return  pm10Mark;
    }
    public void setPm10Mark(String pm10Mark ){
        this.pm10Mark = pm10Mark;
    }

    /**pm25
     *@return
     */
    public BigDecimal getPm25(){
        return  pm25;
    }
    /**pm25
     *@param  pm25
     */
    public void setPm25(BigDecimal pm25 ){
        this.pm25 = pm25;
    }

    public String getPm25Mark(){
        return  pm25Mark;
    }
    public void setPm25Mark(String pm25Mark ){
        this.pm25Mark = pm25Mark;
    }

    /**气压
     *@return
     */
    public BigDecimal getPress(){
        return  press;
    }
    /**气压
     *@param  press
     */
    public void setPress(BigDecimal press ){
        this.press = press;
    }

    public String getPressMark(){
        return  pressMark;
    }
    public void setPressMark(String pressMark ){
        this.pressMark = pressMark;
    }

    /**首要污染物
     *@return
     */
    public String getPrimaryEp(){
        return  primaryEp;
    }
    /**首要污染物
     *@param  primaryEp
     */
    public void setPrimaryEp(String primaryEp ){
        this.primaryEp = primaryEp;
    }

    /**雨量
     *@return
     */
    public BigDecimal getRain(){
        return  rain;
    }
    /**雨量
     *@param  rain
     */
    public void setRain(BigDecimal rain ){
        this.rain = rain;
    }

    /**站点编码
     *@return
     */
    public String getSiteCode(){
        return  siteCode;
    }
    /**站点编码
     *@param  siteCode
     */
    public void setSiteCode(String siteCode ){
        this.siteCode = siteCode;
    }

    /**so2
     *@return
     */
    public BigDecimal getSo2(){
        return  so2;
    }
    /**so2
     *@param  so2
     */
    public void setSo2(BigDecimal so2 ){
        this.so2 = so2;
    }

    /**气温
     *@return
     */
    public BigDecimal getTemp(){
        return  temp;
    }
    /**气温
     *@param  temp
     */
    public void setTemp(BigDecimal temp ){
        this.temp = temp;
    }

    public String getTempMark(){
        return  tempMark;
    }
    public void setTempMark(String tempMark ){
        this.tempMark = tempMark;
    }

    /**风向
     *@return
     */
    public BigDecimal getWd(){
        return  wd;
    }
    /**风向
     *@param  wd
     */
    public void setWd(BigDecimal wd ){
        this.wd = wd;
    }

    public String getWdMark(){
        return  wdMark;
    }
    public void setWdMark(String wdMark ){
        this.wdMark = wdMark;
    }

    /**风速
     *@return
     */
    public BigDecimal getWs(){
        return  ws;
    }
    /**风速
     *@param  ws
     */
    public void setWs(BigDecimal ws ){
        this.ws = ws;
    }

    public String getWsMark(){
        return  wsMark;
    }
    public void setWsMark(String wsMark ){
        this.wsMark = wsMark;
    }

    /**创建时间
     *@return
     */
    public Date getCreateDate(){
        return  createDate;
    }
    /**创建时间
     *@param  createDate
     */
    public void setCreateDate(Date createDate ){
        this.createDate = createDate;
    }

    /**数据时间
     *@return
     */
    public Date getsDatetime(){
        return  sDatetime;
    }
    /**数据时间
     *@param  sDatetime
     */
    public void setsDatetime(Date sDatetime ){
        this.sDatetime = sDatetime;
    }

}
