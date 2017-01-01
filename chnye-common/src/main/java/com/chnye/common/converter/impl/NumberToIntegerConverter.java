package com.chnye.common.converter.impl;

import com.chnye.common.converter.ConversionException;
import com.chnye.common.converter.IConverter;

public class NumberToIntegerConverter implements IConverter<Number, Integer>{

	@Override
	public Integer convert(Number source) throws ConversionException {
		return source.intValue();
	}

	@Override
	public Class<?> getSourceType() {
		return Number.class;
	}

	@Override
	public Class<?> getTargetType() {
		return Integer.class;
	}

}
