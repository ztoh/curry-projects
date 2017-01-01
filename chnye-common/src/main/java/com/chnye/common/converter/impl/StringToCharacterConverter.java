package com.chnye.common.converter.impl;

import com.chnye.common.converter.ConversionException;
import com.chnye.common.converter.IConverter;

public class StringToCharacterConverter implements IConverter<String, Character>{

	@Override
	public Character convert(String source) throws ConversionException {
		if( source.length() >= 1 ){
			return source.charAt( 0 );
		}
		return ' ';
	}

	@Override
	public Class<?> getSourceType() {
		return String.class;
	}

	@Override
	public Class<?> getTargetType() {
		return Character.class;
	}

}
