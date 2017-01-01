package com.chnye.yese.web.utils;

//http://www.grepcode.com/file/repo1.maven.org/maven2/com.github.subchen/jetbrick-commons/1.0.5/jetbrick/web/servlet/RequestUtils.java?av=f

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;


import com.chnye.common.json.fastjson.FastjsonHelper;
import com.sun.xml.internal.ws.wsdl.writer.document.Port;

public class RequestUtil {

	
	
	
	public static String getParametersAsJSON( HttpServletRequest request ){
		Map<String, Object> map = new HashMap<String,Object>();
		Enumeration<String> enu = request.getParameterNames();
		while( enu.hasMoreElements() ){
			String name = enu.nextElement();
			String[] values = request.getParameterValues(name);
			if( null == values || values.length == 0 ){
				continue;
			}
			if( values.length > 1 ){
				map.put( name, values );
			} else {
				map.put( name , values[0] );
			}
		}//end while
		return  FastjsonHelper.toJSONString( FastjsonHelper.from(map) );
	}
	
	
	public static int getParameterAsInteger( HttpServletRequest requst, String key, int defaultValue ){
		String value = requst.getParameter( key );
		if( StringUtils.isEmpty( value ) ){
			return defaultValue;
		}
		try{
			return Integer.parseInt( value );
		} catch ( Exception e ){
			return defaultValue;
		}
	}
	
	
	public static long getParameterAsLong( HttpServletRequest requst, String key, long defaultValue ){
		String value = requst.getParameter( key );
		if( StringUtils.isEmpty( value ) ){
			return defaultValue;
		}
		try{
			return Long.parseLong( value );
		} catch ( Exception e ){
			return defaultValue;
		}
	}
	
	
	public static boolean getParameterAsBoolean( HttpServletRequest requst, String key, boolean defaultValue ){
		String value = requst.getParameter( key );
		if( StringUtils.isEmpty( value ) ){
			return defaultValue;
		}
		try{
			return Boolean.parseBoolean( value );
		} catch ( Exception e ){
			return defaultValue;
		}
	}
	
	

	// 是否是Ajax请求数据
	public static boolean isAjaxRequest(HttpServletRequest request) {
	    return "XMLHttpRequest".equals(request.getHeader("X-Requested-With")) || request.getHeader("accept").contains("application/json");
	}

	// 是否是Pjax请求数据: https://github.com/defunkt/jquery-pjax
	public static boolean isPjaxRequest(HttpServletRequest request) {
	    return StringUtils.isNotEmpty(request.getHeader("X-PJAX"));
	}


	// 是否是文件上传
	public static boolean isMultipartRequest(HttpServletRequest request) {
	    String type = request.getHeader("Content-Type");
	    return (type != null) && (type.startsWith("multipart/form-data"));
	}

	public static boolean isGzipSupported(HttpServletRequest request) {
	    String browserEncodings = request.getHeader("Accept-Encoding");
	    return (browserEncodings != null) && (browserEncodings.contains("gzip"));
	}
	
	
	
	
	public static String getPathInfo( HttpServletRequest request ){
		String path = request.getPathInfo();
		if( null == path ){
			path = request.getServletPath();
		} else {
			path = request.getServletPath() + path;
		}
		if( null == path  || path.trim().length() == 0 ){
			path = "/";
		}
		return path;
	}
	
	//http://www.grepcode.com/file/repo1.maven.org/maven2/org.apache.struts/struts-core/1.3.10/org/apache/struts/util/RequestUtils.java#RequestUtils.requestToServerStringBuffer%28javax.servlet.http.HttpServletRequest%29
	public static URL absoluteURL( HttpServletRequest request, String path ) throws MalformedURLException{
		return new URL( serverURL(request), request.getContextPath() + path );
	}
	
	public static URL serverURL(HttpServletRequest request ) throws MalformedURLException{
		StringBuffer sb = requestToServerStringBuffer( request );
		return new URL( sb.toString() );
	}
	
	private static StringBuffer requestToServerStringBuffer( HttpServletRequest request ){
		return createServerStringBuffer( request.getScheme(), request.getServerName(), request.getServerPort() );
	}


	private static StringBuffer createServerStringBuffer(String scheme, String serverName, int serverPort) {
		StringBuffer sb = new StringBuffer();
		if( serverPort < 80 ){
			serverPort = 80;
		}
		sb.append( scheme );
		sb.append( "://" );
		sb.append( serverName );
		if( ( scheme.equals("http") && serverPort != 80  )
			|| ( scheme.equals("https") && serverPort != 443 ) ){
			sb.append( ':' );
			sb.append( serverPort );
			}
		return sb;
	}
}
