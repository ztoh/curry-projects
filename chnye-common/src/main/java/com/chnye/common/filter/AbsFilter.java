package com.chnye.common.filter;

import java.util.HashSet;
import java.util.Set;

import com.chnye.common.able.IEnable;
import com.chnye.common.able.IListenable;

public abstract class AbsFilter<T> implements IFilter<T>, IEnable, IListenable<IFilterListener> {

	private Set<IFilterListener> listeners = new HashSet<IFilterListener>();

	private boolean enabled = true;
	
	@Override
	public boolean isEnabled(){
		return this.enabled;
	}
	
	@Override
	public void setEnabled( boolean enable ){
		if( enable != this.enabled ){
			this.enabled = enable;
			for( IFilterListener listener : listeners ){
				listener.filterUpdated( this );
			}
		}
	}
	
	@Override
	public void addListener( IFilterListener listener ){
		listeners.add( listener );
	}
	
	@Override
	public void removeListener( IFilterListener listener ){
		listeners.remove( listener );
	}
	
	
}
