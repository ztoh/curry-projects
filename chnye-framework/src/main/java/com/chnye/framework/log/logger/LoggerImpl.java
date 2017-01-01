package com.chnye.framework.log.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerImpl implements ILogger{

	private final org.slf4j.Logger  logger;
	
	public LoggerImpl( org.slf4j.Logger logger ){
		this.logger = logger;
	}
	
	public static ILogger getLogger( Class<?> clazz ){
		org.slf4j.Logger logger = LoggerFactory.getLogger(clazz);
		return new LoggerImpl(logger);
	}
	
	/**
	 * 返回以调用者的类命名的Logger
	 */
	public static ILogger getClassLogger(){
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement element = stackTrace[2];
		String name = element.getClassName();
		return new LoggerImpl( LoggerFactory.getLogger(name) );
	}

	@Override
	public boolean isDebugEnabled() {
		// TODO Auto-generated method stub
		return this.logger.isDebugEnabled();
	}

	@Override
	public boolean isInfoEnabled() {
		// TODO Auto-generated method stub
		return this.logger.isInfoEnabled();
	}

	@Override
	public boolean isWarnEnabled() {
		// TODO Auto-generated method stub
		return this.logger.isWarnEnabled();
	}

	@Override
	public boolean isErrorEnabled() {
		// TODO Auto-generated method stub
		return this.logger.isErrorEnabled();
	}

	@Override
	public void debug(String format, Object... args) {
		// TODO Auto-generated method stub
		if( this.logger.isDebugEnabled() ){
			String message = String.format(format, args);
			this.logger.debug( message );
		}
	}

	@Override
	public void debug(Throwable t, String format, Object... args) {
		// TODO Auto-generated method stub
		if( this.logger.isDebugEnabled() ){
			String message = String.format(format, args);
			this.logger.debug( message, t );
		}
	}

	@Override
	public void info(String format, Object... args) {
		// TODO Auto-generated method stub
		if( this.logger.isInfoEnabled() ){
			String message = String.format(format, args);
			this.logger.info( message );
		}
	}

	@Override
	public void info(Throwable t, String format, Object... args) {
		// TODO Auto-generated method stub
		if( this.logger.isInfoEnabled() ){
			String message = String.format(format, args);
			this.logger.info( message, t );
		}
	}

	@Override
	public void warn(String format, Object... args) {
		// TODO Auto-generated method stub
		if( this.logger.isWarnEnabled() ){
			String message = String.format(format, args);
			this.logger.warn( message );
		}
	}

	@Override
	public void warn(Throwable t, String format, Object... args) {
		// TODO Auto-generated method stub
		if( this.logger.isWarnEnabled() ){
			String message = String.format(format, args);
			this.logger.warn( message, t );
		}
	}

	@Override
	public void error(String format, Object... args) {
		// TODO Auto-generated method stub
		if( this.logger.isErrorEnabled() ){
			String message = String.format(format, args);
			this.logger.error( message );
		}
	}

	@Override
	public void error(Throwable t, String format, Object... args) {
		// TODO Auto-generated method stub
		if( this.logger.isErrorEnabled() ){
			String message = String.format(format, args);
			this.logger.error( message, t );
		}
	}
	
}
