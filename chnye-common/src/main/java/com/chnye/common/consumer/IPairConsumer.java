package com.chnye.common.consumer;



public interface IPairConsumer<T1,T2>{
    void consume( T1 arg1, T2 arg2 );
    
    public static class NOOP_PAIRCONSUMER<T1,T2> implements IPairConsumer<T1,T2>{
    	public void consume( T1 arg1, T2 arg2 ){}
    }
}
