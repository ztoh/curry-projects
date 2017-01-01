package com.chnye.common.thread;

//http://www.grepcode.com/file/repo1.maven.org/maven2/com.revolsys.open/com.revolsys.open.core/2014.09.10-MTEC1/com/revolsys/beans/MethodCallable.java?av=f

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public class MethodCallable<T> implements Callable<T>{

	private Method method;
	private Object object;
	private Object[] parmeters;
	
	public MethodCallable( final Method method, final Object object, final Object... parameters ){
		this.method = method;
		this.object = object;
		this.parmeters = parameters;
	}
	
	@SuppressWarnings("unchecked")
	public T call() throws Exception{
		return (T)method.invoke( object, parmeters );
	}
	
	
}
