package com.rate.system.rate_system.utils;

/**
 * 命名驼峰与下划线之间的转换
 * @author jinjichang
 * @date 2018-03-30
 */
public class ConvertName {
	/**
	 * 驼峰命名转为下划线命名
	 * @param para 驼峰命名
	 * @return 下划线命名
	 */
    public static String HumpToUnderline(String para){
        StringBuilder sb=new StringBuilder(para);
        int temp=0;//定位
        for(int i=0;i<para.length();i++){
            if(Character.isUpperCase(para.charAt(i))){
                sb.insert(i+temp, "_");
                temp+=1;
            }
        }
        return sb.toString().toUpperCase(); 
    }
    /**
     * 下划线命名转为驼峰命名
     * @param para 下划线命名
     * @return 驼峰命名
     */
    public static String UnderlineToHump(String para){
        StringBuilder result=new StringBuilder();
        String a[]=para.split("_");
        for(String s:a){
            if(result.length()==0){
                result.append(s.toLowerCase());
            }else{
                result.append(s.substring(0, 1).toUpperCase());
                result.append(s.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }
}
