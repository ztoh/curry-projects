package com.chnye.common.lifecycle;

public interface Initilize {

	/**
	 * 
	 */
	public void init() throws LifecycleException;
	
	
	/**
	 * 
	 */
	public void reInit() throws LifecycleException;
	
	/**
	 * 
	 */
	public boolean isInit();
}
