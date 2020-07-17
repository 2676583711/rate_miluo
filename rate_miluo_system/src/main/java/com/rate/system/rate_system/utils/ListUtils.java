package com.rate.system.rate_system.utils;

import java.util.ArrayList;

/**
 * 
 * @author jinjichang
 * @date 2018-06-07
 */
public class ListUtils {
	/**
	 * 判断是否匹配集合
	 * @param flag 匹配字符串
	 * @param libs 集合字符串
	 * @return 是否匹配
	 */
	public static boolean inString (String flag, ArrayList<String> libs) {
		if(flag == null || libs == null || libs.isEmpty()) {
			return false;
		}
		for(String lib : libs) {
			if(flag.equals(lib)) {
				return true;
			}
		}
		return false;
	}
}
