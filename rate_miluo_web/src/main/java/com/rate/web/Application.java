package com.rate.web;

import com.rate.system.rate_system.utils.config.PropertiesUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages={"com.rate"})
@EnableScheduling
public class Application {
	public static void main(String[] args) {
	SpringApplication app = new SpringApplication(Application.class);
		app.setDefaultProperties(PropertiesUtils.getInstance().getProperties());
		app.run(args);
		System.out.println("------启动成功----");
	}
}
