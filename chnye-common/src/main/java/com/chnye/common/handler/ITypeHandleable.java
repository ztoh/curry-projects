package com.chnye.common.handler;

public interface ITypeHandleable<T, H>{

	void addHandler( T type, H handler );
	void removeHandler( T type, H handler );
	void removeHandler( H handler );

}