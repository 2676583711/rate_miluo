package com.rate.system.rate_system.entity;

import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.Table;

import java.util.List;

/**
 * @author xxx
 * @date 2019/6/1 16:30
 **/
@Table(name = "miluo_site_power")
public class SitePower {

    @AssignID
    private String id ;
    private Long roleId ;
    private String siteCode ;
    private Long userId;
    private List<String> siteCodes ;


    public List<String> getSiteCodes() {
        return siteCodes;
    }

    public void setSiteCodes(List<String> siteCodes) {
        this.siteCodes = siteCodes;
    }

    public SitePower() {
    }

    public String getId(){
        return  id;
    }
    public void setId(String id ){
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId(){
        return  roleId;
    }
    public void setRoleId(Long roleId ){
        this.roleId = roleId;
    }

    public String getSiteCode(){
        return  siteCode;
    }
    public void setSiteCode(String siteCode ){
        this.siteCode = siteCode;
    }

}
