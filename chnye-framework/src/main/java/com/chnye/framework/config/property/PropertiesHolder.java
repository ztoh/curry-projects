package com.chnye.framework.config.property;



import java.nio.charset.Charset;
import java.util.Properties;

import com.chnye.common.properties.impl.DefaultPropertiesLoader;
import com.chnye.framework.config.AbstractPropertiesHolder;


public class PropertiesHolder extends AbstractPropertiesHolder{

	private Properties props;
	
	public static PropertiesHolder createPropertiesHolder( String name ){
		return new PropertiesHolder( name );
	}
	public static PropertiesHolder createPropertiesHolder( String name, String...locations ){
		return new PropertiesHolder( name, locations );
	}
	
	public PropertiesHolder( String name ){
		this( name, null );
	}
	
	public PropertiesHolder(String name, String[] locations ){
		super.setName(name);
		super.setLocations(locations);
	}
	
	@Override
	public void loadProperties() throws Exception{
//		props = ResourceUtil.loadProperties( locations );
//		props = PropertiesUtil.loadProperties( locations, Charset.defaultCharset().toString() );
		props = DefaultPropertiesLoader.newLoader().load(locations, Charset.defaultCharset().toString() );
	}
	
	@Override
	public Object getProperties(){
		return this.props;
	}

	private String getValue( String key ){
		String sysProperty = System.getProperty( key );
		if( sysProperty != null ){
			return sysProperty;
		}
		if( props.containsKey( key ) ){
			return props.getProperty( key );
		}
		return null;
	}
	
	@Override
	public String getProperty( String key ){
		String value = getValue( key );
		if( value  == null ){
			return null;
		}
		return value;
	}	
	
	@Override
	public void clear(){
		this.props.clear();
	}
	
}
