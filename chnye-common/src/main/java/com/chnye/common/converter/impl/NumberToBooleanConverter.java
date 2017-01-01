package com.chnye.common.converter.impl;

import com.chnye.common.converter.ConversionException;
import com.chnye.common.converter.IConverter;

public class NumberToBooleanConverter implements IConverter<Number, Boolean>{

	@Override
	public Boolean convert(Number source) throws ConversionException {
		return source.intValue() != 0 ;
	}

	@Override
	public Class<?> getSourceType() {
		return Number.class;
	}

	@Override
	public Class<?> getTargetType() {
		return Boolean.class;
	}

}
