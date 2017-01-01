package com.chnye.yese.framework.lifecycle;

import java.util.LinkedList;
import java.util.List;

import com.chnye.yese.common.lifecycle.LifecycleException;

public abstract class AbstractCompositeLifeCycleSupport extends AbstractLifecycleSupport {

	private List<ILifecycle> lifecycles;
	
	protected abstract List<ILifecycle> loadLifecycles() throws LifecycleException;
	
	@Override
	public void start() throws LifecycleException{
		this.lifecycles = loadLifecycles();
		for( ILifecycle lifecycle : lifecycles ){
			lifecycle.start();
		}
		super.start();
	}
	
	@Override
	public void stop() throws LifecycleException{
		for( ILifecycle lifecycle : reverseLifecycles() ){
			try{
				lifecycle.stop();
			} catch ( Throwable t ){
				
			}
		}
		super.stop();
	}
	
	private List<ILifecycle> reverseLifecycles(){
		LinkedList<ILifecycle> reversed = new LinkedList<ILifecycle>();
		for( ILifecycle lifecycle : this.lifecycles ){
			reversed.addFirst( lifecycle );
		}
		return reversed;
	}
	
}
