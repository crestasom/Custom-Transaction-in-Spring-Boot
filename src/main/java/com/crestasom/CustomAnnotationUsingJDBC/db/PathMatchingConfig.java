package com.crestasom.CustomAnnotationUsingJDBC.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.crestasom.CustomAnnotationUsingJDBC.RequestInceptor;

@Configuration
public class PathMatchingConfig implements WebMvcConfigurer {

	@Autowired
	private RequestInceptor requestInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(requestInterceptor);

	}
}
