package com.chnye.biz.entity;

import java.util.List;

import com.chnye.common.base.BaseObject;

public class DbSqlBean extends BaseObject{

	private String database;
	
	private List<String> checkRepeatSqls;
	
	private List<String> executeSqls;

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public List<String> getCheckRepeatSqls() {
		return checkRepeatSqls;
	}

	public void setCheckRepeatSqls(List<String> checkRepeatSqls) {
		this.checkRepeatSqls = checkRepeatSqls;
	}

	public List<String> getExecuteSqls() {
		return executeSqls;
	}

	public void setExecuteSqls(List<String> executeSqls) {
		this.executeSqls = executeSqls;
	}

	
	
}
