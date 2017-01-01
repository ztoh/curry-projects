package com.chnye.framework.server;

//http://www.grepcode.com/file/repo1.maven.org/maven2/com.github.celeskyking/cauli-easyserver/1.2.4/org/cauli/mock/server/IServer.java?av=f
//http://www.grepcode.com/file/repo1.maven.org/maven2/com.github.dreamhead/moco-core/0.10.1/com/github/dreamhead/moco/Server.java?av=f

public interface IServer {
	
	void start() throws ServerException;
	
	void stop() throws ServerException;
	
	
	public enum EServerStatus{
		START,
		STOP
	}
	
	EServerStatus getServerStatus();
	
	public enum EServerProtocol{
		HTTP,
		SOCKET,
		WEBSERVICE,
		WEBSOCKET;
	}
	
	public EServerProtocol getServerProtocol();
	
	void addServerListener( IServerListener listener );
	void removeServerListener( IServerListener listener );
	
}
