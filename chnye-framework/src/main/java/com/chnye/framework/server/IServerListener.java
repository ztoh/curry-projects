package com.chnye.framework.server;


public interface IServerListener {
	void connectionCreated( IConnection connection );
	void connectionRemoved( IConnection connection );
}
