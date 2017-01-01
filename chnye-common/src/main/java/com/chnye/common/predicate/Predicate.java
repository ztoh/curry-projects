package com.chnye.common.predicate;

/**
 * 对于T做任何的运算来得到一个boolean值的结果。
 * 
 * @author chnye
 *
 * @param <T>
 */

//http://grepcode.com/file/repo1.maven.org/maven2/com.googlecode.q-link/q-link-api/0.0.7/com/googlecode/qlink/api/functor/Predicate.java?av=f

public interface Predicate<T>{
	boolean evaluate( T object );	
}