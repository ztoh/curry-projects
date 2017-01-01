package com.chnye.framework.log.logger;

public interface ILogger {

	boolean isDebugEnabled();
	boolean isInfoEnabled();
	boolean isWarnEnabled();
	boolean isErrorEnabled();
	
	void debug( String format, Object... args );
	void debug( Throwable t, String format, Object... args );

	void info( String format, Object... args );
	void info( Throwable t, String format, Object... args );

	void warn( String format, Object... args );
	void warn( Throwable t, String format, Object... args );

	void error( String format, Object... args );
	void error( Throwable t, String format, Object... args );

	
}
