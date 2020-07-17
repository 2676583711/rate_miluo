package com.rate.web.timetask.entity;

import org.beetl.sql.core.annotatoin.Table;

import java.util.Date;

/**
 * @ProjectName: rate_miluo_parent
 * @Package: com.rate.web.timetask
 * @ClassName: StatementType
 * @author: xiaoshi
 * @Description: ${description}
 * @Date: 2019/6/4 14:41
 * @Version: 1.0
 */
@Table(name="miluo_statement_type")
public class StatementType {
    private String id ;
    //在线状态(0:在线，1:离线)
    private String siteStatement ;
    //1:水质   21:微型站   22:标准站  23:国控站  31:涉水污染源
    private String siteType ;
    //时间
    private Date time ;
    //设备编码
    private String equipmentId;

    public StatementType() {
    }

    public String getId(){
        return  id;
    }
    public void setId(String id ){
        this.id = id;
    }

    /**在线状态(0:在线，1:离线)
     *@return
     */
    public String getSiteStatement(){
        return  siteStatement;
    }
    /**在线状态(0:在线，1:离线)
     *@param  siteStatement
     */
    public void setSiteStatement(String siteStatement ){
        this.siteStatement = siteStatement;
    }

    /**1:水质   21:微型站   22:标准站  23:国控站  31:涉水污染源
     *@return
     */
    public String getSiteType(){
        return  siteType;
    }
    /**1:水质   21:微型站   22:标准站  23:国控站  31:涉水污染源
     *@param  siteType
     */
    public void setSiteType(String siteType ){
        this.siteType = siteType;
    }

    /**时间
     *@return
     */
    public Date getTime(){
        return  time;
    }
    /**时间
     *@param  time
     */
    public void setTime(Date time ){
        this.time = time;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }
}
