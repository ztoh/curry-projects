package com.chnye.common.processor;

public interface IThrowableProcessor<T, E extends Throwable> {
    boolean process( T input ) throws E;
}