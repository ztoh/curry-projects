package com.chnye.test.apachelang3;

import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.Test;

public class TestEscape {

	private void assertEscape(){
		//
		System.out.println( "EcmaScript:" + StringEscapeUtils.escapeEcmaScript("Root/PRIVATE/NAME") );
		
		System.out.println( "HTML4:" + StringEscapeUtils.escapeHtml4("Root/PRIVATE/NAME") );
		
		System.out.println( "JAVA:" + StringEscapeUtils.escapeJava("Root/PRIVATE/NAME国产") );
		
		System.out.println( "JSON:" + StringEscapeUtils.escapeJson("Root/PRIVATE/NAME") );
		
		System.out.println( "XML10:" + StringEscapeUtils.escapeXml10("<Root/PRIVATE/NAME>") );
		
		System.out.println( "XML11:" + StringEscapeUtils.escapeXml11("<Root/PRIVATE/NAME>") );
		
	}
	
	@Test
	public void testEscape(){
		assertEscape();
	}
}
