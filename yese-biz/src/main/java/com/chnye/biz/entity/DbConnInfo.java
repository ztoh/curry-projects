package com.chnye.biz.entity;

public class DbConnInfo {
	private String database;
	private String driver;
	private String url;
	private String username;
	private String password;
	
	public static DbConnInfo create( String database, String driver, String url, String username, String password ){
		return new DbConnInfo(database, driver, url, username, password);
	}
	
	public DbConnInfo() {
	}
	
	public DbConnInfo(String database, String driver, String url, String username, String password ) {
		this.database = database;
		this.driver = driver;
		this.url = url;
		this.username = username;
		this.password = password;
	}
	
	
	public String getDatabase() {
		return database;
	}
	public void setDatabase(String database) {
		this.database = database;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
