package com.chnye.framework.config.xml;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.chnye.common.xml.jaxb.definitions.ListMapDefinitions;
import com.chnye.common.xml.jaxb.definitions.ListMapDefinitions.MapDefinition;
import com.chnye.framework.config.IPropertiesHolder;
import com.chnye.framework.config.LifecyclePropertiesHolder;
import com.chnye.framework.config.PropertiesManager;

public class TestJaxbPropertiesHolder {

	
	@Before
	public void init() throws Exception{
		String location = ".\\bin\\com\\chnye\\framework\\config\\xml\\config_rule2.xml";
		JaxbPropertiesHolder holder = JaxbPropertiesHolder.createPropertiesHolder("My Jaxb01", location );
		holder.loadProperties();
		LifecyclePropertiesHolder lifecycleHolder = LifecyclePropertiesHolder.createLifeCyclePropertiesHolder(holder);
		PropertiesManager.getInstance().addProperties( holder.getKey(), lifecycleHolder );
	}
	
	private void assertJaxbPropertiesHolder(){
		IPropertiesHolder holder = PropertiesManager.getInstance().getProperties( "tablerule02" );
		if( holder != null ){
			//遍历并读取规则
			
			//数据库规则
			ListMapDefinitions listMapDefs = (ListMapDefinitions)holder.getProperties();
			List<MapDefinition> mapDefs = listMapDefs.getMapDefinitionsByType( "database" );
			if( mapDefs.size() == 0 ){
				throw new RuntimeException("lost data exception!");
			}
			MapDefinition mapDef = mapDefs.get(0);
			String defaultValue = mapDef.getString("defaultValue" );
			System.out.println( "defaultValue: " + defaultValue );
			
			//字段规则
			mapDefs = listMapDefs.getMapDefinitionsByType( "field" );
			if( mapDefs.size() == 0 ){
				throw new RuntimeException("lost data exception!");
			}
			for( MapDefinition tmpMapDef : mapDefs ){
				String classStr = tmpMapDef.getString("class");
				System.out.println( "class: " + classStr );
			}
		}
	}
	
	@Test
	public void testJaxbPropertiesHolder(){
		assertJaxbPropertiesHolder();
	}
}
