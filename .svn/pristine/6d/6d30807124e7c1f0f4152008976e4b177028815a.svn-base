package com.rate.web.history.entity;

import java.math.*;
import java.util.Date;

import org.beetl.sql.core.annotatoin.AssignID;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

/* 
* 
* gen by beetlsql 2019-05-28
*/
public class MiluoOriginalTreatmentPlant  {
	
	//id
	@AssignID
	private String id ;
	//命令编码
	private String cmdId ;
	//设备编号
	private String siteCode ;
	//站点数据
	private String siteData ;
	//站点类型
	private String siteType ;
	//检测时间
	@JsonFormat(pattern = "MM-dd\nHH:mm", timezone = "GMT+8")
	@DateTimeFormat(pattern = "MM-dd\nHH:mm")
	private Date recordingTime ;
	
	public MiluoOriginalTreatmentPlant() {
	}
	
	/**id
	*@return 
	*/
	public String getId(){
		return  id;
	}
	/**id
	*@param  id
	*/
	public void setId(String id ){
		this.id = id;
	}
	
	/**命令编码
	*@return 
	*/
	public String getCmdId(){
		return  cmdId;
	}
	/**命令编码
	*@param  cmdId
	*/
	public void setCmdId(String cmdId ){
		this.cmdId = cmdId;
	}
	
	/**设备编号
	*@return 
	*/
	public String getSiteCode(){
		return  siteCode;
	}
	/**设备编号
	*@param  siteCode
	*/
	public void setSiteCode(String siteCode ){
		this.siteCode = siteCode;
	}
	
	/**站点数据
	*@return 
	*/
	public String getSiteData(){
		return  siteData;
	}
	/**站点数据
	*@param  siteData
	*/
	public void setSiteData(String siteData ){
		this.siteData = siteData;
	}
	
	/**站点类型
	*@return 
	*/
	public String getSiteType(){
		return  siteType;
	}
	/**站点类型
	*@param  siteType
	*/
	public void setSiteType(String siteType ){
		this.siteType = siteType;
	}
	
	/**检测时间
	*@return 
	*/
	public Date getRecordingTime(){
		return  recordingTime;
	}
	/**检测时间
	*@param  recordingTime
	*/
	public void setRecordingTime(Date recordingTime ){
		this.recordingTime = recordingTime;
	}
	

}