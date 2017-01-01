package com.chnye.framework.context;

public interface IContextListener {
	void onCreate( IContext context );
	void onDestroy( IContext context );
}
