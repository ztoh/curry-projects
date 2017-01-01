package com.chnye.common.handler;



public interface IThrowableHandler<I, O, E extends Throwable> {
    O handle( I input ) throws E;
}