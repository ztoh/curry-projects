package com.chnye.common.aware;

public interface IParentAware<T> {
	void setParent( T parent );
	T getParent();
}
