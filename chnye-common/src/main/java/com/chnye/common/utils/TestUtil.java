package com.chnye.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.springframework.util.ReflectionUtils;

/*
* @Author: anchen
* @Date:   2016-01-17 12:14:10
* @Last Modified by:   anchen
* @Last Modified time: 2016-01-18 00:28:13
*
* http://grepcode.com/file/repo1.maven.org/maven2/com.alibaba.otter/shared.common/4.2.12/com/alibaba/otter/shared/common/utils/TestUtils.java?av=f
*/

public class TestUtil {

    public static Object getField( Object obj, String fieldName ){
        Field field = ReflectionUtils.findField( obj.getClass(), fieldName );
        ReflectionUtils.makeAccessible( field );
        return ReflectionUtils.getField( field, obj );
    }

    public static void setField( Object target, String fieldName, Object args ) throws Exception{
        Field field = ReflectionUtils.findField( target.getClass(), fieldName );
        ReflectionUtils.makeAccessible( field );
        ReflectionUtils.setField( field, target, args );
    }

    public static Object invokeMethod( Object target, String methodName, Object... args ) throws Exception{
        Method method = null;
        if( args == null || args.length == 0 ){
            method = ReflectionUtils.findMethod( target.getClass(), methodName );
        } else {
            Class[] argsClass = new Class[args.length];
            for( int i = 0; i < args.length; i++ ){
                argsClass[i] = args[i].getClass();
            }
            method = ReflectionUtils.findMethod( target.getClass(), methodName, argsClass );
        }
        ReflectionUtils.makeAccessible( method );

        if( args == null || args.length == 0 ){
            return ReflectionUtils.invokeMethod( method, target );
        } else {
            return ReflectionUtils.invokeMethod( method, target, args );
        }
    }


    public static long memoryUsed() {
        Runtime rt = Runtime.getRuntime();
        return rt.totalMemory() - rt.freeMemory();
    }

}