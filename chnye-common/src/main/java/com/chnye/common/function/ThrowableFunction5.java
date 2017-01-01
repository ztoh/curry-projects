package com.chnye.common.function;


public interface ThrowableFunction5<T1, T2, T3, T4, T5, O, E extends Throwable>{
	public O apply( T1 arg1, T2 arg2, T3 arg3, T4 arg4, T5 arg5  ) throws E;
}
