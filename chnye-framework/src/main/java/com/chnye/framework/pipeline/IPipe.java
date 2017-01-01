package com.chnye.framework.pipeline;

//http://www.grepcode.com/file/repo1.maven.org/maven2/org.specrunner/specrunner-core/1.1.0/org/specrunner/pipeline/IPipe.java?av=f


import com.chnye.framework.pipeline.exception.AbortException;
import com.chnye.framework.pipeline.exception.PipelineException;


public interface IPipe {

	
	boolean check( IChannel channel ) throws AbortException;
	
	IChannel process( IChannel channel ) throws AbortException, PipelineException;
	
}
