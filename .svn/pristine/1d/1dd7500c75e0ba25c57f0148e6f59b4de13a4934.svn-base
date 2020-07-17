package com.rate.web.utils;
//import net.sourceforge.pinyin4j.PinyinHelper;
//import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
//import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 获取首字母工具
 *
 * @ClassName ChineseCharacterUtil
 * @Author LiuYong
 * @Date 2019/6/28 14:04
 * @Version 1.0
 **/
public class ChineseCharacterUtil {

    /**
     *  获取汉字首字母或全拼大写字母
     *
     * @param chinese 汉字
     * @param isFull  是否全拼 true:表示全拼 false表示：首字母
     *
     * @return 全拼或者首字母大写字符窜
     */
    public static String getUpperCase(String chinese,boolean isFull){
        return convertChineseToPinyin(chinese,isFull).toUpperCase();
    }

    /**
     * 获取汉字首字母或全拼小写字母
     *
     * @param chinese 汉字
     * @param isFull 是否全拼 true:表示全拼 false表示：首字母
     *
     * @return 全拼或者首字母小写字符窜
     */
    public static  String getLowerCase(String chinese,boolean isFull){
        return convertChineseToPinyin(chinese,isFull).toLowerCase();
    }

    /**
     *
     * @param chinese 汉字字符串
     * @param isFull 是否全拼 true:表示全拼 false表示：首字母
     * @return
     */
    private static String convertChineseToPinyin(String chinese,boolean isFull){
        /***
         * ^[\u2E80-\u9FFF]+$ 匹配所有东亚区的语言
         * ^[\u4E00-\u9FFF]+$ 匹配简体和繁体
         * ^[\u4E00-\u9FA5]+$ 匹配简体
         */
        String regExp="^[\u4E00-\u9FFF]+$";
        StringBuffer sb=new StringBuffer();
        if(chinese==null||"".equals(chinese.trim())){
            return "";
        }
        String pinyin;
        //首字符
        char unit=chinese.charAt(0);
        //是汉字，则转拼音
        if(match(String.valueOf(unit),regExp)){
            pinyin=convertSingleChineseToPinyin(unit);
            if(isFull){
                sb.append(pinyin);
            }
            else{
                sb.append(pinyin.charAt(0));
            }
        }else{
            sb.append(unit);
        }
        return sb.toString();
    }

    /**
     * 将单个汉字转成拼音
     *
     * @param chinese 汉字字符
     *
     * @return 拼音
     */
    private static String convertSingleChineseToPinyin(char chinese){
        HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
        outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        String[] res;
        StringBuffer sb=new StringBuffer();
        try {
            res = PinyinHelper.toHanyuPinyinStringArray(chinese,outputFormat);
//            对于多音字，只用第一个拼音
            sb.append(res[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return sb.toString();
    }

    /***
     * 匹配
     * <P>
     * 根据字符和正则表达式进行匹配
     *
     * @param str 源字符串
     * @param regex 正则表达式
     *
     * @return true：匹配成功  false：匹配失败
     */
    private static boolean match(String str,String regex){
        Pattern pattern=Pattern.compile(regex);
        Matcher matcher=pattern.matcher(str);
        return matcher.find();
    }
}
