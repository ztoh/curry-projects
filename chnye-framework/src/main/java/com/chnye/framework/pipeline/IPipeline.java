package com.chnye.framework.pipeline;

//http://www.grepcode.com/file/repo1.maven.org/maven2/org.specrunner/specrunner-core/1.1.0/org/specrunner/pipeline/IPipeline.java?av=f

import java.util.List;

public interface IPipeline extends IPipe, List<IPipe>{

	void addPipelineListener( IPipeListener listener );
	
	void removePipelineListener( IPipeListener listener );
	
	
}
