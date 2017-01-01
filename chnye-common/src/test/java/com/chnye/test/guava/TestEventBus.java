package com.chnye.test.guava;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Before;
import org.junit.Test;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

public class TestEventBus {

	private EventBus eventBus = null;
	
	private ExecutorService service = null;
	private EventBus asyncEventBus = null; 
	
	@Before
	public void init(){
		eventBus = new EventBus();
		//注册处理器 
		eventBus.register( new MyProcessor() );
		
		service = Executors.newFixedThreadPool(3);
		asyncEventBus = new AsyncEventBus( "my_async_event_bus", service );
		asyncEventBus.register( new MyProcessor() );
	}
	
	private void assertEventBus(){
		System.out.println( "----------assertEventBus----------");
		//发送消息
		User user = new User( "张三", 34 );
		System.out.println( Thread.currentThread() + " send msg:" + user );
		eventBus.post( user );
	}
	
	
	private void assertAsyncEventBus(){
		System.out.println( "----------assertAsyncEventBus----------");
		//发送消息
		User user = new User( "李四", 29 );
		System.out.println( Thread.currentThread() + " send msg:" + user );
		asyncEventBus.post( user );
	}
	
	
	
	@Test
	public void testEventBus(){
		assertEventBus();
	}
	
	@Test
	public void testAsyncEventBus(){
		assertAsyncEventBus();
	}
	
	
	public static class MyProcessor{
		
		@Subscribe
		public void handleEvent( User user ){
			System.out.println( Thread.currentThread() + " receive msg : " + user );
		}
	}//end class
	
	public static class User{
		private String name;
		private int age;
		
		public User( String name, int age ){
			this.name = name;
			this.age = age;
		}
		
		public String toString(){
			return MoreObjects.toStringHelper(this)
				.add("name", name)
				.add("age", age )
				.toString();
		}
	}//end class
	
}
