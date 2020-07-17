package com.rate.web.site.enums;

/**
 * @author shuzhangyao
 * @date 2019/3/22 11:33
 **/
public enum SiteTypeEnum {
    /**
     * ***
     */
    type1("1", "水质站"),
    type2("2", "空气站"),
    type3("3", "涉气污染源"),
    type4("4", "涉水污染源");


    private String code;
    private String name;

    private SiteTypeEnum(String code, String name){
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getNameByCode(String code) {
        SiteTypeEnum[] values = SiteTypeEnum.values();
        for (SiteTypeEnum type : values) {
            if (type.code.equalsIgnoreCase(code)) {
                return type.name;
            }
        }
        return null;
    }

    public static String getCodeByName(String name) {
        SiteTypeEnum[] values = SiteTypeEnum.values();
        for (SiteTypeEnum type : values) {
            if (type.name.equalsIgnoreCase(name)) {
                return type.code;
            }
        }
        return "-1";
    }

}
