package com.chnye.common.thread.pool;

import java.util.concurrent.ExecutorService;

public interface IExecutorServiceFactory {
	ExecutorService createExecutorService( ThreadPoolDefinition config );
}
