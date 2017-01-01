package com.chnye.framework.lifecycle;

import com.chnye.common.lifecycle.LifecycleException;

public interface ILifecycleListener {
	
	public void handleEvent( LifecycleEvent event ) throws LifecycleException;
}
