package com.chnye.framework.context;

import org.junit.Before;
import org.junit.Test;

import com.chnye.framework.config.IPropertiesHolder;
import com.chnye.framework.config.LifecyclePropertiesHolder;
import com.chnye.framework.config.PropertiesManager;
import com.chnye.framework.config.property.PropertiesHolder;


public class TestPropertiesManager {

	@Before
	public void init() throws Exception{
		String[] locations = new String[]{".\\bin\\com\\chnye\\framework\\context\\property\\yese.properties"};
		PropertiesHolder propHolder = PropertiesHolder.createPropertiesHolder("test1", locations);
		propHolder.loadProperties();
		
		String[] locations2 = new String[]{".\\bin\\com\\chnye\\framework\\context\\property\\yese2.properties"};
		PropertiesHolder propHolder2 = PropertiesHolder.createPropertiesHolder("test2", locations2);
		propHolder2.loadProperties();
		LifecyclePropertiesHolder lifecyclePropHolder2 = LifecyclePropertiesHolder.createLifeCyclePropertiesHolder( propHolder2 );
		
		
		PropertiesManager.getInstance().addProperties("test1", propHolder );
		PropertiesManager.getInstance().addProperties("test2", lifecyclePropHolder2 );
		
		
	}
	
	private void assertPropertiesManager(){
		IPropertiesHolder holder = PropertiesManager.getInstance().getProperties("test2");
		System.out.println( "xxx:" + holder.getProperty("FTP_IP") );
	}
	
	@Test
	public void testPropertiesManager(){
		assertPropertiesManager();
	}
}
