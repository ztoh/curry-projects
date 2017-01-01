package com.chnye.framework.lifecycle;

import java.util.EventObject;
import com.chnye.common.enums.IType;
import com.chnye.framework.lifecycle.ILifecycle.LifeCycleStatus;

public class LifecycleEvent extends EventObject implements IType<LifeCycleStatus>{
	
	private Object data = null;
	private LifeCycleStatus type = null;
	
	public LifecycleEvent( LifeCycleStatus type ){
		this( type, null);
	}
	public LifecycleEvent( LifeCycleStatus type,  Object obj ){
		super( obj );
		this.type = type;
	}
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	@Override
	public LifeCycleStatus getType() {
		return type;
	}

	
	
}
