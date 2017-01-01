package com.chnye.common.eventbus;

/**
 * EventManager的key本身就应该是混合的，而不应该是泛型的
 *   如果是泛型，那代表运行时，只接受某种类型
 */

import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReentrantReadWriteLock;


import com.chnye.common.collection.MapSetSupport;
import com.chnye.common.visitor.IVisitor;


public class MapSetsEventManager{
	
	private static final Object DEFAULT_KEY = "defaultKey";
	
	protected Map<Object, Set<IEventListener<?>>>  listenerMaps = MapSetSupport.newConCurrentHashMap();
	private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
	
	private Executor executor;
	
	private static  MapSetsEventManager instance = null;
	
	public static MapSetsEventManager getInstance(){
		if( instance == null ){
			synchronized( MapSetsEventManager.class ){
				if( instance == null ){
					instance = new MapSetsEventManager();
				}
			}//end sync 
		}
		return instance;
	}
	
	
	public MapSetsEventManager(){
		this( null );
	}
	
	public MapSetsEventManager( Executor executor ){
		setExecutor( executor );
	}
	
	
	
	public void setExecutor( Executor executor ){
		this.executor = executor;
	}
	
	public void addListener(IEventListener<?> listener) {
		rwLock.writeLock().lock();
		try {
			MapSetSupport.addListener( DEFAULT_KEY, listener, listenerMaps );
		} finally {
			rwLock.writeLock().unlock();
		}
	}

	
	public void addListener(Object key, IEventListener<?> listener) {
		rwLock.writeLock().lock();
		try {
			MapSetSupport.addListener( key, listener, listenerMaps );
		} finally {
			rwLock.writeLock().unlock();
		}
	}

	public void removeListener(IEventListener<?> listener) {
		rwLock.writeLock().lock();
		try {
			MapSetSupport.removeListener( listener, listenerMaps );
		} finally {
			rwLock.writeLock().unlock();
		}
	}


	public void removeLisener(Object key, IEventListener<?> listener) {
		rwLock.writeLock().lock();
		try {
			MapSetSupport.removeListener(key, listener, listenerMaps );
		} finally {
			rwLock.writeLock().unlock();
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void notifyListeners( final Object event ){
		IVisitor<IEventListener<?>> notification = new IVisitor<IEventListener<?>>(){
			@Override
			public boolean visit(IEventListener listener) {
				listener.onEvent( event );
				return true;
			}
		};
		
		MapSetSupport.notifyListeners(listenerMaps, DEFAULT_KEY, notification );
	}
	
	/**
	 * 发布同步的消息
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void notifyListeners( final Object key, final Object event ){
		IVisitor<IEventListener<?>> notification = new IVisitor<IEventListener<?>>(){
			@Override
			public boolean visit(IEventListener listener) {
				listener.onEvent( event );
				return true;
			}
		};
		
		MapSetSupport.notifyListeners(listenerMaps, key, notification );
	}
	
	/**
	 * 发布异步的消息
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void notifyAsyncListeners( final Object key, final Object event ){
		IVisitor<IEventListener<?>> notification = new IVisitor<IEventListener<?>>(){
			@Override
			public boolean visit(IEventListener listener) {
				listener.onEvent( event );
				return true;
			}
		};
		if( this.executor != null ){
			MapSetSupport.notifyListeners( listenerMaps, key, notification, executor);
		} else {
			MapSetSupport.notifyListeners(listenerMaps, key, notification );
		}
	}


	public void clear(){
		MapSetSupport.clear( listenerMaps );
	}
	
}
