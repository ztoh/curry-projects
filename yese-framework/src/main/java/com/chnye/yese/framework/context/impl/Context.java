package com.chnye.yese.framework.context.impl;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.chnye.yese.framework.context.IContext;
import com.chnye.yese.framework.propertycontainer.impl.PropertyContainer;

public class Context extends PropertyContainer implements IContext{
	
	private String name ;
	private String id;
	
	public Context(){		
		this( "context" );
	}
	
	public Context( String name ){
//		this( name, Collections.synchronizedMap( new HashMap() ) );
		this( name, new ConcurrentHashMap<String,Object>() );
	}
	
	public Context( String name, Map<String,Object> map ){
		super( map );
		this.name = name;
//		this.id = name + "#" + UUIDUtil.getUUID();
	}
	


	@Override
	public String getName() {
		// TODO Auto-generated method stub
		
		return name;
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public void setValueAt(String key, Object value) {
		// TODO Auto-generated method stub
		setProperty( key, value );
	}

	@Override
	public Object getValueAt(String key) {
		// TODO Auto-generated method stub
		return getProperty( key );
	}

	@Override
	public Map<String, Object> getDatas() {
		// TODO Auto-generated method stub
		return getProperties();
	}


	
}
