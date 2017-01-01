package com.chnye.common.utils;

//http://grepcode.com/file/repo1.maven.org/maven2/com.googlecode.q-link/q-link-api/0.0.7/com/googlecode/qlink/api/functor/Aggregator.java?av=f
/**
 * 聚合，聚集
 */

import java.util.Collection;

public interface Aggregator<T, O>{
	O aggregate( Collection<T> lst );
}