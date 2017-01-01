package com.chnye.common.converter.impl;

import com.chnye.common.converter.ConversionException;
import com.chnye.common.converter.IConverter;

public class NumberToDoubleConverter implements IConverter<Number, Double>{

	@Override
	public Double convert(Number source) throws ConversionException {
		return source.doubleValue();
	}

	@Override
	public Class<?> getSourceType() {
		return Number.class;
	}

	@Override
	public Class<?> getTargetType() {
		// TODO Auto-generated method stub
		return Double.class;
	}

}
