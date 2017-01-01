package com.chnye.common.dispatch;


public interface IDispatch<I,O>{
	public O dispatch( I input );
}