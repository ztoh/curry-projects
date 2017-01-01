package com.chnye.common.dispatch;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;


public class QueueWorkerRunnable<T> implements Runnable{

	private BlockingQueue<T> dataQueue;
	private IDispatch<T,?>  dispatcher;
	
	public QueueWorkerRunnable( BlockingQueue<T> dataQueue, IDispatch<T,?> dispatcher ){
		this.dataQueue = dataQueue;
		this.dispatcher = dispatcher;
	}
	
	@Override
	public void run(){
		while( !Thread.currentThread().isInterrupted() ){
            try{
                consume( dataQueue );
            } catch( Throwable t ){
                t.printStackTrace();
            }
        }//end while
	}
	
	private void consume(BlockingQueue<T> dataQueue ) throws Throwable{
		T node = dataQueue.poll( 100, TimeUnit.MICROSECONDS );
		if( node == null ){
			return;
		}
		dispatcher.dispatch( node );
	}

}