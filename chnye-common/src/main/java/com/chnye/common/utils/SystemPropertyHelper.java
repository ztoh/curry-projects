package com.chnye.common.utils;

/**
 * http://www.grepcode.com/file/repository.pentaho.org/artifactory/pentaho/pentaho/pentaho-platform-core/5.1-SNAPSHOT/org/pentaho/platform/util/JVMParameterProvider.java?av=f
 */

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

public class SystemPropertyHelper {

	public static String getString( final String name, final String defaultValue ){
		return System.getProperty( name, defaultValue );
	}
	
	public static Object get( final String name ){
		return System.getProperty( name );
	}
	
	public long getLong( final String name, final long defaultValue ){
		String reValue = getString( name, null );
		if( reValue == null ){
			return defaultValue;
		} else {
			try{
				return Long.parseLong( reValue );
			} catch ( NumberFormatException e ){
				return defaultValue;
			}
		}
	}
	
	public static boolean hasParameter( String name ){
		return get( name ) != null;
	}
	
	public static Date getDateParameter( final String name, final Date defaultValue ){
		return ParameterHelper.parameterToDate( getString( name, null), defaultValue);
	}
	
	public Object getDecimalParameter( final String name, final Object defaultValue ){
		String reValue = getString( name, null );
		if( reValue == null ){
			return defaultValue;
		} else {
			try{
				return new BigDecimal( reValue );
			} catch ( NumberFormatException e ){
				return defaultValue;
			}
		}
	}
	
	public Iterator getParameterNames(){
		Properties props = System.getProperties();
		return props.keySet().iterator();
	}
	
	public Object[] getArray( final String name, final Object[] defaultValue ){
		return ParameterHelper.parameterToObjectArray( get(name), defaultValue );
	}
	
	public String[] getStringArray( final String name, final String[] defaultValue ){
		return ParameterHelper.parameterToStringArray( get(name), defaultValue );
	}
}
