package com.rate.system.rate_system.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.AutoID;
import org.beetl.sql.core.annotatoin.SeqID;
import org.beetl.sql.core.annotatoin.Table;

/**
 * 文件上传
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-19 16:02:20
 */
@Table(name="sys_file")
public class FileDO implements Serializable {
    private static final long serialVersionUID = 1L;
    public FileDO() {
        super();
    }


    public FileDO(Integer type, String url, Date createDate) {
        super();
        this.type = type;
        this.url = url;
        this.createDate = createDate;
    }
    


    public FileDO(String id,Integer type, String fileext, String fileflag, String filename, BigDecimal filesize,
			String pathName,String url, Date createDate) {
		super();
		this.id=id;
		this.type = type;
		this.fileext = fileext;
		this.fileflag = fileflag;
		this.filename = filename;
		this.filesize = filesize;
		this.url = url;
		this.pathName = pathName;
		this.createDate = createDate;
	}
	public FileDO(String id,Integer type, String fileext, String filename, BigDecimal filesize,
				  String pathName,String url, Date createDate) {
		super();
		this.id=id;
		this.type = type;
		this.fileext = fileext;
//		this.fileflag = fileflag;
		this.filename = filename;
		this.filesize = filesize;
		this.url = url;
		this.pathName = pathName;
		this.createDate = createDate;
	}


	@AssignID
	private String id ;
	//文件类型
	private Integer type ;
	//文件后缀
	private String fileext ;
	//文件标识
	private String fileflag ;
	//文件名
	private String filename ;
	private String pathName;
	public String getPathName() {
		return pathName;
	}


	public void setPathName(String pathName) {
		this.pathName = pathName;
	}


	private BigDecimal filesize ;
	//URL地址
	private String url ;
	//创建时间
	private Date createDate ;

	public String getId(){
		return  id;
	}
	public void setId(String id ){
		this.id = id;
	}

	/**文件类型
	*@return 
	*/
	public Integer getType(){
		return  type;
	}
	/**文件类型
	*@param  type
	*/
	public void setType(Integer type ){
		this.type = type;
	}
	
	/**文件后缀
	*@return 
	*/
	public String getFileext(){
		return  fileext;
	}
	/**文件后缀
	*@param  fileext
	*/
	public void setFileext(String fileext ){
		this.fileext = fileext;
	}
	
	/**文件标识
	*@return 
	*/
	public String getFileflag(){
		return  fileflag;
	}
	/**文件标识
	*@param  fileflag
	*/
	public void setFileflag(String fileflag ){
		this.fileflag = fileflag;
	}
	
	/**文件名
	*@return 
	*/
	public String getFilename(){
		return  filename;
	}
	/**文件名
	*@param  filename
	*/
	public void setFilename(String filename ){
		this.filename = filename;
	}
	
	public BigDecimal getFilesize(){
		return  filesize;
	}
	public void setFilesize(BigDecimal filesize ){
		this.filesize = filesize;
	}
	
	/**URL地址
	*@return 
	*/
	public String getUrl(){
		return  url;
	}
	/**URL地址
	*@param  url
	*/
	public void setUrl(String url ){
		this.url = url;
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

    @Override
    public String toString() {
        return "FileDO{" +
                "id=" + id +
                ", type=" + type +
                ", url='" + url + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
