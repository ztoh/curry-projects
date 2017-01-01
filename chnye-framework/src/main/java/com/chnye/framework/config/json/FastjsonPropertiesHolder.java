package com.chnye.framework.config.json;

import java.util.List;
import java.util.Map;

import com.chnye.common.json.fastjson.FastjsonHelper;
import com.chnye.framework.config.AbstractPropertiesHolder;


public class FastjsonPropertiesHolder extends AbstractPropertiesHolder{

	private Object root;
	
	public static FastjsonPropertiesHolder createPropertiesHolder( String name ){
		return new FastjsonPropertiesHolder( name );
	}
	public static FastjsonPropertiesHolder createPropertiesHolder( String name, String...locations ){
		return new FastjsonPropertiesHolder( name, locations );
	}
	
	public FastjsonPropertiesHolder( String name ){
		this( name, null );
	}
	
	public FastjsonPropertiesHolder(String name, String[] locations ){
		super.setName(name);
		super.setLocations(locations);
	}
	
	@Override
	public void loadProperties() throws Exception{
		this.root = FastjsonHelper.fromFilePath( locations[0] );
	}
	
	@Override
	public Object getProperties(){
		return this.root;
	}

	
	@Override
	public String getProperty( String key ){
		String value = FastjsonHelper.getString( this.root, key );
		if( value  == null ){
			return null;
		}
		return value;
	}	
	
	@Override
	public void clear(){
		if( this.root instanceof Map ){
			((Map<String,Object>)root).clear();
		} else if( this.root instanceof List ){
			((List<Object>)root).clear();
		} else{
			this.root = null;
		}
	}
}
