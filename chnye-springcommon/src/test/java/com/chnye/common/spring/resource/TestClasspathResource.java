package com.chnye.common.spring.resource;

import java.io.IOException;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

public class TestClasspathResource {

	private static final String PACKAGE_PATH = "com/chnye/common/spring";
	private static final String RESOURCE_NAME = "example01.xml";
	private static final String NOEXIST_RESOURCE_NAME = "example100.xml";
	
	private static final String RESOURCE_PATH = PACKAGE_PATH + "/" + RESOURCE_NAME;
	private static final String NOEXIST_RESOURCE_PATH = PACKAGE_PATH + "/" + NOEXIST_RESOURCE_NAME;
	
//	private void assertReaderResource( ClasspathResource resource ){
//		
//	}
	
	
	private void assertReaderNoExists( ClassPathResource resource ){
		try {
			resource.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testReadNoExistsFile(){
		assertReaderNoExists( new ClassPathResource( NOEXIST_RESOURCE_PATH ) );
	}
	
	
}
