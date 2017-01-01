package com.chnye.common.converter.impl;

import java.util.Iterator;
import java.util.Map;

import com.chnye.common.collection.MapSupport;
import com.chnye.common.converter.ConversionException;
import com.chnye.common.converter.IConverter;
import com.chnye.common.converter.IConverterManager;

public class DefaultConverterManager implements IConverterManager{

	private 	Map<Class, Map<Class, IConverter>> converters ;
	
	private boolean initialized = false;
	
	@Override
	public Object convert(Object source, Class targetType) throws ConversionException {
		if( source == null ){
			return source;
		}
		if( targetType == Object.class ){
			return source;
		}
		if( targetType.isInstance( source ) ){
			return source;
		}
		IConverter converter = getConverter( targetType );
		if( converter == null ){
			throw new ConversionException("can't convert " + source.getClass().getName() + " to " + targetType );
		}
		return converter.convert( source );
	}

	@Override
	public IConverter getConverter(Class targetType) {
		// TODO Auto-generated method stub
		return converters.get( targetType );
	}

	@Override
	public void registerConverter(IConverter converter) {
		// TODO Auto-generated method stub
		converters.put( converter.getTargetType(), converter );
	}

	@Override
	public void unregisterConverter(IConverter converter) {
		// TODO Auto-generated method stub
		converters.remove( converter.getTargetType() )
	}

	protected void init(){
		if( initialized ){
			return;
		}
		initialized = true;
		
	}
	
	

}
