package com.chnye.common.converter.impl;

import com.chnye.common.converter.ConversionException;
import com.chnye.common.converter.IConverter;

public class BooleanToFloatConverter implements IConverter<Boolean, Float>{

	@Override
	public Float convert(Boolean source) throws ConversionException {
		return source ? 1f : 0f;
	}

	@Override
	public Class<?> getSourceType() {
		return Boolean.class;
	}

	@Override
	public Class<?> getTargetType() {
		return Float.class;
	}

}
