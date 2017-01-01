package com.chnye.common.consumer;



public interface IConsumer<T> {
    void consume( T input );
    
    public static class NOOP_CONSUMER<T> implements IConsumer<T>{
    	public void consume( T input ){}
    }
}