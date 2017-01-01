package com.chnye.common.processor;


public interface IThrowablePairProcessor<T1, T2, E extends Throwable> {
    boolean process( T1 arg1, T2 arg2 ) throws E;
}