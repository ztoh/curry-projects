package com.chnye.common.propertycontainer.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.chnye.common.propertycontainer.AbstractPropertyContainer;
import com.chnye.common.propertycontainer.IPropertyContainer;
import com.chnye.common.propertycontainer.IPropertyListener;





public class PropertyContainer extends AbstractPropertyContainer{

	protected Map<String, Object> data = null;

	/*
	 * 工厂方法
	 */
	public static IPropertyContainer newInstance(){
		return new PropertyContainer();
	}
	
	/*
	 * 工厂方法
	 */
	public static IPropertyContainer newInstance( Map<String, Object> map ){
		return new PropertyContainer( map );
	}
	
	public PropertyContainer(){
		this( new HashMap<String,Object>(), null);
	}
	
	public PropertyContainer( Map<String, Object> map ){
		this( map, null );
	}
	
	public PropertyContainer( Map<String, Object> map, Iterable<IPropertyListener> listeners ){
		super( listeners );
		this.data = map;
	}
	
	@Override
	public void doSetProperty(String property, Object value) {
		// TODO Auto-generated method stub
		this.data.put( property, value );
	}

	@Override
	public Object getProperty(String property) {
		// TODO Auto-generated method stub
		return this.data.get( property );
	}


	@Override
	public boolean hasProperty(String property) {
		// TODO Auto-generated method stub
		return this.data.containsKey( property );
	}

//	@Override
//	public Set<String> keySet() {
//		// TODO Auto-generated method stub
//		Set<String> keys = new HashSet<String>();
//		for( String key : this.data.keySet() ){
//			keys.add( key );
//		}
//		return keys;
//	}

	
//	@Override
//	public Set<Map.Entry<String, Object>> entrySet() {
//		// TODO Auto-generated method stub
//		Set<Map.Entry<String, Object>> sets = new HashSet<Map.Entry<String, Object>>();
//		for( Map.Entry<String, Object>  entry : this.data.entrySet() ){
//			sets.add( entry );
//		}
//		return sets;
//	}

	@Override
	public Map<String,Object> getProperties(){
		return this.data;
	}
	

}
