package com.chnye.common.propertycontainer;

import java.util.Map;
import java.util.Set;

public interface IPropertyContainer {
	
	/*
	 * 设置属性
	 */
	void setProperty( String property, Object value );
	/*
	 * 获取属性
	 */
	Object getProperty( String property );  
	
	/*
	 * 是否有属性
	 */
	boolean hasProperty( String property );
	
	
	/*
	 * 返回所有配置
	 */
	Map<String,Object> getProperties();

	String getString( String property );
	String getString( String property, String defaultValue );
	
	boolean getBoolean( String property );
	boolean getBoolean( String property, boolean defaultValue );
	
	int getInt( String property );
	int getInt( String property, int defaultValue );
	
	long getLong( String property );
	long getLong( String property, long defalutValue );

}
