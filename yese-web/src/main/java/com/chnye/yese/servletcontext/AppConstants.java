package com.chnye.yese.servletcontext;

public class AppConstants{

	//操作系统的文件夹路径分隔符
	public static final String FOLDER_SEPARATOR = System.getProperty("file.separator");
	
	public static final String OS_NAME = System.getProperty("os.name");
	
	
	
	//项目名称
	public static final String APP_NAME = "yese";

	//项目的缺省配置文件名
	public static final String APP_PROPERTY_CONFIG = "YeseConfig";
	
	//项目的配置文件路径，所在的配置变量
	public static final String APP_CONFIG_FOLDER_PATH = AppConstants.APP_NAME + ".config.folder.path";
	



}