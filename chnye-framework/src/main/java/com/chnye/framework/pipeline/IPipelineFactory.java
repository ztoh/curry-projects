package com.chnye.framework.pipeline;

//http://www.grepcode.com/file/repo1.maven.org/maven2/org.specrunner/specrunner-core/1.1.0/org/specrunner/pipeline/IPipelineFactory.java?av=f

import com.chnye.framework.pipeline.exception.PipelineException;

public interface IPipelineFactory {

	IPipeline newPipeline( Object source ) throws PipelineException;
	
	IPipeline newPipeline( IPipe... pipes ) throws PipelineException;
	
}
