package com.chnye.framework.lifecycle;

import java.util.LinkedList;
import java.util.List;

import com.chnye.common.lifecycle.LifecycleException;

public abstract class AbstractCompositeLifecycleSupport extends AbstractLifecycleSupport {

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
