package com.chnye.framework.lifecycle;


import java.util.List;

import com.chnye.common.collection.ListSupport;
import com.chnye.common.lifecycle.LifecycleException;

public abstract class AbstractLifecycleSupport implements ILifecycle, ILifecycleListener{
	
	private Iterable<ILifecycleListener> listeners;
	protected Object data;
	
	protected boolean bInited = false;
	protected boolean bStarted = false;
	

	@SuppressWarnings("unchecked")
	@Override
	public void addLifeCycleListener( ILifecycleListener listener ){
		if( this.listeners == null ){
			this.listeners = ListSupport.newLinkedList();
		}
		listeners = ListSupport.addListener(listener, listeners);
	}
	
	@Override
	public void removeLifeCycleListener( ILifecycleListener listener ){
		if( this.listeners != null ){
			listeners = ListSupport.removeListener(listener, listeners);
		}
	}
	
	@Override
	public Iterable<ILifecycleListener> getLifeCycleListeners(){
		return listeners;
	}
	
	/**
	 * 用于通知所有的监听器
	 */
	public void fireEvent( LifeCycleStatus type, Object data ) throws LifecycleException{
		if( this.listeners == null ){
			return;
		}
		LifecycleEvent event = new LifecycleEvent( type, this  );
		event.setData( data );
		for( ILifecycleListener listener : listeners ){
			listener.handleEvent( event );
		}
	}
	
	/**
	 * 用于自身的启动，停止
	 */
	@Override
	public void handleEvent(LifecycleEvent event) throws LifecycleException {
		this.data = event.getData();
		if( event.getType().equals( LifeCycleStatus.START ) ){
			start();
		} else if( event.getType().equals( LifeCycleStatus.STOP ) ){
			stop();
		}
	}

	@Override
	public void init() throws LifecycleException {
		// TODO Auto-generated method stub
		setInited( true );
	}

	@Override
	public void reInit() throws LifecycleException {
		// TODO Auto-generated method stub
		setInited( true );
	}

	@Override
	public boolean isInit() {
		// TODO Auto-generated method stub
		return this.bInited;
	}

	@Override
	public void start() throws LifecycleException {
		// TODO Auto-generated method stub
		setStarted( true );
	}

	@Override
	public void reStart()	throws LifecycleException {
		// TODO Auto-generated method stub
		setStarted( true );
	}

	@Override
	public boolean isStart() {
		// TODO Auto-generated method stub
		return this.bStarted;
	}

	public abstract void run() ;

	@Override
	public void stop() throws LifecycleException {
		// TODO Auto-generated method stub
		setStarted( false );
	}

	@Override
	public boolean isStop() {
		// TODO Auto-generated method stub
		return !this.bStarted;
	}

	
	private void setInited( boolean bInited ){
		this.bInited = bInited;
	}
	private void setStarted( boolean bStarted ){
		this.bStarted = bStarted;
	}

	public abstract void lifecycleDestroy() throws LifecycleException ;

	
	public abstract void lifecycleClear() throws LifecycleException ;


	
}
