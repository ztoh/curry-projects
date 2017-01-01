package com.chnye.common.converter.impl;

import com.chnye.common.converter.ConversionException;
import com.chnye.common.converter.IConverter;

public class BooleanToIntegerConverter implements IConverter<Boolean, Integer>{

	@Override
	public Integer convert(Boolean source) throws ConversionException {
		return source ? 1 : 0;
	}

	@Override
	public Class<?> getSourceType() {
		return Boolean.class;
	}

	@Override
	public Class<?> getTargetType() {
		return Integer.class;
	}

}
