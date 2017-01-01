package com.chnye.common.dispatch;


public interface ITypeDispatch<T,I,O>{
	public O dispatch( T type, I input );
}