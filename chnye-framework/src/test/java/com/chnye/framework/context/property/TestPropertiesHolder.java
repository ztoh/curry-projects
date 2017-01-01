package com.chnye.framework.context.property;

import org.junit.Test;

import com.chnye.framework.config.property.PropertiesHolder;

public class TestPropertiesHolder {

	
	
	private void assertPropertiesHolder() throws Exception{
		System.out.println( "----------assertPropertiesHolder----------" );
		String[] locations = new String[]{".\\bin\\com\\chnye\\framework\\context\\property\\yese.properties"};
		PropertiesHolder propHolder = PropertiesHolder.createPropertiesHolder("test", locations);
		propHolder.loadProperties();
		System.out.println( propHolder.getProperty("FTP_IP") );
		
	}
	
	@Test
	public void testPropertiesHolder(){
		try {
			assertPropertiesHolder();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
