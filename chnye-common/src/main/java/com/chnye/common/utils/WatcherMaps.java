package com.chnye.common.utils;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;


public class WatcherMaps<K,L> {
	private ConcurrentHashMap<K, Set<L>> listenerMaps = new ConcurrentHashMap<K,Set<L>>();
	
	public void add( K key, L listener ){
		Set<L> listeners = nullSafeGet( key );
		if( !listeners.contains( listener ) ){
			listeners.add( listener );
		}
	}
	
	public void remove( K key, L listener ){
		Set<L> listeners = nullSafeGet( key );
		if( listeners.contains( listener ) ){
			listeners.remove( listener );
		}
	}
	
	public void remove( L listener ){
		for( Map.Entry<K, Set<L>> entry : listenerMaps.entrySet() ){
			entry.getValue().remove( listener );
		}
	}
	
	public Set<L> getValues( K key ){
		return this.listenerMaps.get(key);
	}
	
	protected Set<L> nullSafeGet( K key ){
		Set<L> listeners = this.listenerMaps.get( key );
		if( listeners == null ){
			listeners = new CopyOnWriteArraySet<L>();
			this.listenerMaps.putIfAbsent( key, listeners );
		}
		return listeners;
	}
}
