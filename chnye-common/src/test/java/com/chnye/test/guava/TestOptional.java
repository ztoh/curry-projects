package com.chnye.test.guava;

import org.junit.Test;

import com.google.common.base.MoreObjects;
import com.google.common.base.Optional;

public class TestOptional {

	private static class User{
		String name;
		String title;
		public User( String name, String title ){
			this.name = name;
			this.title = title;
		}
		public String toString(){
			return MoreObjects.toStringHelper(this)
				.add("name", this.name)
				.add("title", this.title)
				.toString();
		}
	}//end class 
	
	/**
	 * 用Optional对象代替User对象后，总是会返回一个Optional对象。
	 *    否则函数返回类型为User， 则有可能为null(null只是一个关键字，用来标识不确定的对象，它不是对象也不是Object对象的实例)。
	 * @param name
	 * @return
	 */
	private Optional<User> getUserByName( String name ){
		if( name != null && name.length() > 0 ){
			return Optional.of( new User( "张三", "大王叫我来巡山") );
		}
		return Optional.absent();
	}
	
	private Optional<User> getUserByTitle( String title ){
		User reUser = null;
		if( title != null && title.length() > 0 ){
			reUser = new User( "张三", "大王叫我来巡山");
		}
		return Optional.fromNullable( reUser );
	}
	
	private void assertOptional(){
		User curUser = null;
//		Optional<User> userOptional = Optional.of( curUser );
		
		Optional<User> userOptional = getUserByName( null );
		System.out.println( "userOptional.isPresent():" + userOptional.isPresent() );
		System.out.println( "userOptional:" + userOptional );
		userOptional = getUserByName( "xxx" );
		System.out.println( "userOptional.isPresent():" + userOptional.isPresent() );
		System.out.println( "userOptional:" + userOptional );
		System.out.println( "userOptional:" + userOptional.get() );
	}
	
	
	@Test
	public void testOptional(){
		assertOptional();
	}
}
