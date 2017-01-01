package com.chnye.common.utils;

//http://www.grepcode.com/file/repo1.maven.org/maven2/org.thymeleaf/thymeleaf/2.1.4.RELEASE/org/thymeleaf/util/Validate.java?av=f

import java.util.Collection;


public class Assert {

	public static void notNull( final Object obj, final String message ){
		if( null == obj ){
			throw new IllegalArgumentException( message );
		}
	}
	
	public static void notEmpty( final String str, final String message ){
		if( null == str || str.trim().equals("") ){
			throw new IllegalArgumentException( message );
		}
	}
	
	public static void notEmpty( final Collection<?> collection, final String message ){
		if( null == collection || collection.size() == 0 ){
			throw new IllegalArgumentException( message );
		}
	}
	
	public static void notEmpty( final Object[] array, final String message ){
		if( null == array || array.length == 0 ){
			throw new IllegalArgumentException( message );
		}
	}
	
	public static void containsNoNulls( final Iterable<?> iter, final String message ){
		for( final Object obj : iter ){
			notNull(obj, message);
		}
	}
	
	public static void containsNoEmpties( final Iterable<String> iter, final String message ){
		for( final String str : iter ){
			notEmpty(str, message);
		}
	}
	
	public static void containsNoNulls( final Object[] array, final String message ){
		for( final Object obj : array ){
			notNull(obj, message);
		}
	}
	
	public static void isTrue( final boolean condition, final String message ){
		if( !condition ){
			throw new IllegalArgumentException( message );
		}
	}
	
}
