package com.chnye.framework.pipeline.impl;

import java.util.Map;

import com.chnye.framework.context.impl.Context;

import com.chnye.framework.pipeline.IChannel;
import com.chnye.framework.pipeline.exception.PipelineException;

public class Channel extends Context implements IChannel{

	public Channel(){
		super( "Channel" );
	}


	public Channel( String name, Map<String,Object> map ){
		super( name, map );
	}
	
	@Override
	public <T> T get(String key, Class<T> type) throws PipelineException {
		Object obj = getProperty( key );
		if( obj == null ){
			throw new PipelineException( "channel not contain " + key );
		}
		if( type == null ){
			throw new PipelineException( "type cannot be null" );
		}
		Class<? extends Object> clazz = obj.getClass();
		if( !type.isAssignableFrom( clazz ) ){
			throw new PipelineException("type[" + clazz + "] cannot cast to type[" + type + "]");
		}
		return type.cast( obj );
	}
	
}
