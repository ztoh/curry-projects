package com.chnye.framework.server;

//http://www.grepcode.com/file/repo1.maven.org/maven2/net.anotheria/ano-net/2.1.0/net/anotheria/net/shared/server/IConnectionListener.java?av=f

public interface IConnectionListener {

	void connectionOpened( IConnection connection );
	
	void connectionClosed( IConnection connection );
}
