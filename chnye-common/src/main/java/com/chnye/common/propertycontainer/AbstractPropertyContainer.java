package com.chnye.common.propertycontainer;

import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.chnye.common.able.IListenable;
import com.chnye.common.collection.ListSupport;


public abstract class AbstractPropertyContainer implements IPropertyContainer,IListenable<IPropertyListener>{

	private Iterable<IPropertyListener>  listeners = null;
	
	public AbstractPropertyContainer(){
		listeners = ListSupport.newLinkedList();
	}
	
	public AbstractPropertyContainer( Iterable<IPropertyListener> listeners ){
		this.listeners = listeners;
	}
	
	@Override
	public void addListener( IPropertyListener listener ){
		if( listener != null &&  listeners != null ){
			listeners = ListSupport.addListener( listener, listeners );
		}
	}
	
	@Override
	public void removeListener( IPropertyListener listener ){
		if( listener != null &&  listeners != null ){
			listeners = ListSupport.removeListener( listener, listeners );
		}
	}
	
	@Override
	public void clearListener(){
		Iterator<IPropertyListener> iter = listeners.iterator();
		while( iter.hasNext() ){
			iter.remove();
		}//end while 
	}
	
	public void fireBeforePropertyChange( String property, Object newValue ){
		if( listeners == null ){
			return;
		}
		Object oldValue = getProperty( property );
		for( IPropertyListener listener : listeners ){
			listener.beforePropertyChange(property, oldValue, newValue);
		}
	}
	
	public void onPropertyChange( String property ){
		if( listeners == null ){
			return;
		}
		for( IPropertyListener listener : listeners ){
			listener.onPropertyChange(property);
		}
	}
	
	public final void setProperty( String property, Object value ){
		fireBeforePropertyChange( property, value );
		doSetProperty( property, value );
		onPropertyChange( property );
	}
	
	public abstract void doSetProperty( String property, Object value );
	public abstract Object getProperty( String property );
	
	@Override
	public boolean hasProperty( String property ){
		return getProperty( property ) != null ;
	}
	
	public abstract Map<String,Object> getProperties();
	
	@Override
	public String getString( String property ){
		Object value = getProperty( property );
		if( null == value ){
			return null;
		}
		return value.toString().trim();
	}
	
	@Override
	public String getString( String property, String defaultValue ){
		String value = getString( property );
		if( value == null ){
			return defaultValue;
		}
		return value.trim();
	}
	
	
	@Override
	public boolean getBoolean( String property ){
		String value = getString( property );
		return Boolean.parseBoolean(value);
		
	}
	
	@Override
	public boolean getBoolean( String property, boolean defaultValue ){
		String value = getString( property );
		if( StringUtils.isNotBlank(value) ){
			return Boolean.parseBoolean(value);
		}
		return defaultValue;
	}
	
	@Override
	public int getInt( String property ){ 
		String value = getString( property );
		return Integer.parseInt( value );
	}
	
	@Override
	public int getInt( String property, int defaultValue ){
		int reInt = defaultValue;
		try{
			return getInt( property );
		} catch ( NumberFormatException ex ){
			return reInt;
		}
	}
	
	@Override
	public long getLong( String property ){ 
		String value = getString( property );
		return Long.parseLong( value );
	}
	
	@Override
	public long getLong( String property, long defaultValue ){
		long reLong = defaultValue;
		try{
			return getLong( property );
		} catch ( NumberFormatException ex ){
			return reLong;
		}
	}
	
	
}
