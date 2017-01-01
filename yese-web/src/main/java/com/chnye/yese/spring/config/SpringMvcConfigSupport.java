package com.chnye.yese.spring.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
public class SpringMvcConfigSupport extends WebMvcConfigurationSupport {

	@Resource
	private Environment env;
	
	
}
