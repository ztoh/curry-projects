package com.chnye.common.lifecycle;

public class LifecycleException extends Exception {
	
	protected String message = null;
	protected Throwable throwable = null;
	
	public LifecycleException(){
		this( null, null );
	}
	public LifecycleException( String message ){
		this( message, null );
	}
	public LifecycleException( Throwable throwable ){
		this( null, throwable);
	}
	public LifecycleException( String message, Throwable throwable ){
		super( message, throwable );
		this.message = message;
		this.throwable = throwable;
	}
	
	public String getMessage(){
		return this.message;
	}
	
	public Throwable getThrowable(){
		return this.throwable;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer("LifecycleException:");
		if( this.message != null ){
			sb.append( message );
			if( this.throwable != null ){
				sb.append( " : ");
			}
		}
		if( this.throwable != null ){
			sb.append( throwable.toString() );
		}
		return sb.toString();
	}
}
