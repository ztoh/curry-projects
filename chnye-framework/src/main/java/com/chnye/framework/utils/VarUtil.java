package com.chnye.framework.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.chnye.framework.context.IContext;





public class VarUtil {
	
	public static final Pattern VAR_PATTERN = Pattern.compile("(\\$)\\{?(\\w+)\\}?");
	
	//http://www.grepcode.com/file/repo1.maven.org/maven2/org.jspringbot/jspringbot-expression/1.3/org/jspringbot/keyword/expression/ELUtils.java#ELUtils
//	public static final Pattern VAR_PATTERN = Pattern.compile("\\$\\{([^\\}]+)\\}", Pattern.CASE_INSENSITIVE);

	public static final Pattern EXPRESSION_PATTERN = Pattern.compile("\\$\\[(.*)\\]", Pattern.CASE_INSENSITIVE);
	
	public static final Pattern PREFIX_EXPRESSION_PATTERN = Pattern.compile("([a-z0-9]+)\\:(.*)", Pattern.CASE_INSENSITIVE);
	
	
	public static String replaceVar( final String param, IContext context ){
		Map<String, String> mapping = new HashMap<String,String>();
		
		Matcher matcher = VAR_PATTERN.matcher( param );
		while( matcher.find() ){
			String var = matcher.group(2);
			String value = context.getString( var );
			if( StringUtils.isBlank( value ) ){
				value = matcher.group();
			}
			mapping.put( matcher.group(), value );
		}//end while
		String reString = param;
//		for( final String key : mapping.keySet() ){
//			reString = reString.replace( key, mapping.get(key) );
//		}
		for( Map.Entry<String, String> entry : mapping.entrySet() ){
			reString = reString.replace( entry.getKey(), entry.getValue() );
		}
		return reString;
	}
	
	
	public static String replaceVars( String string, IContext context ){
		StringBuilder  sb = new StringBuilder( string );
		Matcher matcher = VAR_PATTERN.matcher( sb );
		
		int startIndex = 0;
		while( startIndex < sb.length() && matcher.find(startIndex) ){
			String name = matcher.group(1);
			Object value = context.getValueAt( name );
			if( value != null ){
				String strValue = String.valueOf( value );
				sb.replace(matcher.start(), matcher.end(), strValue );
				startIndex = matcher.start() + strValue.length();
			}
		}//end while
		return sb.toString();
	}
	
}
