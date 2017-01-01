package com.chnye.common.function;


public interface ThrowableFunction2<T1, T2, O, E extends Throwable>{
	public O apply( T1 arg1, T2 arg2  ) throws E;
}
