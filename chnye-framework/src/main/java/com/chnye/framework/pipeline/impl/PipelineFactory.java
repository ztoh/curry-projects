package com.chnye.framework.pipeline.impl;

import com.chnye.framework.pipeline.IPipe;
import com.chnye.framework.pipeline.IPipeline;
import com.chnye.framework.pipeline.IPipelineFactory;
import com.chnye.framework.pipeline.exception.PipelineException;

public class PipelineFactory implements IPipelineFactory{

	@Override
	public IPipeline newPipeline(Object source) throws PipelineException {
		// TODO Auto-generated method stub
		return new Pipeline();
	}

	@Override
	public IPipeline newPipeline(IPipe... pipes) throws PipelineException {
		// 
		return null;
	}

}
