package com.chnye.framework.config.json;

import org.junit.Before;
import org.junit.Test;

import com.chnye.framework.config.json.FastjsonPropertiesHolder;

public class TestFastjsonPropertiesHolder {

	FastjsonPropertiesHolder holder = null;
	
	@Before
	public void init() throws Exception{
		String fileName = "bin\\com\\chnye\\framework\\config\\example01.json";
		holder = FastjsonPropertiesHolder.createPropertiesHolder("my jsonfast properties", fileName );
		holder.loadProperties();
		System.out.println( holder.getProperties() );
	}
	
	private void assertFastjsonPropertiesHolder(){
		System.out.println( "----------assertFastjsonPropertiesHolder----------" );
//		String value = holder.getProperty("avString");
		String value = holder.getProperty("avBar[0].barName");
		System.out.println( "value:" + value );
	}
	
	@Test
	public void testFastjsonPropertiesHolder(){
		assertFastjsonPropertiesHolder();
	}
	
}
