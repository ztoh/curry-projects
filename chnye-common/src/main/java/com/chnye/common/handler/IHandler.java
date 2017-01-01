package com.chnye.common.handler;

public interface IHandler<I, O> {
    O handle( I input );
}