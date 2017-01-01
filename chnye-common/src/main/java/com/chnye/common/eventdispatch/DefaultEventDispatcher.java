package com.chnye.common.eventdispatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class DefaultEventDispatcher implements EventDispatcher{

	private Map<Class<? extends Enum>, List<EventHandler>> handlerMaps = new HashMap<Class<? extends Enum>, List<EventHandler>>();
	private ReadWriteLock lock = new ReentrantReadWriteLock();
	
	@Override
	public void dispatch(Event event) {
		// TODO Auto-generated method stub
		Class<? extends Enum> eventType = event.getType().getDeclaringClass();
		if( handlerMaps.containsKey( eventType ) ){
			List<EventHandler> handlers = handlerMaps.get( eventType );
			for( EventHandler handler : handlers ){
				if( handler.accept( event ) ){
					handler.handle( event );
				}
			}
		}
	}

	@Override
	public void register(Class<? extends Enum> eventType, EventHandler handler) {
		// TODO Auto-generated method stub
		lock.writeLock().lock();
		try{
			if( handlerMaps.containsKey( eventType ) ){
				List<EventHandler> list = handlerMaps.get( eventType );
				if( !list.contains( handler ) ){
					list.add( handler );
				}
			} else {
				List<EventHandler> list = new ArrayList<EventHandler>();
				list.add( handler );
				handlerMaps.put( eventType, list );
			}
		} finally{
			lock.writeLock().unlock();
		}
	}

	@Override
	public void unregister(Class<? extends Enum> eventType, EventHandler handler) {
		// TODO Auto-generated method stub
		lock.writeLock().lock();
		try{
			if( handlerMaps.containsKey( eventType ) ){
				List<EventHandler> list = handlerMaps.get( eventType );
				list.remove( handler );
			}
		} finally {
			lock.writeLock().unlock();
		}
	}

}
