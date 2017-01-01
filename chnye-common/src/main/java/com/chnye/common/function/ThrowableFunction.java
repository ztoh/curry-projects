package com.chnye.common.function;


public interface ThrowableFunction<T, O, E extends Throwable>{
	public O apply( T input ) throws E;
}
