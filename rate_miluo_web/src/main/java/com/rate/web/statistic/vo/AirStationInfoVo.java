package com.rate.web.statistic.vo;

import com.rate.system.rate_system.utils.excel.ExcelField;

/**
 * @author mayn
 * @title: AirStationInfoVo
 * @projectName rate_miluo_parent
 * @description: TODO
 * @date 2020/7/168:34
 */
public class AirStationInfoVo {
    /**
     * 站点名称
     */
    private String siteName;

    //站点编码
    private String siteCode ;


    @ExcelField(title = "站点名称", align = 2, sort = 1)
    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }


    public String getSiteCode() {
        return siteCode;
    }
    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }


}