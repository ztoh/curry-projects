package com.chnye.common.converter;

public interface IConverter<S,T> {

	T convert( S source ) throws ConversionException;
	
	Class<?> getSourceType();
	Class<?> getTargetType();
	
}
