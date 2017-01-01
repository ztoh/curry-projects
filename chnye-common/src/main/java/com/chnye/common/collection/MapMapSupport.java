package com.chnye.common.collection;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;

import com.chnye.common.utils.Assert;
import com.chnye.common.visitor.IVisitor;

public class MapMapSupport {

	
	public static <K1,K2,V> Map<K1, Map<K2,V>> newConcurrentHashMap(){
		return new ConcurrentHashMap<>();
	}
	
	private static <K1,K2,V> Map<K2,V> nullSafeGet( K1 key1, Map<K1, Map<K2,V>> listeners  ){
		Assert.notNull( listeners, "listeners is null" );
		Assert.notNull( key1, "key1 is null" );
		Map<K2,V> values = listeners.get(key1);
		if( values == null ){
			values = new ConcurrentHashMap<K2,V>();
		}
		listeners.put(key1, values);
		return values;
	}
	
	public static <K1,K2,V> Map<K1,Map<K2,V>> addListener( K1 key1, K2 key2, V listener, Map<K1,Map<K2,V>> listeners ){
		Assert.notNull( listeners, "listeners is null" );
		Assert.notNull( listener, "listener is null" );
		Map<K2,V> values = nullSafeGet( key1, listeners );
		if( !values.containsKey( key2 ) ){
			values.put( key2, listener );
		}
		return listeners;
	}
	
	public static <K1,K2,V> Map<K1,Map<K2,V>> removeListener( K1 key1, K2 key2, Map<K1,Map<K2,V>> listeners ){
		Assert.notNull( listeners, "listeners is null" );
		Assert.notNull( key1, "key1 is null" );
		Assert.notNull( key2, "key2 is null" );
		Map<K2,V> values = nullSafeGet( key1, listeners );
		if( values.containsKey( key2 ) ){
			values.remove( key2 );
		}
		return listeners;
	}
	
	public static <K1,K2,V> void clear( Map<K1,Map<K2,V>> listeners ){
		for( Map.Entry<K1, Map<K2,V>>  entry : listeners.entrySet() ){
			entry.getValue().clear();
		}
		listeners.clear();
	}
	
	public static <K1, K2, V> void notifyListeners( Map<K1,Map<K2,V>> listeners, K1 key1, K2 key2, IVisitor<V> visitor ){
		Map<K2,V> values = listeners.get( key1 );
		if( values != null ){
			V listener = values.get( key2 );
			if( listener != null ){
				visitor.visit( listener );
			}
		}
	}
	
	public static <K1, K2, V> void notifyListeners( Map<K1,Map<K2,V>> listeners, K1 key1, K2 key2, final IVisitor<V> visitor, Executor executor ){
		Map<K2,V> values = listeners.get( key1 );
		if( values != null ){
			final V listener = values.get( key2 );
			if( listener != null ){
				executor.execute( new Runnable(){
					@Override
					public void run(){
						try{
							visitor.visit( listener );
						} catch ( Throwable e ){
							e.printStackTrace();
						}
					}
				});	
				
			}
		}
	}
	
}
