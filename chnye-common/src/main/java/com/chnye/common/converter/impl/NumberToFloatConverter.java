package com.chnye.common.converter.impl;

import com.chnye.common.converter.ConversionException;
import com.chnye.common.converter.IConverter;

public class NumberToFloatConverter implements IConverter<Number, Float>{

	@Override
	public Float convert(Number source) throws ConversionException {
		return source.floatValue();
	}

	@Override
	public Class<?> getSourceType() {
		return Number.class;
	}

	@Override
	public Class<?> getTargetType() {
		return Float.class;
	}

}
