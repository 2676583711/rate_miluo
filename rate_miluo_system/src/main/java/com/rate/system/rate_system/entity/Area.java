package com.rate.system.rate_system.entity;

import org.beetl.sql.core.annotatoin.Table;

/* 
* 
* gen by beetlsql 2018-06-07
*/
@Table(name="sys_area")
public class Area  {
	
	//区域编码
	private String areaCode ;
	//区域名称
	private String areaName ;
	//区域类型
	private String areaType ;
	//备注信息
	private String remarks ;
	
	public Area() {
	}
	
	/**区域编码
	*@return 
	*/
	public String getAreaCode(){
		return  areaCode;
	}
	/**区域编码
	*@param  areaCode
	*/
	public void setAreaCode(String areaCode ){
		this.areaCode = areaCode;
	}
	
	/**区域名称
	*@return 
	*/
	public String getAreaName(){
		return  areaName;
	}
	/**区域名称
	*@param  areaName
	*/
	public void setAreaName(String areaName ){
		this.areaName = areaName;
	}
	
	/**区域类型
	*@return 
	*/
	public String getAreaType(){
		return  areaType;
	}
	/**区域类型
	*@param  areaType
	*/
	public void setAreaType(String areaType ){
		this.areaType = areaType;
	}
	
	/**备注信息
	*@return 
	*/
	public String getRemarks(){
		return  remarks;
	}
	/**备注信息
	*@param  remarks
	*/
	public void setRemarks(String remarks ){
		this.remarks = remarks;
	}
	

}
