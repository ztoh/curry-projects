package com.chnye.common.handler;


public interface IAcceptThrowableHandler<T,O, E extends Exception> extends IThrowableHandler<T,O,E> {
    boolean accept( T input );
}