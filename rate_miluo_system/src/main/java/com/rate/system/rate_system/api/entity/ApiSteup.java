package com.rate.system.rate_system.api.entity;

/**
 * app系统提示设置开关0或者null：开启，1：关闭
 * @author	zhangbiao
 * @date	2018-09-11
 */
public class ApiSteup  {
	
	private Long id ;
	private Integer operation ;
	private Integer overrun ;
	private Integer sos ;
	private Long userId ;
	
	public ApiSteup() {
	}
	
	public Long getId(){
		return  id;
	}
	public void setId(Long id ){
		this.id = id;
	}
	
	public Integer getOperation(){
		return  operation;
	}
	public void setOperation(Integer operation ){
		this.operation = operation;
	}
	
	public Integer getOverrun(){
		return  overrun;
	}
	public void setOverrun(Integer overrun ){
		this.overrun = overrun;
	}
	
	public Integer getSos(){
		return  sos;
	}
	public void setSos(Integer sos ){
		this.sos = sos;
	}
	
	public Long getUserId(){
		return  userId;
	}
	public void setUserId(Long userId ){
		this.userId = userId;
	}
	

}
