package com.chnye.common.script.ognl;

//http://www.cnblogs.com/yw-ah/p/5760192.html

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.junit.Test;

import com.chnye.common.script.ognl.OgnlUtil;
import com.google.common.collect.Maps;


public class TestOgnl {

	public static final String DEFAULT_CONFIG = "env.properties";
	
	public static String getInfo(){
		return "${" + DEFAULT_CONFIG + "}";
	}
	
	/** 测试Ongl对静态类的使用
	 * 表达式格式     @[class]@[field/method()]
	 */
	private void assertOgnl(){
		System.out.println( "----------assertOgnl----------" );
		
		
		//取类的静态变量
		Object result = OgnlUtil.getValueQuietly("@com.chnye.common.utils.ognl.TestOgnl@DEFAULT_CONFIG", null, null );
		System.out.println( "取类的静态变量: " + result );
		
		//调用类的静态方法
		Object result2 = OgnlUtil.getValueQuietly("@com.chnye.common.utils.ognl.TestOgnl@getInfo()", null, null );
		System.out.println( "调用类的静态方法: " + result2 );
				
		Object result3 = OgnlUtil.getValueQuietly("@java.lang.String@format('var is [%s]','XX')", null, null );
		System.out.println( "调用类的静态方法: " + result3 );
		
	}
	
	/**
	 * 测试Ognl对，对象的使用
	 */
	private void assertOgnl2(){
		System.out.println( "----------assertOgnl2----------" );
		User user = new User();
		Map<String, Object> ognlContext = new HashMap<String,Object>();
		ognlContext.put("name", "xxxa213");
		ognlContext.put("password", "2323dree");
		
		System.out.println("调用对象方法,getName() " +  OgnlUtil.getValueQuietly("getName()", ognlContext, user ) );
		OgnlUtil.getValueQuietly("setName(#name)", ognlContext, user );
		System.out.println("调用对象方法,getName() " +  OgnlUtil.getValueQuietly("getName()", ognlContext, user ) );
	}
	
	/**
	 * 对数组和集合的访问
	 */
	private void assertOgnl3(){
		System.out.println( "----------assertOgnl3----------" );
		
		Map<String, Object> ognlContext = new HashMap<String,Object>();
		ognlContext.put("name", "xxxa213");
		ognlContext.put("password", "2323dree");
		
		String[] strArrays = {"aa1", "bb2","cc3"};
		
		ArrayList<String> list = new ArrayList<>();
		list.add( "list1" );
		list.add( "list2" );
		list.add( "list3" );
		
		Map<String, String> map = Maps.newHashMap();
		map.put("key1", "value1" );
		map.put("key2", "value2" );
		map.put("key3", "value3" );
		
		//将数组或集合加入Context
		ognlContext.put("TEST_ARRAY", strArrays );
		ognlContext.put("TEST_LIST", list );
		ognlContext.put("TEST_MAP", map );
		
		//Context中， 使用#XXX 访问字符串数组
		System.out.println( OgnlUtil.getValueQuietly("#TEST_ARRAY[0]", ognlContext, null) );
		
		System.out.println( OgnlUtil.getValueQuietly("#TEST_LIST[0]", ognlContext, null) );
		System.out.println( OgnlUtil.getValueQuietly("#TEST_LIST[0+1]", ognlContext, null) );
		System.out.println( OgnlUtil.getValueQuietly("#TEST_LIST[0+2]", ognlContext, null) );
		
		System.out.println( OgnlUtil.getValueQuietly("#TEST_MAP['key1']", ognlContext, null) );
		System.out.println( OgnlUtil.getValueQuietly("#TEST_MAP['key' + '2']", ognlContext, null) );
	
	}
	
	/**
	 * 创建对象
	 */
	private void assertOgnl4(){
		System.out.println( "----------assertOgnl4----------" );
		
		//构造Map,  使用#{}
		Map<String, String> map = (Map<String, String>)OgnlUtil.getValueQuietly("#{'key1':'value1','key2':'value2'}", null, null );
		if( map != null ){
			for( Map.Entry<String, String> entry : map.entrySet() ){
				System.out.println( "\t" + entry.getKey() + " = " + entry.getValue() );
			}
		}
		
		//构造list， 使用{}
		List<String> list = (List<String>)OgnlUtil.getValueQuietly("{'key1','value1','key2'}", null, null);
		if( list != null ){
			int i = 0;
			for( String item : list ){
				System.out.println( "\tlist[" + i + "] = " + item );
				i++;
			}
		}
		
		//直接构造对象
		Object obj = OgnlUtil.getValueQuietly("new java.lang.Object()", null, null );
		System.out.println( "object: " + obj );
		
		
	}
	
	@Test
	public void testOgnl(){
		assertOgnl();
		
		assertOgnl2();
		
		assertOgnl3();
		
		assertOgnl4();
		
	}
	
	
	
	public static class User{
		private String name;
		private String password;
		
		public User(){
		}
		
		public User( String name, String password ){
			this.name = name;
			this.password = password;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
		
	}//end class User
	
}
