package com.chnye.common.lifecycle;

public interface IDestroyable {

	/**
	 * 
	 */
	public void lifecycleDestroy() throws LifecycleException;
	
	/**
	 * 
	 */
	public void lifecycleClear() throws LifecycleException;
}
