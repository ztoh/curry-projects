package com.chnye.common.converter.impl;

import com.chnye.common.converter.ConversionException;
import com.chnye.common.converter.IConverter;

public class StringToIntegerConverter implements IConverter<String, Integer>{

	@Override
	public Integer convert(String source) throws ConversionException {
		try{
			return (int)(Double.parseDouble( source ) );
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
		return Integer.class;
	}

}
