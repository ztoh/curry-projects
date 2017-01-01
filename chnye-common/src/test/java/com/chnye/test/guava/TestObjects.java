package com.chnye.test.guava;

import org.junit.Test;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;

public class TestObjects {

	private static class User implements Comparable<User>{
		String name;
		int age;
		String comments;
		
		public User( String name, int age ){
			this( name, age, null );
		}
		
		public User( String name, int age, String comments ){
			this.name = name;
			this.age = age;
			this.comments = comments;
		}
		
		public String toString(){
			/*
			 * Objects升级为MoreObjects 
			 */
			return MoreObjects.toStringHelper(this)
				.add("name", this.name )
				.add( "age", this.age )
				.add( "comments", this.comments )
				.toString();
		}

		/**
		 * 使用ComparisonChain类来优雅的实现对象之间的比较
		 */
		@Override
		public int compareTo(User other) {
			// TODO Auto-generated method stub
			return ComparisonChain.start()
					.compare( this.name, other.name )
					.compare( this.age, other.age )
					.compare( this.comments, other.comments )
					.result();
		}
	}//end class
	
	
	private void assertObjects(){
		System.out.println( "----------assertObjects----------");
		User curUser = new User( "张三", 43, "汉口市里的" );
		
		//测试 toString方法
		System.out.println( curUser ); 
		
		/**  firstNonNull
		 *   传入两个参数，返回不为空的那个，如果都不为空，则返回第一个参数
		 */
		System.out.println( MoreObjects.firstNonNull( null, "abc" ) );		//abc
		System.out.println( MoreObjects.firstNonNull( "123", "456" ) );		//123
		System.out.println( MoreObjects.firstNonNull( "", "789" ) );		//""
//		System.out.println( MoreObjects.firstNonNull( null, null) );		//NullPointerException
	
		//测试CompareTo方法
		User otherUser = new User( "张三", 43, "汉阳乡下的" );
		System.out.println( "curUser compareTo otherUser:  " + curUser.compareTo( otherUser ) );
		otherUser.comments = "汉口市里的";
		System.out.println( "curUser compareTo otherUser:  " + curUser.compareTo( otherUser ) );
		
	}
	
	@Test 
	public void testObjects(){
		assertObjects();
	}
	
}
