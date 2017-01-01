package com.chnye.yese.framework.task;

import java.util.concurrent.Future;

import ch.qos.logback.core.status.ErrorStatus;

import com.chnye.yese.framework.task.TaskEvent.ETaskStatus;

public final class TaskRunnable<T> implements Runnable{

	private final ITask<T> task;
	private final ITaskListener<T> listener;
	
	private T result;
	private Future<?> future;
	
	private Throwable error;
	
	private boolean bCancelled = false;
	
	
	public TaskRunnable( ITask<T> task, ITaskListener<T> listener ){
		this.task = task;
		this.listener = listener;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		innerBegin();
		try{
			if( task != null ){
				result = task.execute();
			}
			innerComplete();
		} catch ( Throwable t ){
			error = t;
			innerError();
		}
	}

	private boolean isCancelled(){
		return bCancelled || isInterrupted();
	}
	
	private boolean isInterrupted(){
		return Thread.currentThread().isInterrupted();
	}
	
	public boolean cancel(){
		bCancelled = true;
		boolean result = false;
		if( future != null ){
			result = future.cancel( true );
		}
		return result;
	}
	
	private void innerBegin(){
		if( listener != null ){
			listener.notify( new TaskEvent<ETaskStatus>( task, ETaskStatus.ON_BEGIN ) );
		}
	}

	private void innerComplete(){
		if( listener != null ){
			TaskEvent<ETaskStatus> event = new TaskEvent<ETaskStatus>( task, ETaskStatus.ON_COMPLETE );
			TaskResult<T> taskResult = TaskResult.ok(result);
			event.setResult( taskResult );
			listener.notify( event );
		}
	}
	
	private void innerError(){
		if( listener != null ){
			TaskEvent<ETaskStatus> event = new TaskEvent<ETaskStatus>( task, ETaskStatus.ON_ERROR );
			TaskResult<T> taskResult = TaskResult.error( error );
			event.setResult( taskResult );
			listener.notify( event );
		}
	}
	
	public ITask<T> getTask(){		return this.task;	}
	public ITaskListener<T> getListener(){		return this.listener;	}
}
