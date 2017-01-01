package com.chnye.common.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.EventObject;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Before;
import org.junit.Test;

import com.chnye.common.eventbus.IEventListener;
import com.chnye.common.eventbus.MapSetsEventManager;
import com.chnye.common.eventbus.MethodEventObjectListener;
import com.chnye.common.eventbus.MethodObjectListener;
import com.google.common.base.MoreObjects;

public class TestMapSetsEventManager {

	private MapSetsEventManager eventManager = null;
	
	
	@Before
	public void init(){
		//同步的
//		eventManager = new ListenerMapManager();
		
		//异步的
		ExecutorService executor = Executors.newFixedThreadPool(3);
		eventManager = new MapSetsEventManager( executor );
		
		IEventListener<User> listenerUser = new IEventListener<User>(){
			@Override
			public void onEvent(User event) {
				// TODO Auto-generated method stub
				System.out.println( Thread.currentThread() + " event: " + event );
			}
		};
		
		IEventListener<Book> listenerBook = new IEventListener<Book>(){
			@Override
			public void onEvent(Book event) {
				// TODO Auto-generated method stub
				System.out.println( Thread.currentThread() + " receive event: " + event );
			}
		};
		
		//注册
		eventManager.addListener( listenerUser );
		
		eventManager.addListener( Book.class, listenerBook );
	}
	
	private void assertEvent(){
		System.out.println( "----------assertEvent----------" );
		//发送消息
		User user = new User( "张三", 45 );
		System.out.println( Thread.currentThread() + " send event: " + user );
		eventManager.notifyListeners( user );
		
		//发送异步消息
		Book book = new Book( "李四", 23 );
		System.out.println( Thread.currentThread() + " send event: " + book );
//		eventManager.notifyListeners( book.getClass(), book );
		eventManager.notifyAsyncListeners( book.getClass(), book );
		
	}

	/**
	 * 测试 MethodObjectListener
	 */
	private void assertEventMethod() throws NoSuchMethodException, SecurityException{
		System.out.println( "----------assertEventMethod----------" );
		//初始化
		UserService userService = new UserService();
		Method loginEventMethod = UserService.class.getMethod( "loginEvent", Object.class );
		MethodObjectListener userLoginListener = new MethodObjectListener(userService, loginEventMethod);
		
		eventManager.addListener( UserLoginEvent.class, userLoginListener );
		
		
		//发送消息
		User user = new User( "未央", 18 );
		UserLoginEvent event = new UserLoginEvent( user );
		System.out.println( Thread.currentThread() + " send event: " + event );
		eventManager.notifyAsyncListeners( event.getClass(), event );
	}
	
	/**
	 * 改进型的，
	 * 
	 */
	private void assertEventMethod2() throws NoSuchMethodException, SecurityException{
		System.out.println( "----------assertEventMethod2----------" );
		//初始化
		UserService userService = new UserService();
		Method loginEventMethod = UserService.class.getMethod( "loginEvent", User.class );
		MethodEventObjectListener userLoginListener = new MethodEventObjectListener(userService, loginEventMethod);
		
		eventManager.addListener( UserLoginEvent.class, userLoginListener );
		
		
		//发送消息
		User user = new User( "未央", 18 );
		UserLoginEvent event = new UserLoginEvent( user );
		System.out.println( Thread.currentThread() + " send event: " + event );
		eventManager.notifyAsyncListeners( event.getClass(), event );
	}
	
	
	
	
	@Test
	public void testEvent(){
		assertEvent();
	}

	@Test
	public void testEventMethod(){
		try {
			assertEventMethod();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testEventMethod2(){
		try {
			assertEventMethod2();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static class User{
		private String name;
		private int age;
		User( String name, int age ){
			this.name = name;
			this.age = age;
		}
		public String toString(){
			return MoreObjects.toStringHelper(this)
					.add("name", name)
					.add("age", age)
					.toString();
		}
	}//end class
	
	public static class Book{
		private String title;
		private int price;
		Book( String title, int price ){
			this.title = title;
			this.price = price;
		}
		public String toString(){
			return MoreObjects.toStringHelper(this)
					.add("title", title)
					.add("price", price)
					.toString();
		}
	}//end class
	
	
	/**
	 * 
	 */
	public static class UserService{
		
		/**
		 * 处理用户登录事件
		 *   仍然不够完美，见下面的方法
		 * @param event
		 */
		public void loginEvent( Object event ){
			if( event instanceof UserLoginEvent ){
				UserLoginEvent userLoginEvent = (UserLoginEvent)event;
				System.out.println( Thread.currentThread() + " receive login event: " + userLoginEvent.getSource() );
			} else {
				System.out.println( "不能处理的事件:" + event );
			}
		}
		
		/**
		 * 实际情况中往往是下面这个方法，而不是上面这个方法
		 * @param user
		 */
		public void loginEvent( User user ){
			System.out.println( Thread.currentThread() + " receive login user: " + user );
		}
		
	}//end class
	
	
	public static class UserLoginEvent extends EventObject{

		public UserLoginEvent(Object source) {
			super(source);
			// TODO Auto-generated constructor stub
		}
		
	}
	
	
	
	
	
	
}
