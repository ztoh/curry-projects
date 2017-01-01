package com.chnye.common.lifecycle;

public interface IStopable {

	/**
	 * 
	 */
	public void stop() throws LifecycleException;
	
	/**
	 * 
	 */
	boolean isStop();
}
