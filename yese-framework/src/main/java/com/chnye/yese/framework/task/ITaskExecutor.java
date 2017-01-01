package com.chnye.yese.framework.task;

import java.util.concurrent.Future;

public interface ITaskExecutor {
	boolean isRunning();
	void start();
	void stopImmediately();
	void stop();
	
	void run( VoidTask task ) throws TaskException;
	
	<R, E extends Exception> R run( TaskWithException<R,E> task ) throws TaskException;
	
	<E extends Exception> void ( VoidTaskWithException<E> task ) throws TaskExceptn;
	
	<T> T run( ITask<T> task ) throws TaskException;
	
	<T> Future<T> sumit( ITask<T> task ) throws TaskException;
	
}
