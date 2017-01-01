package com.chnye.biz;

import org.junit.Test;

import com.chnye.common.xml.jaxb.definitions.ListMapDefinitions;
import com.chnye.common.xml.jaxb.definitions.ListMapDefinitions.MapDefinition;
import com.chnye.framework.config.IPropertiesHolder;
import com.chnye.framework.config.PropertiesManager;
import com.chnye.framework.config.property.PropertiesHolder;
import com.chnye.framework.config.xml.JaxbPropertiesHolder;

public class TestImportData {

	/**
	 * 测试加载规则数据
	 * @throws Exception 
	 */
	private void assertImportData() throws Exception{
		String path = ".\\bin\\com\\chnye\\biz\\conf\\importdata_rule1.xml";
		
		JaxbPropertiesHolder holder = JaxbPropertiesHolder.createPropertiesHolder( path, path );
		holder.loadProperties();
		PropertiesManager.getInstance().addProperties( holder.getKey(), holder );
		
		//读取配置文件
		ListMapDefinitions listMapDefs = (ListMapDefinitions)holder.getProperties();
		System.out.println( "listMapDefs: " + listMapDefs );
		MapDefinition beginDef =  listMapDefs.getMapDefinitionByKey( ImportDataConstants.NODE_START );
//		System.out.println( "beginDef: " + beginDef );
		
		MapDefinition curMapDef = beginDef;
		while( curMapDef != null ){
			
			//执行当前节点任务
			if( !curMapDef.getKey().equals( ImportDataConstants.NODE_START ) && !curMapDef.getKey().equals( ImportDataConstants.NODE_END ) ){
				String tableRuleSrc = curMapDef.getString("src");
				System.out.println( "tableRuleSrc: " + tableRuleSrc );
				
				JaxbPropertiesHolder tableRuleHolder = JaxbPropertiesHolder.createPropertiesHolder( tableRuleSrc, tableRuleSrc );
				tableRuleHolder.loadProperties();
				PropertiesManager.getInstance().addProperties( tableRuleHolder.getKey(), tableRuleHolder );
				ListMapDefinitions tableRules = (ListMapDefinitions)tableRuleHolder.getProperties();
				System.out.println( "tableRules: " + tableRules );
				
			}
			
			//进入下一个节点
			if( curMapDef.getKey().equals( ImportDataConstants.NODE_END ) ){
				curMapDef = null;
			} else {
				String nextKey = curMapDef.getString( ImportDataConstants.NODE_NEXT );
				if( nextKey != null ){
					curMapDef = listMapDefs.getMapDefinitionByKey( nextKey );
				}
			} 
		}
		
		
	}
	
	
	@Test
	public void TestImportData(){
		try {
			assertImportData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
