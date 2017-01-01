package com.chnye.common.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EnumUtil {
	private EnumUtil(){
	}
	
	public static <E extends Enum<?>> Map<String,E> getEnumMap( Class<E> enumClass ){
		Map<String,E> map = new LinkedHashMap<String,E>();
		
		for( E e : enumClass.getEnumConstants() ){
			map.put( e.name(), e );
		}
		return map;
	}
	
	public static <E extends Enum<?>>  List<E> getEnumList( Class<E> enumClass ){
		return new ArrayList<E>( Arrays.asList( enumClass.getEnumConstants() ) );
	}
	
	public static <E extends Enum<E>> boolean isValidEnum( Class<E> enumClass, String enumName ){
		if( enumName == null ){
			return false;
		}
		try{
			Enum.valueOf( enumClass, enumName );
			return true;
		} catch ( IllegalArgumentException e ){
			return false;
		}
	}
	
	/**
	 * 返回名字对应的枚举值
	 * @param enumClass
	 * @param enumName
	 * @return
	 */
	public static <E extends Enum<E>> E getEnum( Class<E> enumClass, String enumName ){
		if( enumName == null ){
			return null;
		}
		try{
			return Enum.valueOf( enumClass, enumName );
		} catch ( IllegalArgumentException e ){
			return null;
		}
	}
	
	
	/**
	 * 过滤掉不想要的枚举值
	 * @param enumClass		枚举类
	 * @param excludes		不想要的枚举值
	 * @return
	 */
	public static <E extends Enum<E>> List<E> list( Class<E> enumClass, E... excludes ){
		List<E> results = new ArrayList<E>();
		E[] types = enumClass.getEnumConstants();
		List<E> excludesEnums = Arrays.asList( excludes );
		for( E type : types ){
			if( !excludesEnums.contains(  type ) ){
				results.add( type );
			}
		}
		return results;
	}
	
	
	
	
	
}
