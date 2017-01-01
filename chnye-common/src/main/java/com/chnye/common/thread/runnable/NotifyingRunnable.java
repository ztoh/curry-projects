package com.chnye.common.thread.runnable;

public abstract class NotifyingRunnable implements Runnable{
	private final IRunnableListener listener;
	
	public NotifyingRunnable( final IRunnableListener listener ){
		this.listener = listener;
		listener.runnableRegistered( this );
	}
	
	@Override
	public void run(){
		listener.runnableStarted( this );
		innerRun();
		listener.runnableDone( this );
	}
	
	public abstract void innerRun();
	public abstract void cancel();
	
	public static interface IRunnableListener{
		void runnableRegistered( NotifyingRunnable runnable );
		void runnableStarted( NotifyingRunnable runnable );
		void runnableDone( NotifyingRunnable runnable );
	}
}