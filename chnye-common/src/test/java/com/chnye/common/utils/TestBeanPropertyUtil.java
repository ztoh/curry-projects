package com.chnye.common.utils;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.chnye.common.reflection.BeanUtil;

public class TestBeanPropertyUtil {

	public static User user = null;
	
	@Before
	public void init(){
		user = new User("zs", 13, new Date() );
		List<Book> books = new ArrayList<Book>();
		for( int i = 0; i < 3; i++ ){
			Book book = new Book("book" + i , i *11 );
			books.add( book );
		}
		user.setBooks( books );
	}
	
	private void assertBeanPropertyUtil(){
	  PropertyDescriptor propertyDescriptor = BeanUtil.descriptor( User.class, "name" );
	  System.out.println( "propertyDescriptor: " + propertyDescriptor );
	
	  PropertyDescriptor propertyDescriptor3 = BeanUtil.descriptor( User.class, "books" );
	  System.out.println( "propertyDescriptor3: " + propertyDescriptor3 );
	  
	  Class<?> clazz = BeanUtil.getClass( User.class, "books" );
	  System.out.println( "clazz: " + clazz );
	  
	}
	
	@Test
	public void testBeanPropertyUtil(){
		assertBeanPropertyUtil();
	}
	
	
	public static class User{
		private String name;
		private int age;
		private Date loginDate;
		private List<Book> books;
		
		public User( String name, int age, Date loginDate ){
			this.name = name;
			this.age = age;
			this.loginDate = loginDate;
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
		public Date getLoginDate() {
			return loginDate;
		}
		public void setLoginDate(Date loginDate) {
			this.loginDate = loginDate;
		}
		
		public void setBooks( List<Book> books ){
			this.books = books;
		}
		
		
		
	}//end class
	
	public static class Book{
		private String title;
		private int price;
		
		public Book( String title, int price ){
			this.title = title;
			this.price = price;
		}
		
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public int getPrice() {
			return price;
		}
		public void setPrice(int price) {
			this.price = price;
		}

		public String toString(){
			return "Book:title[" + this.title + "] price[" + this.price + "]";
		}
	}//end class 
	
}
