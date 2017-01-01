package com.chnye.common.dispatch;


public interface IThrowableDispatch<I,O,E extends Exception>{
	public O dispatch( I input ) throws E;
}