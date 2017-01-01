package com.chnye.yese.web.utils;

import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;

import javax.servlet.ServletContext;

//http://www.grepcode.com/file/repo1.maven.org/maven2/com.github.subchen/jetbrick-commons/1.0.5/jetbrick/web/servlet/ServletUtils.java?av=f

public class ServletUtil {

	public static String getWebRoot( ServletContext context ){
		String rootPath = context.getRealPath("/");
		if( rootPath == null ){
			try{
				URL url = context.getResource("/");
				if( null != url && "file".equals( url.getProtocol()) ){
					rootPath = URLDecoder.decode( url.getFile(), "utf-8" );
				}
			} catch( IOException e ){
				throw new RuntimeException( e );
			}
		}
		return rootPath;
	}
}
