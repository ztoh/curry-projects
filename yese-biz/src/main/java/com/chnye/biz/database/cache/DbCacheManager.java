package com.chnye.biz.database.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import com.chnye.biz.database.impl.ConnectionFactory;
import com.chnye.biz.entity.DbConnInfo;
import com.chnye.biz.entity.DbConnection;
import com.chnye.common.utils.ListenerMapSetsHelper;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class DbCacheManager {

	private static DbCacheManager instance;
	
	public static DbCacheManager getInstance(){
		if( instance == null ){
			synchronized ( DbCacheManager.class ) {
				if( instance == null ){
					instance = new DbCacheManager();
				}
			}
		}
		return instance;
	}
	
	private DbCacheManager(){
		curCache = createCache();
	}
	
	private LoadingCache<DbConnInfo, DbConnection> curCache;
	
	
	public LoadingCache<DbConnInfo, DbConnection> createCache(){
		LoadingCache<DbConnInfo, DbConnection> cache = DbCacheUtil.newDbCacheByBuilder( new CacheLoader<DbConnInfo, DbConnection>(){
			@Override
			public DbConnection load(DbConnInfo key) throws Exception {
				// TODO Auto-generated method stub
				return doGetFreeConnection( key );
			}
			
		});
		return cache;
	}
	
	public DbConnection doGetFreeConnection( DbConnInfo key ){
		//新建数据库连接
		DbConnection dbConnection = ConnectionFactory.factory( key.getDatabase(), key.getDriver(), key.getUrl(), key.getUsername(), key.getPassword() );
		return dbConnection;
	}
	
	
	
	public DbConnection getFreeConnectionByCache( DbConnInfo key ) throws ExecutionException{
		//先通过缓存快照寻找空闲的数据库链接
		Map<DbConnInfo, DbConnection> dbConnectionMaps = curCache.asMap();
		for( Map.Entry<DbConnInfo, DbConnection> entry : dbConnectionMaps.entrySet() ){
			if( entry.getKey().getDatabase().equals( key.getDatabase() )  && entry.getValue().isFree() ){
				DbConnection dbConnection = curCache.get( entry.getKey() );
				if( dbConnection.setbFree( true, false) ){
					return dbConnection;
				}
			}
		}
		//缓存没有命中
		return curCache.get( key );
	}
	

	


}
