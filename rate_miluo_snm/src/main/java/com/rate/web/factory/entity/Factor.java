package com.rate.web.factory.entity;

import org.beetl.sql.core.annotatoin.AutoID;
import org.beetl.sql.core.annotatoin.Table;

/**

* <p>Title: Factor</p>  

* <p>Description: </p>  

* @author chenh  

* @date 2019年5月27日
 */
@Table(name="miluo_pollutant_factor")
public class Factor {
	
	//监测因子id
	@AutoID
	private Integer id ;
	//检测因子编码2005
	private String code2005 ;
	//检测因子编码2017
	private String code2017 ;
	//数据类型
	private String dataType ;
	//下限值
	private String downValue ;
	//浮动值
	private String floatValue ;
	//检测因子名称
	private String name ;
	//范围
	private String ranges ;
	//单位
	private String unitConc ;
	//上限值
	private String upValue ;
	//范围类型
	private Integer rangesType ;
	
	
	
	
	public Factor() {
	}
	
	/**监测因子id
	*@return 
	*/
	public Integer getId(){
		return  id;
	}
	/**监测因子id
	*@param  id
	*/
	public void setId(Integer id ){
		this.id = id;
	}
	
	/**检测因子编码2005
	*@return 
	*/
	public String getCode2005(){
		return  code2005;
	}
	/**检测因子编码2005
	*@param  code2005
	*/
	public void setCode2005(String code2005 ){
		this.code2005 = code2005;
	}
	
	/**检测因子编码2017
	*@return 
	*/
	public String getCode2017(){
		return  code2017;
	}
	/**检测因子编码2017
	*@param  code2017
	*/
	public void setCode2017(String code2017 ){
		this.code2017 = code2017;
	}
	
	/**数据类型
	*@return 
	*/
	public String getDataType(){
		return  dataType;
	}
	/**数据类型
	*@param  dataType
	*/
	public void setDataType(String dataType ){
		this.dataType = dataType;
	}
	
	/**检测因子名称
	*@return 
	*/
	public String getName(){
		return  name;
	}
	/**检测因子名称
	*@param  name
	*/
	public void setName(String name ){
		this.name = name;
	}
	
	/**范围
	*@return 
	*/
	public String getRanges(){
		return  ranges;
	}
	/**范围
	*@param  range
	*/
	public void setRanges(String ranges ){
		this.ranges = ranges;
	}
	
	/**单位
	*@return 
	*/
	public String getUnitConc(){
		return  unitConc;
	}
	/**单位
	*@param  unitConc
	*/
	public void setUnitConc(String unitConc ){
		this.unitConc = unitConc;
	}

	public String getDownValue() {
		return downValue;
	}

	public void setDownValue(String downValue) {
		this.downValue = downValue;
	}

	public String getFloatValue() {
		return floatValue;
	}

	public void setFloatValue(String floatValue) {
		this.floatValue = floatValue;
	}

	public String getUpValue() {
		return upValue;
	}

	public void setUpValue(String upValue) {
		this.upValue = upValue;
	}

	public Integer getRangesType() {
		return rangesType;
	}

	public void setRangesType(Integer rangesType) {
		this.rangesType = rangesType;
	}
	
	
	
}
