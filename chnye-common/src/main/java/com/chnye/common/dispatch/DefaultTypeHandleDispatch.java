package com.chnye.common.dispatch;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import com.chnye.common.handler.IHandler;
import com.chnye.common.handler.ITypeHandleable;


public class DefaultTypeHandleDispatch<T,I,O,H extends IHandler> implements ITypeDispatch<T,I,O>, ITypeHandleable<T,H>{

	private ConcurrentHashMap<T, Set<H>>  handlerMaps = new ConcurrentHashMap<T,Set<H>>();
	

	
	public O dispatch( T type, I input ){
		Set<H> handlers = nullSafeGet( type );
		for( H handler : handlers ){
			handler.handle( input );
		}
		return null;
	}

	public void addHandler( T type, H handler ){
		Set<H> handlers = nullSafeGet( type );
		if( !handlers.contains( handler ) ){
			handlers.add( handler );
		}
	}
	
	public void removeHandler( T type, H handler ){
		Set<H> handlers = nullSafeGet( type );
		if( handlers.contains( handler ) ){
			handlers.remove( handler );
		}
	}
	
	public void removeHandler( H handler ){
		for( Map.Entry<T, Set<H>> entry : handlerMaps.entrySet() ){
			entry.getValue().remove( handler );
		}
	}
	
	private Set<H> nullSafeGet( T type ){
		Set<H> handlers = handlerMaps.get( type );
		if( handlers == null ){
			handlers = new CopyOnWriteArraySet<H>();
			handlerMaps.putIfAbsent( type, handlers );
		}
		return handlers;
	}
}