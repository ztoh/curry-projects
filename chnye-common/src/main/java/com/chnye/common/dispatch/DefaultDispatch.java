package com.chnye.common.dispatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.chnye.common.handler.IAcceptHandler;


public class DefaultDispatch<I,O> implements IDispatch<I,O>{

	private List<IAcceptHandler<I,O>>  handlers = new ArrayList<IAcceptHandler<I,O>>();
	
	private ReadWriteLock  lock = new ReentrantReadWriteLock();
	
	public O dispatch( I input ){
		for( IAcceptHandler<I,O> handler : handlers ){
			if( handler.accept( input ) ){
				return handler.handle( input );
			}
		}
		return null;
	}

	public void addAcceptHandler( IAcceptHandler<I,O> handler ){
		lock.writeLock().lock();
		try{
			if( !handlers.contains( handler ) ){
				handlers.add( handler );
			}
		} finally {
			lock.writeLock().unlock();
		}
	}
	
	public void removeAcceptHandler( IAcceptHandler<I,O> handler ){
		lock.writeLock().lock();
		try{
			if( handlers.contains( handler ) ){
				handlers.remove( handler );
			}
		} finally {
			lock.writeLock().unlock();
		}
	}
}