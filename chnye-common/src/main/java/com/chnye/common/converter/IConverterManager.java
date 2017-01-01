package com.chnye.common.converter;

//http://www.grepcode.com/file/repo1.maven.org/maven2/de.intarsys.opensource/isrt/4.10/de/intarsys/tools/converter/IConverterRegistry.java?av=f

public interface IConverterManager {

	<T> T convert( Object source, Class<T> targetType ) throws ConversionException;
	
	IConverter getConverter( Class targetType );
	
	
	void registerConverter( IConverter converter );
	void unregisterConverter( IConverter converter );
	
}
