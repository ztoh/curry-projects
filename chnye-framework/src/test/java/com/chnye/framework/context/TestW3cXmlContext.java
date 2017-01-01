package com.chnye.framework.context;

import org.junit.Before;
import org.junit.Test;

import com.chnye.framework.context.xml.impl.W3cXmlContext;

public class TestW3cXmlContext {

	private W3cXmlContext context = null;
	
	@Before
	public void init(){
		context = new W3cXmlContext( "Cosp" );
	}
	
	/**
	 * xpath中数组下标是从1开始的
	 */
	private void assertW3cXmlContext(){
		System.out.println( "----------assertW3cXmlContext----------" );
		//设置值
		context.setValueAt("Cosp/PUBLIC/RETCODE", "99" );
		context.setValueAt("Cosp/PUBLIC/RETMSG", "建档案错误");
		context.setValueAt("Cosp/PUBLIC/SeqNo", "323545646" );
		context.setValueAt("Cosp/PRIVATE/Trade[1]/HostRetCode", "435" );
		context.setValueAt("Cosp/PRIVATE/Trade[1]/HostRetMsg", "appid is not registered" );
		context.setValueAt("Cosp/PRIVATE/Trade[2]/IAccNo", "34023023ABE" );
		
		System.out.println( "context:" + context.toString() );
		
		
		//取值
//		String retCode = (String)context.getValueAt("PUBLIC/)
		
		
	}
	
	@Test
	public void testW3cXmlContext(){
		assertW3cXmlContext();
	}
	
}
