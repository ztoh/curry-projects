package com.chnye.common.converter.impl;

import com.chnye.common.converter.ConversionException;
import com.chnye.common.converter.IConverter;

public class StringToFloatConverter implements IConverter<String, Float>{

	@Override
	public Float convert(String source) throws ConversionException {
		try{
			return (float)(Double.parseDouble( source ) );
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
		return Float.class;
	}

}
