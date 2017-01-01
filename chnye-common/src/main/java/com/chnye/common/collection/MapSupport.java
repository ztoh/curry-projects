package com.chnye.common.collection;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;

import com.chnye.common.utils.Assert;
import com.chnye.common.visitor.IVisitor;


public class MapSupport {

//	/**
//	 * 回调listener的接口
//	 */
//	public interface Notification<L>{
//		void notify( L listener );
//	}
	
	/**
	 * 提供的，初始化集合的方法
	 * @return
	 */
	public static <T,L> Map<T,L> newConCurrentHashMap(){
		return new ConcurrentHashMap<T,L>();
	}
	
	
	
	public static <T,L> Map<T,L> addListener( T key,  L listener, Map<T, L> listeners ){
		Assert.notNull( listeners, "listeners is null" );
		if( listener != null ){
			if( !listeners.containsKey( key ) ){
				listeners.put( key, listener );
			}
		} else {
			listeners.remove( key );
		}
		return listeners;
	}
	
	public static <T,L> Map<T,L> removeListener( T key, L listener, Map<T,L> listeners ){
		Assert.notNull( listeners, "listeners is null" );
		if( listeners.containsKey( key ) ){
			listeners.remove( key );
		}
		return listeners;
	}
	
	public static <T,L> Map<T,L> removeListener( L listener, Map<T,L> listeners ){
		Assert.notNull( listeners, "listeners is null" );
		T key = null;
		for( Map.Entry<T, L> entry : listeners.entrySet() ){
			L value = entry.getValue();
			if( value.equals( listener ) ){
				key = entry.getKey();
				break;
			}
		}
		listeners.remove(key);
		return listeners;
	}
	
//	public static <T,L> void clear( Map<T,Set<L>> listeners ){
//		for( Map.Entry<T, Set<L>>  entry : listeners.entrySet() ){
//			entry.getValue().clear();
//		}
//		listeners.clear();
//	}
	public static <T,L> void clear( Map<T,L> listeners ){
		Assert.notNull( listeners, "listeners is null" );
		listeners.clear();
	}	
	
	public static <T, L> void notifyListeners( Map<T,L> listeners, T key, IVisitor<L> visitor ){
		L listener = listeners.get( key );
		synchronized( listener ){
				try{
					visitor.visit( listener );
				} catch ( Throwable e ){
					e.printStackTrace();
				}
		}//end sync
	}
	
	public static <T, L> void notifyListeners( Map<T, L> listeners, T key, final IVisitor<L> visitor, Executor executor ){
		final L listener = listeners.get( key );
		executor.execute( new Runnable(){
				@Override
				public void run(){
					synchronized( listener ){
						try{
							visitor.visit(listener);
						} catch ( Throwable e ){
							e.printStackTrace();
						}
					}//end sync
				}
		});			
	}
	
}
