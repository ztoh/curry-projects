package com.chnye.common.able;

import java.util.Collection;

public interface IRegisteable<T> {
	void register( T t );
	void unregister( T t );
	Collection<T> list();
	boolean isEmpty();
	void clear();
	int size();
}
