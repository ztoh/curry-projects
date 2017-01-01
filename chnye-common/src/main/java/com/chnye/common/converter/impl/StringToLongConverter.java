package com.chnye.common.converter.impl;

import com.chnye.common.converter.ConversionException;
import com.chnye.common.converter.IConverter;

public class StringToLongConverter implements IConverter<String, Long>{

	@Override
	public Long convert(String source) throws ConversionException {
		try{
			return Long.parseLong( source );
		} catch ( Exception e ){
			throw new ConversionException( e );
		}
	}

	@Override
	public Class<?> getSourceType() {
		return String.class;
	}

	@Override
	public Class<?> getTargetType() {
		return Long.class;
	}

}
