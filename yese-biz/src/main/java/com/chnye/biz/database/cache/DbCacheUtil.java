package com.chnye.biz.database.cache;

import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

public class DbCacheUtil {

	public static <K,V> LoadingCache<K,V> newDbCacheByBuilder( CacheLoader<K,V> cacheLoader ){
		LoadingCache<K,V> cache = CacheBuilder.newBuilder()
			.maximumSize(10)
			.softValues()
//			.refreshAfterWrite(10, TimeUnit.MINUTES )       //给定时间内内没有读写访问，则回收
			.expireAfterWrite( 10, TimeUnit.MINUTES )		//给定时间内没有写访问，则回收
			.expireAfterAccess(10, TimeUnit.MINUTES )        //缓存过期时间为3分钟
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
