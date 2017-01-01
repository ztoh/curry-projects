package com.chnye.common.thread.pool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.ClassUtils;



public class ThreadPoolExecutorFactory implements IExecutorServiceFactory{

	public  ExecutorService createExecutorService( ThreadPoolDefinition config ){
		if( config == null ){
			return null;
		}
		
		RejectedExecutionHandler rejectedHandler = null;
		try{
			if( config.getRejectedPolicy() != null ){
//				rejectedHandler = (RejectedExecutionHandler)ClassUtils.forName( config.getRejectedPolicy(),  Thread.currentThread().getContextClassLoader() );
				rejectedHandler = (RejectedExecutionHandler)ClassUtils.getClass( Thread.currentThread().getContextClassLoader(), config.getRejectedPolicy() ).newInstance();
			}
		} catch( Throwable t){
			t.printStackTrace();
		}
		BlockingQueue workQueue = new LinkedBlockingQueue<>( config.getQueueSize() );
		ExecutorService executor = null;
		if( rejectedHandler != null ){
			executor = new ThreadPoolExecutor( config.getMinSize(), config.getMaxSize(), config.getKeepAliveTime(), TimeUnit.SECONDS, workQueue, new NamedThreadFactory(null), rejectedHandler );
		} else {
			executor = new ThreadPoolExecutor( config.getMinSize(), config.getMaxSize(), config.getKeepAliveTime(), TimeUnit.SECONDS, workQueue, new NamedThreadFactory(null) );
		}
		return executor;
	}
}
