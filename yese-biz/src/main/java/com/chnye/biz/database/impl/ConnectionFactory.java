package com.chnye.biz.database.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.chnye.biz.entity.DbConnection;

public class ConnectionFactory {
	public static Connection newConnection( String driver, String url, String user, String password ) throws SQLException, ClassNotFoundException{
		Class.forName( driver );
		return DriverManager.getConnection(url, user, password);
	}
	
	
	public static DbConnection factory( String id, String driver, String url, String user, String password ){
		DbConnection newDbConnection = null;
		try{
//			Connection newConnection = newConnection(driver, url, user, password);
//			if( newConnection != null ){
//				newDbConnection = new DbConnection();
//				newDbConnection.setConnection( newConnection );
//				newDbConnection.setbFree(true, true);
//				newDbConnection.setId( id );
//				return newDbConnection;
//			}
			newDbConnection = new DbConnection();
			newDbConnection.setId(id);
			newDbConnection.setbFree(true, true);
			return newDbConnection;
		} catch( Exception e ){
			e.printStackTrace();
		}
		return null;
	}
}
