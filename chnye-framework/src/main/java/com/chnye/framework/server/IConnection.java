package com.chnye.framework.server;

//http://www.grepcode.com/file/repo1.maven.org/maven2/net.anotheria/ano-net/2.1.0/net/anotheria/net/shared/server/IConnection.java?av=f

public interface IConnection {

	void open();
	
	void close();
	
	void addConnectionListener( IConnectionListener listener );
	
	void removeConnectionListener( IConnectionListener listener );
}
