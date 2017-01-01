package com.chnye.framework.common.exception;

public class FrameworkRuntimeException extends RuntimeException{
	private int code;
	
	public FrameworkRuntimeException(){
		
	}
	
	public FrameworkRuntimeException( int code ){
		this.code = code;
	}
	
	public FrameworkRuntimeException( int code, String msg ){
		super( msg );
		this.code = code;
	}
	
	public FrameworkRuntimeException( String msg, Throwable cause ){
		super( msg, cause );
}
	
	public FrameworkRuntimeException( String msg ){
		super( msg );
	}
	
	public FrameworkRuntimeException( Throwable cause ){
		super( cause );
	}
	
}
