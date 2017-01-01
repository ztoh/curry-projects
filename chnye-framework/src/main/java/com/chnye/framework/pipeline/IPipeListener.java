package com.chnye.framework.pipeline;

//http://www.grepcode.com/file/repo1.maven.org/maven2/org.specrunner/specrunner-core/1.1.0/org/specrunner/pipeline/IPipeListener.java?av=f

public interface IPipeListener {

	void onBeforeCheck( IChannel channel );
	
	void onAfterCheck( IChannel channel );
	
	void onBeforeProcess( IChannel channel );
	
	void onAfterProcess( IChannel channel );
}
