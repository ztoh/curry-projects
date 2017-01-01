package com.chnye.common.eventbus;

/**
 * 把某个对象的方法包装为监听器
 * 		前提条件:  方法只有一个的参数，且类型为事件对象，这里为Object型 
 */

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodObjectListener implements IEventListener<Object> {

	protected String descriptor;
	protected Object instance;
	protected Method method;
	
	
	public MethodObjectListener( Object instance, Method method ){
		this.instance = instance;
		this.method = method;
		this.descriptor = "MethodListener : " + method.getName() ;
		method.setAccessible( true );
	}
	
	@Override
	public void onEvent(Object event) {
		// TODO Auto-generated method stub
		try{
			method.invoke( instance, event );
		} catch ( InvocationTargetException e ){
			e.printStackTrace();		
		} catch (IllegalAccessException | IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	public Object getInstance(){
		return this.instance;
	}

}
