package com.chnye.common.properties.impl;

import java.io.File;
import java.util.Properties;

import com.chnye.common.properties.IPropertiesLoader;
import com.chnye.common.properties.PropertiesLoaderException;

public class DefaultPropertiesLoader implements IPropertiesLoader{

	private FileSystemPropertiesLoader fileSystemPropertiesLoader;
	
	private ClassPathPropertiesLoader classPathPropertiesLoader;
	
	
	public static DefaultPropertiesLoader newLoader(){
		return new DefaultPropertiesLoader();
	}
	
	public DefaultPropertiesLoader(){
		fileSystemPropertiesLoader = FileSystemPropertiesLoader.newLoader();
		classPathPropertiesLoader = ClassPathPropertiesLoader.newLoader();
	}
	
	@Override
	public Properties load(String location, String encoding ) throws PropertiesLoaderException {
		if( isFileExists( location ) ){
			return fileSystemPropertiesLoader.load( location, encoding );
		} else {
			return classPathPropertiesLoader.load( location, encoding );
		}
	}
	
	private static boolean isFileExists( String location ){
		File file = new File( location );
		return file.exists();
	}

	public Properties load( String[] locations, String encoding ) throws PropertiesLoaderException { 
		Properties allProps = new Properties();
		for( String location : locations ){
			Properties props = load( location, encoding );
			allProps.putAll( props );
		}
		return allProps;
	}
	
}
