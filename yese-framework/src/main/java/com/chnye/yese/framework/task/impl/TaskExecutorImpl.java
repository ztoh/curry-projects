package com.chnye.yese.framework.task.impl;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

import com.chnye.yese.common.threadpool.ThreadPoolDefinition;
import com.chnye.yese.common.threadpool.ThreadPoolExecutorFactory;
import com.chnye.yese.framework.task.ITask;
import com.chnye.yese.framework.task.ITaskExecutor;
import com.chnye.yese.framework.task.TaskWithException;
import com.chnye.yese.framework.task.VoidTask;

public class TaskExecutorImpl implements ITaskExecutor{

	private final AtomicBoolean bRunning = new AtomicBoolean();
	private ExecutorService executorService;
	
	
	@Override
	public boolean isRunning() {
		// TODO Auto-generated method stub
		return bRunning.get();
	}
	@Override
	public void start() {
		// TODO Auto-generated method stub
		if( bRunning.compareAndSet( false, true) ){
			if( executorService == null ){
				ThreadPoolDefinition def = ThreadPoolDefinition.getDefault();
				executorService = ThreadPoolExecutorFactory.getInstance().createExecutorService( def );
			}
		}
	}
	@Override
	public void stopImmediately() {
		// TODO Auto-generated method stub
		if( bRunning.compareAndSet( true, false) ){
			List<Runnable> cancelledTasks = executorService.shutdownNow();
			for( Runnable runnable : cancelledTasks ){
				if( runnable instanceof RunnableFuture<?> ){
					( (RunnableFuture<?>)runnable ).cancel( true );
				}
			}//end for 
			executorService = null;
			
		}
	}
	@Override
	public void stop() {
		// TODO Auto-generated method stub
		if( bRunning.compareAndSet( true, false) ){
			executorService.shutdown();
			executorService = null;
		}
	}
	
	@Override
	public void run(VoidTask task) throws TaskException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public <R, E extends Exception> R run(TaskWithException<R, E> task)
			throws TaskException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> T run(ITask<T> task) throws TaskException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> Future<T> sumit(ITask<T> task) throws TaskException {
		// TODO Auto-generated method stub
		Future<T> future = executorService.submit( new TaskCallableWrapper(task) );
		return future;
	}
	
	
	
}
