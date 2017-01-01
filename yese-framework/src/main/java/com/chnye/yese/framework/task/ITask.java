package com.chnye.yese.framework.task;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public interface ITask<T>{
	
	T execute() throws Exception;
	
	Map<String,Object> getMetaData();
	
//	ITaskFuture<T> getTaskFuture();

	//让ITask继承Callable就省掉了这两个接口
//	void execute() throws Exception;
	
	//将Task接口适配成Callable接口
	Callable<T> getCallable();
	
}
