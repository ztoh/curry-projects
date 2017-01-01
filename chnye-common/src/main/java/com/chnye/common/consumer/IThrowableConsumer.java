package com.chnye.common.consumer;

public interface IThrowableConsumer<T, E extends Throwable> {
    void consume( final T input ) throws E;
    
    public static class NOOP_THROWABLECONSUMER<T, E extends Throwable> implements IThrowableConsumer<T, E>{
    	public void consume( final T inputT ) throws E{}
    }
}