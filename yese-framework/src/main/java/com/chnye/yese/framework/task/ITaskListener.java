package com.chnye.yese.framework.task;

public interface ITaskListener {
	void notify( TaskEvent<ETaskStatus> event );
}
