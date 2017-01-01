package com.chnye.common.utils;

//http://grepcode.com/file/repo1.maven.org/maven2/com.nimworks/nw.commons/1.1/nw/commons/StopWatch.java?av=f

public class StopWatch {
	private long startTime;
	private long total;
	boolean running = false;
	
	public StopWatch(){
		this( true );
	}
	
	public StopWatch( boolean bStart ){
		if( bStart ){
			start();
		}
	}
	
	public long elapseTime(){
		if( running ){
			return this.total + System.currentTimeMillis() - this.startTime;
		}
		return this.total;
	}
	
	public long currentElapsedTime(){
		if( running ){
			return System.currentTimeMillis() - this.startTime;
		}
		return 0L;
	}
	
	public void zero(){
		this.total = 0L;
		start();
	}
	
	public void start(){
		this.startTime = System.currentTimeMillis();
		running  = true;
	}
	
	public void stop(){
		if( running ){
			this.total += System.currentTimeMillis() - this.startTime;
			running = false;
		}
	}
	
	public void mark(){
		stop();
		start();
	}
	
	public String elapsedTimeToMessage( String message ){
		return message + " in " + elapseTime() + " ms.";
	}
	
	public String currentElapsedTimeToMessage( String message ){
		return message + " in " + currentElapsedTime() + " ms.";
	}
	
	public String toString(){
		return super.toString() + "[running=" + running + ", startTime=" + startTime + ", total=" + total + "]";
	}
}
