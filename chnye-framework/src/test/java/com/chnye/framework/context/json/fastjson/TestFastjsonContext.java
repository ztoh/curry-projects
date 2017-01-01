package com.chnye.framework.context.json.fastjson;

import org.junit.Before;
import org.junit.Test;

import com.chnye.framework.context.json.fastjson.impl.FastjsonContext;

public class TestFastjsonContext {

	FastjsonContext context = null;
	
	@Before
	public void init(){
		context = FastjsonContext.create("MY_JSON_CONTEXT_1" );
	}
	
	private void assertFastjsonContext(){
		System.out.println( "----------assertFastjsonContext----------" );
		context.setValueAt("name", "Jordan" );
		context.setValueAt("title", "NBA boss" );
		context.setValueAt("Team.name", "黄蜂队");
//		context.setValueAt("Team.content", "林书豪,福尔福斯,杰克");
		/*
		 *这种场景下,Team路径被改写，下面的数据生效的话，则上面的路径便失效，所以会造成上面的数据丢失。 
		 * 结论是：尽量少用或不用数组形式。
		 */
		context.setValueAt("Team[1].content", "林书豪,福尔福斯,杰克");
		
		System.out.println( context.toString() );
	}

	@Test
	public void testFastjsonContext(){
		assertFastjsonContext();
	}

}
