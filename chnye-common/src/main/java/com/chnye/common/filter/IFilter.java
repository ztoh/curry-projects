package com.chnye.common.filter;

public interface IFilter<T> {
	boolean filter( T input );
	
	public static final IFilter<Object> ALWAYS = new IFilter<Object>(){
		public boolean filter( Object input ){
	  		return true;
	  	}	
	};
	
	public static final IFilter<Object> NEVER = new IFilter<Object>(){
		public boolean filter( Object input ){
			return false;
		}
	};
}
