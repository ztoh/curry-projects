package com.chnye.framework.config;

public interface IPropertiesHolder {

	void setName( String name );
	String getName();
	
	void setLocations( String[] locations );
	String[] getLocations();
	
	void loadProperties() throws Exception;
	
	Object getProperties();
	
	String getProperty( String key );
	String getProperty( String key, String defaultValue );
	
	Boolean getBoolean( String key );
	Boolean getBoolean( String key, boolean defaultValue );
	
	Integer getInteger( String key );
	Integer getInteger( String key, int defaultValue );
	
	Long getLong( String key );
	Long getLong( String key, long defaultValue );
	
	Double getDouble( String key );
	Double getDouble( String key, double defaultValue );
	
	void clear();
}
