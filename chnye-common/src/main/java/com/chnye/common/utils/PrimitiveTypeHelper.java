package com.chnye.common.utils;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;
import java.util.WeakHashMap;

//http://www.grepcode.com/file/repo1.maven.org/maven2/com.helger/ph-commons/6.1.0/com/helger/commons/lang/ClassHelper.java?av=f

public class PrimitiveTypeHelper {

	private static final Map<Class<?>, Class<?>> PRIMITIVE_TO_WRAPPER = new WeakHashMap<Class<?>, Class<?>>(8);
	
	private static final Map<Class<?>, Class<?>> WRAPPER_TO_PRIMITIVE = new WeakHashMap<Class<?>, Class<?>>(8);
	
	
	static{
		registerPrimitiveMapping( boolean.class, 	Boolean.class );
		registerPrimitiveMapping( byte.class, 		Byte.class );
		registerPrimitiveMapping( char.class, 		Character.class );
		registerPrimitiveMapping( double.class, 	Double.class );
		registerPrimitiveMapping( float.class, 	Float.class );
		registerPrimitiveMapping( int.class, 		Integer.class );
		registerPrimitiveMapping( long.class, 		Long.class );
		registerPrimitiveMapping( short.class, 	Short.class );
		
	}


	private static void registerPrimitiveMapping(Class<?> primitiveType,
			Class<?> wrapperType) {
		PRIMITIVE_TO_WRAPPER.put(primitiveType, wrapperType);
		WRAPPER_TO_PRIMITIVE.put(wrapperType, primitiveType);
	}
	
	public static boolean isPrimitiveType( final Class<?> clazz ){
		return clazz != null && PRIMITIVE_TO_WRAPPER.containsKey( clazz );
	}
	
	public static boolean isWrapperType( final Class<?> clazz ){
		return clazz != null && WRAPPER_TO_PRIMITIVE.containsKey( clazz );
	}
	
	public static Class<?> getWrapperClass( final Class<?> clazz ){
		if( isWrapperType( clazz ) ){
			return clazz;
		}
		return PRIMITIVE_TO_WRAPPER.get( clazz );	
	}
	
	public static Class<?> getPrimitiveClass( Class<?> clazz ){
		if( isPrimitiveType( clazz ) ){
			return clazz;
		}
		return WRAPPER_TO_PRIMITIVE.get( clazz );
	}
	
	public static boolean isStringClass( final Class<?> clazz ){
		if( null == clazz ){
			return false;
		}
		return CharSequence.class.isAssignableFrom( clazz );
	}
	
	public static boolean isCharacterClass( final Class<?> clazz ){
		if( null == clazz ){
			return false;
		}
		return Character.class.isAssignableFrom( clazz ) || char.class.isAssignableFrom( clazz );
	}
	
	public static boolean isBooleanClass( final Class<?> clazz ){
		if( null == clazz ){
			return false;
		}
		return Boolean.class.isAssignableFrom( clazz ) || boolean.class.isAssignableFrom( clazz );
	}
	
	
	public static boolean isFloatingPointClass ( final Class <?> aClass){
	  if (aClass == null)
	    return false;
	  return Double.class.isAssignableFrom (aClass) ||
	         double.class.isAssignableFrom (aClass) ||
	         Float.class.isAssignableFrom (aClass) ||
	         float.class.isAssignableFrom (aClass) ||
	         BigDecimal.class.isAssignableFrom (aClass);
	}

	public static boolean isIntegerClass ( final Class <?> aClass){
	  if (aClass == null)
	    return false;
	  return Byte.class.isAssignableFrom (aClass) ||
	         byte.class.isAssignableFrom (aClass) ||
	         Integer.class.isAssignableFrom (aClass) ||
	         int.class.isAssignableFrom (aClass) ||
	         Long.class.isAssignableFrom (aClass) ||
	         long.class.isAssignableFrom (aClass) ||
	         Short.class.isAssignableFrom (aClass) ||
	         short.class.isAssignableFrom (aClass) ||
	         BigInteger.class.isAssignableFrom (aClass);
	}
	
	
}
