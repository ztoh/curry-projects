package com.chnye.common.handler;


public interface IAcceptHandler<T,O> extends IHandler<T,O> {
    boolean accept( T input );
}