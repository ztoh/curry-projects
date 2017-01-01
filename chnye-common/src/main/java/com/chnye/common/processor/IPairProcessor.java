package com.chnye.common.processor;


public interface IPairProcessor<T1, T2> {
    boolean process( T1 arg1, T2 arg2 );
}