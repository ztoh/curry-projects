package com.chnye.common.converter.impl;

import com.chnye.common.converter.ConversionException;
import com.chnye.common.converter.IConverter;

public class StringToBooleanConvterter implements IConverter<String, Boolean>{

	@Override
	public Boolean convert(String source) throws ConversionException {
		source = source.toLowerCase().trim();
		if( source.equals("false") || source.equals("no") || source.equals("0") ){
			return false;
		}
		if( source.equals("true") || source.equals("yes") || source.equals("1") ){
			return true;
		}
		return	Boolean.valueOf( source );

	}

	@Override
	public Class<?> getSourceType() {
		return String.class;
	}

	@Override
	public Class<?> getTargetType() {
		return Boolean.class;
	}

}
