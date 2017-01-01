package com.chnye.framework.pipeline;

//http://www.grepcode.com/file/repo1.maven.org/maven2/org.specrunner/specrunner-core/1.1.0/org/specrunner/pipeline/IChannelFactory.java?av=f

import java.util.Map;

public interface IChannelFactory {

	IChannel newChannel();
	
	IChannel newChannel( Map<String,Object> load );
}
