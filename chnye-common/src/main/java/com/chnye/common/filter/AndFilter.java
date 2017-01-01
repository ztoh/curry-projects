package com.chnye.common.filter;

public class AndFilter<T> extends AbsComposedFilter<T> {

	public boolean filter( T input ){
		for( IFilter<T> filter : filters ){
			if( !filter.filter(input) ){
				return false;
			}
		}
		return true;
	}

}
