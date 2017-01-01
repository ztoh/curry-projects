package com.chnye.yese.framework.task;

public interface ITaskRunner {
	void run( ITask task, ITaskListener listener );
	void run( ITask task );
	void shutdown();
}
