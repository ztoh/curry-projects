package com.chnye.yese.servletcontext;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.chnye.framework.config.IPropertiesHolder;
import com.chnye.framework.config.PropertiesManager;
import com.chnye.framework.config.property.PropertiesHolder;



public class ProjectServletContextListener implements ServletContextListener{

	private static final Logger logger = LoggerFactory.getLogger( ProjectServletContextListener.class );
	
	@Override
	public void contextInitialized(ServletContextEvent event ) {

		
		logger.debug("=================Yese Project Context Init================");
		
		//1.检查并设置一些自己依赖的关键变量
		
		String config_path = System.getProperty( AppConstants.APP_CONFIG_FOLDER_PATH );
		logger.debug("config_path=" + config_path );
		
		//WIN:  d:/xxx/webapps/yese/WEB-INF/classes/
		String class_path = this.getClass().getClassLoader().getResource("/").getPath();
		logger.debug("class_path=" + class_path );
		
		//WIN:  d:\xxx\webapps\yese\
		String root_path = event.getServletContext().getRealPath("/");
		logger.debug("root_path=" + root_path );
		
		
		
		if( config_path == null || config_path.trim().length() == 0 ){
			if( AppConstants.OS_NAME.toLowerCase().indexOf("linux") >= 0 ){
				config_path = root_path + "/WEB-INF/config/";
			}else if( AppConstants.OS_NAME.toLowerCase().indexOf("win") >= 0 ){
				config_path = root_path + "\\WEB-INF\\config\\";
			} else{
				File cf = new File(".");
				config_path = cf.getAbsolutePath() + "/config/";	
			}
			logger.debug("SETTING: System.setProperty(" + AppConstants.APP_CONFIG_FOLDER_PATH + ")=" + config_path );
			System.setProperty( AppConstants.APP_CONFIG_FOLDER_PATH, config_path );
		} else {
			logger.debug("CURRENT: System.setProperty(" + AppConstants.APP_CONFIG_FOLDER_PATH + ")=" + config_path );
		}
		
		//2.加载自己的配置文件类
		String appPropertyFile = event.getServletContext().getInitParameter( AppConstants.APP_PROPERTY_CONFIG );
		if( appPropertyFile == null || StringUtils.isBlank( appPropertyFile ) ){
			throw new RuntimeException("Property file not found! " + appPropertyFile );
		}
		logger.debug( "appPropertyFile=" + appPropertyFile );
		IPropertiesHolder propertiesHolder = PropertiesHolder.createPropertiesHolder( AppConstants.APP_PROPERTY_CONFIG, appPropertyFile);
		try{
			propertiesHolder.loadProperties();
			PropertiesManager.getInstance().addProperties(AppConstants.APP_PROPERTY_CONFIG, propertiesHolder );
		} catch ( Exception e ){
			e.printStackTrace();
		}
		
	}
	
	public void contextDestroyed( ServletContextEvent event ){
		try{
			//xxx.shutdown();
			logger.debug("=================Yese Project Context Destroy================");
		} catch( Exception e ){
			e.printStackTrace();
		}	
	}



}