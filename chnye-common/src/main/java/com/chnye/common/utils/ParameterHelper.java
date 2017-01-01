package com.chnye.common.utils;

/**
 * http://www.grepcode.com/file/repository.pentaho.org/artifactory/pentaho/pentaho/pentaho-platform-core/5.1-SNAPSHOT/org/pentaho/platform/util/ParameterHelper.java?av=f
 */

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.Date;

public class ParameterHelper {

	/**
	 * import org.apache.commons.lang3.StringUtils;
	 *     StringUtils.defaultIfEmpty(conf.getRemark(), "")
	 *     StringUtils.defaultString(str, "");
	 */
	public static String parameterToString( final String value, final String defaultValue ){
		if( value == null ){
			return defaultValue;
		}
		return value;
	}
	
	public static long parameterToLong( final String value, final long defaultValue ){
		if( value == null ){
			return defaultValue;
		}
		try{
			long longValue = Long.valueOf( value ).longValue();
			return longValue;
		} catch ( Exception e ){
			e.printStackTrace();
		}
		return defaultValue;
	}
	
	public static Date parameterToDate( final String value, final Date defaultValue ){
		try{
			Date date = DateFormat.getInstance().parse( value );
			if( date == null ){
				return defaultValue;
			}
			return date;
		} catch ( Exception e ){
			return defaultValue;
		}
	}
	
	public static BigDecimal parameterToDecimal( final String value, final BigDecimal defaultValue ){
		if( value == null ){
			return defaultValue;
		}
		try{
			BigDecimal bd = new BigDecimal( value );
			return bd;
		} catch ( Exception e ){
		}
		return defaultValue;
	}
	
	public static Object[] parameterToObjectArray( final Object value, final Object[] defaultValue ){
		if( value == null ){
			return defaultValue;
		}
		if( value instanceof Object[] ){
			return (Object[])value;
		}
		return new Object[]{ value };
	}
	
	public static String[] parameterToStringArray( final Object value, final String[] defaultValue ){
		if( value == null ){
			return defaultValue;
		}
		if( value instanceof String[] ){
			return (String[])value;
		}
		if( value instanceof Object[] ){
			Object[] tmpArray = (Object[])value;
			for( int i = 0; i < tmpArray.length; i++ ){
				tmpArray[i] = tmpArray[i].toString();
			}
			return (String[])tmpArray;
		}
		return new String[]{ value.toString() };
	}
	
}
