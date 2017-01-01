package com.chnye.yese.servletcontext;

public class SystemPropertyUtil{

	
	public static String getAppConfigFolderPath() throws Exception{
		String config_path = System.getProperty( AppConstants.APP_CONFIG_FOLDER_PATH );
		if( config_path == null ){
			throw new RuntimeException( "缺少系统配置参数:" + AppConstants.APP_CONFIG_FOLDER_PATH );
		}
		if( !config_path.endsWith( AppConstants.FOLDER_SEPARATOR ) ){
			config_path = config_path + AppConstants.FOLDER_SEPARATOR;
		}
		return config_path;
	}
}