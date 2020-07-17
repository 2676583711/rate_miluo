package com.rate.system.rate_system.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;

/**
 * 
 *@Title: Log
 *Description: 日志类
 *@author YuanTao
 *@date 2018年5月18日 下午4:31:36
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
	/**
	 * 
	 * Title:value
	 *@author YuanTao
	 *Description: 得到注解中的value值
	 *@return
	 *@date 2018年5月18日 下午4:42:05
	 */
	String value() default "";
}
