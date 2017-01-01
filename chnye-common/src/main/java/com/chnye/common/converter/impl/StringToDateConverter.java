package com.chnye.common.converter.impl;

import java.util.Date;

import com.chnye.common.converter.ConversionException;
import com.chnye.common.converter.IConverter;

public class StringToDateConverter implements IConverter<String, Date>{

	@Override
	public Date convert(String source) throws ConversionException {
		try{
			return new Date( (long)(Double.parseDouble( source )) );
		} catch (Exception e ){
			throw new ConversionException( e );
		}
	}

	@Override
	public Class<?> getSourceType() {
		return String.class;
	}

	@Override
	public Class<?> getTargetType() {
		return Date.class;
	}

}
