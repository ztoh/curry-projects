package com.chnye.common.reflection;

import java.net.URL;

public class ClassLoaderUtil {
	
	private ClassLoaderUtil(){}
	
	public static ClassLoader getDefaultClassLoader(){
		ClassLoader cl = null;
		try{
			cl = Thread.currentThread().getContextClassLoader();
		} catch ( Throwable ex ){
			if( cl == null ){
				ClassLoaderUtil.class.getClassLoader();
			}
		}
		return cl;
	}
	
	public static ClassLoader getContextClassLoader(){
		return Thread.currentThread().getContextClassLoader();
	}
	
	public static Class<?> loadClass( String className ) throws ClassNotFoundException{
		return loadClass( className, getDefaultClassLoader() );
	}
	
	public static Class<?> loadClass( String className, ClassLoader classLoader ) throws ClassNotFoundException{
		if( className == null ){
			return null;
		}
		if( classLoader == null ){
			return Class.forName( className );
		} else {
			return Class.forName( className, true, classLoader );
		}		
	}
	
	public static Object newInstance( String className ) throws Exception{
		return newInstance( loadClass( className ) );
	}
	
	public static Object newInstance( Class<?> clazz ) throws Exception{
		if( clazz == null ){
			return null;
		}
		try{
			return clazz.newInstance();
		} catch ( Exception e ){
			throw new RuntimeException( e );
		}
	}
	
	public static URL getResource( String resourceName ){
		ClassLoader cl = null;
		URL url = null;
		cl = getDefaultClassLoader();
		if( cl == null ){
			cl = ClassLoader.getSystemClassLoader();
		}
		if( cl != null ){
			url = cl.getResource( resourceName );
		}
		return url;
	}
	
//	public static <T> Class<T> getClass( T obj ){
//		Class<T> clazz = (Class<T>)obj.getClass();
//		return clazz;
//	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getObjectInstance( String className ){
		try{
			Class<?> clazz = loadClass( className );
			return (T) newInstance( clazz );
		} catch ( Exception e ){
			throw new RuntimeException( e );
		}
	}
	
}
