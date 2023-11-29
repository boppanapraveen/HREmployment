package com.employee;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {


	@Override
	public void addCorsMappings(CorsRegistry registry) {
	    registry.addMapping("/**")
	            .allowedOriginPatterns("*") // You can still use * here
	            .allowedMethods("PUT", "DELETE", "GET", "POST")
	            .allowedHeaders("Origin", "Content-Type", "Accept", "Authorization");
	}

}


