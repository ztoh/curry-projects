package com.chnye.common.script.jexl;

//http://www.tools138.com/create/article/20150312/020008993.html

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;




public class TestJexl {

	public static JexlEngine jexlEngine;
	public static JexlContext jexlContext;
	
	@Before
	public void init(){
		jexlEngine = new JexlEngine();
		jexlContext = new MapContext();
	}
	
	private void assertJexl(){
		System.out.println( "----------assertJexl----------");
		//Pojo对象
		User user = new User("zhangsan", 33, "WuhanBoy", "IT manager");
		//字符串数组
		String[] strArrays = {"a1","b2","c3","d4"};
		//List
//		List<String>  list = Lists.newArrayList("l1","l2","l3");
		List<String>  list = ImmutableList.of( "l1", "l2", "l3" );
		//Map
//		Map<String, String> map = Maps.newHashMap();
		Map<String, String> map = ImmutableMap.of("k1", "v1", "k2", "v2", "k3", "v3", "k4", "v4", "k5", "v5" );
		
		jexlContext.set("TEST_USER", user );
		jexlContext.set("TEST_ARRAYS", strArrays );
		jexlContext.set("TEST_LIST", list );
		jexlContext.set("TEST_MAP", map );
		jexlContext.set("PRICE@$@A", "2" );
		jexlContext.set("NUMBER", "3" );
		
		//表达式
		List<String> jexlExprs = ImmutableList.of("TEST_USER.name", "TEST_USER.title",
				"size(TEST_LIST)", "TEST_LIST[0].length", "TEST_LIST[1].toUpperCase()",
				"TEST_MAP['k1']", "PRICE@$@A + '+' + NUMBER ");
		for( String jexlExpr : jexlExprs ){
			//建立表达式
			Expression expression = jexlEngine.createExpression( jexlExpr );
			//传入上下文,并执行
			Object obj = expression.evaluate( jexlContext );
			System.out.println( expression.getExpression() + " = " + obj );
		}

	}
	
	/** http://stackoverflow.com/questions/13336761/does-anyone-have-any-simple-jexl-examples-using-a-loop-i-am-looking-to-iterate
	 * 执行一段脚本
	 */
	public void assertJexl2(){
		System.out.println( "----------assertJexl2----------");
		List<String> list = ImmutableList.of("Lone", "Ltwo", "Lthree" );
		
		JexlContext jexlContext = new MapContext();
		jexlContext.set("list", list );
		
		Map<String,Object> functions = new HashMap<String,Object>();
		functions.put("system", System.out );
		
		//往引擎中注入函数
		jexlEngine.setFunctions( functions );
		
		Expression expression = jexlEngine.createExpression("for( item : list ){ system:println(item) }");
		expression.evaluate( jexlContext );
		
	}
	
	
	
	@Test
	public void testJexl(){
		assertJexl();
		
		assertJexl2();
	}
	
	
	public static class User{
		private String name;
		private int age;
		private String title;
		private String comments;
		
		public User( String name, int age, String title, String comments ){
			this.name = name;
			this.age = age;
			this.title = title;
			this.comments = comments;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getComments() {
			return comments;
		}

		public void setComments(String comments) {
			this.comments = comments;
		}
		
		
	}
	
}
