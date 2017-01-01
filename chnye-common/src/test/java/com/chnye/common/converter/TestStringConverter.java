package com.chnye.common.converter;

import org.junit.Before;
import org.junit.Test;

import com.chnye.common.converter.impl.StringToIntegerConverter;
import com.chnye.common.converter.impl.StringToLongConverter;

public class TestStringConverter {

	@Before
	public void init(){
		
	}
	
	private void assertStringConverter(){
		StringToIntegerConverter convertToInteger = new StringToIntegerConverter();
		StringToLongConverter convertToLong = new StringToLongConverter();
		
		String testStr1 = "122345"; 
		Object obj = testStr1;
		Integer iInt = convertToInteger.convert( obj );
		System.out.println( "int:" + iInt );
	}
	
	@Test
	public void testStringConverter(){
		assertStringConverter();
	}
}
