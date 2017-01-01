package com.chnye.framework.script.jexl;

import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.MapContext;
import org.junit.Before;
import org.junit.Test;

import com.chnye.common.script.jexl.JexlUtil;
import com.chnye.common.script.jexl.JexlUtil.VarCallback;
import com.chnye.framework.context.xml.impl.W3cXmlContext;


public class TestJexlEngine {

	public static W3cXmlContext context = null;
	
	public static String expressionStr = "${Root/PRIVATE/AGE}+${Root/PRIVATE/TYPE}";
	@Before
	public void init(){
		context = new W3cXmlContext("Root");
		context.setValueAt("Root/PRIVATE/NAME", "张晖" );
		context.setValueAt("Root/PRIVATE/AGE", "32" );
		context.setValueAt("Root/PRIVATE/MOBILE", "17720580910");
		context.setValueAt("Root/PRIVATE/TYPE", "5");
		
		System.out.println( "context:  " + context.toString() );
	}
	
	/**
	 * 模拟实际场景
	 * @throws Exception 
	 */
	private void assertJexlEngine() throws Exception{
		System.out.println("-----------assertJexlEngine----------");
		Expression expr = JexlUtil.createExpression(expressionStr);
//		JexlContext jexlContext = new MapContext();
		
		//计算变量值的回调函数
//		VarCallback varCallback = new VarCallback(){
//			@Override
//			public Object getValue(String var) {
//				// TODO Auto-generated method stub
//				System.out.println( "var: " + var );
//				Object result =  context.getValueAt( var );
//				System.out.println( "result:" + result );
//				return result;
//			}
//		};
		//初始化JexlContext
//		JexlUtil.initJexlContext(expressionStr, jexlContext, varCallback );
		JexlContextWrapper jexlContext = new JexlContextWrapper( context );
		Object result = JexlUtil.evaluateExpression(jexlContext, expr );
		
		System.out.println( "result: " + result );
	}
	
	@Test
	public void testJexlEngine(){
		try {
			assertJexlEngine();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
