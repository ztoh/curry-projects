package com.chnye.yese.framework.task;

import java.util.concurrent.TimeUnit;

public class TaskResult<T>{
		
	  private Throwable throwable;
	  private  T result;
	  private Long timeout;
	  private TimeUnit timeunit;

	  public TaskResult( final Throwable e ){ this.throwable = e; }
	  public TaskResult( final T result ){  this.result = result; }
	  public TaskResult(final long timeout, final TimeUnit timeunit ){
	    this.timeout = timeout;
	    this.timeunit = timeunit;
	  }
	  
	  public static <T> TaskResult<T> error( final Throwable e ){
	    return new TaskResult<T>(e);
	  }
	  public static <T> TaskResult<T> ok( final T result ){
	    return new TaskResult<T>( result );
	  }
	  public static <T> TaskResult<T> timeout( final long timeout, final TimeUnit timeunit ){
	    return new TaskResult<T>(timeout, timeunit);
	  }

	  public boolean isError(){  return throwable != null; }
	  public boolean isOk(){  return !isError()  && !isTimeout(); }
	  public boolean isTimeout(){ return timeout != null; }
	  public T getResult(){ return result; }
	  public Throwable getThrowable(){  return throwable; }
	  public Long getTimeout(){ return timeout; }
	  public TimeUnit getTimeunit(){  return timeunit; }

	
}
