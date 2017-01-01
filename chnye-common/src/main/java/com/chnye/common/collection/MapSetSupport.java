package com.chnye.common.collection;

/**
 * 回调接口的用处，以及为什么要这么用。
 *     设计这个类本意是将一些经常重用的，模式化的，不变的代码固定下来。 
 *		所以在notifyListeners()函数中循环遍历listener，就放在本类中实现，但是最终调用listener需要Event泛型参数
 * 		很显然，这个Event泛型与本类无关，如果传递进来就会造成勿扰。所以这里就设计了回调接口
 */

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executor;

import com.chnye.common.utils.Assert;
import com.chnye.common.visitor.IVisitor;


public class MapSetSupport {

//	/**
//	 * 回调listener的接口
//	 */
//	public interface Notification<L>{
//		void notify( L listener );
//	}
	
	/**
	 * 提供的，初始化集合的方法
	 */
	public static <T,L> Map<T,Set<L>> newConCurrentHashMap(){
//		return new LinkedHashMap<T,Set<L>>();
		return new ConcurrentHashMap<T,Set<L>>();
	}
	
	private static <T,L> Set<L> nullSafeGet( T key, Map<T, Set<L>> listeners  ){
		Assert.notNull( listeners, "listeners is null" );
		Assert.notNull( key, "key is null" );
		Set<L> values = listeners.get(key);
		if( values == null ){
			values = new CopyOnWriteArraySet<L>();
		}
		listeners.put(key, values);
		return values;
	}
	
	public static <T,L> Map<T,Set<L>> addListener( T key,  L listener, Map<T,Set<L>> listeners ){
		Assert.notNull( listeners, "listeners is null" );
		Assert.notNull( listener, "listener is null" );
		Set<L> values = nullSafeGet( key, listeners );
		if( !values.contains( listener ) ){
			values.add( listener );
		}
		return listeners;
	}
	
	public static <T,L> Map<T,Set<L>> removeListener( T key, L listener, Map<T,Set<L>> listeners ){
		Assert.notNull( listeners, "listeners is null" );
		Assert.notNull( listener, "listener is null" );
		Set<L> values = nullSafeGet( key, listeners );
		if( values.contains( listener ) ){
			values.remove( listener );
		}
		return listeners;
	}
	
	public static <T,L> Map<T,Set<L>> removeListener( L listener, Map<T,Set<L>> listeners ){
		Assert.notNull( listeners, "listeners is null" );
		Assert.notNull( listener, "listener is null" );
		for( Map.Entry<T, Set<L>>  entry : listeners.entrySet() ){
			entry.getValue().remove( listener );
		}
		return listeners;
	}
	
	public static <T,L> void clear( Map<T,Set<L>> listeners ){
		for( Map.Entry<T, Set<L>>  entry : listeners.entrySet() ){
			entry.getValue().clear();
		}
		listeners.clear();
	}
	
	
	public static <T, L> void notifyListeners( Map<T,Set<L>> listeners, T key, IVisitor<L> visitor ){
		Set<L> values = listeners.get( key );
		for( L listener : values ){
			synchronized( listener ){
				try{
					visitor.visit( listener );
				} catch ( Throwable e ){
					e.printStackTrace();
				}
			}//end sync
		}
	}
	
	public static <T, L> void notifyListeners( Map<T, Set<L>> listeners, T key, final IVisitor<L> visitor, Executor executor ){
		Set<L> values = listeners.get(key);
		for( final L listener : values ){
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
	
	
}
