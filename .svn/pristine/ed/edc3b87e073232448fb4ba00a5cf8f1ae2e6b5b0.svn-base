package com.rate.web.vidicon.entity;

import java.util.Date;

import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.Table;

/**
* @ClassName: VidiconFile
* @Description: 摄像头文件关系表
* @author jiangya
* @date 2019年6月14日 下午4:00:57
*
*/
@Table(name="miluo_vidicon_file")
public class VidiconFile  {
	@AssignID
	private String id ;
	//文件表Id
	private String fileId ;
	//摄像头id
	private Long vidiconId ;
	//截图时间
	private Date printscreenDate ;
	
	
	public String getId(){
		return  id;
	}
	public void setId(String id ){
		this.id = id;
	}
	
	/**文件表Id
	*@return 
	*/
	public String getFileId(){
		return  fileId;
	}
	/**文件表Id
	*@param  fileId
	*/
	public void setFileId(String fileId ){
		this.fileId = fileId;
	}
	
	/**摄像头id
	*@return 
	*/
	public Long getVidiconId(){
		return  vidiconId;
	}
	/**摄像头id
	*@param  vidiconId
	*/
	public void setVidiconId(Long vidiconId ){
		this.vidiconId = vidiconId;
	}
	
	/**截图时间
	*@return 
	*/
	public Date getPrintscreenDate(){
		return  printscreenDate;
	}
	/**截图时间
	*@param  printscreenDate
	*/
	public void setPrintscreenDate(Date printscreenDate ){
		this.printscreenDate = printscreenDate;
	}
	
	

}