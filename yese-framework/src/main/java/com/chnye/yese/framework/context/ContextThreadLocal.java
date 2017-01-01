package com.chnye.yese.framework.context;


public class ContextThreadLocal {
	private static ThreadLocal<ContextThreadLocal> threadLocal = new ThreadLocal<ContextThreadLocal>();
	private IContext context;
	
	private ContextThreadLocal( IContext context ){
		this.context = context;
	}
	
	public static void setContext( IContext context ){
		ContextThreadLocal contextHolder = threadLocal.get();
		if( contextHolder == null ){
			contextHolder = new ContextThreadLocal( context );
			threadLocal.set( contextHolder );
		} else {
			contextHolder.context = context;
		}
	}
	
	public static IContext getContext(){
		ContextThreadLocal contextHolder = threadLocal.get();
		if( contextHolder != null ){
			return contextHolder.context;
		}
		return null;
	}
}
