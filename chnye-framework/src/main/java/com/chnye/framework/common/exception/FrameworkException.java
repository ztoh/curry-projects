package com.chnye.framework.common.exception;

//https://github.com/ww20081120/framework/blob/master/framework-common/src/main/java/com/fccfc/framework/common/FrameworkException.java


public class FrameworkException extends Exception{
	private int code;
	
	public FrameworkException( int code ){
		this.code = code;
	}
	
	public FrameworkException( int code, String msg ){
		super( msg );
		this.code = code;
	}
	
	public FrameworkException( int code, String msg, Object... params ){
		this( code, String.format(msg, params) );
	}
	
	public FrameworkException( int code, Throwable t ){
		super( t );
		this.code = code;
	}
	
	public FrameworkException( String msg, FrameworkException exp ){
		this( exp.code,  msg, exp );
	}
	
	public FrameworkException( int code, String msg, Throwable t ){
		super( msg, t );
		this.code = code;
	}
	
	public FrameworkException( String msg, FrameworkException exp, Object... params ){
		this( exp.code, String.format( msg, params ), exp );
	}
	
	public FrameworkException( int code, String msg, Throwable t, Object... params ){
		this( code, String.format( msg, params ), t );
	}
	
	public int getCode(){
		return code;
	}
}
