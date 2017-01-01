package com.chnye.common.function;



public interface ThrowableFunction4<T1, T2, T3, T4, O, E extends Throwable>{
	public O apply( T1 arg1, T2 arg2, T3 arg3, T4 arg4  ) throws E;
}
