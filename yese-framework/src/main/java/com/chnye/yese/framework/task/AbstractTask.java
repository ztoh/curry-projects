package com.chnye.yese.framework.task;


import java.util.concurrent.Callable;


public abstract class AbstractTask<T> implements ITask<T>{


	@Override
	public Callable<T> getCallable() {
		// TODO Auto-generated method stub
		return new MyCallable<T>( this );
	}


	private static class MyCallable<T> implements Callable<T>{
		private ITask<T> task;
		
		public MyCallable( ITask<T> task ){
			this.task = task;
		}
		
		@Override
		public T call() throws Exception {
			// TODO Auto-generated method stub
			return task.execute();
		}
		
	}
}
