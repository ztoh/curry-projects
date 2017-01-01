package com.chnye.common.processor;


public interface IProcessor<T> {
    boolean process( T input );
    
    public static final IProcessor TRUE = new IProcessor(){
    	public boolean process( Object input ){  return true; }
    };
}