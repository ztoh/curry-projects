package com.chnye.common.utils;



public class ObjectUtil {

	public static <T> T nullSafe( final T target, final T defaultValue ){
		return target != null ? target : defaultValue;
	}
	
}
