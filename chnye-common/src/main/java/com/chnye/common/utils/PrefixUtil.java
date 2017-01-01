package com.chnye.common.utils;

//http://www.grepcode.com/file/repo1.maven.org/maven2/org.thymeleaf/thymeleaf/2.1.4.RELEASE/org/thymeleaf/util/PrefixUtils.java?av=f

/**
 * 对于字符串 key=value
 *    getPrexfix()返回key
 *    getUnprefixed()返回value
 */

public class PrefixUtil {

	public static boolean hasPrefix( final String name, char prefix ){
		final int colonPos = name.indexOf( prefix );
		return colonPos != -1;
	}
	
	public static String getPrefix( final String name, char prefix ){
		final int colonPos = name.indexOf( prefix );
		if( colonPos != -1 ){
			return name.substring( 0, colonPos );
		}
		return null;
	}
	
	public static String getUnprefixed( final String name, char prefix ){
		final int colonPos = name.indexOf( prefix );
		if( colonPos != -1 ){
			return name.substring( colonPos + 1 );
		}
		return name;
	}
}
