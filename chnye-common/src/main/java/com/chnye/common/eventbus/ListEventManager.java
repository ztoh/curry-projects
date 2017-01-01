package com.chnye.common.eventbus;

import java.util.concurrent.Executor;

import com.chnye.common.collection.ListSupport;
import com.chnye.common.visitor.IVisitor;

public class ListEventManager {

	
	protected Iterable<IEventListener<?>> listeners = ListSupport.newLinkedList();
	
	
	private Executor executor;
	
	public ListEventManager(){
		this( null );
	}
	
	public ListEventManager( Executor executor ){
		setExecutor( executor );
	}
	
	public void setExecutor( Executor executor ){
		this.executor = executor;
	}
	
	public void addListener(IEventListener<?> listener) {
		listeners = ListSupport.addListener( listener, listeners );
	}
	
	public void removeListener(IEventListener<?> listener) {
		listeners = ListSupport.removeListener( listener, listeners );
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void notifyListeners( final Object event ){
		IVisitor<IEventListener<?>> visitor = new IVisitor<IEventListener<?>>(){
			@Override
			public boolean visit(IEventListener listener) {
				listener.onEvent( event );
				return true;
			}
		};
		
		ListSupport.notifyListeners(listeners, visitor );
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void notifyAsyncListeners(  final Object event ){
		IVisitor<IEventListener<?>> visitor = new IVisitor<IEventListener<?>>(){
			@Override
			public boolean visit(IEventListener listener) {
				listener.onEvent( event );
				return true;
			}
		};
		if( this.executor != null ){
			ListSupport.notifyListeners( listeners, visitor, executor);
		} else {
			ListSupport.notifyListeners(listeners, visitor );
		}
	}
	
}
