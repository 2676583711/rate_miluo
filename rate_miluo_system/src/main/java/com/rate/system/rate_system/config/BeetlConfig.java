package com.rate.system.rate_system.config;

import java.util.HashMap;
import java.util.Map;

import org.beetl.core.GroupTemplate;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.stereotype.Service;

/**
 * 
 * @author jinjichang
 * @date 2018-04-18
 */
@Configuration
public class BeetlConfig {
	@Value(value = "${productName}")
	private String productName;

	@Value("${productVersion}")
	private String productVersion;

	@Value("${copyrightYear}")
	private String copyrightYear;

	@Value("${technicalSupport}")
	private String technicalSupport;
	@Value("${constructionUnit}")
	private String constructionUnit;

	/**
	 * beetl配置
	 */

	@Bean(initMethod = "init", name = "beetlConfigs")
	public BeetlGroupUtilConfiguration getBeetlGroupUtilConfiguration() {
		BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlConfiguration();
		ResourcePatternResolver patternResolver = ResourcePatternUtils
				.getResourcePatternResolver(new DefaultResourceLoader());
		try {
			// WebAppResourceLoader 配置root路径是关键
			ClasspathResourceLoader cploder = new ClasspathResourceLoader(BeetlConfig.class.getClassLoader(), "views");
			beetlGroupUtilConfiguration.setResourceLoader(cploder);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 读取配置文件信息
		beetlGroupUtilConfiguration.setConfigFileResource(patternResolver.getResource("classpath:config/beetl.yml"));
		Map<String, Object> sharedVars = new HashMap<String, Object>();
		sharedVars.put("productName", productName);
		sharedVars.put("productVersion", productVersion);
		sharedVars.put("copyrightYear", copyrightYear);
		sharedVars.put("technicalSupport", technicalSupport);
		sharedVars.put("constructionUnit", constructionUnit);
		beetlGroupUtilConfiguration.setSharedVars(sharedVars);
		return beetlGroupUtilConfiguration;
	}

	@Bean(name = "beetlViewResolver")
	public BeetlSpringViewResolver getBeetlSpringViewResolver(
			@Qualifier("beetlConfigs") BeetlGroupUtilConfiguration beetlGroupUtilConfiguration) {
		BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
		beetlSpringViewResolver.setSuffix(".html");
		beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
		beetlSpringViewResolver.setOrder(0);
		beetlSpringViewResolver.setConfig(beetlGroupUtilConfiguration);
		return beetlSpringViewResolver;
	}
}