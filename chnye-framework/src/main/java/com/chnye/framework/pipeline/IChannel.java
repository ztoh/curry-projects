package com.chnye.framework.pipeline;

import com.chnye.framework.context.IContext;
import com.chnye.framework.pipeline.exception.PipelineException;

public interface IChannel extends IContext{

	<T> T get(String key, Class<T> type ) throws PipelineException;
}
