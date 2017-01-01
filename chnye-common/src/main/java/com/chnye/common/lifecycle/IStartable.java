package com.chnye.common.lifecycle;

public interface IStartable {
	
	/**
	 * 
	 */
	public void start() throws LifecycleException;
	
	/**
	 * 
	 */
	public void reStart() throws LifecycleException;
	
	/**
	 * 
	 */
	public boolean isStart();
	
}
