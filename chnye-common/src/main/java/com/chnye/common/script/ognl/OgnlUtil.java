package com.chnye.common.script.ognl;

//http://www.grepcode.com/file/repo1.maven.org/maven2/com.manydesigns/elements/4.1.3/com/manydesigns/elements/ognl/OgnlUtils.java#OgnlUtils


import java.util.Map;

import ognl.Ognl;
import ognl.OgnlException;
import ognl.TypeConverter;


public class OgnlUtil {

	
	public static Object getValueQuietly( String expression, Map ognlContext, Object root ){
		Object parseOgnlExpression = parseExpressionQuietly( expression );
		return getValueQuietly(parseOgnlExpression, ognlContext, root);
	}

	private static Object parseExpressionQuietly(String expression) {
		// TODO Auto-generated method stub
		if( expression == null ){
			return null;
		}
		Object result;
		try{
			result = Ognl.parseExpression( expression );
		} catch( OgnlException e ){
			e.printStackTrace();
			result = null;
		}
		return result;
	}
	
	public static Object getValueQuietly( Object parseExpression, Map ognlContext, Object root ){
		if( parseExpression == null ){
			return null;
		}
		Object result;
		try{
			if( ognlContext == null ){
				result = Ognl.getValue( parseExpression, root );
			} else {
				result = Ognl.getValue( parseExpression, ognlContext, root );
			}
		} catch( OgnlException e ){
			e.printStackTrace();
			result = null;
		}
		return result;
	}
	
	
	
	
	
//	public static Object convertValue( Object value, Class toType ){
//		TypeConverter typeConverter = ognlContext.getTypeConverter();
//		return typeConverter.convertValue(ognlContext, null, null, null, value, toType );
//	}
//	
//	public static Object convertValueQuietly( Object value, Class toType ){
//		try{
//			return convertValue(value, toType);
//		} catch( Throwable e ){
//			e.printStackTrace();
//			return null;
//		}
//	}
	
	
	
}
