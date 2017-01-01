package com.chnye.framework.lifecycle;

import java.util.List;


public interface ILifecycle extends com.chnye.common.lifecycle.ILifecycle{
//	public static final String EVENT_START = "start";
//	public static final String EVENT_STOP = "stop";

	public enum LifeCycleStatus{
		INIT, REINIT,
		START, RESTART,
		STOP, DESTORY
	}
	
	public void addLifeCycleListener( ILifecycleListener listener );
	public void removeLifeCycleListener( ILifecycleListener listener );
	public Iterable<ILifecycleListener> getLifeCycleListeners();
}
