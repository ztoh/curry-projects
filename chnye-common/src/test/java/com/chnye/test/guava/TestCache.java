package com.chnye.test.guava;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.google.common.base.MoreObjects;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

public class TestCache {

	private static class User{
		String id;
		String name;
		String title;
		public User( String id, String name, String title ){
			this.id = id;
			this.name = name;
			this.title = title;
		}
//		public String toString(){
//			return MoreObjects.toStringHelper( this )
//				.add("id", this.id )
//				.add("name", this.name )
//				.toString();
//		}
	}//end class 
	
	public static class CacheUtil{
		public static <K,V> LoadingCache<K,V> newCacheByBuilder( CacheLoader<K,V> cacheLoader ){
			LoadingCache<K,V> cache = CacheBuilder.newBuilder()
				.maximumSize(10)
				.softValues()
//				.refreshAfterWrite(10, TimeUnit.MINUTES )       //给定时间内内没有读写访问，则回收
				.expireAfterWrite( 10, TimeUnit.MINUTES )		//给定时间内没有写访问，则回收
				.expireAfterAccess(3, TimeUnit.MINUTES )        //缓存过期时间为3分钟
				.removalListener( new RemovalListener<K,V>(){
					@Override
					public void onRemoval(RemovalNotification<K, V> notification) {
						// TODO Auto-generated method stub
						System.out.println( notification.getKey() + " 被移除!" );
					}
				})
				.build( cacheLoader );
			return cache;
		}
	
	}
	
	
	
	private LoadingCache<String, User> curCache;
	
	
	public LoadingCache<String, User> createCache(){
		LoadingCache<String, User> cache = CacheUtil.newCacheByBuilder( new CacheLoader<String, User>(){
			@Override
			public User load(String key) throws Exception {
				// TODO Auto-generated method stub
				return getUserFromDB( key );
			}
			
			private User getUserFromDB( String key ){
				System.out.println( "getUserFromDB: key=" + key + " name=" + "name" + key );
				return new User( key, "name" + key, "title" );
			}
		});
		return cache;
	}
	
	
	private void assertCache() throws ExecutionException{
		curCache = createCache();
		
		for( int i = 0; i <12 ; i++ ){
			System.out.println( "get[" + i + "]" +  curCache.get("zansan" + i ) );
		}
		for( int i = 12; i > 0; i-- ){
			System.out.println( "get[" + i + "]" +  curCache.get("zansan" + i ) );
		}
	}
	
	
	@Test
	public void testCache(){
		try {
			assertCache();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
