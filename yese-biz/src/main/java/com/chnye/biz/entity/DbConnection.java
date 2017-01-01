package com.chnye.biz.entity;

import java.sql.Connection;
import java.util.concurrent.atomic.AtomicBoolean;

import com.chnye.common.base.BaseObject;

public class DbConnection extends BaseObject{
	private String id;
	
	private AtomicBoolean bFree = new AtomicBoolean( true );
	
	private Connection connection;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isFree() {
		return bFree.get();
	}

//	public void setbFree(AtomicBoolean bFree) {
//		this.bFree = bFree;
//	}
	
	public boolean setbFree( boolean expect, boolean update ){
		return this.bFree.compareAndSet(expect, update);
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	
}
