package com.chnye.common.converter.impl;

import com.chnye.common.converter.ConversionException;
import com.chnye.common.converter.IConverter;

public class StringToDoubleConverter implements IConverter<String, Double>{

	@Override
	public Double convert(String source) throws ConversionException {
		try{
			return Double.parseDouble( source );
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
		return Double.class;
	}

}
