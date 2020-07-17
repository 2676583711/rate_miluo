package com.rate.web.airsite.entity;

import org.beetl.sql.core.annotatoin.AutoID;
import org.beetl.sql.core.annotatoin.Table;

/**
 * @author xxx
 * @date 2019/5/30 9:32
 **/
@Table(name = "miluo_site_param")
public class SiteParam {

    /**
     * id
     */
    @AutoID
    private Integer id;
    /**
     * 设备ID
     */
    private Integer videoId;
    /**
     * 监测因子名称(英文)
     */
    private String name;
    /**
     *上限值
     */
    private String top;
    /**
     *下限值
     */
    private String bottom;
    /**
     *名字描述（中文：温度）
     */
    private String description;
    /**
     *单位
     */
    private String unit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public String getBottom() {
        return bottom;
    }

    public void setBottom(String bottom) {
        this.bottom = bottom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
