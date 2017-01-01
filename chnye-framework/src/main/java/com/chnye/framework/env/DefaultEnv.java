package com.chnye.framework.env;

/*
 * https://github.com/ztoh/app-engine/blob/master/common/src/main/java/com/appengine/common/config/DefaultProfileLoader.java
 */

import com.chnye.framework.config.LifecyclePropertiesHolder;
import com.chnye.framework.config.PropertiesManager;
import com.google.common.base.Optional;

public class DefaultEnv extends AbstractEnv {

	public static final DefaultEnv instance = new DefaultEnv();
	
	public static DefaultEnv getInstance(){
		return instance;
	}
	
	private Env env;
	
	@Override
	Env getEnv() {
		// TODO Auto-generated method stub
		//先通过环境变量判断
//		String strEnv = System.getenv( APP_ENV_VAR );
//		if( strEnv == null ){
//			//再次配置文件中读取
//			Optional<String> envOptional = readFromProperties();
//			if( envOptional.isPresent() ){
//				strEnv = envOptional.get();
//			}
//			if( strEnv == null ){
//				env = DEFAULT_ENV;
//			}
//			System.setProperty( APP_ENV_VAR, env.name() );
//		}
		return env;
	}

	
	private Optional<String> readFromProperties(){
//		LifecyclePropertiesHolder holder = PropertiesManager.getInstance().getProperties( APP_ENV );
//		String strEnv = holder.getProperty( APP_ENV_VAR );
		String strEnv = "";
		return Optional.fromNullable( strEnv );
	}
	
	
	public static boolean isDev(){
		return instance.getEnv() == Env.DEV;
	}
	public static boolean isTest(){
		return instance.getEnv() == Env.TEST;
	}
	public static boolean isProd(){
		return instance.getEnv() == Env.PROD;
	}
	
}
