package com.chnye.common.collection;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import com.chnye.common.base.BaseObject;

public class TestListenersHelper {

	public static Iterable<User> iterable= null; 
	
	@Before
	public void init(){
		iterable = ListSupport.newLinkedList();
		User user = new User("U1");
		iterable = ListSupport.addListener(user, iterable );
	}
	
	private void assertListenersHelper(){
		User user = new User("U2");
		
		//往集合中添加数据
		//错误代码: 所增加的会丢失
//		ListenersHelper.addListener(user, iterable );
		//正确代码
		iterable = ListSupport.addListener(user, iterable );
		
		Iterator<User> iter = iterable.iterator();
		while( iter.hasNext() ){
			System.out.println( "iter: " + iter.next() );
		}
	}
	
	@Test
	public void testListenersHelper(){
		assertListenersHelper();
	}
	
	
	public static class User extends BaseObject{
		private String name;

		public User( String name ){
			this.name = name;
		}
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
	}
	
}
