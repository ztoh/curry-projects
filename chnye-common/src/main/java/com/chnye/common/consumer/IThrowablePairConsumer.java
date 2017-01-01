package com.chnye.common.consumer;

public interface IThrowablePairConsumer<T1, T2, E extends Throwable> {
    void consume( final T1 arg1, final T2 arg2 ) throws E;
    
    public static class NOOP_THROWABLEPAIRCONSUMER<T1,T2, E extends Throwable> implements IThrowablePairConsumer<T1, T2, E>{
    	public void consume( final T1 arg1, final T2 arg2 ) throws E{}
    }
}