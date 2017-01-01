package com.chnye.common.function;


public interface ThrowableFunction3<T1, T2, T3, O, E extends Throwable>{
	public O apply( T1 arg1, T2 arg2, T3 arg3  ) throws E;
}
