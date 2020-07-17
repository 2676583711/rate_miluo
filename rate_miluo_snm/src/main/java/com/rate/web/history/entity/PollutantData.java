package com.rate.web.history.entity;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import org.msgpack.MessagePack;
import org.msgpack.template.Templates;
import org.beetl.sql.core.annotatoin.AssignID;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

/**

* <p>Title: PollutantData</p>  

* <p>Description: </p>  

* @author chenh  

* @date 2019年5月27日
 */
public class PollutantData implements Serializable {

	private static final long serialVersionUID = 1L;
	private MessagePack msgpack = new MessagePack();
	//编码
	@AssignID
	private String id ;
	//命令编码
	private Integer cmdId ;
	//站点类型
	private Integer siteType ;
	//站点编码
	private String siteCode ;
	//站点数据
	private byte[] siteData ;
	//数据时间
	@JsonFormat(pattern = "MM-dd\nHH:mm", timezone = "GMT+8")
	@DateTimeFormat(pattern = "MM-dd\nHH:mm")
	private Date dataTime ;
	//数据反序列化
	private String siteDataStr;
	
	public PollutantData() {
	}
	
	/**编码
	*@return 
	*/
	public String getId(){
		return  id;
	}
	/**编码
	*@param  id
	*/
	public void setId(String id ){
		this.id = id;
	}
	
	/**命令编码
	*@return 
	*/
	public Integer getCmdId(){
		return  cmdId;
	}
	/**命令编码
	*@param  cmdId
	*/
	public void setCmdId(Integer cmdId ){
		this.cmdId = cmdId;
	}
	
	/**站点类型
	*@return 
	*/
	public Integer getSiteType(){
		return  siteType;
	}
	/**站点类型
	*@param  siteType
	*/
	public void setSiteType(Integer siteType ){
		this.siteType = siteType;
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
	
	/**站点数据
	*@return 
	*/
	public byte[] getSiteData(){
		return  siteData;
	}
	/**站点数据
	*@param  siteData
	*/
	public void setSiteData(byte[] siteData ){
		this.siteData = siteData;
		try {
			setSiteDataStr(msgpack.read(siteData,Templates.TString));;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**数据时间
	*@return 
	*/
	public Date getDataTime(){
		return  dataTime;
	}
	/**数据时间
	*@param  dataTime
	*/
	public void setDataTime(Date dataTime ){
		this.dataTime = dataTime;
	}
	
	/**
	 * @return the siteDateStr
	 */
	public String getSiteDataStr() {
		return siteDataStr;
	}

	/**
	 * @param siteDateStr the siteDateStr to set
	 */
	public void setSiteDataStr(String siteDataStr) {
		this.siteDataStr = siteDataStr;
	}

	@Override
	public String toString() {
		return "PollutantData [id=" + id + ", cmdId=" + cmdId + ", siteType=" + siteType + ", siteCode=" + siteCode
				+ ", siteData=" + Arrays.toString(siteData) + ", dataTime=" + dataTime + ", siteDataStr=" + siteDataStr
				+ "]";
	}


}