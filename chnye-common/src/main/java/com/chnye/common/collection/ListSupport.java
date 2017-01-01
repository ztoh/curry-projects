package com.chnye.common.collection;

/**
 * http://www.grepcode.com/file/repo1.maven.org/maven2/org.neo4j/neo4j-kernel/2.2.3/org/neo4j/helpers/Listeners.java#Listeners
 * 
 * 2016-12-31
 *   日志: 此工具类通过在add/remove操作时new了一个新的集合来，绕过了锁的问题。
 *        因此要注意的点是: 在调用完add/remove要将返回值重新赋值给外面的集合.
 */

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;

import com.chnye.common.utils.Assert;
import com.chnye.common.visitor.IVisitor;

public class ListSupport {
	
//	/**
//	 * 回调listener的接口
//	 */
//	public interface Notification<T>{
//		void notify( T listener );
//	}
	
	public static <T> Iterable<T> newLinkedList(){
		return new LinkedList<T>();
	}
	
	public static <T> Iterable<T> addListener( T listener, Iterable<T> listeners ){
		Assert.notNull( listeners, "listeners is null" );
		List<T> newListeners = new LinkedList<T>( (Collection<T>)listeners  );
		if( listener != null ){
			newListeners.add( listener );
		}
		return newListeners;
	}
	
	public static <T> Iterable<T> removeListener( T listener, Iterable<T> listeners ){
		Assert.notNull( listeners, "listeners is null" );
		List<T> newListeners = new LinkedList<T>( (Collection<T>)listeners );
		newListeners.remove( listener );
		return newListeners;
	}
	
	public static <T> void notifyListeners( Iterable<T> listeners, IVisitor<T> visitor ){
		for( T listener : listeners ){
			synchronized( listener ){
				try{
					visitor.visit( listener );
				} catch ( Throwable e ){
					e.printStackTrace();
				}
			}
		}
	}
	
	public static <T> void notifyListeners( Iterable<T> listeners, final IVisitor<T> visitor, Executor executor ){
		for( final T listener : listeners ){
			executor.execute( new Runnable(){
				@Override
				public void run(){
					synchronized( listener ){
						try{
							visitor.visit(listener);
						} catch ( Throwable e ){
							e.printStackTrace();
						}
					}
				}
			});
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
