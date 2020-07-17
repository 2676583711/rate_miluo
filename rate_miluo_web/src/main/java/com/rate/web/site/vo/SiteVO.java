package com.rate.web.site.vo;

/**
 * @ClassName SiteVO
 * @Author LiuYong
 * @Date 2019/6/28 16:14
 * @Version 1.0
 **/
public class SiteVO {

    /**
     * 站点编号
     */
    private String id;

    /**
     * 站点名称
     */
    private String name;

    /**
     * 站点类型（1 监测水  2 空气 3 污染源 ）
     */
    private String siteType;

    /**
     * 站点细分类别（21微型站 22标准站 23国控站 ）
     */
    private String siteCategory;

    /**
     * 站点状态
     */
    private String siteStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSiteType() {
        return siteType;
    }

    public void setSiteType(String siteType) {
        this.siteType = siteType;
    }

    public String getSiteCategory() {
        return siteCategory;
    }

    public void setSiteCategory(String siteCategory) {
        this.siteCategory = siteCategory;
    }

    public String getSiteStatus() {
        return siteStatus;
    }

    public void setSiteStatus(String siteStatus) {
        this.siteStatus = siteStatus;
    }
}
