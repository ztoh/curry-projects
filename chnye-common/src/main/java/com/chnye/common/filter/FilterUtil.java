package com.chnye.common.filter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FilterUtil {

	
	public static <T> List<T> filter( List<? extends T> inputs,  IFilter<T> filter ){
		List<T> result = new ArrayList<T>();
		for( T input : inputs ){
			if( filter.filter(input) ){
				result.add( input );
			}
		}
		return result;
	}
	
	public static <T> void filterInPlace( List<? extends T> inputs, IFilter<T> filter ){
		Iterator<? extends T> iter = inputs.iterator();
		while( iter.hasNext() ){
			if( !filter.filter( iter.next() ) ){
				iter.remove();
			}
		}
	}
	
}
