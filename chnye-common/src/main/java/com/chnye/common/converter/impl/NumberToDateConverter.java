package com.chnye.common.converter.impl;

import java.sql.Date;

import com.chnye.common.converter.ConversionException;
import com.chnye.common.converter.IConverter;

public class NumberToDateConverter implements IConverter<Number, Date>{

	@Override
	public Date convert(Number source) throws ConversionException {
		return new Date( source.longValue() );
	}

	@Override
	public Class<?> getSourceType() {
		return Number.class;
	}

	@Override
	public Class<?> getTargetType() {
		return Date.class;
	}

}
