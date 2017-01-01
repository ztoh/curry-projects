package com.chnye.common.function;

//http://www.grepcode.com/file/repo1.maven.org/maven2/com.googlecode.q-link/q-link-core/0.0.7/com/googlecode/qlink/core/utils/AggregationUtils.java?av=f

import java.util.Iterator;
import java.util.List;

public class Reducers {

	public static <T>  T applyReducer( List<T> list, Reducer<T> r ){
		Iterator<T> iter = list.iterator();
		T a = iter.next();
		T b = iter.next();
		
		T result = r.reduce(a, b);
		for( ; iter.hasNext(); ){
			result = r.reduce( result, iter.next() );
		}
		return result;
	}
}
