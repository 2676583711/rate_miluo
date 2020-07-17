package com.rate.web.vidicon.entity;
import java.util.Date;

import org.beetl.sql.core.annotatoin.Table;


/* 
* 
* gen by beetlsql 2019-06-04
*/
@Table(name="miluo_vidicon_owner")
public class VidiconOwner  {
	
	private Long ownerId ;
	//AccessToken
	private String accessToken ;
	//appKey
	private String appKey ;
	//账号所有人
	private String ownerName ;
	//Secret
	private String secret ;
	//创建时间
	private Date createDate ;
	//令牌更新时间
	private Date tokenUpdateDate ;
	
	public Long getOwnerId(){
		return  ownerId;
	}
	public void setOwnerId(Long ownerId ){
		this.ownerId = ownerId;
	}
	
	/**AccessToken
	*@return 
	*/
	public String getAccessToken(){
		return  accessToken;
	}
	/**AccessToken
	*@param  accessToken
	*/
	public void setAccessToken(String accessToken ){
		this.accessToken = accessToken;
	}
	
	/**appKey
	*@return 
	*/
	public String getAppKey(){
		return  appKey;
	}
	/**appKey
	*@param  appKey
	*/
	public void setAppKey(String appKey ){
		this.appKey = appKey;
	}
	
	/**账号所有人
	*@return 
	*/
	public String getOwnerName(){
		return  ownerName;
	}
	/**账号所有人
	*@param  ownerName
	*/
	public void setOwnerName(String ownerName ){
		this.ownerName = ownerName;
	}
	
	/**Secret
	*@return 
	*/
	public String getSecret(){
		return  secret;
	}
	/**Secret
	*@param  secret
	*/
	public void setSecret(String secret ){
		this.secret = secret;
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
	
	/**令牌更新时间
	*@return 
	*/
	public Date getTokenUpdateDate(){
		return  tokenUpdateDate;
	}
	/**令牌更新时间
	*@param  tokenUpdateDate
	*/
	public void setTokenUpdateDate(Date tokenUpdateDate ){
		this.tokenUpdateDate = tokenUpdateDate;
	}
	

}