package com.chnye.common.dispatch;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;


public class DefaultAsyncDispatch<I,O> implements IDispatch<I,O>{

	private LinkedBlockingQueue<I> dataQueue;
	private ExecutorService executor;
	private int WORKER_COUNT = 5;
	private QueueWorkerRunnable<I>[]  queueWorkers;
	
	private IDispatch<I,O> syncDispatcher;
	
	
	public DefaultAsyncDispatch( IDispatch<I,O> syncDispatcher ){
		this.syncDispatcher = syncDispatcher;
	}
	
	public void init(){
		this.dataQueue = new LinkedBlockingQueue<I>();
		
		this.executor = Executors.newFixedThreadPool( WORKER_COUNT );
		
		queueWorkers = new QueueWorkerRunnable[ WORKER_COUNT ];
		for( int i = 0; i < WORKER_COUNT; i++ ){
			queueWorkers[i] = new QueueWorkerRunnable<I>( dataQueue, syncDispatcher );
			executor.submit( queueWorkers[i] );
		}	
	}
	
	@Override
	public O dispatch( I input ){
		dataQueue.add( input );
		return null;
	}
	
}